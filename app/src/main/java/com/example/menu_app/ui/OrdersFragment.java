package com.example.menu_app.ui;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.menu_app.R;
import com.example.menu_app.adapter.ChiefOrderAdapter;
import com.example.menu_app.adapter.OrderAdapter;
import com.example.menu_app.api.OrderAPI;
import com.example.menu_app.api.UsersAPI;
import com.example.menu_app.model.Order;
import com.example.menu_app.model.Users;
import com.example.menu_app.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {

    RecyclerView recyclerView;
    Button Confirm, Completed;

    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        recyclerView = view.findViewById(R.id.AllOrderRecyclerView);

        Confirm = view.findViewById(R.id.OrderConfirm);
        Completed = view.findViewById(R.id.OrderCompleted);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        final Call<Users> usersCall = usersAPI.getUserDetails(Url.token);

        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){
                    OrderAPI orderAPI = Url.getInstance().create(OrderAPI.class);
                    Call<List<Order>> listCall = orderAPI.getorder();
                    if (response.body().isWaiter() == true){
                        listCall.enqueue(new Callback<List<Order>>() {
                            @Override
                            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                                OrderAdapter orderAdapter = new OrderAdapter(getActivity(),response.body());
                                recyclerView.setAdapter(orderAdapter);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                orderAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<List<Order>> call, Throwable t) {
                                Toast.makeText(getActivity(), "Error" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (response.body().isChief() == true){
                        listCall.enqueue(new Callback<List<Order>>() {
                            @Override
                            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                                ChiefOrderAdapter chiefOrderAdapter = new ChiefOrderAdapter(getActivity(), response.body());
                                recyclerView.setAdapter(chiefOrderAdapter);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                chiefOrderAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<List<Order>> call, Throwable t) {
                                Toast.makeText(getActivity(), "Error" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    return;
                }
                else{
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
