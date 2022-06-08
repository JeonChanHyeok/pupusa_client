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
    private String wsServerUrl = "ws://175.200.243.163:8080/inchatroom/websocket";
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

            }
        });
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
                    adapter.addItem(i.getAddress(), i.getMenusName(), i.getDate(), i.getPrice());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });


/*
        adapter.addItem("부산 가야로 11길 22로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.05", 10000);
        adapter.addItem("부산 가야로 22길 22로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.04", 20000);
        adapter.addItem("부산 가야로 33길 33로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.03", 20000);
        adapter.addItem("부산 가야로 44길 44로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.02", 20000);
        adapter.addItem("부산 가야로 55길 55로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.01", 20000);
        adapter.addItem("부산 가야로 11길 22로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.05", 230000);
        adapter.addItem("부산 가야로 22길 22로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.04", 20000);
        adapter.addItem("부산 가야로 33길 33로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.03", 20000);
        adapter.addItem("부산 가야로 44길 44로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.02", 10000);
        adapter.addItem("부산 가야로 55길 55로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.01", 20000);
        adapter.addItem("부산 가야로 11길 22로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.05", 20000);
        adapter.addItem("부산 가야로 22길 22로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.04", 20000);
        adapter.addItem("부산 가야로 33길 33로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.03", 40000);
        adapter.addItem("부산 가야로 44길 44로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.02", 20000);
        adapter.addItem("부산 가야로 55길 55로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.01", 20000);
*/



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
