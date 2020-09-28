package com.example.menu_app.api;

import com.example.menu_app.model.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrderAPI {

    @POST("order")
    Call<Void> order(@Body Order order);

    @GET("order")
    Call<Void> getorder();

}
