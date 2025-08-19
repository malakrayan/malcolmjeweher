package com.example.malcolmjeweher;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import com.example.malcolmjeweher.models.jewelryitem;
import java.util.ArrayList;
import com.example.malcolmjeweher.adapters.ProductAdapter;

public class item_jewelry extends AppCompatActivity {
    private int id;
    private String name;
    private int imageResId;
    private int price;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_jewelry);
        Intent intent = getIntent();
        if (intent != null) {
            String productName = intent.getStringExtra("PRODUCT_NAME");
            String productDescription = intent.getStringExtra("PRODUCT_DESCRIPTION");
            int productImageResId = intent.getIntExtra("PRODUCT_IMAGE", 0);


        }
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return  name;
    }

    public int getPrice() {
        return price;
    }
}



