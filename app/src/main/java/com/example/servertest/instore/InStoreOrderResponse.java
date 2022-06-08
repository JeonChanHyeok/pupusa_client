package com.example.servertest.instore;

public class InStoreOrderResponse {
    private Long roomId;
    private String address;
    private String menusName;
    private String date;
    private int price;

    public Long getRoomId() {
        return roomId;
    }

    public String getAddress() {
        return address;
    }

    public String getMenusName() {
        return menusName;
    }

    public String getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }
}
