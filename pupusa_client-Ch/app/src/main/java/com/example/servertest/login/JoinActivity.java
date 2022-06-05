package com.example.servertest.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.MainActivity;
import com.example.servertest.R;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    EditText joinNameEt;
    EditText joinEmailEt;
    EditText joinPwEt;
    EditText joinPwCheckEt;
    Button dupChkBtn, joinBtn;

    Gson gson = new Gson();

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
        joinBtn = findViewById(R.id.btn_join);

        dupChkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String joinEmail = joinEmailEt.getText().toString();
                System.out.println(joinEmail);
                Call<DupResponse> dup = service.userEmailDupChk(joinEmail);
                dup.enqueue(new Callback<DupResponse>() {
                    @Override
                    public void onResponse(Call<DupResponse> call, Response<DupResponse> response) {
                        if(response.body().getCode() == 1){
                            dupchk = true;
                            dupChkBtn.setClickable(false);
                            Toast.makeText(JoinActivity.this, "사용하실 수 있는 이메일입니다.", Toast.LENGTH_SHORT).show();
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

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String joinName = joinNameEt.getText().toString();
                String joinEmail = joinEmailEt.getText().toString();
                String joinPw = joinPwEt.getText().toString();
                String joinPwCheck = joinPwCheckEt.getText().toString();

                if(dupchk){
                    if(joinPw.equals(joinPwCheck)){
                        JoinData user = new JoinData(joinEmail, joinPw, joinName);
                        String objJson = gson.toJson(user);
                        startJoin(objJson);
                    }else{
                        Toast.makeText(JoinActivity.this, "비밀번호 확인을 해주세요.", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(JoinActivity.this, "아이디 중복체크를 해주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        joinEmailEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dupchk = false;
                dupChkBtn.setClickable(true);
            }
        });


    }


    private void startJoin(String json) {
        Call<JoinResponse> join = service.userJoin(json);
        join.enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                try{
                    if(response.body().getCode() == 1){
                        Toast.makeText(JoinActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(JoinActivity.this, "회원가입에 오류가 발생하였습니다. 잠시 후 다시 시도해 주십시오.", Toast.LENGTH_SHORT).show();
                    }
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
