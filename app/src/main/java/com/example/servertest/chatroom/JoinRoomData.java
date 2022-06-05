package com.example.servertest.chatroom;

public class JoinRoomData {
    Long roomId;
    String userId;

    public JoinRoomData(Long roomId, String userId) {
        this.roomId = roomId;
        this.userId = userId;
    }
}
