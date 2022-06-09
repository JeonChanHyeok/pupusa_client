package com.example.servertest.instore;

import java.util.List;

public class StoreOrderContentResponse {
    private Long chatRoomId;
    private Long payOrderId;

    private String address;
    private String orderTime;
    private String orderRequest;
    private List<String> menus;
    private List<Integer> menucount;
    private List<Integer> prices;
    private int allPrice;
    private int delPee;
    private int state;

    public int getState() {
        return state;
    }

    public int getDelPee() {
        return delPee;
    }

    public List<Integer> getMenucount() {
        return menucount;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public Long getPayOrderId() {
        return payOrderId;
    }

    public String getAddress() {
        return address;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getOrderRequest() {
        return orderRequest;
    }

    public List<String> getMenus() {
        return menus;
    }

    public List<Integer> getPrices() {
        return prices;
    }

    public int getAllPrice() {
        return allPrice;
    }
}
