package com.example.realfirestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.realfirestore.databinding.ActivityUpdateactivityBinding;

public class updateactivity extends AppCompatActivity {
  ActivityUpdateactivityBinding binding;
  String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdateactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        key=getIntent().getStringExtra("key");

    }
}