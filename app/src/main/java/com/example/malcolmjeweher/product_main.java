package com.example.malcolmjeweher;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.malcolmjeweher.R;
import com.example.malcolmjeweher.Fragment.ProductDetailsFragment;
import com.example.malcolmjeweher.models.product;

public class product_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);

        if (findViewById(R.id.fragment_container) != null && savedInstanceState == null) {

            // Create a dummy product to demonstrate
            product dummyProduct = new product(
                    "Gold Designer Ring",
                    2500.00,
                    "This exquisite gold ring is crafted with precision and features a unique design, perfect for special occasions or as an everyday statement piece. Made from 24K pure gold. Weight: 5.2g.",
                    R.drawable.gold,
                    4.5f
            );


            ProductDetailsFragment detailsFragment = ProductDetailsFragment.newInstance(dummyProduct);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, detailsFragment);
            fragmentTransaction.commit();
        }
    }
}