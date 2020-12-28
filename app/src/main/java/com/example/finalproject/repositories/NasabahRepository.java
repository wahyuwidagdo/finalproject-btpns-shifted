package com.example.finalproject.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.model.APIResponse;
import com.example.finalproject.model.LoginRequest;
import com.example.finalproject.model.RegisterRequest;
import com.example.finalproject.networking.API;
import com.example.finalproject.networking.RetrofitService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NasabahRepository {

    private static NasabahRepository nasabahRepository;
    private API api;

    public static NasabahRepository getInstance() {
        if (nasabahRepository == null) {
            nasabahRepository = new NasabahRepository();
        }
        return nasabahRepository;
    }

    public NasabahRepository() {
        api = RetrofitService.createService(API.class);
    }

    public MutableLiveData<APIResponse> postRegister(RegisterRequest registerRequest) {
        String request = new Gson().toJson(registerRequest);
        System.out.println("request : " + request);
        MutableLiveData<APIResponse> response = new MutableLiveData<>();
        api.register(registerRequest).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> responses) {
                response.setValue(responses.body());
                System.out.println("test : " + responses.body());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                System.out.println("test " + t.getMessage());
            }
        });
        return response;
    }

    public MutableLiveData<APIResponse> postLogin(LoginRequest loginRequest) {
        MutableLiveData<APIResponse> loginResult = new MutableLiveData<>();
        api.login(loginRequest).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                loginResult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                loginResult.setValue(null);
            }
        });
        return loginResult;
    }

    public MutableLiveData<APIResponse> getSaldo(String string) {
        MutableLiveData<APIResponse> saldoRequest = new MutableLiveData<>();
        Log.d("Test 1", "Test 1");
        api.getSaldo(string).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                saldoRequest.setValue(response.body());
                System.out.println("Test : ");
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                saldoRequest.setValue(null);
                System.out.println("Test Failure");
            }
        });
        return saldoRequest;
    }

}
