package com.example.capston;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

public class menu_list extends AppCompatActivity {

    private RecyclerView recyclerView, recyclerView2;
    private menu_list_adapter adapter;
    private menu_main_viewadapter adapter2;
    private TextView textView;
    ArrayList<menu_main_item> mList;

    //메뉴 리스트
    ListView listView;

    //메인메뉴 리사이클러뷰 아이템
    private Drawable mImageDrawable;
    private String menu_name;
    private int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TabLayout tabs = findViewById(R.id.tabs);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

//        tabs.addTab(tabs.newTab().setText("대표메뉴"));
//        tabs.addTab(tabs.newTab().setText("신메뉴"));
//        tabs.addTab(tabs.newTab().setText("순살시리즈"));
//        tabs.addTab(tabs.newTab().setText("교촌시리즈"));
//        tabs.addTab(tabs.newTab().setText("레드시리즈"));
//        tabs.addTab(tabs.newTab().setText("허니시리즈"));
//        tabs.addTab(tabs.newTab().setText("믹스시리즈"));
//
//
//        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                int position = tab.getPosition();
//                Log.d("MainActivity", "선택된 탭 : " + position);
//
//                Fragment selected = null;
//                if (position == 0) {
//                    //selected = fragment1;
//                } else if (position == 1) {
//                    //selected = fragment2;
//                } else if (position == 2) {
//                    //selected = fragment3;
//                }
////                getSupportFragmentManager().beginTransaction()
////                        .replace(R.id.container, selected).commit();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//


        scrollMenu();
        mainMenuRecycler();
        selectMenuListView();

    }

    //메뉴들 탭 리사이클러뷰
    private void scrollMenu() {

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
    //위와 연관
    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();
            Toast.makeText(menu_list.this, str, Toast.LENGTH_SHORT).show();
            textView = findViewById(R.id.textView);

        }
    };


    //대표메뉴 리사이클러뷰
    public void mainMenuRecycler(){
        recyclerView2 = findViewById(R.id.recycler2);
        mList = new ArrayList<>();

        adapter2 = new menu_main_viewadapter(mList);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        mImageDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.appimage, null);
        menu_name = "Crocus";
        price = 1000;
        addItem(mImageDrawable, menu_name, price);
        addItem(mImageDrawable, menu_name, price);
        addItem(mImageDrawable, menu_name, price);
        addItem(mImageDrawable, menu_name, price);
        addItem(mImageDrawable, menu_name, price);

        menu_main_deco decoration = new menu_main_deco();
        recyclerView2.addItemDecoration(decoration);

        adapter2.notifyDataSetChanged();
    }

    private void addItem(Drawable icon, String mainText, int subText) {
        menu_main_item item = new menu_main_item();

        item.setImage(icon);
        item.setMenu_name(mainText);
        item.setPrice(subText);

        mList.add(item);
    }

    //선택할 메뉴 리스트 뷰
    private void selectMenuListView(){
        listView = findViewById(R.id.lv_menu_list_list_view);
        SelectMenuItemAdapter adapter = new SelectMenuItemAdapter();
        listView.setAdapter(adapter);

        // 아이템 추가
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘1번", "24dp Black", 1000);
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘2번", "24dp Black", 1000);
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘3번", "24dp Black", 10000);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SelectMenuItem item = (SelectMenuItem) parent.getItemAtPosition(position);

                String menuName = item.getMenuName();
                Toast.makeText(getApplicationContext(),menuName,Toast.LENGTH_SHORT).show();
            }
        });
        setListViewHeightBasedOnItems(listView);
    }

    //스크롤 뷰안에 있는 리스트뷰 높이 계산하기 위함
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            //setDynamicHeight(listView);
            return true;

        } else {
            return false;
        }
    }

}
