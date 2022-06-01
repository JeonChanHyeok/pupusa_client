package com.example.detail_order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button announcementbtn = findViewById(R.id.location);
        //임시로 위치변경버튼을 눌렀을 때 리스트 뷰가 나오도록했고 추후 화면을 합칠 때 수정해야함
        announcementbtn.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), detailOrder.class);
                startActivity(intent);
            }
        });
        
    }
}