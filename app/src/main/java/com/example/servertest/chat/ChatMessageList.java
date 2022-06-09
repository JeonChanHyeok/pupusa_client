package com.example.servertest.chat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatMessageList {
    @SerializedName("chatMessageList")
    private List<ChatMessageResponse> chatMessageList;
    private int chatRoomState;

    public int getChatRoomState() {
        return chatRoomState;
    }

    public List<ChatMessageResponse> getChatMessageList() {
        return chatMessageList;
    }
}
