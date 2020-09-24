package com.example.menu_app.api;

import com.example.menu_app.model.Users;
import com.example.menu_app.serverresponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UsersAPI {

    @FormUrlEncoded
    @POST("user/")
    Call<SignUpResponse> checkUser(@Field("Email") String Email, @Field("Password") String Password);

    @GET("user/me")
    Call<Users> getUserDetails(@Header("Authorization")String token);

    @PUT("user/me")
    Call<Users> UpdateDetails(@Header("Authorization")String token,@Body Users users);
}
