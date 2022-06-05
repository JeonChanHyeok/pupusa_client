package com.example.servertest.store;

import com.google.gson.annotations.SerializedName;

// 정보 요청 시 보낼 데이터
public class StoreData {

    @SerializedName("storeId")
    private Long storeId;

    @SerializedName("storeName")
    private String storeName;

    public StoreData(Long storeId, String storeName) {
        this.storeId = storeId;
        this.storeName = storeName;
    }
}
