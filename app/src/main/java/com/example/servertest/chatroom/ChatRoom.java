package com.example.servertest.chatroom;

import com.google.gson.annotations.SerializedName;

public class ChatRoom {
    @SerializedName("userId")
    private String userId;

    @SerializedName("chatRoomName")
    private String chatRoomName;

    @SerializedName("chatRoomAddress")
    private String chatRoomAddress;

    @SerializedName("chatRoomStoreId")
    private Long chatRoomStoreId;

    @SerializedName("chatRoomInfo")
    private String chatRoomInfo;

    public ChatRoom(String userId, String chatRoomName, String chatRoomAddress, Long chatRoomStoreId, String chatRoomInfo) {
        this.userId = userId;
        this.chatRoomName = chatRoomName;
        this.chatRoomAddress = chatRoomAddress;
        this.chatRoomStoreId = chatRoomStoreId;
        this.chatRoomInfo = chatRoomInfo;
    }

    public String getUserId() {
        return userId;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public String getChatRoomAddress() {
        return chatRoomAddress;
    }

    public Long getChatRoomStoreId() {
        return chatRoomStoreId;
    }

    public String getChatRoomInfo() {
        return chatRoomInfo;
    }
}

/*
* public void InitializeChattingData()
    {
        chattingDataList = new ArrayList<ChatMessage>();
        String roomIdData = roomId;
        Call chat = service.loadChatData(roomIdData);
        chat.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String chatlist = new Gson().toJson(response.body());
                chatMessageList = new Gson().fromJson(chatlist,ChatMessageList.class);
                for(ChatMessage c:chatMessageList.getChatMessageList()){
                    chattingDataList.add(new ChatMessage(c.getMessage(), c.getRoomId(), c.getUserId()));
                }
                mAdapter = new ChatRoomAdapter(getApplicationContext(),chattingDataList);
                listView.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });
    }
*
*
*
*
*
* */

