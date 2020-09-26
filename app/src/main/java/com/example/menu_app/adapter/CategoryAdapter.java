package com.example.menu_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu_app.CategoryActivity;
import com.example.menu_app.R;
import com.example.menu_app.model.Category;
import com.example.menu_app.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>{

    private List<Category> categoryList;
    private Context context;

    public CategoryAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_display,null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final Category category = categoryList.get(position);
        Picasso.get().load(Url.base_url_image + categoryList.get(position).getCategoryImage()).into(holder.imgcategory);
        holder.tvCName.setText(category.getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoryActivity.class);
                intent.putExtra("categoryid",category.get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgcategory;
        private TextView tvCName;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgcategory = itemView.findViewById(R.id.imgCategory);
            tvCName = itemView.findViewById(R.id.tvCategoryName);
        }
    }
}
