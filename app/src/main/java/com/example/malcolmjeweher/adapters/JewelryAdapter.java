package com.example.malcolmjeweher.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.malcolmjeweher.DatabaseHelper;
import com.example.malcolmjeweher.R;
import com.example.malcolmjeweher.models.jewelryitem;
import com.example.malcolmjeweher.product_main;

import java.util.List;

public class JewelryAdapter extends RecyclerView.Adapter<JewelryAdapter.JewelryViewHolder> {

    private List<jewelryitem> jewelryList;

    public JewelryAdapter(List<jewelryitem> jewelryList) {
        this.jewelryList = jewelryList;
    }

    @NonNull
    @Override
    public JewelryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ربط ملف الـ XML الخاص بالعنصر (activity_item_jewelry)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_jewelry, parent, false);
        return new JewelryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JewelryViewHolder holder, int position) {
        jewelryitem item = jewelryList.get(position);

        // 1. تعيين البيانات الأساسية (الاسم، السعر، الصورة)
        if (item.getName() != null) {
            holder.name.setText(item.getName());
        }
        if (item.getPrice() != null) {
            holder.price.setText(String.valueOf(item.getPrice()));
        }
        holder.image.setImageResource(item.getImageResId());

        // --- 2. كود زر النجمة (لحفظ المنتج في قاعدة البيانات باسم المستخدم) ---
        holder.starIcon.setOnClickListener(v -> {
            Context context = v.getContext();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("حفظ القطعة باسمك");

            final EditText input = new EditText(context);
            input.setHint("اكتب اسمك هنا...");
            builder.setView(input);

            builder.setPositiveButton("حفظ", (dialog, which) -> {
                String userName = input.getText().toString().trim();
                if (!userName.isEmpty()) {
                    DatabaseHelper db = new DatabaseHelper(context);
                    boolean success = db.insertData(userName, item.getName(), item.getPrice(), item.getImageResId());

                    if (success) {
                        // تغيير شكل النجمة لنجمة مملوءة (أيقونة نظام أندرويد)
                        holder.starIcon.setImageResource(android.R.drawable.btn_star_big_on);
                        Toast.makeText(context, "تم الحفظ بنجاح!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "الرجاء كتابة اسم", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("إلغاء", null);
            builder.show();
        });

        // --- 3. كود زر ADD (للانتقال لصفحة تفاصيل المنتج) ---
        holder.btnAdd.setOnClickListener(v -> {
            Context context = v.getContext();
            // إنشاء Intent للانتقال إلى الأكتيفيتي التي تحتوي على الوصف
            Intent intent = new Intent(context, product_main.class);

            // تمرير البيانات للأكتيفيتي التالية
            intent.putExtra("PRODUCT_NAME", item.getName());
            intent.putExtra("PRODUCT_PRICE", item.getPrice());
            intent.putExtra("PRODUCT_IMAGE", item.getImageResId());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return jewelryList != null ? jewelryList.size() : 0;
    }

    // كلاس ViewHolder لتعريف العناصر وربطها بالـ IDs
    public static class JewelryViewHolder extends RecyclerView.ViewHolder {
        ImageView image, starIcon;
        TextView name, price;
        Button btnAdd;

        public JewelryViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_jewelry);
            name = itemView.findViewById(R.id.tv_jewelry_name);
            price = itemView.findViewById(R.id.price);
            starIcon = itemView.findViewById(R.id.img_star_save);
            btnAdd = itemView.findViewById(R.id.bt_sign); // تأكد أن الـ ID هو bt_sign في ملف الـ XML
        }
    }
}