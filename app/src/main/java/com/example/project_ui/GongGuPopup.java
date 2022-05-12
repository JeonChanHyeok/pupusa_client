package com.example.project_ui;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// 가입탈퇴 팝업
public class GongGuPopup extends AppCompatActivity {

    Button backbtn, gonggulistbtn,gonggubtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 상태바 제거 ( 전체화면 모드 )
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.gonggu_popup);

        backbtn = (Button) findViewById(R.id.backbtn);
        gonggulistbtn = (Button) findViewById(R.id.gonggulistbtn);
        gonggubtn = (Button) findViewById(R.id.gonggubtn);

    }

    //공구방 리스트 버튼 클릭
    public void mgonggulist(View v){

        finish();
    }


    //공구방생성 버튼 클릭
    public void mgonggu(View v){
        finish();
    }
    //뒤로가기 버튼 클릭
    public void mback(View v){
        finish();
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