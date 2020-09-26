package com.example.menu_app.api;

import com.example.menu_app.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryAPI {

    @GET("category/")
    Call<List<Category>> getCategory();
}
