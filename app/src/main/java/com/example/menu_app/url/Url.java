package com.example.menu_app.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
    //For testing
//    public static final String base_url = "http://192.168.0.110:3000/";
    //For App Use
    public static final String base_url = "http://10.0.2.2:3000/";
    public static final String base_url_image ="http://10.0.2.2:3000/upload/";
    public static String userid;
    public static String token = "Bearer ";

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
