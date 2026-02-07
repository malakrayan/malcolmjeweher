package com.example.malcolmjeweher;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.malcolmjeweher.adapters.SavedItemsAdapter;
import com.example.malcolmjeweher.models.SavedPurchaseModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SavedPurchasesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SavedItemsAdapter adapter;
    List<SavedPurchaseModel> savedList;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_purchases);
        Toolbar toolbar = findViewById(R.id.toolbar_saved);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("المشتريات المحفوظة");
        }

        recyclerView = findViewById(R.id.saved_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);
        savedList = new ArrayList<>();
        loadData();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

// تحديد أننا حالياً في صفحة المحفوظات
        bottomNavigationView.setSelectedItemId(R.id.nav_saved);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // اذهب للصفحة الرئيسية
                 startActivity(new Intent(getApplicationContext(), Main_view.class));
                 finish();
                return true;
            } else if (id == R.id.nav_saved) {
                return true; // نحن هنا بالفعل
            } else if (id == R.id.nav_profile) {
                // اذهب لصفحة البروفايل
                return true;
            }
            return false;
        });
    }

    private void loadData() {
        Cursor cursor = db.getAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "لا توجد بيانات محفوظة", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                // ترتيب الأعمدة في قاعدة البيانات الخاصة بك: 0=ID, 1=User, 2=Item, 3=Price, 4=Image
                savedList.add(new SavedPurchaseModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4)
                ));
            }
            adapter = new SavedItemsAdapter(savedList, this);
            recyclerView.setAdapter(adapter);
        }
    }
}