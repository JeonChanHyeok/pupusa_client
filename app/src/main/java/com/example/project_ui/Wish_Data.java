package com.example.project_ui;

public class Wish_Data {
    private int poster;
    private int poster2;
    private int poster3;

    private String store;
    private String star_score ;

    public Wish_Data(int poster, int poster2, int poster3, String store, String star_score){
        this.poster = poster;

        this.poster2 = poster2;

        this.poster3 = poster3;

        this.store = store;

        this.star_score = star_score;

    }

    public int getPoster()
    {
        return this.poster;
    }

    public int getPoster2()
    {
        return this.poster2;
    }

    public int getPoster3()
    {
        return this.poster3;
    }

    public String getStore()
    {
        return this.store;
    }

    public String getstarscore()
    {
        return this.star_score;
    }
}