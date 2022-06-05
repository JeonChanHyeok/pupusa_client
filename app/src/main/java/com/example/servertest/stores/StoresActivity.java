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

import java.util.ArrayList;

public class StoresActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<StoresRecyclerViewItem> mList;
    private StoresRecyclerViewAdapter mRecyclerViewAdapter;

    Long storeId;
    Intent intent;
    ListView list;
    StoresListViewAdapter adapter;
    Intent chatIntent;

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
        addItem("1인분주문");
        addItem("신규 맛집");
        addItem("치킨");
        addItem("피자");
        addItem("족발");




        mRecyclerViewAdapter = new StoresRecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mRecyclerViewAdapter.setOnItemClickListener(new StoresRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(StoresActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                //temp(position);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Store store;
                switch (position){
                    case 0:
                        chatIntent = new Intent(getApplicationContext(), GongGuPopup.class);
                        chatIntent.putExtra("loginedId", loginedId);
                        chatIntent.putExtra("isLogin", isLogin);
                        chatIntent.putExtra("storeId", 0L);
                        startActivity(chatIntent);
                        break;
                    case 1:
                        chatIntent = new Intent();
                        chatIntent.putExtra("loginedId", loginedId);
                        chatIntent.putExtra("isLogin", isLogin);
                        chatIntent.putExtra("storeId", 1L);
                        startActivity(chatIntent);
                        break;
                }
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

//.....................리스트뷰



        list = (ListView) findViewById(R.id.lv_store_select_list2);
        adapter = new StoresListViewAdapter();

        list.setAdapter(adapter);

        adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_kyochon), "교촌치킨", "4.5", "3000원", "15~30분");
        adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_gcova), "지코바치킨", "4.9", "3000원", "15~30분");
        /*adapter.additem(ContextCompat.getDrawable(this, R.drawable.puradak), "푸라닭치킨", "4.6", "3000원", "15~30분");
        adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_60), "60계치킨", "4.8", "3000원", "15~30분");
        adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_bhc), "BHC치킨", "4.9", "3000원", "15~30분");
        adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_cheogatjib), "처갓집치킨", "4.6", "3000원", "15~30분");
        adapter.additem(ContextCompat.getDrawable(this, R.drawable.bbq), "BBQ", "4.5", "3000원", "15~30분");
        adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_nana), "네네치킨", "4.5", "3000원", "15~30분");
        */
    }
    public void temp(int i){
        ListView list;
        StoresListViewAdapter adapter;

        list = (ListView)findViewById(R.id.lv_store_select_list2);
        adapter = new StoresListViewAdapter();

        list.setAdapter(adapter);

        switch (i){
            case 0:
                break;
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_kyochon), "교촌치킨", "4.5","3000원","15~30분");
                adapter.additem(ContextCompat.getDrawable(this, R.drawable.ic_gcova), "지코바치킨", "4.9","3000원","15~30분");
                break;
        }

        adapter.notifyDataSetChanged();
    }

}