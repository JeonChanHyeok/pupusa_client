package com.example.servertest.store;

import com.google.gson.annotations.SerializedName;

public class Store {
    // 생성자 시작
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

    public Store(Long storeId, String storeName, String storeAddress, String storeImage, String storeInfo, int delieveryFee, String category) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeImage = storeImage;
        this.storeInfo = storeInfo;
        this.delieveryFee = delieveryFee;
        this.category = category;
    }
    // 생성자 끝

    // getter 시작
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

    // getter 시작

    // setter 시작

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public void setStoreInfo(String storeInfo) {
        this.storeInfo = storeInfo;
    }

    public void setDelieveryFee(int delieveryFee) {
        this.delieveryFee = delieveryFee;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // setter 끝
}
