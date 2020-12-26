package com.example.finalproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject.model.APIResponse;
import com.example.finalproject.model.LoginRequest;
import com.example.finalproject.model.RegisterRequest;
import com.example.finalproject.repositories.NasabahRepository;

public class NasabahViewModel extends ViewModel {

    private NasabahRepository nasabahRepository;
    private LiveData<APIResponse> mutableLiveData;
    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        nasabahRepository = nasabahRepository.getInstance();
    }

    public LiveData<APIResponse> postRegister(RegisterRequest registerRequest) {
        if (mutableLiveData == null) {
            nasabahRepository = nasabahRepository.getInstance();
        }
        mutableLiveData = nasabahRepository.postRegister(registerRequest);
        return mutableLiveData;
    }

    public LiveData<APIResponse> postLogin(LoginRequest loginRequest) {
        if (mutableLiveData == null) {
            nasabahRepository = nasabahRepository.getInstance();
        }
        mutableLiveData = nasabahRepository.postLogin(loginRequest);
        return mutableLiveData;
    }

    public LiveData<APIResponse> getSaldo(String string) {
        if (mutableLiveData == null) {
            nasabahRepository = nasabahRepository.getInstance();
        }
        mutableLiveData = nasabahRepository.getSaldo(string);
        return mutableLiveData;
    }

}
