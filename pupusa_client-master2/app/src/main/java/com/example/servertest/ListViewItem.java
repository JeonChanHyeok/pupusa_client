package com.example.servertest;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable post;
    private String title;
    private String descript;
    private String pickup_time;
    private String pickup_charge;

    //private 인한 get/set으로 값 받기
    public Drawable getPost() {
        return post;
    }

    public void setPost(Drawable post) {
        this.post = post;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public String getPickup_charge() {
        return pickup_charge;
    }

    public void setPickup_charge(String pickup_charge) { this.pickup_charge = pickup_charge; }
}
