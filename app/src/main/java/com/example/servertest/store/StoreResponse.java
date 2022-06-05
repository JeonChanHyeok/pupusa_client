package com.example.servertest.store;


import com.google.gson.annotations.SerializedName;

// 돌아올 데이터
public class StoreResponse {

    @SerializedName("storeName")
    private String storeName;

    public String getStoreName() {
        return storeName;
    }
}
