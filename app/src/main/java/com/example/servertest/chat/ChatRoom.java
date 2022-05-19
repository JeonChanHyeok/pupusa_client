package com.example.servertest.chat;

import com.google.gson.annotations.SerializedName;

public class ChatRoom {
    @SerializedName("chatRoomId")
    private Long chatRoomId;

    @SerializedName("chatRoomName")
    private String chatRoomName;

    public ChatRoom() {
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public ChatRoom(Long chatRoomId, String chatRoomName) {
        this.chatRoomId = chatRoomId;
        this.chatRoomName = chatRoomName;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }
}
