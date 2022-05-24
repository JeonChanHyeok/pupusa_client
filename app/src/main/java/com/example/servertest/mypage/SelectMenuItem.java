package com.example.capston;

import android.graphics.drawable.Drawable;

public class SelectMenuItem {
    private String menuName;
    private int menuPrice;
    private String menuContent;
    private Drawable menuImg;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuContent() {
        return menuContent;
    }

    public void setMenuContent(String menuContent) {
        this.menuContent = menuContent;
    }

    public Drawable getMenuImg() {
        return menuImg;
    }

    public void setMenuImg(Drawable menuImg) {
        this.menuImg = menuImg;
    }
}
