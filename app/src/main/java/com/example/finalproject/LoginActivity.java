package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalproject.databinding.ActivityLoginBinding;
import com.example.finalproject.model.APIResponse;
import com.example.finalproject.model.LoginRequest;
import com.example.finalproject.viewmodel.NasabahViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton, registerButton;
    private ActivityLoginBinding binding;
    private NasabahViewModel nasabahViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        findViewById();
        onClickGroup();
    }

    void init() {
        nasabahViewModel = ViewModelProviders.of(this).get(NasabahViewModel.class);
    }

    void findViewById() {
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
    }

    void onClickGroup() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    void doLogin() {
        String username = binding.username.getText().toString();
        String password = binding.password.getText().toString();
        LoginRequest loginRequest = new LoginRequest(username, password);
        nasabahViewModel.postLogin(loginRequest).observe(this, apiResponse -> {
            APIResponse response = apiResponse;
            if (response.getResponse() == 200) {
                SharedPreferences sharedPreferences = getSharedPreferences("com.example.finalproject.login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("com.example.finalproject.login", binding.username.getText().toString());
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                new MaterialAlertDialogBuilder(this)
                        .setTitle("Gagal Login")
                        .setMessage("Username atau Password salah")
                        .setNegativeButton("close", null)
                        .show();
            }
        });
    }
}