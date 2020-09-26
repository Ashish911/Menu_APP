package com.example.menu_app.api;

import com.example.menu_app.model.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FoodAPI {

    @GET("food/")
    Call<List<Food>> getFood();

    @GET("food/search/{foodName}")
    Call<List<Food>> searchFood(@Path("foodName") String FoodName);

    @GET("food/getByCategory/{id}")
    Call<List<Food>> getFoodByID(@Path("id") String Categoryid);

}
