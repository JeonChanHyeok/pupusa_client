package com.example.servertest.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.MainActivity;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.example.servertest.R;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    String id;
    String pw;
    Gson gson = new Gson();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*    아래부터 전찬혁 코드 */

        EditText et_login_email = (EditText)findViewById(R.id.et_login_email);
        EditText et_login_pw = (EditText)findViewById(R.id.et_login_pw);
        TextView tv_sign_up = (TextView) findViewById(R.id.tv_sign_up);
        Button btn_login = (Button) findViewById(R.id.btn_login);

        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*id = et_login_email.getText().toString();
                pw = et_login_pw.getText().toString();
                JoinData data = new JoinData(id, pw);
                String objJson = gson.toJson(data);
                startJoin(objJson);*/
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = et_login_email.getText().toString();
                pw = et_login_pw.getText().toString();
                LoginData data = new LoginData(id, pw);
                String objJson = gson.toJson(data);
                startLogin(objJson);
            }
        });


    }

    private void startLogin(String json) {
        Call<LoginResponse> login = service.userLogin(json);
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                try{
                    if(response.body().getCode() == 1){
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent inLogin = new Intent(getApplicationContext(), MainActivity.class);
                        inLogin.putExtra("islogin", 1);
                        inLogin.putExtra("loginedId", id);
                        startActivity(inLogin);
                        finish();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("log",t.getMessage());
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
