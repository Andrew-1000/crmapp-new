package com.example.crmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crmapp.classes.Farmer;
import com.example.crmapp.classes.ApiClient;
import com.example.crmapp.interfaces.ApiInterface;
import com.example.crmapp.responses.StatusResponse;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddFarmerActivity extends AppCompatActivity {
    String[] farmerTypes = {"Farmer","Agent"};
    AppCompatAutoCompleteTextView selectFarmer;


    EditText farmerName,farmerID, farmerPhoneNo;
    MaterialButton saveFarmer, cancel_button;

    ApiInterface apiInterface;
    Activity activity;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farmer);
        selectFarmer = findViewById(R.id.growerType);

        ArrayAdapter<String> farmerTypeArrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, farmerTypes);
        selectFarmer.setAdapter(farmerTypeArrAdapter);

        activity = AddFarmerActivity.this;
        context = AddFarmerActivity.this;

        apiInterface = ApiClient.getMainClient().create(ApiInterface.class);

        farmerName = findViewById((R.id.farmerName));
        farmerPhoneNo = findViewById((R.id.farmerPhoneNo));
        farmerID = findViewById((R.id.farmerID));
        //save button
        saveFarmer = findViewById(R.id.saveFarmer);
        cancel_button = findViewById(R.id.cancel_button);

        saveFarmer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SubmitData();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SplashScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }

    private void SubmitData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("FarmerName", farmerName.getText().toString());
        map.put("FarmerID", farmerID.getText().toString());
        map.put("FarmerPhoneNo", farmerPhoneNo.getText().toString());
        map.put("FarmerType", selectFarmer.getEditableText().toString());



        Call<StatusResponse> call = apiInterface.insertFarmer(map);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AddFarmerActivity.this,  response.body().getMessage() + ", FarmerID - " + response.body().getData(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, AddFarmerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AddFarmerActivity.this, "Some error occurred while calling the API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Toast.makeText(AddFarmerActivity.this, "Some error occurred while calling the API 2", Toast.LENGTH_SHORT).show();
            }
        });

    }

}