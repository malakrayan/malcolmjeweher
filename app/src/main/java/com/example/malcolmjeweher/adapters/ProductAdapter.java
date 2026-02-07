package com.example.malcolmjeweher.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.malcolmjeweher.Fragment.ProductDetailsFragment;
import com.example.malcolmjeweher.product_main;
import com.example.malcolmjeweher.R;
import com.example.malcolmjeweher.item_jewelry;
import com.example.malcolmjeweher.models.jewelryitem;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<jewelryitem> jewelryitem;
    private LayoutInflater layoutInflater;
    public ProductAdapter(Context context, ArrayList<jewelryitem> jewelryList) {
        this.context = context;
        this.jewelryitem= jewelryList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return jewelryitem.size();
    }

    @Override
    public Object getItem(int position) {
        return jewelryitem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_item_jewelry, parent, false);
        }

        final item_jewelry currentItem = jewelryitem.get(position);


        ImageView imageview_jewelry = convertView.findViewById(R.id.img_jewelry);
        TextView nameTextView = convertView.findViewById(R.id.tv_jewelry_name);
        TextView priceTextView = convertView.findViewById(R.id.price);
        Button addButton = convertView.findViewById(R.id.bt_sign);

        imageview_jewelry.setImageResource(currentItem.getImageResId());
        nameTextView.setText(currentItem.getName());
        priceTextView.setText(String.valueOf(currentItem.getPrice()) + "$");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, product_main.class);
                intent.putExtra("PRODUCT_NAME", currentItem.getName());
                intent.putExtra("PRODUCT_PRICE", currentItem.getPrice());
                intent.putExtra("PRODUCT_IMAGE", currentItem.getImageResId());
                context.startActivity(intent);
            }
        });

        return convertView;
    }




    }