package com.example.servertest.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    EditText joinNameEt;
    EditText joinEmailEt;
    EditText joinPwEt;
    EditText joinPwCheckEt;
    Button dupChkBtn;

    private boolean dupchk = false;

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinNameEt = findViewById(R.id.et_join_name);
        joinEmailEt = findViewById(R.id.et_join_email);
        joinPwEt = findViewById(R.id.et_join_pw);
        joinPwCheckEt = findViewById(R.id.et_join_pw_check);
        dupChkBtn = findViewById(R.id.btn_join_check);

        String joinName = joinNameEt.getText().toString();
        String joinEmail = joinEmailEt.getText().toString();
        String joinPw = joinPwEt.getText().toString();
        String joinPwCheck = joinPwCheckEt.getText().toString();

        dupChkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<DupResponse> dup = service.userEmailDupChk(joinEmail);
                dup.enqueue(new Callback<DupResponse>() {
                    @Override
                    public void onResponse(Call<DupResponse> call, Response<DupResponse> response) {
                        if(response.body().getCode() == 1){
                            dupchk = true;
                            dupChkBtn.setClickable(false);
                            Toast.makeText(JoinActivity.this, "중복확인 완료", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(JoinActivity.this, "이미 가입된 이메일입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DupResponse> call, Throwable t) {
                        Toast.makeText(JoinActivity.this, "통신 오류", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }


    private void startJoin(String json) {
        Call<JoinResponse> join = service.userJoin(json);
        join.enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                try{

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {

            }
        });
    }
}
