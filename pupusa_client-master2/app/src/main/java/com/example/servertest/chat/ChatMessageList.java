package com.example.servertest.chat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatMessageList {
    @SerializedName("chatMessageList")
    private List<ChatMessage> chatMessageList;

    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }
}
