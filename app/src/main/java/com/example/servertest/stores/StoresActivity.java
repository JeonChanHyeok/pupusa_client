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
import com.example.servertest.customlistview.ListViewAdapter;
import com.example.servertest.customlistview.RecyclerViewAdapter;
import com.example.servertest.customlistview.RecyclerViewItem;
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

public class StoresActivity extends AppCompatActivity {

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

        // 가게 선택 시 가게에 대한 정보 Toast로 출력
        ListView store_listview = findViewById(R.id.lv_store_select_list_2);
        ListViewAdapter store_listViewAdapter = new ListViewAdapter();
        store_listview.setAdapter(store_listViewAdapter);
        store_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                store_listViewAdapter.getItem(position);

                Toast.makeText(StoresActivity.this, position, Toast.LENGTH_SHORT).show();

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

        list = (ListView)findViewById(R.id.lv_store_select_list_2);
        adapter = new ListViewAdapter();

        list.setAdapter(adapter);

    }

    public void temp(int i){

        StoreActivity storeActivity = new StoreActivity();

        ListView list;

        list = (ListView)findViewById(R.id.lv_store_select_list_2);
        adapter = new ListViewAdapter();
        
        list.setAdapter(adapter);
        String objJson = mRecyclerViewAdapter.getmList().get(i).getSubText();
        getStoreName(objJson);
        adapter.notifyDataSetChanged();

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