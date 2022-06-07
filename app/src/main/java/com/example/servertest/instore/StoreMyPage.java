package com.example.servertest.instore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.example.servertest.stores.StoreOrderCheck;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StoreMyPage extends AppCompatActivity {
    private Gson gson = new Gson();
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Long storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_ceo_page);

        storeId = getIntent().getLongExtra("storeId", 0L);
        String objJson = gson.toJson(storeId);
        Call<InStoreInfoResponse> call = service.storeInfoLoad(objJson);
        call.enqueue(new Callback<InStoreInfoResponse>() {
            @Override
            public void onResponse(Call<InStoreInfoResponse> call, Response<InStoreInfoResponse> response) {
                String str = gson.toJson(response.body());
                InStoreInfoResponse inStoreInfoResponse = gson.fromJson(str, InStoreInfoResponse.class);

                TextView tv_name = (TextView) findViewById(R.id.tv_in_store_vip_level);
                tv_name.setText(inStoreInfoResponse.getStoreName());
                ImageView iv = (ImageView) findViewById(R.id.iv_in_store_profile_image);
                switch (inStoreInfoResponse.getCategory()){
                    case "치킨":
                        iv.setImageResource(R.drawable.ic_chicken);
                        break;
                    case "피자":
                        iv.setImageResource(R.drawable.ic_bunsik2);
                        break;
                }

            }

            @Override
            public void onFailure(Call<InStoreInfoResponse> call, Throwable t) {

            }
        });

        Button btn_order = findViewById(R.id.btn_in_store_order_history);
        Button btn_review = findViewById(R.id.btn_in_store_review_management);
        Button btn_logout = findViewById(R.id.btn_in_store_logout);

        //주문내역
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreMyPage.this, StoreOrderCheck.class);
                startActivity(intent);
            }
        });

        //로그아웃
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreMyPage.this, StoreLoginActivity.class);
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
                //Intent intent = new Intent(StoreMyPage.this, StoreMyReview.class);
                //startActivity(intent);
            }
        });

    }

    //뒤로가기 방지
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }



}