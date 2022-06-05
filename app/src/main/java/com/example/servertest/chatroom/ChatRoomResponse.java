package com.example.servertest.chatroom;

import com.google.gson.annotations.SerializedName;

public class ChatRoomResponse {
    @SerializedName("chatRoomId")
    private Long chatRoomId;

    @SerializedName("chatRoomName")
    private String chatRoomName;

    @SerializedName("chatRoomStoreName")
    private String chatRoomStoreName;

    @SerializedName("inRoomUserCount")
    private int inRoomUserCount;

    @SerializedName("chatRoomAddress")
    private String chatRoomAddress;

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public String getChatRoomStoreName() {
        return chatRoomStoreName;
    }

    public void setChatRoomStoreName(String chatRoomStoreName) {
        this.chatRoomStoreName = chatRoomStoreName;
    }

    public int getInRoomUserCount() {
        return inRoomUserCount;
    }

    public void setInRoomUserCount(int inRoomUserCount) {
        this.inRoomUserCount = inRoomUserCount;
    }

    public String getChatRoomAddress() {
        return chatRoomAddress;
    }

    public void setChatRoomAddress(String chatRoomAddress) {
        this.chatRoomAddress = chatRoomAddress;
    }
}
