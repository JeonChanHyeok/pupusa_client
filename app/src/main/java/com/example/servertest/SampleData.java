package com.example.servertest;

public class SampleData {
    private String title;
    private String address;
    private String contents;
    private int poster;

    public SampleData(int poster, String title, String address, String contents){
        this.poster = poster;
        this.title = title;
        this.address = address;
        this.contents = contents;
    }

    public SampleData(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
    public int getPoster() { return this.poster; }

    public String getTitle()
    {
        return this.title;
    }

    public String getMyContext()
    {
        return this.contents;
    }

    public String getAddress() { return this.address;}
}
