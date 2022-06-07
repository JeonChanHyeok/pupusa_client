package com.example.servertest.chatroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;
import com.example.servertest.chat.ChatRoomActivity;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateChatRoomActivity extends AppCompatActivity {
    private Gson gson = new Gson();
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    String chatRoomTitle;
    String chatRoomAddress;
    String chatRoomStoreName;
    String chatRoomInfo;

    String loginedId;
    int isLogin;
    Long chatRoomStoreId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat_room);

        Intent getIntent = getIntent();

        loginedId = getIntent.getStringExtra("loginedId");
        isLogin = getIntent.getIntExtra("isLogin",0);
        chatRoomStoreId = getIntent.getLongExtra("storeId",0);
        chatRoomStoreName = "치킨집1";
        EditText etChatRoomTitle = (EditText) findViewById(R.id.et_create_chat_room_input_title);
        EditText etChatRoomAddress = (EditText) findViewById(R.id.et_create_chat_room_input_address);
        TextView tvChatRoomStoreName = (TextView) findViewById(R.id.tv_create_chat_room_input_store_name);
        EditText etChatRoomInfo = (EditText) findViewById(R.id.et_create_chat_room_input_content);

        Button btnMake = (Button) findViewById(R.id.btn_create_chat_room_make);

        btnMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatRoomTitle = etChatRoomTitle.getText().toString();
                chatRoomAddress = etChatRoomAddress.getText().toString();
                chatRoomInfo = etChatRoomInfo.getText().toString();

                ChatRoom chatRoom = new ChatRoom(loginedId, chatRoomTitle, chatRoomAddress, chatRoomStoreId, chatRoomInfo);
                String objJson = gson.toJson(chatRoom);
                Call<Long> makeChat = service.makeChatRoom(objJson);
                makeChat.enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        Intent goChatRoom = new Intent(getApplicationContext(), ChatRoomActivity.class);
                        goChatRoom.putExtra("loginedId", loginedId);
                        goChatRoom.putExtra("roomId", response.body());
                        goChatRoom.putExtra("isLogin", isLogin);
                        startActivity(goChatRoom);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {

                    }
                });
            }
        });




    }
}
