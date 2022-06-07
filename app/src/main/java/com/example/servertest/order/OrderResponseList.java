package com.example.servertest.order;

import java.util.List;

public class OrderResponseList {
    List<OrderResponse> orderResponseList;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<OrderResponse> getOrderResponseList() {
        return orderResponseList;
    }

    public void setOrderResponseList(List<OrderResponse> orderResponseList) {
        this.orderResponseList = orderResponseList;
    }
}
