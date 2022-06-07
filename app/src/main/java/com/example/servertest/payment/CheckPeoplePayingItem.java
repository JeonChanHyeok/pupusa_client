package com.example.servertest.payment;

import java.util.ArrayList;

public class CheckPeoplePayingItem {
    public ArrayList<String> child;
    public ArrayList<Boolean> payChk;
    public String parentName;

    CheckPeoplePayingItem(String parentName){
        this.parentName = parentName;
        child = new ArrayList<String>();
        payChk = new ArrayList<>();
    }
}
