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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompHeader;

public class ChatRoomActivity extends Activity {

    private StompClient mStompClient;
    private List<StompHeader> headerList;
    private Gson gson = new Gson();
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    private String wsServerUrl = "ws://ec2-34-227-207-122.compute-1.amazonaws.com:8080/inchatroom/websocket";
    private static final String TAG = "ChatRoomActivity";

    List<ChatMessage> chatMessageList;
    ChatMessageList cml;

    private ArrayList<ChatDataItem> dataList;
    ImageButton btn;
    EditText editText;
    RecyclerView recyvlerv;
    ChatAdapter adapter;
    Long roomId;
    String loginedId;
    int isLogin;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);   //나중에 바꾸기

        Intent getIntent = getIntent();

        roomId = getIntent.getLongExtra("roomId",0L);
        loginedId = getIntent.getStringExtra("loginedId");
        isLogin = getIntent.getIntExtra("isLogin",0);

        System.out.println("AAA: " + roomId + ", " + loginedId);

        initStomp();
        initData();

        recyvlerv = findViewById(R.id.rc_activity_chat_room);
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
                LocalDate now_day = LocalDate.now();
                String today = now_day.toString();
                LocalTime now_time = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                String time_s = now_time.format(formatter);
                String chatTime = today + " " + time_s;
                ChatMessage chat = new ChatMessage(editText.getText().toString(), roomId, loginedId, chatTime);

                String objJson = gson.toJson(chat);
                sendMsg(objJson);

                //dataList.add(new ChatDataItem(editText.getText().toString(), "사용자2", ChatCode.ViewType.RIGHT_CONTENT));  //editText.getText().toString(), "사용자2", Code.ViewType.RIGHT_CONTENT
                recyvlerv.scrollToPosition(dataList.size()-1);  //텍스트 입력하면 하단으로 자동 이동
                editText.setText("");
            }
        });

    }

    private void initData(){
        dataList = new ArrayList<>();
        chatMessageList = new ArrayList<ChatMessage>();
        String roomIdData = roomId.toString();
        Call chat = service.loadChatData(roomIdData);
        chat.enqueue(new Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call call, Response response) {
                String chatlist = new Gson().toJson(response.body());
                cml = new Gson().fromJson(chatlist,ChatMessageList.class);
                ChatMessageResponse c = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for(int i = 0 ; i < cml.getChatMessageList().size() ; i ++) {
                    c = cml.getChatMessageList().get(i);
                    if(i == 0){
                        if(c.getUserId().equals(loginedId)) {
                            dataList.add(new ChatDataItem(c.getMessageText(),c.getUserName(),ChatCode.ViewType.RIGHT_CONTENT));
                        }else {
                            dataList.add(new ChatDataItem(c.getMessageText(),c.getUserName(),ChatCode.ViewType.LEFT_CONTENT));
                        }
                    }else{
                        ChatMessageResponse temp = cml.getChatMessageList().get(i-1);
                        String time_temp = temp.getMessageTime().split(" ")[0];
                        String time = c.getMessageTime().split(" ")[0];
                        try {
                            Date date_temp = new Date(dateFormat.parse(time_temp).getTime());
                            Date date = new Date(dateFormat.parse(time).getTime());
                            int compare = date_temp.compareTo(date);
                            if(compare < 0) dataList.add(new ChatDataItem(time_temp.split("-")[0] + "년 " + time_temp.split("-")[1] + "월 " + time_temp.split("-")[2] + "일",null,ChatCode.ViewType.CENTER_CONTENT));
                            if(c.getUserId().equals(loginedId)) {
                                dataList.add(new ChatDataItem(c.getMessageText(),c.getUserName(),ChatCode.ViewType.RIGHT_CONTENT));
                            }else {
                                dataList.add(new ChatDataItem(c.getMessageText(),c.getUserName(),ChatCode.ViewType.LEFT_CONTENT));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                adapter.setMyDataList(dataList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }



        /*dataList.add(new ChatDataItem("시용자1님 입장했음",null, ChatCode.ViewType.CENTER_CONTENT));
        dataList.add(new ChatDataItem("사용자2님 입장했음",null, ChatCode.ViewType.CENTER_CONTENT));
        dataList.add(new ChatDataItem("안녕하세요11","사용자1", ChatCode.ViewType.LEFT_CONTENT));
        dataList.add(new ChatDataItem("안녕하세요22","사용자2", ChatCode.ViewType.RIGHT_CONTENT));
        */


    // 채팅방 입장을 위한 소켓 통신/ 소켓 열기
    @SuppressLint("CheckResult")
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
                    this.initData();
                });
        // add Header
        headerList=new ArrayList<>();
        mStompClient.connect(headerList);
    }

    public void sendMsg(String c){
        mStompClient.send("/chat/sendmsg" ,c).subscribe();
    }


}