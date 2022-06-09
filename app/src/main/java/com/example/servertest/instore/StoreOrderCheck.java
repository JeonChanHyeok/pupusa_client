package com.example.servertest.instore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompHeader;
import retrofit2.Call;

public class StoreOrderCheck extends AppCompatActivity {
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Gson gson = new Gson();
    ListView listView;
    Button button;
    Long storeId;
    private StompClient mStompClient;
    private List<StompHeader> headerList;
    private String wsServerUrl = "ws://ec2-34-227-207-122.compute-1.amazonaws.com:8080/inchatroom/websocket";
    private static final String TAG = "ChatRoomActivity";
    StoreOrderCheckAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order_check);
        storeId = getIntent().getLongExtra("storeId", 0L);
        listView = findViewById(R.id.ll_order_check);
        button = findViewById(R.id.btn_order_check_unconfirm);
        adapter = new StoreOrderCheckAdapter();
        listView.setAdapter(adapter);

        initStomp();
        orderList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                StoreOrderCheckItem item = (StoreOrderCheckItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), StoreOrderHistoryMain.class);
                intent.putExtra("payOrderId", item.getPayOrderId());
                startActivityForResult(intent, 0);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String text = data.getStringExtra("text");
        orderList();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void orderList(){
        String objJson = gson.toJson(storeId);
        Call call = service.orderLoadInStore(objJson);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String str = gson.toJson(response.body());
                InStoreOrderResponseList inStoreOrderResponseList = new InStoreOrderResponseList();
                inStoreOrderResponseList = gson.fromJson(str, InStoreOrderResponseList.class);
                List<InStoreOrderResponse> inStoreOrderResponses = inStoreOrderResponseList.getInStoreOrderResponseList();
                adapter.orderCheckItemList.clear();
                for(InStoreOrderResponse i:inStoreOrderResponses){
                    adapter.addItem(i.getPayOrderId(), i.getAddress(), i.getMenusName(), i.getDate(), i.getPrice(), i.getState());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });


    }


    // 채팅방 입장을 위한 소켓 통신/ 소켓 열기
    @SuppressLint("CheckResult")
    public void initStomp(){
        mStompClient= Stomp.over(Stomp.ConnectionProvider.OKHTTP, wsServerUrl);

        mStompClient.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {
                case OPENED:
                    Log.d(TAG, "Stomp connection opened");
                    break;
                case ERROR:
                    Log.e(TAG, "Error", lifecycleEvent.getException());
                    if(lifecycleEvent.getException().getMessage().contains("EOF")){

                    }
                    break;
                case CLOSED:
                    Log.d(TAG, "Stomp connection closed");
                    break;
            }
        });

        mStompClient.topic("/topic/chat/order/" + storeId)
                .subscribe(topicMessage -> {
                    System.out.println(topicMessage.getPayload());
                    this.orderList();
                });
        // add Header
        headerList=new ArrayList<>();
        mStompClient.connect(headerList);
    }
}
