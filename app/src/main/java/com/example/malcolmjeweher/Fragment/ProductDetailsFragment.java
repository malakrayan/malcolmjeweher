package com.example.malcolmjeweher.Fragment;

import android.app.Activity;
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
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.malcolmjeweher.DatabaseHelper;
import com.example.malcolmjeweher.R;
import com.example.malcolmjeweher.models.product;

import org.json.JSONException;

public class ProductDetailsFragment extends Fragment {

    private product currentProduct;
    private TextView productName, productPrice, productDescription;
    private ImageView productImage;
    private RatingBar productRating;
    private Button btnAddToCart;

    public static ProductDetailsFragment newInstance(product p) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("selected_product", p);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentProduct = (product) getArguments().getSerializable("selected_product");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product_detail, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(v -> {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            });
        }

        productImage = view.findViewById(R.id.product_main_image);
        productName = view.findViewById(R.id.product_name);
        productPrice = view.findViewById(R.id.product_price);
        productDescription = view.findViewById(R.id.product_description);
        productRating = view.findViewById(R.id.product_rating);
        btnAddToCart = view.findViewById(R.id.btn_add_to_cart);
        Button btnAddToWishlist = view.findViewById(R.id.btn_add_to_wishlist);

        if (currentProduct != null) {
            productName.setText(currentProduct.getName());
            productPrice.setText("SAR " + currentProduct.getPrice());
            productImage.setImageResource(currentProduct.getImageResourceId());
            productRating.setRating(currentProduct.getRating());
            productDescription.setText("جاري جلب التفاصيل...");

            fetchProductDetailsFromAPI(currentProduct.getId());
        }

        if (btnAddToCart != null) {
            btnAddToCart.setOnClickListener(v -> addToCart());
        }

        return view;

    }

    private void fetchProductDetailsFromAPI(int id) {

        String url = "https://fakestoreapi.com/products/" + id;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String description = response.getString("description");
                        productDescription.setText(description);
                    } catch (JSONException e) {
                        productDescription.setText(currentProduct.getDescription());
                    }
                },
                error -> {

                    productDescription.setText(currentProduct.getDescription());
                }
        );
        Volley.newRequestQueue(getContext()).add(request);
    }

    private void addToCart() {
        if (currentProduct != null) {
            DatabaseHelper db = new DatabaseHelper(getContext());
            boolean success = db.insertData(
                    "User_Default",
                    currentProduct.getName(),
                    currentProduct.getPrice(),
                    currentProduct.getImageResourceId()
            );

            if (success) {
                Toast.makeText(getContext(), "تمت إضافة " + currentProduct.getName() + " إلى السلة! 💍", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "فشلت عملية الإضافة", Toast.LENGTH_SHORT).show();
            }
        }
    }
}