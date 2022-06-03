package com.example.servertest.chat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servertest.R;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompHeader;

public class ChatRoomActivity extends Activity {

    private StompClient mStompClient;
    private List<StompHeader> headerList;
    private Gson gson;
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    private String wsServerUrl = "ws://175.200.243.163:8080/inchatroom/websocket";
    private static final String TAG = "ChatRoomActivity";


    private ArrayList<ChatDataItem> dataList;
    ImageButton btn;
    EditText editText;
    ChatAdapter adapter;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);   //나중에 바꾸기

        Intent getIntent = getIntent();


        initData();

        RecyclerView recyvlerv = findViewById(R.id.rc_activity_chat_room);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyvlerv.setLayoutManager(manager);

        adapter = new ChatAdapter(dataList);
        recyvlerv.setAdapter(adapter);

        btn = findViewById(R.id.btn_chat_room_send);
        editText = findViewById(R.id.et_chat_room_input_text);


        //editText에 글자 입력시 버튼 활성성
       editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    btn.setClickable(true);
                    btn.setBackgroundResource(R.drawable.ic_btn_chat_yellow);
                } else {
                    btn.setClickable(false);
                    btn.setBackgroundResource(R.drawable.ic_btn_chat_gray);
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(ChatRoomActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                dataList.add(new ChatDataItem(editText.getText().toString(), "사용자2", ChatCode.ViewType.RIGHT_CONTENT));  //editText.getText().toString(), "사용자2", Code.ViewType.RIGHT_CONTENT
                recyvlerv.scrollToPosition(dataList.size()-1);  //텍스트 입력하면 하단으로 자동 이동
                adapter.notifyDataSetChanged(); // 변경되었음을 어답터에 알려준다.
                editText.setText("");

            }
        });

    }

    private void initData(){
        dataList = new ArrayList<>();




        /*dataList.add(new ChatDataItem("시용자1님 입장했음",null, ChatCode.ViewType.CENTER_CONTENT));
        dataList.add(new ChatDataItem("사용자2님 입장했음",null, ChatCode.ViewType.CENTER_CONTENT));
        dataList.add(new ChatDataItem("안녕하세요11","사용자1", ChatCode.ViewType.LEFT_CONTENT));
        dataList.add(new ChatDataItem("안녕하세요22","사용자2", ChatCode.ViewType.RIGHT_CONTENT));
        */
    }

    // 채팅방 입장을 위한 소켓 통신/ 소켓 열기
    /*@SuppressLint("CheckResult")
    public void initStomp(){
        mStompClient= Stomp.over(Stomp.ConnectionProvider.OKHTTP, wsServerUrl);

        mStompClient.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {
                case OPENED:
                    Log.d(TAG, "Stomp connection opened");
                    break;
                case ERROR:
                    Log.e(TAG, "Error", lifecycleEvent.getException());
                    if(lifecycleEvent.getException().getMessage().contains("EOF")){

                    }
                    break;
                case CLOSED:
                    Log.d(TAG, "Stomp connection closed");
                    break;
            }
        });

        mStompClient.topic("/topic/chat/room/" + roomId)
                .subscribe(topicMessage -> {
                    System.out.println(topicMessage.getPayload());
                    this.InitializeChattingData();
                });
        // add Header
        headerList=new ArrayList<>();
        mStompClient.connect(headerList);
    }*/

    public void sendMsg(String c){
        mStompClient.send("/chat/sendmsg" ,c).subscribe();
    }


    //EditText이외에 다른 부분 클릭 시 키보드 내려가기
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        View focusView = getCurrentFocus();
//        if (focusView != null) {
//            Rect rect = new Rect();
//            focusView.getGlobalVisibleRect(rect);
//            int x = (int) ev.getX(), y = (int) ev.getY();
//            if (!rect.contains(x, y)) {
//                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                if (imm != null)
//                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
//                focusView.clearFocus();
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }

}