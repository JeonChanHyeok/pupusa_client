package com.example.servertest.stores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servertest.R;
import com.example.servertest.alret.AlertSampleData;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoresActivity extends AppCompatActivity {

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Gson gson = new Gson();

    private RecyclerView mRecyclerView;
    private ArrayList<StoresRecyclerViewItem> mList;
    private StoresRecyclerViewAdapter mRecyclerViewAdapter;

    StoreResponseList storeResponseList;
    Long storeId;
    Intent intent;
    ListView list;
    StoresListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        intent = getIntent();

        String loginedId = intent.getStringExtra("loginedId");
        String category = intent.getStringExtra("category");
        int isLogin = intent.getIntExtra("isLogin", 0);
        storeId = 0L;


        firstInit();
        TextView tvTitle = (TextView) findViewById(R.id.tv_store_select);
        addItem("전체");
        addItem("치킨");
        addItem("중식");
        addItem("분식");
        String objJson = category;
        getStoreName(objJson);





        mRecyclerViewAdapter = new StoresRecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mRecyclerViewAdapter.setOnItemClickListener(new StoresRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String objJson = mRecyclerViewAdapter.getmList().get(position).getSubText();
                getStoreName(objJson);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent chatIntent = new Intent(getApplicationContext(), GongGuPopup.class);
                chatIntent.putExtra("loginedId", loginedId);
                chatIntent.putExtra("isLogin", isLogin);
                chatIntent.putExtra("storeId", adapter.itemList.get(position).getId());
                chatIntent.putExtra("storeName", adapter.itemList.get(position).getTitle());
                startActivity(chatIntent);
            }
        });
    }

    public void firstInit() {
        mRecyclerView = (RecyclerView) findViewById(R.id.store_select_recyclerView);
        mList = new ArrayList<>();
    }


    public void addItem(String subText) {
        StoresRecyclerViewItem item = new StoresRecyclerViewItem();
        item.setSubText(subText);
        mList.add(item);
        list = (ListView) findViewById(R.id.lv_store_select_list2);
        adapter = new StoresListViewAdapter();
        list.setAdapter(adapter);
    }


    public void getStoreName(String objJson) {
        Call store = service.getStoreId(objJson);
        store.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String str = new Gson().toJson(response.body());
                storeResponseList = new Gson().fromJson(str, StoreResponseList.class);
                System.out.println("storeRL: " + str);
                adapter.itemList.clear();
                for (StoreResponse s : storeResponseList.getStoreResponseList()) {
                    switch (s.getCategory()){
                        case "치킨":
                            adapter.additem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_chicken), s.getStoreName(), "4.5", s.getDelieveryFee() + "원", "15~30분" , s.getStoreId());
                            break;
                        case "분식":
                            adapter.additem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_bunsik2), s.getStoreName(), "4.5", s.getDelieveryFee() + "원", "15~30분", s.getStoreId());
                            break;
                        case "중식":
                            adapter.additem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_china2), s.getStoreName(), "4.5", s.getDelieveryFee() + "원", "15~30분" , s.getStoreId());
                            break;
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}