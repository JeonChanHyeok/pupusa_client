package com.example.capston;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class my_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        Button btn_order = findViewById(R.id.button_order_history);
        Button btn_review = findViewById(R.id.button_review_management);
        Button btn_wish_list = findViewById(R.id.button_wish_list);

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                Intent intent = new Intent(my_page.this, order_history.class);
                startActivity(intent);
            }
        });

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(my_page.this, my_review.class);
                startActivity(intent);
            }
        });

        btn_wish_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(my_page.this, service_center.class);
                startActivity(intent);
            }
        });
    }



}