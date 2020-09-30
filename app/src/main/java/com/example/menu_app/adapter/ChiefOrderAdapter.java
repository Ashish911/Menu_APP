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

import com.example.menu_app.ChiefDashboardActivity;
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

public class ChiefOrderAdapter extends RecyclerView.Adapter<ChiefOrderAdapter.ViewHolder>{

    private List<Order> orderList;
    private Context context;

    public ChiefOrderAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chief_order,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Order order = orderList.get(position);

        final String FoodN = order.getFoodName();
        final String FoodI = order.getFoodImage();
        final String Price = order.getPrice();
        final String Quantity = order.getQuantity();
        final String FullN = order.getFullName();
        final String TableN = order.getTableName();
        final boolean confirmed = order.isConfirmed();
        final boolean completed = order.isCompleted();

        if (confirmed == true){
            Picasso.get().load(Url.base_url_image + orderList.get(position).getFoodImage()).into(holder.FoodImage);
            holder.FoodName.setText(order.getFoodName());
            holder.Quantity.setText("Quantity:  " + order.getQuantity());


            holder.Completed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Order orders = new Order(FoodN,FoodI,Price, Quantity, FullN, TableN, confirmed, true);
                    OrderAPI orderAPI = Url.getInstance().create(OrderAPI.class);
                    Call<Order> listCall = orderAPI.updateOrder(orderList.get(position).getId(), orders);
                    listCall.enqueue(new Callback<Order>() {
                        @Override
                        public void onResponse(Call<Order> call, Response<Order> response) {
                            Toast.makeText(context, "You have completed an order", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(context, ChiefDashboardActivity.class);
                            context.startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Order> call, Throwable t) {
                            Toast.makeText(context, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            if (completed == true){
                holder.Completed.setVisibility(View.INVISIBLE);
                holder.greenCompleted.setVisibility(View.VISIBLE);
            } else if ( completed == false){
                holder.greenCompleted.setVisibility(View.INVISIBLE);
                holder.Completed.setVisibility(View.VISIBLE);
            }
        }
        else {
            Toast.makeText(context, "Sorry but there are no confirmed orders", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView FoodImage;
        TextView FoodName,Quantity,greenCompleted;
        Button Completed ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FoodImage = itemView.findViewById(R.id.ChiefOrderedImage);
            FoodName = itemView.findViewById(R.id.ChiefOrderedFoodName);
            Quantity = itemView.findViewById(R.id.ChiefOrderedQuantity);
            Completed = itemView.findViewById(R.id.OrderCompleted);
            greenCompleted= itemView.findViewById(R.id.OrderChiefCompleted);
        }
    }

}
