package com.example.crmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crmapp.classes.ApiClient;
import com.example.crmapp.interfaces.ApiInterface;
import com.example.crmapp.responses.StatusResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CashInHandActivity extends AppCompatActivity {

    TextInputEditText openingBalanceInpt,closingBalanceInpt;
    MaterialButton saveClosingBlnceBtn,cncelClosingBlnceBtn;

    ApiInterface apiInterface;
    Activity activity;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_in_hand);

        activity = CashInHandActivity.this;
        context = CashInHandActivity.this;

        apiInterface = ApiClient.getMainclient().create(ApiInterface.class);

//        edit text fields
        openingBalanceInpt = findViewById(R.id.openingBalanceInpt);
        closingBalanceInpt = findViewById(R.id.closingBalanceInput);
//        buttons
        saveClosingBlnceBtn = findViewById(R.id.saveClosingBlnceBtn);
        cncelClosingBlnceBtn = findViewById(R.id.cncelClosingBlnceBtn);


//        Button click events
        saveClosingBlnceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitClosingBlnce();
            }
        });


        cncelClosingBlnceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SplashScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void submitClosingBlnce() {
        HashMap<String, String> map = new HashMap<>();
        map.put("opening", openingBalanceInpt.getText().toString());
        map.put("closing", closingBalanceInpt.getText().toString());

        Call<StatusResponse> call = apiInterface.insertCash(map);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CashInHandActivity.this,  response.body().getMessage() + ", cash_id - " + response.body().getData(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, CashInHandActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(CashInHandActivity.this, "Some error occurred while calling the API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Toast.makeText(CashInHandActivity.this, "Some error occurred while calling the API 2", Toast.LENGTH_SHORT).show();

            }
        });
    }

}