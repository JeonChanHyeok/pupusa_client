package com.example.servertest.chat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatRoomList {
    @SerializedName("chatroomlist")
    private List<ChatRoom> chatroomlist;

    public List<ChatRoom> getchatroomlist() {
        return chatroomlist;
    }
}
