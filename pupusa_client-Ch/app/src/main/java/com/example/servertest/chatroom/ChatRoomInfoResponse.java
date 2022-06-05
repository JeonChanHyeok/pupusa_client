package com.example.servertest.chatroom;

public class ChatRoomInfoResponse {
    private String chatRoomName;
    private String chatRoomMasterUser;
    private int chatRoomUserCount;
    private String chatRoomContent;
    private String storeCategory;
    private String storeName;
    private String chatRoomAddress;

    public String getChatRoomAddress() {
        return chatRoomAddress;
    }

    public void setChatRoomAddress(String chatRoomAddress) {
        this.chatRoomAddress = chatRoomAddress;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public String getChatRoomMasterUser() {
        return chatRoomMasterUser;
    }

    public void setChatRoomMasterUser(String chatRoomMasterUser) {
        this.chatRoomMasterUser = chatRoomMasterUser;
    }

    public int getChatRoomUserCount() {
        return chatRoomUserCount;
    }

    public void setChatRoomUserCount(int chatRoomUserCount) {
        this.chatRoomUserCount = chatRoomUserCount;
    }

    public String getChatRoomContent() {
        return chatRoomContent;
    }

    public void setChatRoomContent(String chatRoomContent) {
        this.chatRoomContent = chatRoomContent;
    }

    public String getStoreCategory() {
        return storeCategory;
    }

    public void setStoreCategory(String storeCategory) {
        this.storeCategory = storeCategory;
    }
}
