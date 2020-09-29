package com.example.menu_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu_app.R;
import com.example.menu_app.WaiterDashboardActivity;
import com.example.menu_app.api.OrderAPI;
import com.example.menu_app.model.Order;
import com.example.menu_app.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Order order = orderList.get(position);
        Picasso.get().load(Url.base_url_image + orderList.get(position).getFoodImage()).into(holder.FoodImage);
        holder.FoodName.setText(order.getFoodName());
        holder.Quantity.setText("Quantity:  " + order.getQuantity());
        final String FoodN = order.getFoodName();
        final String FoodI = order.getFoodImage();
        final String Price = order.getPrice();
        final String Quantity = order.getQuantity();
        final String FullN = order.getFullName();
        final String TableN = order.getTableName();
        final boolean confirmed = order.isConfirmed();
        final boolean completed = order.isCompleted();
        holder.Confirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order orders = new Order(FoodN,FoodI,Price, Quantity, FullN, TableN, true, completed);
                OrderAPI orderAPI = Url.getInstance().create(OrderAPI.class);
                Call<Order> listCall = orderAPI.updateOrder(orderList.get(position).getId(), orders);
                listCall.enqueue(new Callback<Order>() {
                    @Override
                    public void onResponse(Call<Order> call, Response<Order> response) {
                        Toast.makeText(context, "You have confirmed an order", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context, WaiterDashboardActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Order> call, Throwable t) {
                        Toast.makeText(context, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderAPI orderAPI = Url.getInstance().create(OrderAPI.class);
                Call<Void> listCall = orderAPI.removeOrder(orderList.get(position).getId());
                listCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(context, "You have remove a order", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context, WaiterDashboardActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(context, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        if (confirmed == true){
            holder.Confirmed.setVisibility(View.INVISIBLE);
            holder.greenConfirmed.setVisibility(View.VISIBLE);
        } else if ( confirmed == false){
            holder.greenConfirmed.setVisibility(View.INVISIBLE);
            holder.Confirmed.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView FoodImage;
        ImageButton Remove;
        TextView FoodName,Quantity,greenConfirmed;
        Button Confirmed;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FoodImage = itemView.findViewById(R.id.OrderedImage);
            FoodName = itemView.findViewById(R.id.OrderedFoodName);
            Quantity = itemView.findViewById(R.id.OrderedQuantity);
            Confirmed = itemView.findViewById(R.id.OrderConfirm);
            greenConfirmed = itemView.findViewById(R.id.OrderWaiterConfirm);
            Remove = itemView.findViewById(R.id.OrderRemove);
        }
    }
}
