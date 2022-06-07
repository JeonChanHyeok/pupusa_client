package com.example.servertest.instore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StoreLoginActivity extends AppCompatActivity {

    private Gson gson = new Gson();
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    private Button storenameinputbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_login_main);

        storenameinputbtn = findViewById(R.id.btn_store_name_input);
        EditText storeId = findViewById(R.id.et_store_name_input);

        //임시로 위치변경버튼을 눌렀을 때 리스트 뷰가 나오도록했고 추후 화면을 합칠 때 수정해야함
        storenameinputbtn.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
            @Override
            public void onClick(View view) {
                try{
                    Long storeId_long = Long.parseLong(storeId.getText().toString());
                    String objJson = gson.toJson(storeId_long);
                    Call<StoreLoginResponse> call = service.storeLogin(objJson);
                    call.enqueue(new Callback<StoreLoginResponse>() {
                        @Override
                        public void onResponse(Call<StoreLoginResponse> call, Response<StoreLoginResponse> response) {
                            if(response.body().getCode() == 1){
                                Intent intent = new Intent(getApplicationContext(), StoreMyPage.class);
                                intent.putExtra("storeId", storeId_long);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(StoreLoginActivity.this, "오류발생", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<StoreLoginResponse> call, Throwable t) {
                            Toast.makeText(StoreLoginActivity.this, "오류발생", Toast.LENGTH_SHORT).show();
                        }

                    });


                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "제대로 된 가게 id를 써주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}