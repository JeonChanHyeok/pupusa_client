package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import com.example.servertest.chat.ChatRoom;
import com.example.servertest.chat.ChatRoomController;
import com.example.servertest.chat.ChatRoomList;
import com.example.servertest.chat.JoinRoomData;
import com.example.servertest.login.LoginActivity;
import com.example.servertest.mypage.MyPageActivity;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.example.servertest.servicecenter.service_center;
import com.example.servertest.Map_Main;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.kakao.util.maps.helper.Utility;
//import com.kakao.util.maps.helper.Utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Gson gson = new Gson();

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

    Button map_btn;
    //지도 출력용 테스트 버튼

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

        map_btn = (Button)findViewById(R.id.map_button);
        //툴바의 MAP버튼 연결
        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Address Btn Clicked", Toast.LENGTH_LONG).show(); // 버튼 테스트용 -> 잘 작동함

                Intent map = new Intent(getApplicationContext(), Map_Main.class);
                startActivity(map);
            }
        });

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

