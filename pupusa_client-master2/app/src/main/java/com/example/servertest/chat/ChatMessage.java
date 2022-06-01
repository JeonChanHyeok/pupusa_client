package com.example.servertest.chat;

import com.google.gson.annotations.SerializedName;

public class ChatMessage {
    @SerializedName("message")
    private String message;

    @SerializedName("roomId")
    private String roomId;

    @SerializedName("userId")
    private String userId;

    public ChatMessage(String msg, String roomId, String userId) {
        this.message = msg;
        this.roomId = roomId;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
