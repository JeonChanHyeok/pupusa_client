package com.example.servertest.store;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.servertest.chat.ChatMessage;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.example.servertest.customlistview.customview_MainActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {


    List<Store> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.store_select_activity_main);
    }


}