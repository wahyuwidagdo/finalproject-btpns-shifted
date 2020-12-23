package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.databinding.ActivityRegisterBinding;
import com.example.finalproject.model.APIResponse;
import com.example.finalproject.model.RegisterRequest;
import com.example.finalproject.viewmodel.NasabahViewModel;

public class RegisterActivity extends AppCompatActivity {

    private NasabahViewModel nasabahViewModel;
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
    }

    void init() {
        binding.username.setText("edo");
        binding.password.setText("edo");
        binding.namaLengkap.setText("Wahyu F. Widagdo");
        binding.email.setText("edo@edo.com");
        binding.nohp.setText("081234567890");
        nasabahViewModel = ViewModelProviders.of(this).get(NasabahViewModel.class);
        nasabahViewModel.init();
    }

    void onClickGroup() {
        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });
    }

    private void doRegister() {
        String username = binding.username.getText().toString();
        String password = binding.password.getText().toString();
        String namaLengkap = binding.namaLengkap.getText().toString();
        String email = binding.email.getText().toString();
        String nohp = binding.nohp.getText().toString();

        if (password.equals("")) {
            Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            RegisterRequest registerRequest = new RegisterRequest(username, password, namaLengkap, email, nohp);
            nasabahViewModel.postRegister(registerRequest).observe(this, nasabahResponse -> {
                System.out.println("atas response : " + nasabahResponse.getMessage());
                APIResponse response = nasabahResponse;
                if (response.getResponse() == 200) {
                    moveToMessageActivity("Sukses", "Anda Berhasil Register", 200);
                }
            });
        }
    }

    void moveToMessageActivity(String status, String message, int code) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("status", status);
        bundle.putString("message", message);
        bundle.putInt("code", code);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}