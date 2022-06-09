package com.example.servertest.menu;

import android.content.Intent;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.servertest.R;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuList extends AppCompatActivity {

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Gson gson = new Gson();

    private RecyclerView mbRecyclerView, mmRecyclerView;
    private MenuBarAdapter mbAdapter;
    private MainMenuAdapter mmAdapter;
    private SelectMenuItemAdapter smiAdapter;
    private TextView menuBarItem;
    private Toolbar toolbar;
    ArrayList<MainMenuItem> mmList;
    View header;
    TextView menuTitleHeaderTextView;
    String[] menuBarItemArray;
    MenuResponseList menuResponseList;

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

        Long roomId = getIntent().getLongExtra("roomId", 0L);

        toolbar = findViewById(R.id.toolbar_menu_list);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);  //뒤로가기 버튼 생김

        listView = findViewById(R.id.lv_menu_list_menu_list);
        smiAdapter = new SelectMenuItemAdapter();


        String objJson = gson.toJson(roomId);
        Call call = service.getMenus(objJson);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String str = gson.toJson(response.body());
                menuResponseList = gson.fromJson(str, MenuResponseList.class);
                for(MenuResponse m: menuResponseList.getMenuList()){
                    switch (m.getCategory()) {
                        case "치킨":
                            smiAdapter.addItem(ContextCompat.getDrawable(MenuList.this, R.drawable.ic_chicken), m.getInfo(), m.getName(), m.getPrice(), m.getMenuId());
                            break;
                        case "중식":
                            smiAdapter.addItem(ContextCompat.getDrawable(MenuList.this, R.drawable.ic_china2), m.getInfo(), m.getName(), m.getPrice(), m.getMenuId());
                            break;
                        case "분식":
                            smiAdapter.addItem(ContextCompat.getDrawable(MenuList.this, R.drawable.ic_bunsik2), m.getInfo(), m.getName(), m.getPrice(), m.getMenuId());
                            break;
                    }
                }
                smiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });


        listView.setAdapter(smiAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectMenuItem item = (SelectMenuItem) parent.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.putExtra("menuId", item.getMenuId());
                setResult(RESULT_OK, intent);
                finish();

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
