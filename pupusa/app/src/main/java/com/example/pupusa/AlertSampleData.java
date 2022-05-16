package com.example.pupusa;

public class AlertSampleData {
    private String title;
    private String contents;
    private String date;

    public AlertSampleData(String title, String contents, String date){
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public String getTitle() { return this.title; }

    public String getMyContents() { return this.contents; }

    public String getDate() { return this.date; }
}
