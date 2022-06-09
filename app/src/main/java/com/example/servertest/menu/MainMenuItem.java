package com.example.servertest.menu;

import android.graphics.drawable.Drawable;

public class MainMenuItem {
    private String menuName;
    private int price;
    private Drawable image;
    private Long menuId;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
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
