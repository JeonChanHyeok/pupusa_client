package com.example.servertest.mypage;

import android.graphics.drawable.Drawable;

public class order_history_item {
    private Drawable shop_img;  // 가게 사진
    private String shop_name;  //가게 이름
    private String date; //주문 날짜
    private String menu; //사용자가 주문한 메뉴
    private int price; //메뉴에 대한 가격

    public Drawable getShop_img() { return shop_img; }

    public void setShop_img(Drawable shop_img) {
        this.shop_img = shop_img;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
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
