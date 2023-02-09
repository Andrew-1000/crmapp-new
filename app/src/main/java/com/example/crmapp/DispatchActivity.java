package com.example.crmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.crmapp.classes.Driver;
import com.example.crmapp.classes.Vehicle;
import com.example.crmapp.interfaces.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DispatchActivity extends AppCompatActivity {
    //    String[] driversList = {"Driver 1","Driver 2","Driver 3","Driver 4","Driver 5"};
//    String[] vehicleList = {"Vehicle 1","Vehicle 2","Vehicle 3","Vehicle 4","Vehicle 5"};
    String[] centersList = {"Center One","Center Two","Center Three","Center Four"};


    private ApiInterface apiInterface;
    private ArrayList<Driver> driversList;
    private ArrayList<Vehicle> vehicleList;


    //declared variables
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

//        ArrayAdapter<String> driverArrAdptr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, driversList);
//        driverOptions.setAdapter(driverArrAdptr);

//        ArrayAdapter<String> vehicleArrAdapter;
//        vehicleArrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vehicleList);
//        vehicleOptions.setAdapter(vehicleArrAdapter);

        ArrayAdapter<String> centerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,centersList );
        centerOptns.setAdapter(centerArrayAdapter);
        displayDrivers();
        displayVehicles();
    }

    private void displayDrivers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        Call<ArrayList<Driver>> arrayListCall = apiInterface.getAllDrivers();
        arrayListCall.enqueue(new Callback<ArrayList<Driver>>() {
            @Override
            public void onResponse(Call<ArrayList<Driver>> call, Response<ArrayList<Driver>> response) {
                driversList = response.body();
                String[] stringArr = new String[driversList.size()];

                for (int i = 0; i < driversList.size(); i++) {
                    stringArr[i] = driversList.get(i).getF_name() + " " + driversList.get(i).getL_name();
                }
//                Custom custom = new Custom(modelArrayList, DispatchActivity.this, R.layout.singleview);
//                lv.setAdapter(custom);

                driverOptions.setAdapter(new ArrayAdapter<String>(DispatchActivity.this, android.R.layout.simple_list_item_1, stringArr));
            }

            @Override
            public void onFailure(Call<ArrayList<Driver>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error occured", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void displayVehicles() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        Call<ArrayList<Vehicle>> arrayListCall = apiInterface.callVehicle();
        arrayListCall.enqueue(new Callback<ArrayList<Vehicle>>() {
            @Override
            public void onResponse(Call<ArrayList<Vehicle>> call, Response<ArrayList<Vehicle>> response) {
                vehicleList = response.body();
//                assert vehicleList != null;
                String[] stringArr = new String[vehicleList.size()];

                for (int i = 0; i < vehicleList.size(); i++) {
                    stringArr[i] = vehicleList.get(i).getNumber_plate();
                }
//                Custom custom = new Custom(modelArrayList, DispatchActivity.this, R.layout.singleview);
//                lv.setAdapter(custom);

                vehicleOptions.setAdapter(new ArrayAdapter<String>(DispatchActivity.this, android.R.layout.simple_list_item_1, stringArr));
            }

            @Override
            public void onFailure(Call<ArrayList<Vehicle>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error occured", Toast.LENGTH_LONG).show();
            }

        });


    }
}