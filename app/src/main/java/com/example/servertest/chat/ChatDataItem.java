package com.example.servertest.chat;

public class ChatDataItem {
    private String content;
    private String name;
    private int viewType;

    public ChatDataItem(String content, String name, int viewType) {
        this.content = content;
        this.name = name;
        this.viewType = viewType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
