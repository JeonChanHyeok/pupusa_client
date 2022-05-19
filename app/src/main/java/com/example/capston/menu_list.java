package com.example.capston;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

public class menu_list extends AppCompatActivity {

    private RecyclerView recyclerView;
    private menu_list_adapter adapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TabLayout tabs = findViewById(R.id.tabs);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        tabs.addTab(tabs.newTab().setText("대표메뉴"));
        tabs.addTab(tabs.newTab().setText("신메뉴"));
        tabs.addTab(tabs.newTab().setText("순살시리즈"));
        tabs.addTab(tabs.newTab().setText("교촌시리즈"));
        tabs.addTab(tabs.newTab().setText("레드시리즈"));
        tabs.addTab(tabs.newTab().setText("허니시리즈"));
        tabs.addTab(tabs.newTab().setText("믹스시리즈"));


        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : " + position);

                Fragment selected = null;
                if (position == 0) {
                    //selected = fragment1;
                } else if (position == 1) {
                    //selected = fragment2;
                } else if (position == 2) {
                    //selected = fragment3;
                }
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        init();


    }

    private void init() {

        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("대표메뉴");
        itemList.add("신메뉴");
        itemList.add("순살시리즈");
        itemList.add("교촌시리즈");
        itemList.add("레드시리즈");
        itemList.add("허니시리즈");
        itemList.add("믹스시리즈");
        itemList.add("후라이드시리즈");
        itemList.add("신화시리즈");
        itemList.add("반반메뉴");
        itemList.add("사이드메뉴");

        adapter = new menu_list_adapter(this, itemList, onClickItem);
        recyclerView.setAdapter(adapter);

        menu_list_deco decoration = new menu_list_deco();
        recyclerView.addItemDecoration(decoration);
    }


    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();
            Toast.makeText(menu_list.this, str, Toast.LENGTH_SHORT).show();
            textView = findViewById(R.id.textView);

        }
    };
}
