package com.example.crmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class DispatchLaunchActivity extends AppCompatActivity {

    MaterialButton dispatchActivityButton;
    MaterialButton dispatchRejctButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_launch);
        dispatchActivityButton = findViewById(R.id.dispatchBtn);
        dispatchRejctButton = findViewById(R.id.dispatchRejectButton);
        dispatchActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DispatchLaunchActivity.this, DispatchActivity.class);
                startActivity(intent);
            }
        });

        dispatchRejctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RejectedCenterLevelActivity.class);
                startActivity(intent);

            }
        });
    }
}