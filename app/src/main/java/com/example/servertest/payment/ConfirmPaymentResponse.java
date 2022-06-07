package com.example.servertest.payment;

import java.util.List;

public class ConfirmPaymentResponse {
    private List<Long> menuId;
    private List<String> userId;
    private List<String> userName;
    private List<String> menuName;
    private List<Integer> menuPrice;
    private List<Boolean> isPay;
    private String masterUserId;
    private String roomStoreName;
    private String roomPickUpAddress;
    private String unLuckyManId;
    private int roomDeliPee;
    private int userCount;

    public String getUnLuckyManId() {
        return unLuckyManId;
    }

    public void setUnLuckyManId(String unLuckyManId) {
        this.unLuckyManId = unLuckyManId;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public String getRoomStoreName() {
        return roomStoreName;
    }

    public void setRoomStoreName(String roomStoreName) {
        this.roomStoreName = roomStoreName;
    }

    public String getRoomPickUpAddress() {
        return roomPickUpAddress;
    }

    public void setRoomPickUpAddress(String roomPickUpAddress) {
        this.roomPickUpAddress = roomPickUpAddress;
    }

    public int getRoomDeliPee() {
        return roomDeliPee;
    }

    public void setRoomDeliPee(int roomDeliPee) {
        this.roomDeliPee = roomDeliPee;
    }

    public String getMasterUserId() {
        return masterUserId;
    }

    public void setMasterUserId(String masterUserId) {
        this.masterUserId = masterUserId;
    }

    public List<Boolean> getIsPay() {
        return isPay;
    }

    public void setIsPay(List<Boolean> isPay) {
        this.isPay = isPay;
    }

    public List<Long> getMenuId() {
        return menuId;
    }

    public void setMenuId(List<Long> menuId) {
        this.menuId = menuId;
    }

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    public List<String> getUserName() {
        return userName;
    }

    public void setUserName(List<String> userName) {
        this.userName = userName;
    }

    public List<String> getMenuName() {
        return menuName;
    }

    public void setMenuName(List<String> menuName) {
        this.menuName = menuName;
    }

    public List<Integer> getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(List<Integer> menuPrice) {
        this.menuPrice = menuPrice;
    }
}
