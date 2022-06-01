package com.example.servertest.announcement;

import java.util.ArrayList;

public class AnnouncementGroup {
    public ArrayList<String> child;
    public String groupName;
    AnnouncementGroup(String name){
        groupName = name;
        child = new ArrayList<String>();
    }
}


