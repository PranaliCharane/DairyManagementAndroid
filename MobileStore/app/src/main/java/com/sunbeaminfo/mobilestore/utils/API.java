package com.sunbeaminfo.mobilestore.utils;

import com.google.gson.JsonObject;
import com.sunbeaminfo.mobilestore.entity.Order;
import com.sunbeaminfo.mobilestore.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    String BASE_URL ="http://192.168.1.6:4000";

    @POST("/user/login")
    Call<JsonObject> loginUser(@Body User user);

    @POST("/user/register")
    Call<JsonObject> registerUser(@Body User user);

    @GET("/user/{id}")
    Call<JsonObject> getUserById(@Path("id")int id);

    @GET("/ProductDetails/")
    Call<JsonObject> getAllProductDetails();

    @POST("/orders/placeorder")
    Call<JsonObject> placeOrder(@Body Order order);

    @GET("/orders/{id}")
    Call<JsonObject> getuserOrders(@Path("id")int id);
}
