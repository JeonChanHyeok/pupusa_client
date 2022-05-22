package com.example.capston;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class MenuList extends AppCompatActivity {

    private RecyclerView mbRecyclerView, mmRecyclerView;
    private MenuBarAdapter mbAdapter;
    private MainMenuAdapter mmAdapter;
    private SelectMenuItemAdapter smiAdapter;
    private TextView menuBarItem;
    ArrayList<MainMenuItem> mmList;

    //메뉴 리스트
    ListView listView;

    //메인메뉴 리사이클러뷰 아이템
    private Drawable mImageDrawable;
    private String menuName;
    private int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        menuBarRecycler();
        mainMenuRecycler();
        selectMenuListView();

    }

    //메뉴들 탭 리사이클러뷰
    private void menuBarRecycler() {

        mbRecyclerView = findViewById(R.id.recycler_menu_list_menu_bar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mbRecyclerView.setLayoutManager(layoutManager);

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

        mbAdapter = new MenuBarAdapter(this, itemList, onClickItem);
        mbRecyclerView.setAdapter(mbAdapter);

        MenuBarDeco decoration = new MenuBarDeco();
        mbRecyclerView.addItemDecoration(decoration);
    }
    //위와 연관
    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();
            Toast.makeText(MenuList.this, str, Toast.LENGTH_SHORT).show();
            menuBarItem = findViewById(R.id.tv_menu_bar_item);

        }
    };


    //대표메뉴 리사이클러뷰
    public void mainMenuRecycler(){
        mmRecyclerView = findViewById(R.id.recycler_menu_list_main_menu_bar);
        mmList = new ArrayList<>();

        mmAdapter = new MainMenuAdapter(mmList);
        mmRecyclerView.setAdapter(mmAdapter);
        mmRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        mImageDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.appimage, null);
        menuName = "Crocus";
        price = 1000;
        addItem(mImageDrawable, menuName, price);
        addItem(mImageDrawable, menuName, price);
        addItem(mImageDrawable, menuName, price);
        addItem(mImageDrawable, menuName, price);
        addItem(mImageDrawable, menuName, price);

        MainMenuDeco decoration = new MainMenuDeco();
        mmRecyclerView.addItemDecoration(decoration);

        mmAdapter.notifyDataSetChanged();
    }

    private void addItem(Drawable icon, String mainText, int subText) {
        MainMenuItem item = new MainMenuItem();

        item.setImage(icon);
        item.setMenuName(mainText);
        item.setPrice(subText);

        mmList.add(item);
    }

    //선택할 메뉴 리스트 뷰
    private void selectMenuListView(){
        listView = findViewById(R.id.lv_menu_list_menu_list);
        smiAdapter = new SelectMenuItemAdapter();
        listView.setAdapter(smiAdapter);

        // 아이템 추가
        smiAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘1번", "24dp Black", 1000);
        smiAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘2번", "24dp Black", 1000);
        smiAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘3번", "24dp Black", 10000);

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
