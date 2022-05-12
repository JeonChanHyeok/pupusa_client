package com.example.project_ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<SampleData> movieDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //탈퇴팝업 버튼
        Button btn01;
        //공구팝업 버튼
        Button btn02;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //찜 목록(wishlist)
        Button wishbtn=findViewById(R.id.wishbtn);
        wishbtn.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Wish_List_Parent.class);
                startActivity(intent);
            }
        });
        //참여했던 채팅방 목록(chatlist)
        Button chatbtn=findViewById(R.id.chatbtn);
        chatbtn.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Chat_List_Parent.class);
                startActivity(intent);
            }
        });


        //비밀번호 변경하기버튼
        Button passwordbtn=findViewById(R.id.button2);
        passwordbtn.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
            @Override
            public void onClick(View view) {
                EditText editText = (EditText)findViewById((R.id.editTextTextPassword));
                EditText editText2 = (EditText)findViewById((R.id.editTextTextPassword3));

                if(editText != editText2) {
                    Toast.makeText(getApplicationContext(), "서로 암호가 다릅니다.", Toast.LENGTH_SHORT).show();
                                   }
                else{
                    Toast.makeText(getApplicationContext(), "성공적으로 변경되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //공지 사항(announcement)
        Button announcementbtn=findViewById(R.id.announcementbtn);
        announcementbtn.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Announcement.class);
                    startActivity(intent);
            }
        });
        Button.OnClickListener btnListener1 = new View.OnClickListener(){

            public void onClick(View v){
                switch (v.getId()){
                    case R.id.button5:
                        Intent intent = new Intent(MainActivity.this, GongGuPopup.class);
                        startActivityForResult(intent, 1);
                        break;
                }
            }
        };
        btn02 = (Button)findViewById(R.id.button5);
        btn02.setOnClickListener(btnListener1);

        //쿠폰함 (coupon)
        Button couponbtn=findViewById(R.id.couponbtn);
        couponbtn.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), coupon.class);
                startActivity(intent);
            }
        });
        //회원탈퇴 팝업
        Button.OnClickListener btnListener = new View.OnClickListener(){

            public void onClick(View v){
                switch (v.getId()){
                    case R.id.button4:
                        Intent intent = new Intent(MainActivity.this, PopupActivity.class);
                        startActivityForResult(intent, 1);
                        break;
                }
            }
        };
        btn01 = (Button)findViewById(R.id.button4);
        btn01.setOnClickListener(btnListener);
    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}