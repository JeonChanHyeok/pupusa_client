package com.example.servertest;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("UserID")
    private String UserID;

    @SerializedName("UserPW")
    private String UserPW;

    public JoinData(String userID, String userPW) {
        this.UserID = userID;
        this.UserPW = userPW;
    }
}
