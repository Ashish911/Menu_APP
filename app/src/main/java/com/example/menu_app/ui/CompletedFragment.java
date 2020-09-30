package com.example.menu_app.ui;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.menu_app.R;
import com.example.menu_app.adapter.CompletedOrderAdapter;
import com.example.menu_app.api.OrderAPI;
import com.example.menu_app.model.Order;
import com.example.menu_app.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedFragment extends Fragment {

    RecyclerView recyclerView;

    public CompletedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_completed, container, false);

        recyclerView = view.findViewById(R.id.AllCompletedRecyclerView);


        OrderAPI orderAPI = Url.getInstance().create(OrderAPI.class);
        Call<List<Order>> listCall = orderAPI.getOrder();
        listCall.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                CompletedOrderAdapter completedOrderAdapter = new CompletedOrderAdapter(response.body(),getActivity());
                recyclerView.setAdapter(completedOrderAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                completedOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
