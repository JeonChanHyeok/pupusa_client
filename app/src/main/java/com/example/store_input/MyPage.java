package com.example.store_input;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceo_page);

        Button btn_order = findViewById(R.id.btn_my_page_order_history);
        Button btn_review = findViewById(R.id.btn_my_page_review_management);
        Button btn_logout = findViewById(R.id.btn_my_page_logout);

        //주문내역
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                Intent intent = new Intent(MyPage.this, OrderCheck.class);
                startActivity(intent);
            }
        });

        //로그아웃
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

        //리뷰내역
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, MyReview.class);
                startActivity(intent);
            }
        });

    }

    //뒤로가기 방지
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }



}