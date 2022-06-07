package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

//import com.example.servertest.alret.AlertActivity;
import com.example.servertest.announcement.AnnouncementActivity;
import com.example.servertest.chatroom.ChatRoomInfoActivity;
import com.example.servertest.chatroom.ChatRoomList;
import com.example.servertest.chatroom.ChatRoomListAdapter;
import com.example.servertest.chatroom.ChatRoomResponse;
import com.example.servertest.login.LoginActivity;
import com.example.servertest.mypage.MyPageActivity;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
<<<<<<< HEAD
<<<<<<< HEAD

import com.example.servertest.servicecenter.ServiceCenter;
import com.example.servertest.Map_Main;
import com.example.servertest.customlistview.customview_MainActivity;

import com.example.servertest.servicecenter.ServiceCenter;

import com.example.servertest.stores.StoresActivity;

=======
import com.example.servertest.servicecenter.service_center;
import com.example.servertest.Map_Main;
import com.example.servertest.customlistview.customview_MainActivity;
>>>>>>> parent of a8c9519 (Merge branch 'master' into Seonhyun_Kim)
=======
import com.example.servertest.servicecenter.service_center;
import com.example.servertest.Map_Main;
import com.example.servertest.customlistview.customview_MainActivity;
>>>>>>> parent of a8c9519 (Merge branch 'master' into Seonhyun_Kim)
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.kakao.util.maps.helper.Utility;
//import com.kakao.util.maps.helper.Utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import java.util.Map;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Gson gson = new Gson();
    RecyclerView recyclerView;
    MainRecyclerAdapter adapter;
    RecyclerView recyclerView2;
    MainRecyclerAdapter2 recyclerAdapter2;
    ListView lvChatRoomList;
    ChatRoomListAdapter chatRoomListAdapter;

    private static final String TAG = "Main_Activity";

    private ImageView ivMenu;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private SearchView mSearchView;
    private Intent main_intent;
    private int isLogin = 0;
    private String loginedId;
    private String loginedName;
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
        loginedName = main_intent.getStringExtra("loginedName");
        chatRoomList = new ChatRoomList();
        listView = (ListView)findViewById(R.id.listView);



        //this.InitializeDrawerLayout();

        this.InitializeDrawerLayout();

        this.InitializeSearchView();

        bindList1();
        bindList2();
        if(isLogin == 1){
            bindList3();
            lvChatRoomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Long roomId = chatRoomListAdapter.getItem(position).getChatRoomId();
                    Toast.makeText(MainActivity.this, "방번호 : "+ roomId, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ChatRoomInfoActivity.class);
                    intent.putExtra("roomId", roomId);
                    intent.putExtra("loginedId", loginedId);
                    intent.putExtra("isLogin", isLogin);
                    startActivity(intent);
                }
            });
        }

        adapter.setOnItemClickListener(new MainRecyclerAdapter.OnItemClickListener() {
            @Override

            public void onResponse(Call call, Response response) {
                String chatlist = new Gson().toJson(response.body());
                chatRoomList = new Gson().fromJson(chatlist,ChatRoomList.class);
//                for(ChatRoom c:chatRoomList.getchatroomlist()){
//                    chattingRoomDataList.add(new ChatRoomListData(R.drawable.ic_kyochon2,"방제목 : " + c.getChatRoomName(),"부산시 연제구 연산4동","방번호 : " + c.getChatRoomId()));
//                }
                myAdapter = new ChatRoomListAdapter(getApplicationContext(), chattingRoomDataList);
                listView.setAdapter(myAdapter);

            public void onItemClick(View v, int position) {
                switch (position){
                    case 0:
                        Intent storeIntent = new Intent(getApplicationContext(), StoresActivity.class);
                        storeIntent.putExtra("category", "치킨");
                        storeIntent.putExtra("loginedId", loginedId);
                        storeIntent.putExtra("islogin", isLogin);
                        startActivity(storeIntent);
                        break;
                }

            }
            }
    
        recyclerAdapter2.setOnItemClickListener(new MainRecyclerAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(MainActivity.this, recyclerAdapter2.getArrayList().get(position), Toast.LENGTH_SHORT).show();
                RequestChatRoom requestChatRoom = new RequestChatRoom(loginedId, recyclerAdapter2.getArrayList().get(position));
                String objJson = gson.toJson(requestChatRoom);
                initChatRoomData(objJson);
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
<<<<<<< HEAD
<<<<<<< HEAD
                        it.putExtra("loginedId", loginedId);
                        it.putExtra("loginedName", loginedName);
                        it.putExtra("islogin", isLogin);
=======
>>>>>>> parent of a8c9519 (Merge branch 'master' into Seonhyun_Kim)
=======
>>>>>>> parent of a8c9519 (Merge branch 'master' into Seonhyun_Kim)
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

//        Intent it = new Intent(this, AlertActivity.class);
//        it.putExtra("it_tag", tag);
//        startActivity(it);
    }

    //지도를 위한 키 해시값 추출하는 메소드
    //출처: https://manorgass.tistory.com/76 [생각하는 개발자:티스토리]
    public String getKeyHashBase64(Context context) {
        PackageInfo packageInfo = Utility.getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.DEFAULT);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}

    //매개변수로 카테고리 넘기기
    public void initChatRoomData(String objJson){
        Call loadRoomList = service.loadRoomList(objJson);
        loadRoomList.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String str = gson.toJson(response.body());
                System.out.println(str);
                chatRoomList = gson.fromJson(str, ChatRoomList.class);
                chatRoomListAdapter.setSample(chatRoomList.getchatroomlist());
                chatRoomListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

    }

    private void bindList1() {
        //List item 생성
        List<MainRecyclerItem> itemList = new ArrayList<>();

        itemList.add(new MainRecyclerItem(R.drawable.ic_chicken));
        itemList.add(new MainRecyclerItem(R.drawable.ic_japan2));
        itemList.add(new MainRecyclerItem(R.drawable.ic_china2));
        itemList.add(new MainRecyclerItem(R.drawable.ic_bunsik2));

        //Recycler View
        RecyclerView recyclerView = findViewById(R.id.rv_main);

        //Adapter 추가
        this.adapter = new MainRecyclerAdapter(itemList);
        recyclerView.setAdapter(adapter);

        //Layout magager 추가
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)) ; // 좌우 스크롤

    }
    private void bindList2(){
        recyclerView2 = findViewById(R.id.rv_Whole_chattingRoom);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)) ; // 좌우 스크롤
        recyclerAdapter2 = new MainRecyclerAdapter2();
        for (int i = 0; i < 6; i++) {
            String str1 = "";
            switch (i) {
                case 0:
                    str1 = "전체";
                    break;
                case 1:
                    str1 = "치킨";
                    break;
                case 2:
                    str1 = "피자";
                    break;
                case 3:
                    str1 = "분식";
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
    public void bindList3(){
        lvChatRoomList = findViewById(R.id.lv_main_chat_room_list);
        chatRoomListAdapter = new ChatRoomListAdapter(getApplicationContext());
        RequestChatRoom requestChatRoom = new RequestChatRoom(loginedId, "전체");
        String objJson = gson.toJson(requestChatRoom);
        initChatRoomData(objJson);
        lvChatRoomList.setAdapter(chatRoomListAdapter);
    }

    private class RequestChatRoom{
        String requestUserId;
        String requestCategory;

        public RequestChatRoom(String requestUserId, String requestCategory) {
            this.requestUserId = requestUserId;
            this.requestCategory = requestCategory;
        }
    }

}
