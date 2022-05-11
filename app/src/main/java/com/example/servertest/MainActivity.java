package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    private ProgressBar mProgressView;
    String id;
    String pw;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText e_id = (EditText)findViewById(R.id.id);
        EditText e_pw = (EditText)findViewById(R.id.pw);
        Button join = (Button) findViewById(R.id.join);
        Button login = (Button) findViewById(R.id.login);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = e_id.getText().toString();
                pw = e_pw.getText().toString();
                JoinData data = new JoinData(id, pw);
                String objJson = gson.toJson(data);
                startJoin(objJson);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = e_id.getText().toString();
                pw = e_pw.getText().toString();
                LoginData data = new LoginData(id, pw);
                String objJson = gson.toJson(data);
                startLogin(objJson);
            }
        });


    }

    private void startJoin(String json) {
        Call<JoinResponse> join = service.userJoin(json);
        join.enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                try{
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Log.e("log",t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("log",t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}