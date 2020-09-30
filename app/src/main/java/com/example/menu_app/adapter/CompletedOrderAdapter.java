package com.example.menu_app.adapter;

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

import com.example.menu_app.R;
import com.example.menu_app.model.Order;
import com.example.menu_app.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CompletedOrderAdapter extends RecyclerView.Adapter<CompletedOrderAdapter.ViewHolder>{

    private Context context;
    private List<Order> orderList;

    public CompletedOrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_completed,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Order order = orderList.get(position);

        final boolean confirmed = order.isConfirmed();
        final boolean completed = order.isCompleted();

        Toast.makeText(context, "" + completed, Toast.LENGTH_SHORT).show();

        if (confirmed == true && completed == true){
            Picasso.get().load(Url.base_url_image + orderList.get(position).getFoodImage()).into(holder.FoodImage);
            holder.FoodName.setText(order.getFoodName());
            holder.Quantity.setText("Quantity:  " + order.getQuantity());
            holder.FullName.setText("For " + order.getFullName());
        } else {
            holder.FoodName.setVisibility(View.INVISIBLE);
            holder.Quantity.setVisibility(View.INVISIBLE);
            holder.FullName.setVisibility(View.INVISIBLE);
            holder.FoodImage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView FoodImage;
        TextView FoodName,Quantity,FullName;
        CircleImageView completed;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FoodImage = itemView.findViewById(R.id.COrderedImage);
            FoodName = itemView.findViewById(R.id.COrderedFoodName);
            Quantity = itemView.findViewById(R.id.COrderedQuantity);
            FullName = itemView.findViewById(R.id.COrderedFullName);
            completed = itemView.findViewById(R.id.greenstatus);
        }
    }
}
