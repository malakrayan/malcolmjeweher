package com.example.malcolmjeweher;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.malcolmjeweher.adapters.JewelryAdapter;
import com.example.malcolmjeweher.models.jewelryitem;

import java.util.ArrayList;
import java.util.List;

public class Main_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Malcolm Jeweler");
        }
        // --

        RecyclerView recyclerView = findViewById(R.id.jewelry_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.GridLayoutManager(this, 2));

        List<jewelryitem> items = new ArrayList<>();
        items.add(new jewelryitem(1, "عقد ملكي", "1200$", R.drawable.ring2));
        items.add(new jewelryitem(2, "عقد ذهب", "2500$", R.drawable.ring3));
        items.add(new jewelryitem(3, "عقد وردة", "800$", R.drawable.ring6));
        items.add(new jewelryitem(4, "خاتم ألماس", "1200$", R.drawable.ring4));
        items.add(new jewelryitem(5, "عقد ذهب", "2500$", R.drawable.ring5));
        items.add(new jewelryitem(6, "سوار أنيق", "800$", R.drawable.gold));

        JewelryAdapter adapter = new JewelryAdapter(items);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_saved_list) {

            Intent intent = new Intent(Main_view.this, SavedPurchasesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}