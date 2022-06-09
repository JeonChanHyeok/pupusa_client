package com.example.servertest.menu;

public class MenuResponse {
    private Long menuId;
    private String name;
    private int price;
    private String category;
    private String info;

    public Long getMenuId() {
        return menuId;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
