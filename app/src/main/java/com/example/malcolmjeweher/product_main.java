package com.example.malcolmjeweher;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.malcolmjeweher.Fragment.ProductDetailsFragment;
import com.example.malcolmjeweher.models.jewelryitem;
import com.example.malcolmjeweher.models.product;

public class product_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);

        if (findViewById(R.id.fragment_container) != null && savedInstanceState == null) {

            jewelryitem selectedItem = (jewelryitem) getIntent().getSerializableExtra("SELECTED_ITEM");

            if (selectedItem != null) {
                double priceValue = 0.0;
                try {
                    priceValue = Double.parseDouble(selectedItem.getPrice());
                } catch (Exception e) {
                    priceValue = 0.0;
                }

                product selectedProduct = new product(
                        selectedItem.getId(),
                        selectedItem.getName(),
                        selectedItem.getPrice(),
                        "جاري تحميل الوصف من الـ API للمنتج رقم: " + selectedItem.getId(),
                        selectedItem.getImageResId(),
                        4.5f
                );

                ProductDetailsFragment detailsFragment = ProductDetailsFragment.newInstance(selectedProduct);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, detailsFragment)
                        .commit();
            }
        }
    }
}