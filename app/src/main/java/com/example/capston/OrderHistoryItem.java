package com.example.capston;

import android.graphics.drawable.Drawable;

public class OrderHistoryItem {
    private Drawable storeImg;  // 가게 사진
    private String storeName;  //가게 이름
    private String date; //주문 날짜
    private String menu; //사용자가 주문한 메뉴
    private int price; //메뉴에 대한 가격

    public Drawable getStoreImg() { return storeImg; }

    public void setStoreImg(Drawable storeImg) {
        this.storeImg = storeImg;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
