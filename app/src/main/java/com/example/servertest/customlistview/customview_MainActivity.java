package com.example.servertest.customlistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servertest.R;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.example.servertest.store.StoreActivity;
import com.example.servertest.store.StoreResponse;
import com.example.servertest.store.StoreResponseList;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class customview_MainActivity extends AppCompatActivity {

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    private ArrayList<StoreResponseList> storeResponseLists;


    private RecyclerView mRecyclerView;
    private ArrayList<RecyclerViewItem> mList;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private ListView mListView;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_select_activity_main);
        firstInit();
        TextView tvTitle = (TextView) findViewById(R.id.tv_store_select);
        addItem("전체");
        addItem("치킨");
        addItem("피자");
        addItem("중식");

        mRecyclerViewAdapter = new RecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
              //Toast.makeText(customview_MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
              temp(position);
              adapter.notifyDataSetChanged();
            }
        });

    }

    public void firstInit() {
        mRecyclerView = (RecyclerView) findViewById(R.id.store_select_recyclerView);
        mList = new ArrayList<>();
    }


    public void addItem(String subText){
        RecyclerViewItem item = new RecyclerViewItem();

        item.setSubText(subText);

        mList.add(item);

//.....................리스트뷰

        ListView list;
        ListViewAdapter adapter;

        list = (ListView)findViewById(R.id.lv_store_select_list2);
        adapter = new ListViewAdapter();

        list.setAdapter(adapter);

//        adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_kyochon), "교촌치킨", "4.5","3000원","15~30분");
//        adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_gcova), "지코바치킨", "4.9","3000원","15~30분");
//        adapter.additem(ContextCompat.getDrawable(this, R.drawable.puradak), "푸라닭치킨", "4.6","3000원","15~30분");
//        adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_60), "60계치킨", "4.8","3000원","15~30분");
//        adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_bhc), "BHC치킨", "4.9","3000원","15~30분");
//        adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_cheogatjib), "처갓집치킨", "4.6","3000원","15~30분");
//        adapter.additem(ContextCompat.getDrawable(this, R.drawable.bbq), "BBQ", "4.5","3000원","15~30분");
//        adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_nana), "네네치킨", "4.5","3000원","15~30분");


    }
    public void temp(int i){

        StoreActivity storeActivity = new StoreActivity();

        ListView list;

        list = (ListView)findViewById(R.id.lv_store_select_list2);
        adapter = new ListViewAdapter();

        list.setAdapter(adapter);
        String objJson = mRecyclerViewAdapter.getmList().get(i).getSubText();
        getStoreName(objJson);
        adapter.notifyDataSetChanged();

/*
        switch (i){
            case 0:
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.puradak), "푸라닭치킨", "4.6","3000원","15~30분");

                adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_60), "60계치킨", "4.8","3000원","15~30분");
                break;
            case 1:
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_kyochon), "교촌치킨", "4.5","3000원","15~30분");
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_gcova), "지코바치킨", "4.9","3000원","15~30분");
                break;
            case 2:
                getStoreName("치킨");
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.puradak), "푸라닭치킨", "4.6","3000원","15~30분");
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_60), "60계치킨", "4.8","3000원","15~30분");
                break;
            case 3:
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_kyochon), "교촌치킨", "4.5","3000원","15~30분");
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_gcova), "지코바치킨", "4.9","3000원","15~30분");
                break;
            case 4:
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.puradak), "푸라닭치킨", "4.6","3000원","15~30분");
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_60), "60계치킨", "4.8","3000원","15~30분");
                break;
            case 5:
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_kyochon), "교촌치킨", "4.5","3000원","15~30분");
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_gcova), "지코바치킨", "4.9","3000원","15~30분");
                break;
        }*/

        adapter.notifyDataSetChanged();

    }
    public void getStoreName(String objJson) {
        Call store = service.getStoreId(objJson);
        store.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String str = new Gson().toJson(response.body());
                StoreResponseList storeResponseList = new Gson().fromJson(str, StoreResponseList.class);
                System.out.println("storeRL: " + str);
                for(StoreResponse s : storeResponseList.getStoreResponseList()){
                    adapter.additem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_restaurant), s.getStoreName(), "4.5",s.getDelieveryFee() +"원","15~30분");
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });


    }
}