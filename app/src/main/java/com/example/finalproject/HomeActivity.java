package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.finalproject.databinding.ActivityHomeBinding;
import com.example.finalproject.model.APIResponse;
import com.example.finalproject.viewmodel.NasabahViewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class HomeActivity extends AppCompatActivity {

    private ImageButton mutasiImageButton, plnImageButton;
    private ActivityHomeBinding binding;
    private NasabahViewModel nasabahViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        onClickGroup();
        init();
    }

    void init() {
        nasabahViewModel = ViewModelProviders.of(this).get(NasabahViewModel.class);
        nasabahViewModel.init();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.finalproject.login", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("com.example.finalproject.login", "");
        System.out.println("Username : " + username);
        nasabahViewModel.getSaldo(username).observe(this, new Observer<APIResponse>() {
            @Override
            public void onChanged(APIResponse apiResponse) {
                DecimalFormat currency = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols Rp = new DecimalFormatSymbols();
                Rp.setCurrencySymbol("Rp. ");
                Rp.setMonetaryDecimalSeparator(',');
                Rp.setGroupingSeparator('.');
                currency.setDecimalFormatSymbols(Rp);
                Double saldo = Double.parseDouble(apiResponse.getSaldo());
                binding.totalSaldoTextView.setText(currency.format(saldo));
            }
        });
    }

    void onClickGroup() {
        binding.mutasiImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MutasiActivity.class);
                startActivity(intent);
            }
        });

        binding.plnImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PlnActivity.class);
                startActivity(intent);
            }
        });
    }
}