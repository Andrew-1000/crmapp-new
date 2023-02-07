package com.example.crmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

public class DispatchActivity extends AppCompatActivity {
    String[] driversList = {"Driver 1","Driver 2","Driver 3","Driver 4","Driver 5"};
    String[] vehicleList = {"Vehicle 1","Vehicle 2","Vehicle 3","Vehicle 4","Vehicle 5"};
    String[] centersList = {"Center One","Center Two","Center Three","Center Four"};

    AppCompatAutoCompleteTextView driverOptions;
    AppCompatAutoCompleteTextView vehicleOptions;
    AppCompatAutoCompleteTextView centerOptns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        driverOptions = findViewById(R.id.driverOptions);
        vehicleOptions = findViewById(R.id.vehicleOptions);
        centerOptns = findViewById(R.id.centerOptions);

        ArrayAdapter<String> driverArrAdptr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, driversList);
        driverOptions.setAdapter(driverArrAdptr);

        ArrayAdapter<String> vehicleArrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vehicleList);
        vehicleOptions.setAdapter(vehicleArrAdapter);

        ArrayAdapter<String> centerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,centersList );
        centerOptns.setAdapter(centerArrayAdapter);
    }
}