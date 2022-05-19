package com.example.capston;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class create_chat_room extends AppCompatActivity {

    // 초기변수설정
    EditText edit_addr;
    // 주소 요청코드 상수 requestCode
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;



   // @Override
   // protected void onStart() {
    //    super.onStart();
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Log.d("TAG", "data"+result);
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent intent = result.getData();
                    String data = intent.getExtras().getString("data");
                    edit_addr.setText(data);
                }
                else{

                }
            }
        });

    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_chat_room);

        // UI 요소 연결
        edit_addr = findViewById(R.id.input_address);

        // 터치 안되게 막기
        edit_addr.setFocusable(false);
        // 주소입력창 클릭
        edit_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("주소설정페이지", "주소입력창 클릭");
                int status = connect_internet_address.getConnectivityStatus(getApplicationContext());
                if(status == connect_internet_address.TYPE_MOBILE || status == connect_internet_address.TYPE_WIFI) {

                    Log.i("주소설정페이지", "주소입력창 클릭");
                    Intent i = new Intent(getApplicationContext(), connect_address_api.class);
                    // 화면전환 애니메이션 없애기
                    overridePendingTransition(0, 0);
                    // 주소결과
                    //startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
                    launcher.launch(i);
                }else {
                    Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//        Log.i("test", "onActivityResult");
//
//        switch (requestCode) {
//            case SEARCH_ADDRESS_ACTIVITY:
//                if (resultCode == RESULT_OK) {
//                    String data = intent.getExtras().getString("data");
//                    if (data != null) {
//                        Log.i("test", "data:" + data);
//                        edit_addr.setText(data);
//                    }
//                }
//                break;
//        }
//    }
}
