package com.example.pupusa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;

import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";

    private ImageView ivMenu;
    private DrawerLayout drawerLayout;
    //private NavigationView navigationView;
    private Toolbar toolbar;
    private SearchView mSearchView;

    ArrayList<SampleData> chattingDataList;

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
        //navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);

        //액션바 변경하기(들어갈 수 있는 타입 : Toolbar type
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 클릭됨");
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

        this.InitializeChattingData();

        ListView listView = (ListView)findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this,chattingDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getTitle(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
    public void InitializeChattingData()
    {
        chattingDataList = new ArrayList<SampleData>();
        chattingDataList.add(new SampleData(R.drawable.ic_kyochon2,"교촌치킨","부산시 연제구 연산4동", "10시에 교촌 드실분... 파티구합니다!!! ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ"));
        chattingDataList.add(new SampleData(R.drawable.ic_60gye,"60계치킨","부산시 연제구 연산4동", "10시에 60계 드실분"));
        chattingDataList.add(new SampleData(R.drawable.ic_nene,"네네치킨","부산시 연제구 연산4동", "10시에 네네 드실분"));
        chattingDataList.add(new SampleData(R.drawable.ic_thunder2,"썬더치킨","부산시 연제구 연산4동", "10시에 썬더 드실분"));
        chattingDataList.add(new SampleData(R.drawable.ic_gcova,"지코바","부산시 연제구 연산4동", "10시에 지코바 드실분"));
        chattingDataList.add(new SampleData(R.drawable.ic_kyochon2,"굽네치킨","부산시 연제구 연산4동", "10시에 굽네 드실분"));
        chattingDataList.add(new SampleData(R.drawable.ic_kyochon2,"BHC","부산시 연제구 연산4동", "10시에 BHC 드실분"));
        chattingDataList.add(new SampleData(R.drawable.ic_kyochon2,"BBQ","부산시 연제구 연산4동", "10시에 BBQ 드실분"));
        chattingDataList.add(new SampleData(R.drawable.ic_kyochon2,"노랑통닭","부산시 연제구 연산4동", "10시에 노랑통닭 드실분"));
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

    private void setSupportActionBar(Toolbar toolbar) {
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
