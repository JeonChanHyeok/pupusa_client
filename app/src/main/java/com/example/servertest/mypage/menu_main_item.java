package com.example.capston;

import android.graphics.drawable.Drawable;

import androidx.drawerlayout.widget.DrawerLayout;

public class menu_main_item {
    private String menu_name;
    private int price;
    private Drawable image;

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
