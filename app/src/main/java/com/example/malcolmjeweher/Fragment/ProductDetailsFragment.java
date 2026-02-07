package com.example.malcolmjeweher.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.malcolmjeweher.R;
import com.example.malcolmjeweher.models.product;
import com.example.malcolmjeweher.CartActivity;

public class ProductDetailsFragment extends Fragment {

    private static final String ARG_PRODUCT = "product";
    private product product;

    public static ProductDetailsFragment newInstance(product product) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putString("name", product.getName());
        args.putDouble("price", product.getPrice());
        args.putString("description", product.getDescription());
        args.putInt("imageResId", product.getImageResourceId());
        args.putFloat("rating", product.getRating());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String name = getArguments().getString("name");
            double price = getArguments().getDouble("price");
            String description = getArguments().getString("description");
            int imageResId = getArguments().getInt("imageResId");
            float rating = getArguments().getFloat("rating");
            product = new product(name, price, description, imageResId, rating);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product_detail, container, false);

        ImageView productMainImage = view.findViewById(R.id.product_main_image);
        TextView productName = view.findViewById(R.id.product_name);
        TextView productPrice = view.findViewById(R.id.product_price);
        RatingBar productRating = view.findViewById(R.id.product_rating);
        TextView productDescription = view.findViewById(R.id.product_description);
        Button btnAddToCart = view.findViewById(R.id.btn_add_to_cart);
        Button btnAddToWishlist = view.findViewById(R.id.btn_add_to_wishlist);

        if (product != null) {
            productMainImage.setImageResource(product.getImageResourceId());
            productName.setText(product.getName());
            productPrice.setText("SAR " + String.format("%.2f", product.getPrice()));
            productRating.setRating(product.getRating());
            productDescription.setText(product.getDescription());
        }

        btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Added to cart", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), CartActivity.class);
            startActivity(intent);
        });

        btnAddToWishlist.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Added to wishlist", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}