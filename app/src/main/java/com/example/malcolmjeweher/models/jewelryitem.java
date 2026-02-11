package com.example.malcolmjeweher.models;

import com.example.malcolmjeweher.item_jewelry;
import java.io.Serializable;
public class jewelryitem extends item_jewelry implements Serializable {
    private int id;
    private String name;
    private String price;
    private int imageResId;

    public jewelryitem(int id, String name, String price, int imageResId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
