package com.example.finalproject.networking;

import com.example.finalproject.model.APIResponse;
import com.example.finalproject.model.LoginRequest;
import com.example.finalproject.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {

    @POST("/register")
    Call<APIResponse> register (@Body RegisterRequest registerRequest);

    @POST("/login/")
    Call<APIResponse> login (@Body LoginRequest loginRequest);

    @GET("/checksaldo/{username}")
    Call<APIResponse> getSaldo(@Path("username")String checkSaldo);

    @GET("/nasabah")
    Call<APIResponse> getNasabah();

    @GET("/pln")
    Call<APIResponse> getPln();

}
