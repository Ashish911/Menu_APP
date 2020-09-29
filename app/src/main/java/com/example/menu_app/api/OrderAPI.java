package com.example.menu_app.api;

import com.example.menu_app.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrderAPI {

    @POST("order")
    Call<Void> order(@Body Order order);

    @GET("order")
    Call<List<Order>> getorder();

    @DELETE("order/{id}")
    Call<Void> removeOrder(@Path("id") String id);

    @PATCH("order/{id}")
    Call<Order> updateOrder(@Path("id") String id, @Body Order order);

}
