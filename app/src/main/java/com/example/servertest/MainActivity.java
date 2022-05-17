package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servertest.chat.ChatRoom;
import com.example.servertest.chat.ChatRoomController;
import com.example.servertest.chat.ChatRoomList;
import com.example.servertest.chat.JoinRoomData;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

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
    private TextView logo;
    private Intent main_intent;
    private int isLogin;
    private String loginedId;
    private ChatRoomList chatRoomList;
    private ListView listView;
    private MyAdapter myAdapter;
    private EditText make_room_name;
    private Button btn_make;

    ArrayList<SampleData> chattingRoomDataList;

    //초기변수설정
    EditText edit_addr;
    //주소 요청코드 상수 requestCode
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchView = findViewById(R.id.search_view); // SearchView
        ivMenu = findViewById(R.id.iv_menu);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        logo = findViewById(R.id.tv_logo);
        main_intent = getIntent();
        isLogin = main_intent.getIntExtra("islogin",0);
        loginedId = main_intent.getStringExtra("loginedId");
        chatRoomList = new ChatRoomList();
        listView = (ListView)findViewById(R.id.listView);
        make_room_name = findViewById(R.id.make_room_name);
        btn_make = findViewById(R.id.btn_make_room);
        if(isLogin == 1){
            logo.setText(loginedId + "님");
        }

        //액션바 변경하기(들어갈 수 있는 타입 : Toolbar type
        setSupportActionBar(toolbar);

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

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

        //UI 요소 연결
        edit_addr = findViewById(R.id.edit_addr);

        //터치 안되게 막기
        edit_addr.setFocusable(false);

        edit_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("주소설정페이지", "주소입력창 클릭");
                int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
                if(status == NetworkStatus.TYPE_MOBILE || status == NetworkStatus.TYPE_WIFI) {

                    Log.i("주소설정페이지", "주소입력창 클릭");
                    Intent i = new Intent(getApplicationContext(), AddressApiActivity.class);
                    //화면전환 애니메이션 없애기
                    overridePendingTransition(0, 0);
                    //주소 결과
                    startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
                }else {
                    Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.InitializeChattingRoomData();


        btn_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String room_name = make_room_name.getText().toString();
                System.out.println(room_name);
                ChatRoom c = new ChatRoom();
                c.setChatRoomName(room_name);
                String objJson = gson.toJson(c);
                Call make_chat = service.makeChatRoom(objJson);
                make_chat.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        InitializeChattingRoomData();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
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
        });

    }
    public void InitializeChattingRoomData()
    {
        chattingRoomDataList = new ArrayList<SampleData>();
        Call chat = service.loadRoomList();
        chat.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String chatlist = new Gson().toJson(response.body());
                chatRoomList = new Gson().fromJson(chatlist,ChatRoomList.class);
                for(ChatRoom c:chatRoomList.getchatroomlist()){
                    chattingRoomDataList.add(new SampleData(R.drawable.ic_kyochon2,"방제목 : " + c.getChatRoomName(),"부산시 연제구 연산4동","방번호 : " + c.getChatRoomId()));
                }
                myAdapter = new MyAdapter(getApplicationContext(), chattingRoomDataList);
                listView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.i("test", "onActivityResult");

        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (requestCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        Log.i("test", "data:" + data);
                        edit_addr.setText(data);
                    }
                }
                break;
        }
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




}