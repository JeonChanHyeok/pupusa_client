package com.example.servertest.chat;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;

import java.util.ArrayList;

public class ChatLobby extends AppCompatActivity {
    ArrayList<String> chatRoomList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_lobby);




    }
}
