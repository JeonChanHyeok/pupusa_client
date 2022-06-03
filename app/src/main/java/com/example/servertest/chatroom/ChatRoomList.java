package com.example.servertest.chatroom;

import com.example.servertest.chatroom.ChatRoom;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatRoomList {
    @SerializedName("chatroomlist")
    private List<ChatRoom> chatroomlist;

    public List<ChatRoom> getchatroomlist() {
        return chatroomlist;
    }
}
