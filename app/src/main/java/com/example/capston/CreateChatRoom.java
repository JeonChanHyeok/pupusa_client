package com.example.capston;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CreateChatRoom extends AppCompatActivity {

    Toolbar toolbar;
    // 초기변수설정
    EditText editAddr;
    // 주소 요청코드 상수 requestCode
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat_room);


        toolbar = findViewById(R.id.toolbar_createchatroom);

        // UI 요소 연결
        editAddr = findViewById(R.id.et_create_chat_room_input_address);

        // 터치 안되게 막기
        editAddr.setFocusable(false);
        // 주소입력창 클릭
        editAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("주소설정페이지", "주소입력창 클릭");
                int status = ConnectInternetAddress.getConnectivityStatus(getApplicationContext());
                if(status == ConnectInternetAddress.TYPE_MOBILE || status == ConnectInternetAddress.TYPE_WIFI) {

                    Log.i("주소설정페이지", "주소입력창 클릭");
                    Intent i = new Intent(getApplicationContext(), ConnectAddressApi.class);
                    // 화면전환 애니메이션 없애기
                    overridePendingTransition(0, 0);
                    // 주소결과
                    //startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
                    i.putExtra("i", SEARCH_ADDRESS_ACTIVITY);
                    launcher.launch(i);
                }else {
                    Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->{
                if(result.getResultCode() == RESULT_OK){
                    Intent intent = result.getData();
                    String data = intent.getExtras().getString("data");
                    Log.i("데이터", data);
                    editAddr.setText(data);
                }
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            Log.d("TAG", "data"+result);
//            if(result.getResultCode() == Activity.RESULT_OK){
//                Intent intent = result.getData();
//                String data = intent.getExtras().getString("data");
//                editAddr.setText(data);
//            }
//            else{
//            }
//        }
    });

    //EditText이외에 다른 부분 클릭 시 키보드 내려가기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
