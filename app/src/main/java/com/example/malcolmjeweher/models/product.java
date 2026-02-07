package com.example.malcolmjeweher.models;

public class product {
        private String name;
        private double price;
        private String description;
        private int imageResourceId;
        private float rating;

        public product(String name, double price, String description, int imageResourceId, float rating) {
            this.name = name;
            this.price = price;
            this.description = description;
            this.imageResourceId = imageResourceId;
            this.rating = rating;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
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

