package com.example.servertest.store;

import android.util.Log;
import android.widget.Toast;

import com.example.servertest.MainActivity;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StoreActivity extends AppCompatActivity {

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    private void getStoreName(String data) {

        service.getStorename(data).enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                StoreResponse result = response.body();
                Toast.makeText(StoreActivity.this, result.getStoreName(), Toast.LENGTH_SHORT).show();


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
