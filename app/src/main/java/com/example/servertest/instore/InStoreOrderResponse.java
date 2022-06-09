package com.example.servertest.instore;

public class InStoreOrderResponse {
    private Long payOrderId;
    private String address;
    private String menusName;
    private String date;
    private int price;
    private int state;

    public int getState() {
        return state;
    }

    public Long getPayOrderId() {
        return payOrderId;
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
