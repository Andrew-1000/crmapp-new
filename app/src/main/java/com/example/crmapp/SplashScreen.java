package com.example.crmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        MaterialButton purchaseButton = findViewById(R.id.purchaseBtn);
        MaterialButton dispatchBtn = findViewById(R.id.dispatchButton);
        MaterialButton addFarmer = findViewById(R.id.addFarmerButton);
        MaterialButton expensesBtn = findViewById(R.id.expensesBtton);
        MaterialButton reception = findViewById(R.id.openReception);
        MaterialButton reportsBtn = findViewById(R.id.reportsButton);
        MaterialButton settingsBtn = findViewById(R.id.settingsButton);
        MaterialButton cashInHandBtn = findViewById(R.id.cashInHandBtn);
        purchaseButton.setOnClickListener(view -> {
            Intent intent = new Intent(SplashScreen.this, PurchaseActivity.class);
            startActivity(intent);
        });

        dispatchBtn.setOnClickListener(view -> {
            Intent intent  = new Intent(SplashScreen.this, DispatchLaunchActivity.class);
            startActivity(intent);
        });

        addFarmer.setOnClickListener(view -> {
            Intent intent = new Intent(SplashScreen.this, AddFarmerActivity.class);
            startActivity(intent);
        });

        expensesBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SplashScreen.this, ExpensesActivity.class);
            startActivity(intent);
        });
        reception.setOnClickListener(view -> {
            Intent intent = new Intent(SplashScreen.this, ReceptionLaunchActivity.class);
            startActivity(intent);
        });
        reportsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SplashScreen.this, ReportsActivity.class);
            startActivity(intent);
        });
        settingsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SplashScreen.this, SettingsActivity.class);
            startActivity(intent);
        });
        cashInHandBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SplashScreen.this, CashInHandActivity.class);
            startActivity(intent);
        });
    }
}