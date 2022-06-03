package com.example.servertest.chatroom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;
import com.example.servertest.chat.ChatMessage;
import com.example.servertest.chat.ChatMessageList;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompHeader;

/*public class ChatRoomController extends AppCompatActivity {
    private StompClient mStompClient;
    private List<StompHeader> headerList;

    private Gson gson;

    private static final String TAG = "ChatRoomController";

    private ChatMessageList chatMessageList;
    private ArrayList<ChatMessage> chattingDataList;

    private ListView listView;
    private ChatRoomAdapter mAdapter;

    private Button btn_exit;
    private Button btn_send;
    private EditText editText;

    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    private Intent chatRoomIntent;
    private String roomId;
    private String loginedId;
    private String wsServerUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_chatting_room);
        gson = new Gson();

        btn_exit = findViewById(R.id.exit);
        btn_send = findViewById(R.id.send_msg);
        editText = findViewById(R.id.text_chat);


        chatRoomIntent = getIntent();
        loginedId = chatRoomIntent.getStringExtra("userId");
        roomId = chatRoomIntent.getStringExtra("roomId");

        wsServerUrl = "ws://ec2-34-227-207-122.compute-1.amazonaws.com:8080/inchatroom/websocket";
        listView = (ListView) findViewById(R.id.listView_chat_message);

        initStomp();
        this.InitializeChattingData();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                ChatMessage c = new ChatMessage(msg, roomId, loginedId);
                String objJson = gson.toJson(c);
                sendMsg(objJson);
            }
        });

        //나가기 버튼
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinRoomData joinRoomData = new JoinRoomData(roomId, loginedId);
                String objJson = gson.toJson(joinRoomData);
                Call exitRoom = service.exitChatRoom(objJson);
                exitRoom.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        finish();
                    }
                    @Override
                    public void onFailure(Call call, Throwable t) {
                    }
                });
            }
        });
    }

    //채팅 불러오기


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
                    this.InitializeChattingData();
                });
        // add Header
        headerList=new ArrayList<>();
        mStompClient.connect(headerList);
    }

    public void sendMsg(String c){
        mStompClient.send("/chat/sendmsg" ,c).subscribe();
    }
}*/

public class ChatRoomController{
    private Gson gson = new Gson();
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    private Long roomId = 0L;
    public Long makeChatRoom(String userId, String chatRoomName, String chatRoomAddress, Long chatRoomStoreId, String chatRoomInfo){
        ChatRoom chatRoom = new ChatRoom(userId, chatRoomName, chatRoomAddress, chatRoomStoreId, chatRoomInfo);
        String objJson = gson.toJson(chatRoom);
        Call<Long> makeChat = service.makeChatRoom(objJson);
        ;
        makeChat.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                roomId = response.body();
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
        return roomId;
    }
}
