package com.example.servertest.store;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.servertest.MainActivity;
import com.example.servertest.R;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    List<Store> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.store_select_activity_main);

        items = new ArrayList<>();

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String[] str = new String[items.size()];
                for (int i = 0; i < str.length; i++) {
                    Store store = items.get(i);
                    str[i] = (i + 1) + ". " + store.getStoreName() + " [ " + store.getCategory() + " ] ";
                }
            }
        };
    }
    public void getStoreName(String category) {

        String objJson = new Gson().toJson(category);

        service.getStoreId(objJson).enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                StoreResponse result = response.body();
                System.out.println("store result: " + result.getCategory());


                //Toast.makeText(getApplicationContext(), result.getStoreName(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {

                Toast.makeText(StoreActivity.this, "가게명 가져오기 에러", Toast.LENGTH_SHORT).show();
                Log.e("가게명 가져오기 에러", t.getMessage());
                t.printStackTrace();

            }
        });
    }

}
