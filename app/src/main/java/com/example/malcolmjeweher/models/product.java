package com.example.malcolmjeweher.models;

import java.io.Serializable;

public class product implements Serializable {
    private int id;
    private String name;
    private  String price;
    private String description;
    private int imageResourceId;
    private float rating;


    public product(int id, String name,String price, String description, int imageResourceId, float rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public float getRating() {
        return rating;
    }
}

