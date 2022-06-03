package com.example.servertest.stores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;
import com.example.servertest.chatroom.CreateChatRoomActivity;

// 가입탈퇴 팝업
public class GongGuPopup extends Activity {

    Button backbtn, gonggulistbtn,gonggubtn;
    String loginedId;
    int isLogin;
    Long storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 상태바 제거 ( 전체화면 모드 )
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gonggu_popup);

        Intent getIntent = getIntent();
        loginedId = getIntent.getStringExtra("loginedId");
        isLogin = getIntent.getIntExtra("isLogin", 0);
        storeId = getIntent.getLongExtra("storeId", 0);



        backbtn = (Button) findViewById(R.id.backbtn);
        gonggulistbtn = (Button) findViewById(R.id.gonggulistbtn);
        gonggubtn = (Button) findViewById(R.id.gonggubtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gonggubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createChatRoomIntent = new Intent(getApplicationContext(), CreateChatRoomActivity.class);
                createChatRoomIntent.putExtra("loginedId", loginedId);
                createChatRoomIntent.putExtra("isLogin", isLogin);
                createChatRoomIntent.putExtra("storeId", storeId);
                startActivity(createChatRoomIntent);
            }
        });
        gonggulistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}