package com.example.servertest.chatroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import org.w3c.dom.Text;

public class ChatRoomInfoActivity extends AppCompatActivity {

    private Gson gson = new Gson();
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    String loginedId;
    int isLogin;
    Long roomId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_chat_room);

        Intent getIntent = getIntent();
        isLogin = getIntent.getIntExtra("islogin",0);
        loginedId = getIntent.getStringExtra("loginedId");
        roomId = getIntent.getLongExtra("roomId", 0L);

        TextView tvChatRoomTitle = (TextView) findViewById(R.id.tv_information_chat_room_title);
        TextView tvChatRoomMasterUser = (TextView) findViewById(R.id.tv_information_chat_room_master_name);
        TextView tvChatRoomUserCount = (TextView) findViewById(R.id.tv_information_chat_room_participating_person);
        TextView tvChatRoomStoreName = (TextView) findViewById(R.id.tv_information_chat_room_shop_name);
        TextView tvChatRoomContent = (TextView) findViewById(R.id.tv_information_chat_room_content);
        TextView tvChatRoomAddress = (TextView) findViewById(R.id.tv_information_chat_room_address);
        Button btnJoinRoom = (Button) findViewById(R.id.btn_information_chat_room_participate_room);

        String objJson = gson.toJson(roomId);
        Call loadRoomInfo = service.loadRoomInfo(objJson);
        loadRoomInfo.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String str = gson.toJson(response.body());
                ChatRoomInfoResponse chatRoomInfoResponse = gson.fromJson(str, ChatRoomInfoResponse.class);

                tvChatRoomTitle.setText(roomId + "번 방 : " +chatRoomInfoResponse.getChatRoomName());
                tvChatRoomMasterUser.setText("방장 : " + chatRoomInfoResponse.getChatRoomMasterUser());
                tvChatRoomUserCount.setText("" + chatRoomInfoResponse.getChatRoomUserCount() + " / 8");
                tvChatRoomStoreName.setText(chatRoomInfoResponse.getStoreName());
                tvChatRoomContent.setText(chatRoomInfoResponse.getChatRoomContent());
                tvChatRoomAddress.setText(chatRoomInfoResponse.getChatRoomAddress());
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        btnJoinRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinRoomData joinRoomData = new JoinRoomData(roomId, loginedId);
                String objJson = gson.toJson(joinRoomData);
                Call<Integer> goChat = service.goChatRoom(objJson);
                goChat.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.body() == 2){
                            Toast.makeText(ChatRoomInfoActivity.this, "인원 초과 입니다.", Toast.LENGTH_SHORT).show();
                        }else{
                            Intent goChatRoom = new Intent(getApplicationContext(), ChatRoomActivity.class);
                            goChatRoom.putExtra("loginedId", loginedId);
                            goChatRoom.putExtra("roomId", roomId);
                            goChatRoom.putExtra("isLogin", isLogin);
                            startActivity(goChatRoom);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        });








    }
}
