package com.example.detail_order;

import java.util.ArrayList;

public class detailOrderGroup {
    public ArrayList<String> child;
    public String groupName;
    detailOrderGroup(String name){
        groupName = name;
        child = new ArrayList<String>();
    }
}


