package com.example.pupusa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ChattingActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        this.InitializeDrawerLayout();
    }

    @SuppressLint({"NonConstantResourceId", "RtlHardcoded"})
    public void InitializeDrawerLayout() {
        ImageView ivMenu = findViewById(R.id.iv_rightMenu);
        drawerLayout = findViewById(R.id.drawer2);
        NavigationView navigationView = findViewById(R.id.navigation_chattingroom);
        Toolbar toolbar = findViewById(R.id.toolbar);

        //액션바 변경하기(들어갈 수 있는 타입 : Toolbar type
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivMenu.setOnClickListener(v -> {
            drawerLayout.openDrawer(Gravity.RIGHT);
        });

        //네비게이션 뷰 아이템 클릭시 동작 수행
//        navigationView.setNavigationItemSelectedListener(menuItem -> {
//            menuItem.setChecked(true);
//
//            String title = menuItem.getTitle().toString();
//            switch(menuItem.getItemId())
//            {
//                case R.id.menu_my_info:
//                    Toast.makeText(getApplicationContext(), title + ": 내 정보를 확인합니다..", Toast.LENGTH_SHORT).show();
//                    Intent it = new Intent(this, UserLogin.class);
//                    startActivity(it);
//                    return true;
//
//                case R.id.menu_notice:
//                    Toast.makeText(getApplicationContext(), title + ": 공지사항을 확인합니다.", Toast.LENGTH_SHORT).show();
//                    return true;
//
//                case R.id.menu_service_center:
//                    Toast.makeText(getApplicationContext(), title + ": 서비스 센터에 접속합니다.", Toast.LENGTH_SHORT).show();
//                    return true;
//
//                case R.id.menu_policy:
//                    Toast.makeText(getApplicationContext(), title + ": 약관 및 정책을 확인합니다.", Toast.LENGTH_SHORT).show();
//                    return true;
//
//            }
//            drawerLayout.closeDrawer(GravityCompat.START);
//            return true;
//        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}
