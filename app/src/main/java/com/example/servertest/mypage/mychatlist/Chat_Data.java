package com.example.servertest.mypage.mychatlist;

public class Chat_Data {
    private int poster;
    private String store;
    private String store_location;
    private String store_content;

    public Chat_Data(int poster, String store, String store_location, String store_content){
        this.poster = poster;

        this.store = store;

        this.store_location = store_location;

        this.store_content = store_content;

    }

    public int getPoster()
    {
        return this.poster;
    }

    public String getStore()
    {
        return this.store;
    }

    public String store_location()
    {
        return this.store_location;
    }


    public String store_content()
    {
        return this.store_content;
    }
}