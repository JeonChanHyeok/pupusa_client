package com.example.servertest;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("UserID")
    String UserID;

    @SerializedName("UserPW")
    String UserPW;

    public LoginData(String userID, String userPW) {
        this.UserID = userID;
        this.UserPW = userPW;
    }
}
