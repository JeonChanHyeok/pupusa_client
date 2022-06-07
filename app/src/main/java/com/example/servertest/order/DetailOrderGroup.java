package com.example.servertest.order;

import java.util.ArrayList;

public class DetailOrderGroup {
    public ArrayList<String> child;
    public String groupName;
    DetailOrderGroup(String name){
        groupName = name;
        child = new ArrayList<String>();
    }
}


