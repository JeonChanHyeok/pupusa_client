package com.example.servertest.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.mypage.coupon.Coupon;
import com.example.servertest.mypage.mychatlist.MyChatHistoryListActivity;
import com.example.servertest.mypage.wishlist.WishListActivity;
import com.example.servertest.R;

public class MyPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Button btn_order = findViewById(R.id.btn_my_page_order_history);
        Button btn_review = findViewById(R.id.btn_my_page_review_management);
        Button btn_wish_list = findViewById(R.id.btn_my_page_wish_list);
        Button btn_chat_history = findViewById(R.id.btn_my_page_chattingroom_history);
        Button btn_coupon = findViewById(R.id.btn_my_page_coupon_management);

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                Intent intent = new Intent(MyPageActivity.this, order_history.class);
                startActivity(intent);
            }
        });

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, my_review.class);
                startActivity(intent);
            }
        });

        btn_wish_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WishListActivity.class);
                startActivity(intent);
            }
        });

        btn_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Coupon.class);
                startActivity(intent);
            }
        });

        btn_chat_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyChatHistoryListActivity.class);
                startActivity(intent);
            }
        });
    }



}