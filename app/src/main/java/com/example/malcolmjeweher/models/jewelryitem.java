package com.example.malcolmjeweher.models;

import com.example.malcolmjeweher.item_jewelry;

public class jewelryitem extends item_jewelry {
        private int id;
        private String name;
        private int imageResId;
        private int price;

        public jewelryitem(int id, String name, int imageResId, int price) {
            this.id = id;
            this.name = name;
            this.imageResId = imageResId;
            this.price = price;
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

        public int getPrice() {

            return price;
        }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}

