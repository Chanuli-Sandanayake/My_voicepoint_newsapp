package com.example.voicepoint;

public class NewsItem {
    public String title;
    public String category;
    public String imageUrl;
    public String date;


    //newsdetails
    public String description;


    public NewsItem() {}

    public NewsItem(String title, String category, String imageUrl, String date) {
        this.title = title;
        this.category = category;
        this.imageUrl = imageUrl;
        this.date = date;

        //newsdetails
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getImageUrl() { return imageUrl; }
    public String getDate() { return date; }


    //newsdetails
    public String getDescription() { return description; }
}
