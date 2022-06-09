package com.example.servertest.stores;


import com.google.gson.annotations.SerializedName;

// 돌아올 데이터
public class StoreResponse {

    @SerializedName("storeId")
    private Long storeId;

    @SerializedName("storeName")
    private String storeName;

    @SerializedName("storeAddress")
    private String storeAddress;

    @SerializedName("storeImage")
    private String storeImage;

    @SerializedName("storeInfo")
    private String storeInfo;

    @SerializedName("delieveryFee")
    private int delieveryFee;

    @SerializedName("category")
    private String category;

    public Long getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public String getStoreInfo() {
        return storeInfo;
    }

    public int getDelieveryFee() {
        return delieveryFee;
    }

    public String getCategory() {
        return category;
    }
}
