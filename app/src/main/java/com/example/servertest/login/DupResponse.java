package com.example.servertest.login;

import com.google.gson.annotations.SerializedName;

public class DupResponse {
    @SerializedName("code")
    private int code;

    public int getCode() {
        return code;
    }
}
