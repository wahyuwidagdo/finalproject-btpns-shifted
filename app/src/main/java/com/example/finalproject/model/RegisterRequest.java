package com.example.finalproject.model;

public class RegisterRequest {

    String username;
    String password;
    String namaLengkap;
    String email;
    String nohp;

    public RegisterRequest(String username, String password, String namaLengkap, String email, String nohp) {
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.nohp = nohp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
}
