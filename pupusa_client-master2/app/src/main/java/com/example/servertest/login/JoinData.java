package com.example.servertest.login;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("userId")
    private String userId;

    @SerializedName("userPw")
    private String userPw;

    public JoinData(String userID, String userPW) {
        this.userId = userID;
        this.userPw = userPW;
    }
}
