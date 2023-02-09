package com.example.crmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SettingsActivity extends AppCompatActivity {
    MaterialButton addBluetoothDevice;
    int REQUEST_ENABLE_BT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        addBluetoothDevice = findViewById(R.id.addDeviceBtn);


        addBluetoothDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint({"NewApi", "LocalSuppress"}) Intent
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    intent = new Intent(getApplicationContext(), MainsActivity.class);
//                }
                startActivity(intent);

            }
        });
    }
}