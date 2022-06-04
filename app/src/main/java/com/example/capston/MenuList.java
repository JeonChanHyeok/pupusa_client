package com.example.capston;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    private Toolbar toolbar;
    ArrayList<MainMenuItem> mmList;
    ImageButton menuListWishBtn;
    ImageView menuListWishImageView;
    boolean i = true;
    ArrayList<String> itemList;
    ArrayList<MenuBarItem> menuBarItemsList;
    View header;
    TextView menuTitleHeaderTextView;
    String[] menuBarItemArray;


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

        toolbar = findViewById(R.id.toolbar_menu_list);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);  //뒤로가기 버튼 생김

        menuBarRecycler();
        mainMenuRecycler();
        selectMenuListView();
        changeWishList();

    }

    //가게 찜 등록 버튼 클릭
    public void changeWishList() {
        menuListWishBtn = (ImageButton) findViewById(R.id.btn_menu_list_wish);

        menuListWishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == true) {
                    menuListWishBtn.setBackgroundResource(R.drawable.wish_list);
                    i = false;
                } else {
                    menuListWishBtn.setBackgroundResource(R.drawable.empty_wish_list);
                    i = true;
                }
            }
        });
    }

    //툴바 뒤로가기 키
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {  //toolbar의 back키 눌렀을 때 동작
                finish();
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                startActivity(intent);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    //메뉴들 탭 리사이클러뷰
    public void menuBarRecycler() {

        mbRecyclerView = findViewById(R.id.recycler_menu_list_menu_bar);
        menuBarItemsList = new ArrayList<>();

        mbAdapter = new MenuBarAdapter(menuBarItemsList);
        mbRecyclerView.setAdapter(mbAdapter);
        mbRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        menuBarItemArray = new String[]{"신메뉴", "순살시리즈", "교촌시리즈", "레드시리즈", "허니시리즈"};

        itemList = new ArrayList<>();

        for (String s : menuBarItemArray) {
            menuBarAddItem(s);
        }
//        menuBarAddItem("신메뉴");
//        menuBarAddItem("순살시리즈");
//        menuBarAddItem("교촌시리즈");
//        menuBarAddItem("레드시리즈");
//        menuBarAddItem("허니시리즈");
//        menuBarAddItem("믹스시리즈");
//        menuBarAddItem("후라이드시리즈");
//        menuBarAddItem("신화시리즈");
//        menuBarAddItem("반반메뉴");
//        menuBarAddItem("사이드메뉴");

        mbAdapter.setOnItemClickListener(new MenuBarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                showOtherList(position);
                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        MenuBarDeco decoration = new MenuBarDeco();
        mbRecyclerView.addItemDecoration(decoration);


        mbAdapter.notifyDataSetChanged();
    }

    //메뉴 바 아이템 추가
    private void menuBarAddItem(String text) {
        MenuBarItem item = new MenuBarItem();
        item.setMenuBarItem(text);
        menuBarItemsList.add(item);
    }

    //메뉴 바 클릭 시 해당 position에 선택된 값에 따른 리스트뷰 변경
    public void showOtherList(int position) {
        listView = findViewById(R.id.lv_menu_list_menu_list);
        smiAdapter = new SelectMenuItemAdapter();

        menuTitleHeaderTextView = findViewById(R.id.tv_menu_title_header);

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switch (position) {
                            case 0:
                                Log.i("aaa", "0");
                                menuTitleHeaderTextView.setText(menuBarItemArray[0]);
                                smiAdapter.addItem(ContextCompat.getDrawable(MenuList.this, R.drawable.appimage), "아이콘1번", "24dp Black", 1000);
                                smiAdapter.addItem(ContextCompat.getDrawable(MenuList.this, R.drawable.appimage), "아이콘2번", "24dp Black", 1000);
                                smiAdapter.addItem(ContextCompat.getDrawable(MenuList.this, R.drawable.appimage), "아이콘3번", "24dp Black", 10000);
                                break;
                            case 1:
                                Log.i("aaa", "1");
                                menuTitleHeaderTextView.setText(menuBarItemArray[1]);
                                smiAdapter.addItem(ContextCompat.getDrawable(MenuList.this, R.drawable.appimage), "아이콘4번", "24dp Black", 1000);
                                smiAdapter.addItem(ContextCompat.getDrawable(MenuList.this, R.drawable.appimage), "아이콘5번", "24dp Black", 1000);
                                smiAdapter.addItem(ContextCompat.getDrawable(MenuList.this, R.drawable.appimage), "아이콘6번", "24dp Black", 10000);
                                break;
                            case 2:
                                Log.i("aaa", "2");
                                menuTitleHeaderTextView.setText(menuBarItemArray[2]);
                                smiAdapter.addItem(ContextCompat.getDrawable(MenuList.this, R.drawable.appimage), "아이콘7번", "24dp Black", 1000);
                                smiAdapter.addItem(ContextCompat.getDrawable(MenuList.this, R.drawable.appimage), "아이콘8번", "24dp Black", 1000);
                                smiAdapter.addItem(ContextCompat.getDrawable(MenuList.this, R.drawable.appimage), "아이콘9번", "24dp Black", 10000);
                                break;
                        }
                    }
                });
            }
        }).start();
        listView.setAdapter(smiAdapter);
//        switch (position){
//            case 0:
//                menuTitleHeaderTextView.setText("신메뉴");
//                smiAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘1번", "24dp Black", 1000);
//                smiAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘2번", "24dp Black", 1000);
//                smiAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘3번", "24dp Black", 10000);
//            case 1:
//                menuTitleHeaderTextView.setText("순살시리즈");
//                smiAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘4번", "24dp Black", 1000);
//                smiAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘5번", "24dp Black", 1000);
//                smiAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘6번", "24dp Black", 10000);
//            case 2:
//                menuTitleHeaderTextView.setText("교촌시리즈");
//                smiAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘7번", "24dp Black", 1000);
//                smiAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘8번", "24dp Black", 1000);
//                smiAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.appimage),"아이콘9번", "24dp Black", 10000);
//        }

        smiAdapter.notifyDataSetChanged();
    }


    //대표메뉴 리사이클러뷰
    public void mainMenuRecycler() {
        mmRecyclerView = findViewById(R.id.recycler_menu_list_main_menu_bar);
        mmList = new ArrayList<>();

        mmAdapter = new MainMenuAdapter(mmList);
        mmRecyclerView.setAdapter(mmAdapter);
        mmRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        mmAdapter.setOnItemClickListener(new MainMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(MenuList.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        mImageDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.appimage, null);
        menuName = "Crocus";
        price = 1000;
        mainMenuAddItem(mImageDrawable, menuName, price);
        mainMenuAddItem(mImageDrawable, "asd", price);
        mainMenuAddItem(mImageDrawable, "zxc", price);
        mainMenuAddItem(mImageDrawable, "Qwe", price);
        mainMenuAddItem(mImageDrawable, "fdg", price);

        MainMenuDeco decoration = new MainMenuDeco();
        mmRecyclerView.addItemDecoration(decoration);

        mmAdapter.notifyDataSetChanged();


    }

    //메인메뉴에 아이템 추가
    private void mainMenuAddItem(Drawable icon, String mainText, int subText) {
        MainMenuItem item = new MainMenuItem();

        item.setImage(icon);
        item.setMenuName(mainText);
        item.setPrice(subText);

        mmList.add(item);
    }

    //선택할 메뉴 리스트 뷰
    private void selectMenuListView() {
        listView = findViewById(R.id.lv_menu_list_menu_list);
        smiAdapter = new SelectMenuItemAdapter();
        header = getLayoutInflater().inflate(R.layout.menu_title_header, null, false);
        listView.addHeaderView(header);
        listView.setAdapter(smiAdapter);

        // 아이템 추가
        smiAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.appimage), "아이콘1번", "24dp Black", 1000);
        smiAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.appimage), "아이콘2번", "24dp Black", 1000);
        smiAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.appimage), "아이콘3번", "24dp Black", 10000);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SelectMenuItem item = (SelectMenuItem) parent.getItemAtPosition(position);

                String menuName = item.getMenuName();
                Toast.makeText(getApplicationContext(), menuName, Toast.LENGTH_SHORT).show();
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
