package com.example.malcolmjeweher.models;

public class SavedPurchaseModel {
    private int id;
    private String userName;
    private String itemName;
    private String price;
    private int imageResId;

    public SavedPurchaseModel(int id, String userName, String itemName, String price, int imageResId) {
        this.id = id;
        this.userName = userName;
        this.itemName = itemName;
        this.price = price;
        this.imageResId = imageResId;
    }


    public int getId() { return id; }
    public String getUserName() { return userName; }
    public String getItemName() { return itemName; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }
}
