package com.example.servertest.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("UserId")
    private String UserId;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return UserId;
    }

}
