package com.example.servertest.chat;

import com.google.gson.annotations.SerializedName;

public class ChatMessage {
    @SerializedName("messageText")
    private String messageText;

    @SerializedName("chatRoomId")
    private Long chatRoomId;

    @SerializedName("userId")
    private String userId;

    @SerializedName("messageTime")
    private String messageTime;

    public ChatMessage(String messageText, Long chatRoomId, String userId, String messageTime) {
        this.messageText = messageText;
        this.chatRoomId = chatRoomId;
        this.userId = userId;
        this.messageTime = messageTime;
    }
}
