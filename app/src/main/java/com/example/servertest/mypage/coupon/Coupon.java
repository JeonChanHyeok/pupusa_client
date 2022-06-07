package com.example.servertest.mypage.coupon;

import android.os.Bundle;
import android.view.Display;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;

public class Coupon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_list);

        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

    }
}