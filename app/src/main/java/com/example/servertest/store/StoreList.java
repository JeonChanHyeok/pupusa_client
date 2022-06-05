package com.example.servertest.store;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoreList {

    @SerializedName("storeList")
    private List<StoreList> storeList;

    public List<StoreList> getStoreList() { return storeList; }

}
