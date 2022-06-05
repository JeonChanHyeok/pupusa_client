package com.example.capston;

import android.graphics.drawable.Drawable;

public class MyReviewWrittenItem {
    private String storeName; //가게이름
    private Drawable reviewRate; //리뷰 별점
    private Drawable image; //메뉴 사진
    private String date; //날짜
    private String content; //리뷰 내용
    private String menu; //메뉴
    private float ratingStar;

    public float getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(float ratingStar) {
        this.ratingStar = ratingStar;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Drawable getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(Drawable reviewRate) {
        this.reviewRate = reviewRate;
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
