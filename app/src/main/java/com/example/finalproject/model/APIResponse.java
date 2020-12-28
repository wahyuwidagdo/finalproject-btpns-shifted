package com.example.finalproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("Saldo")
    @Expose
    private String saldo;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("response")
    @Expose
    private int response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }
}
