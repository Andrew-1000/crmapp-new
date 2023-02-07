package com.example.crmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PayActivity extends AppCompatActivity {
    TextView textView1, textView2, textView3, textView4, textView5, textView6;
    AppCompatAutoCompleteTextView paySelection;
    String[] paymentModes = {"M-Pesa", "Cash", "Bank Transfer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        textView1 = findViewById(R.id.farmerTextView);
        textView2 = findViewById(R.id.bagTypeTxtView);
        textView3 = findViewById(R.id.qualityTxtView);
        textView4 = findViewById(R.id.unitPrceTxtView);
        textView5 = findViewById(R.id.weightTextView);
        textView6 = findViewById(R.id.totalPriceTxtView);
        paySelection = findViewById(R.id.paymentOptions);
        getDataFromPreviousActivity();
        ArrayAdapter<String> paymntTypes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, paymentModes);
        paySelection.setAdapter(paymntTypes);

    }

    private void getDataFromPreviousActivity() {
        Intent intent = getIntent();
        String getFarmer = intent.getStringExtra("selectedFarmer");
        String bagType = intent.getStringExtra("selectedBagType");
        String qualitySelection = intent.getStringExtra("selectdQlty");
        String weight = intent.getStringExtra("totalWeight");
        textView1.setText(getFarmer);
        textView2.setText(bagType);
        textView3.setText(qualitySelection);
        textView5.setText(weight);
    }

}