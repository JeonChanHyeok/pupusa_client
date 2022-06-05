package com.example.servertest.chat;

public class ChatMessageResponse {
    private String messageText;
    private String userId;
    private String messageTime;
    private String userName;

    public String getMessageText() {
        return messageText;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public String getUserName() {
        return userName;
    }
}
