package com.example.malcolmjeweher;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.ArrayList;

import com.example.malcolmjeweher.models.jewelryitem;
import com.example.malcolmjeweher.adapters.ProductAdapter;

public class Main_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_view);

        ArrayList<jewelryitem> jewelryList = new ArrayList<>();
        jewelryList.add(new jewelryitem(1, "خاتم ذهب", R.drawable.gold, 500));
        jewelryList.add(new jewelryitem(2, "طقم ذهب فاخر", R.drawable.ring2, 250));
        jewelryList.add(new jewelryitem(3, "قلادة ذهبية", R.drawable.ring3, 1200));
        jewelryList.add(new jewelryitem(1, "شبكة خواتم", R.drawable.ring4, 1500));
        jewelryList.add(new jewelryitem(2, "طقم فاخر", R.drawable.ring5, 1250));
        jewelryList.add(new jewelryitem(3, "طقم الوردة", R.drawable.ring6, 1200));

        GridView gridView = findViewById(R.id.jewelry_grid);
        ProductAdapter adapter = new ProductAdapter(this, jewelryList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jewelryitem selectedItem = jewelryList.get(position);

                Intent intent = new Intent(Main_view.this, item_jewelry.class);

                intent.putExtra("PRODUCT_NAME", selectedItem.getName());
                intent.putExtra("PRODUCT_PRICE", selectedItem.getPrice());
                intent.putExtra("PRODUCT_IMAGE", selectedItem.getImageResId());

                startActivity(intent);
            }
        });
    }
}

