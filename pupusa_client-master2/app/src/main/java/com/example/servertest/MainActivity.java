package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servertest.announcement.AnnouncementActivity;
import com.example.servertest.chat.ChatRoom;
import com.example.servertest.chat.ChatRoomController;
import com.example.servertest.chat.ChatRoomList;
import com.example.servertest.chat.JoinRoomData;
import com.example.servertest.login.LoginActivity;
import com.example.servertest.mypage.MyPageActivity;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.example.servertest.servicecenter.service_center;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Gson gson = new Gson();
    RecyclerView recyclerView2;
    RecyclerAdapter2 recyclerAdapter2;

    private static final String TAG = "Main_Activity";

    private ImageView ivMenu;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private SearchView mSearchView;
    private Intent main_intent;
    private int isLogin = 0;
    private String loginedId;
    private ChatRoomList chatRoomList;
    private ListView listView;
    private ChatRoomListAdapter myAdapter;

    ArrayList<ChatRoomListData> chattingRoomDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchView = findViewById(R.id.search_view); // SearchView

        main_intent = getIntent();
        isLogin = main_intent.getIntExtra("islogin",0);
        loginedId = main_intent.getStringExtra("loginedId");
        chatRoomList = new ChatRoomList();
        listView = (ListView)findViewById(R.id.listView);

        this.InitializeDrawerLayout();
        this.InitializeSearchView();
        this.InitializeChattingRoomData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                if(isLogin == 1) {
                    Toast.makeText(getApplicationContext(),
                            myAdapter.getItem(position).getMyContext() + "번 방에 입장합니다.",
                            Toast.LENGTH_LONG).show();
                    JoinRoomData joinRoomData = new JoinRoomData(myAdapter.getItem(position).getMyContext().substring(6), loginedId);
                    String objJson = gson.toJson(joinRoomData);
                    Call joinRoom = service.goChatRoom(objJson);
                    joinRoom.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Intent go_in_chat = new Intent(getApplicationContext(), ChatRoomController.class);
                            go_in_chat.putExtra("userId", loginedId);
                            go_in_chat.putExtra("roomId", myAdapter.getItem(position).getMyContext().substring(6));
                            startActivity(go_in_chat);
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "로그인이 안 되어 있습니다.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        bindList();

        recyclerView2 = findViewById(R.id.rv_Whole_chattingRoom);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)) ; // 좌우 스크롤
        recyclerAdapter2 = new RecyclerAdapter2();


        for (int i = 0; i < 6; i++) {
            String str1 = "";
            switch (i) {
                case 0:
                    str1 = "치킨";
                    break;
                case 1:
                    str1 = "피자";
                    break;
                case 2:
                    str1 = "분식";
                    break;
                case 3:
                    str1 = "야식";
                    break;
                case 4:
                    str1 = "일식";
                    break;
                case 5:
                    str1 = "패스트푸드";
                    break;
            }
            recyclerAdapter2.setArrayData(str1);
        }
        recyclerView2.setAdapter(recyclerAdapter2);
    }


    public void InitializeChattingRoomData()
    {
        chattingRoomDataList = new ArrayList<ChatRoomListData>();
        Call chat = service.loadRoomList();
        chat.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String chatlist = new Gson().toJson(response.body());
                chatRoomList = new Gson().fromJson(chatlist,ChatRoomList.class);
                for(ChatRoom c:chatRoomList.getchatroomlist()){
                    chattingRoomDataList.add(new ChatRoomListData(R.drawable.ic_kyochon2,"방제목 : " + c.getChatRoomName(),"부산시 연제구 연산4동","방번호 : " + c.getChatRoomId()));
                }
                myAdapter = new ChatRoomListAdapter(getApplicationContext(), chattingRoomDataList);
                listView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    //네비게이션 바 뒤로가기
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint({"NonConstantResourceId", "RtlHardcoded"})
    public void InitializeDrawerLayout() {
        ivMenu = findViewById(R.id.iv_menu);
        drawerLayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);

        //액션바 변경하기(들어갈 수 있는 타입 : Toolbar type
        setSupportActionBar(toolbar);

        ivMenu.setOnClickListener(v -> {
            Log.d(TAG, "onClick: 클릭됨");
            drawerLayout.openDrawer(Gravity.LEFT);
        });

        //네비게이션 뷰 아이템 클릭시 동작 수행
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);

            String title = menuItem.getTitle().toString();
            switch(menuItem.getItemId())
            {
                case R.id.menu_my_info:
                    Toast.makeText(getApplicationContext(), title + ": 내 정보를 확인합니다..", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "내정보/로그인");
                    if(isLogin == 0){
                        Intent it = new Intent(this, LoginActivity.class);
                        startActivity(it);
                    }else{
                        Intent it = new Intent(this, MyPageActivity.class);
                        startActivity(it);
                    }
                    return true;

                case R.id.menu_notice:
                    Toast.makeText(getApplicationContext(), title + ": 공지사항을 확인합니다.", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "공지사항");
                    Intent intent_an = new Intent(getApplicationContext(), AnnouncementActivity.class);
                    startActivity(intent_an);
                    return true;

                case R.id.menu_service_center:
                    Toast.makeText(getApplicationContext(), title + ": 서비스 센터에 접속합니다.", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "서비스 센터");
                    Intent intent_sc = new Intent(this, service_center.class);
                    startActivity(intent_sc);
                    return true;

                case R.id.menu_policy:
                    Toast.makeText(getApplicationContext(), title + ": 약관 및 정책을 확인합니다.", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "약관 및 정책");
                    return true;

            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    public void InitializeSearchView() {
        SearchView mSearchView = findViewById(R.id.search_view); // SearchView

        //검색 창 동작 수행
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //입력 받은 문자열 처리
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            //입력란의 문자열이 바뀔 때 처리
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    //알림센터 화면전환
    public void nextLayout(View v) {
        int id = v.getId();
        ImageView imageView = v.findViewById(id);
        String tag = (String) imageView.getTag();

        Intent it = new Intent(this, AlertActivity.class);
        it.putExtra("it_tag", tag);
        startActivity(it);
    }

    public void nextLayout2(View v) {
        int id = v.getId();
        TextView textView = v.findViewById(id);
        String tag = (String) textView.getTag();

        Intent it = new Intent(this, AlertActivity.class);
        it.putExtra("it_tag", tag);
        startActivity(it);
    }

    private void bindList() {
        //List item 생성
        List<RecyclerItem> itemList = new ArrayList<>();

        itemList.add(new RecyclerItem(R.drawable.temp));
        itemList.add(new RecyclerItem(R.drawable.ic_japan));
        itemList.add(new RecyclerItem(R.drawable.ic_china));
        itemList.add(new RecyclerItem(R.drawable.ic_bunsik));

        //Recycler View
        RecyclerView recyclerView = findViewById(R.id.rv_main);

        //Adapter 추가
        RecyclerAdapter adapter = new RecyclerAdapter(itemList);
        recyclerView.setAdapter(adapter);

        //Layout magager 추가
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)) ; // 좌우 스크롤

    }
}

