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

import com.example.menu_app.FoodDisplayActivity;
import com.example.menu_app.R;
import com.example.menu_app.model.Food;
import com.example.menu_app.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ItemViewHolder>{

    private List<Food> foodList;
    private Context context;

    public FoodAdapter(List<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_homefood, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        final Food food = foodList.get(position);
        Picasso.get().load(Url.base_url_image + foodList.get(position).getFoodImage()).into(holder.Image);
        holder.Name.setText(food.getFoodName());
        holder.Price.setText("Rs " + food.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodDisplayActivity.class);
                intent.putExtra("id", foodList.get(position).get_id());
                intent.putExtra("Name", foodList.get(position).getFoodName());
                intent.putExtra("Tag", foodList.get(position).getTags());
                intent.putExtra("Tag2", foodList.get(position).getTags2());
                intent.putExtra("Description", foodList.get(position).getDescription());
                intent.putExtra("Price", foodList.get(position).getPrice());
                intent.putExtra("Image", foodList.get(position).getFoodImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView Image;
        private TextView Name;
        private TextView Price;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.HomeFoodPic);
            Name = itemView.findViewById(R.id.tvHomeFoodName);
            Price = itemView.findViewById(R.id.tvHomeFoodPrice);
        }
    }
}
