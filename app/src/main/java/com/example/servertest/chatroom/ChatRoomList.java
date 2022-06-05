package com.example.servertest.chatroom;

import com.example.servertest.chatroom.ChatRoom;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatRoomList {
    @SerializedName("chatRoomResponses")
    private List<ChatRoomResponse> chatRoomResponses;

    public List<ChatRoomResponse> getchatroomlist() {
        return chatRoomResponses;
    }
}
