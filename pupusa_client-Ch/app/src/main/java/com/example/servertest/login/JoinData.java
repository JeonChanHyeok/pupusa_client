package com.example.servertest.login;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("userId")
    private String userId;

    @SerializedName("userPw")
    private String userPw;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userPhoneNumber")
    private String userPhoneNumber;

    @SerializedName("userAddress")
    private String userAddress;

    @SerializedName("userSmsChk")
    private boolean userSmsChk;

    @SerializedName("userPushChk")
    private boolean userPushChk;

    @SerializedName("userGrade")
    private int userGrade;

    @SerializedName("deleted")
    private boolean deleted;

    public JoinData(String userId, String userPw, String userName) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userPhoneNumber = "";
        this.userAddress = "";
        this.userSmsChk = false;
        this.userPushChk = false;
        this.userGrade = 0;
        this.deleted = false;
    }
}
