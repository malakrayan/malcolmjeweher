package com.example.malcolmjeweher.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.malcolmjeweher.DatabaseHelper;
import com.example.malcolmjeweher.R;
import com.example.malcolmjeweher.models.SavedPurchaseModel;

import java.util.List;

public class SavedItemsAdapter extends RecyclerView.Adapter<SavedItemsAdapter.ViewHolder> {
    private List<SavedPurchaseModel> list;
    private DatabaseHelper db;
    private Context context;

    public SavedItemsAdapter(List<SavedPurchaseModel> list, Context context) {
        this.list = list;
        this.context = context;
        this.db = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_saved_purchase, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SavedPurchaseModel item = list.get(position);
        holder.userName.setText("المشتري: " + item.getUserName());
        holder.itemName.setText(item.getItemName());
        holder.image.setImageResource(item.getImageResId());

        holder.btnDelete.setOnClickListener(v -> {
            // حذف من قاعدة البيانات
            db.deleteItem(item.getId());
            // حذف من القائمة المعروضة وتحديث الشاشة
            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
            Toast.makeText(context, "تم الحذف!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() { return list.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, itemName;
        ImageView image;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                    // هذه الأسطر هي التي كانت ناقصة وتسبب الـ NullPointerException
                    userName = itemView.findViewById(R.id.tv_saved_user);
                    itemName = itemView.findViewById(R.id.tv_saved_item_name);
                    image = itemView.findViewById(R.id.saved_item_image);
                    btnDelete = itemView.findViewById(R.id.btn_delete_saved);
                }
            }

        }

