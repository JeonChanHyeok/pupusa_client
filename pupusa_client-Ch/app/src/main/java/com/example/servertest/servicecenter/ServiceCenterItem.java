package com.example.servertest.servicecenter;

import android.graphics.drawable.Drawable;

public class ServiceCenterItem {
    private Drawable icon;
    private String title;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
