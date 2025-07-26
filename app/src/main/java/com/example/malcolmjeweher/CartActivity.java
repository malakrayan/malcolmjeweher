package com.example.malcolmjeweher;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView cartItemsRecyclerView;
    EditText etDiscountCoupon;
    Button btnApplyCoupon;
    TextView tvItemTotalValue, tvShippingValue, tvDiscountValue, tvGrandTotalValue;
    Button btnContinueShopping, btnProceedToPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.toolbar_cart);
        etDiscountCoupon = findViewById(R.id.et_discount_coupon);
        btnApplyCoupon = findViewById(R.id.btn_apply_coupon);
        tvItemTotalValue = findViewById(R.id.tv_item_total_value);
        tvShippingValue = findViewById(R.id.tv_shipping_value);
        tvDiscountValue = findViewById(R.id.tv_discount_value);
        tvGrandTotalValue = findViewById(R.id.tv_grand_total_value);
        btnContinueShopping = findViewById(R.id.btn_continue_shopping);
        btnProceedToPay = findViewById(R.id.btn_proceed_to_pay);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("My Cart");
        }


        btnApplyCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String couponCode = etDiscountCoupon.getText().toString().trim();
                if (couponCode.isEmpty()) {
                    Toast.makeText(CartActivity.this, "الرجاء إدخال رمز القسيمة", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        btnContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

            }




    }
