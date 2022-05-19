package com.example.project_ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Wish_Data> movieDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //탈퇴팝업 버튼
        Button btn01;
        //공구팝업 버튼
        Button btn02;
        //체크박스
        CheckBox checkbox;
        CheckBox checkbox2;

        EditText firstText, secondText;
        ImageView setImage;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        // 비밀번호 확인 표시
        firstText = (EditText)findViewById(R.id.firstText);
        secondText = (EditText)findViewById(R.id.secondText);
        setImage = (ImageView)findViewById(R.id.setImage);

        secondText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(firstText.getText().toString().equals(secondText.getText().toString())) {
                    setImage.setImageResource(R.drawable.sign_up_password_right);
                } else {

                    setImage.setImageResource(R.drawable.sign_up_password_currect);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        //채팅방 입장화면(chat_info)
        Button chatinfobtn=findViewById(R.id.chatinfobtn);
        chatinfobtn.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Chat_Info.class);
                startActivity(intent);
            }
        });
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
                EditText editText = (EditText) findViewById(( R.id.secondText ));
                EditText editText2 = (EditText) findViewById(( R.id.firstText ));

                if (firstText.getText().toString().equals("") || firstText.getText().toString() == null) {
                    Toast.makeText(getApplicationContext(), "기존 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                } else if(secondText.getText().toString().equals("") || secondText.getText().toString() == null)
                {
                    Toast.makeText(getApplicationContext(), "바꿀 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                }else if(firstText.getText().toString().equals(secondText.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "성공적으로 변경되었습니다.", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getApplicationContext(), "서로 암호가 다릅니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //SMS 수신동의 체크박스
        checkbox = findViewById(R.id.checkBox);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //체크 되었을때
                if(checkbox.isChecked()){
                    Toast.makeText(getApplicationContext(),"SMS 수신동의 되었습니다.",Toast.LENGTH_SHORT).show();
                } //체크 안됬을때
                else{ Toast.makeText(getApplicationContext(),"체크 해제 되었습니다.",Toast.LENGTH_SHORT).show(); }

            } });
        //알림설정 체크박스
        checkbox2 = findViewById(R.id.checkBox2);
        checkbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //체크 되었을때
                if(checkbox2.isChecked()){
                    Toast.makeText(getApplicationContext(),"알림설정 체크되었습니다.",Toast.LENGTH_SHORT).show();
                } //체크 안됬을때
                else{ Toast.makeText(getApplicationContext(),"체크 해제 되었습니다.",Toast.LENGTH_SHORT).show(); } } });

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
                Intent intent = new Intent(getApplicationContext(), Coupon.class);
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