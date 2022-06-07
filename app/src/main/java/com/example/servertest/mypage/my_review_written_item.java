package com.example.servertest.mypage;

import android.graphics.drawable.Drawable;

public class my_review_written_item {
    private String store_name; //가게이름
    private Drawable icon; //리뷰 별점
    private Drawable image; //메뉴 사진
    private String date; //날짜
    private String content; //리뷰 내용
    private String menu; //메뉴

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
