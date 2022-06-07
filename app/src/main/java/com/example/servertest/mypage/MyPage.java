package com.example.servertest.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.servertest.MainActivity;
import com.example.servertest.R;
import com.example.servertest.mypage.myreview.MyReview;
import com.example.servertest.mypage.orderhistory.OrderHistory;
import com.example.servertest.servicecenter.ServiceCenter;

public class MyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Button btn_order = findViewById(R.id.btn_my_page_order_history);
        Button btn_review = findViewById(R.id.btn_my_page_review_management);
        Button btn_wish_list = findViewById(R.id.btn_my_page_wish_list);
        Button btn_locker = findViewById(R.id.btn_my_page_coupon_management);
        Button btn_chat =findViewById(R.id.btn_my_page_chattingroom_history);
        Button btn_change_info = findViewById(R.id.btn_my_page_changing_information);
        Button btn_logout = findViewById(R.id.btn_my_page_logout);

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                Intent intent = new Intent(MyPage.this, OrderHistory.class);
                startActivity(intent);
            }
        });

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, MyReview.class);
                startActivity(intent);
            }
        });

        btn_wish_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, ServiceCenter.class);
                startActivity(intent);
            }
        });

        btn_locker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, CreateChatRoom.class);
                startActivity(intent);
            }
        });

        btn_chat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, InformationChatRoom.class);
                startActivity(intent);
            }
        });

        btn_change_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, MenuList.class);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPage.this, MainActivity.class);
                intent.putExtra("islogin", 0);
                intent.putExtra("loginedId", "");
                startActivity(intent);
                finish();
            }
        });

    }



}