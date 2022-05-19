package com.example.servertest.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("userId")
    String userId;

    @SerializedName("userPw")
    String userPw;

    public LoginData(String userID, String userPW) {
        this.userId = userID;
        this.userPw = userPW;
    }
}
