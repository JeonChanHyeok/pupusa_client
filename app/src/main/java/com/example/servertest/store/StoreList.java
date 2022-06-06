package com.example.servertest.store;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoreList {

    @SerializedName("storeList")
    private List<Store> storeList;

    public List<Store> getStoreList() { return storeList; }

}
