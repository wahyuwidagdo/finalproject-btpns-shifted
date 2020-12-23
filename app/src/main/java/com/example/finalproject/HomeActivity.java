package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    private ImageButton mutasiImageButton, plnImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById();
        onClickGroup();
    }

    void findViewById() {
        mutasiImageButton = findViewById(R.id.mutasiImageButton);
        plnImageButton = findViewById(R.id.plnImageButton);
    }

    void onClickGroup() {
        mutasiImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MutasiActivity.class);
                startActivity(intent);
            }
        });

        plnImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PlnActivity.class);
                startActivity(intent);
            }
        });
    }
}