package com.example.servertest.login;

public class User {
    private String userId;

    private String userPw;

    private String userName;

    private String userPhoneNumber;

    private String userAddress;

    private boolean userSmsChk;

    private boolean userPushChk;

    private int userGrade;

    private boolean deleted;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isUserSmsChk() {
        return userSmsChk;
    }

    public void setUserSmsChk(boolean userSmsChk) {
        this.userSmsChk = userSmsChk;
    }

    public boolean isUserPushChk() {
        return userPushChk;
    }

    public void setUserPushChk(boolean userPushChk) {
        this.userPushChk = userPushChk;
    }

    public int getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(int userGrade) {
        this.userGrade = userGrade;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
