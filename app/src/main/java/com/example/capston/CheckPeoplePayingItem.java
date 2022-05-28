package com.example.capston;

import java.util.ArrayList;

public class CheckPeoplePayingItem {
    public ArrayList<String> child;
    public String parentName;

    CheckPeoplePayingItem(String parentName){
        this.parentName = parentName;
        child = new ArrayList<String>();
    }
}
