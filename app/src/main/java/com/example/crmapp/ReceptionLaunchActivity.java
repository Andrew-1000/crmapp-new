package com.example.crmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class ReceptionLaunchActivity extends AppCompatActivity {
    MaterialButton receptionActivity, receptionRejctBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_launch);
        receptionActivity = findViewById(R.id.receivedBtn);
        receptionRejctBtn = findViewById(R.id.rejectedButtonRcptn);
        receptionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(ReceptionLaunchActivity.this, ReceptionActivity.class);
                startActivity(intent);
            }
        });

        receptionRejctBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReceptionLaunchActivity.this, ReceptionRejectedActivity.class);
                startActivity(intent);
            }
        });
    }
}