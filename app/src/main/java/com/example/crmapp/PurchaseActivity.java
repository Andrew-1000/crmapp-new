package com.example.crmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class PurchaseActivity extends AppCompatActivity {
    String[] farmersList = {"FarmerA", "FarmerB","FarmerC", "FarmerD"};
    String[] bagType = {"BagTypeA", "BagTypeB", "BagTypeC", "BagTypeD"};
    String[] quantityParamter = {"Grams", "Kilograms", "Milligrams"};
    String[] percentageTypes = {"10", "20", "50", "75", "100"};
    AppCompatAutoCompleteTextView farmerSelection;
    AppCompatAutoCompleteTextView bagTypeSelection;
    AppCompatAutoCompleteTextView qtyParamterSlction;
    AppCompatAutoCompleteTextView paymnt_types;
    AppCompatAutoCompleteTextView percentageSelection;
    TextInputEditText tareWeight, totlWeightCaptured;
    MaterialButton addTareWeight, payButton;
    //R.id.percentage
    //R.id.tareWeightValue
//    Material Button//R.id.addTareWeightBtn
//    TextInputEditText totalWeightCaptured
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        farmerSelection = findViewById(R.id.farmerOptions);
        bagTypeSelection = findViewById(R.id.bagTypeOptions);
        qtyParamterSlction = findViewById(R.id.quantityOptions);
        percentageSelection = findViewById(R.id.percentage);
        tareWeight = findViewById(R.id.tareWeightValue);
        addTareWeight = findViewById(R.id.addTareWeightBtn);
        totlWeightCaptured = findViewById(R.id.totalWeightCaptured);
        payButton = findViewById(R.id.pay);
//        paymnt_types = findViewById(R.id.payment_type);

        //An Array adapter to populate data onto our list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, farmersList);
        farmerSelection.setAdapter(arrayAdapter);

        ArrayAdapter<String> bagTypArrayAdptr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bagType);
        bagTypeSelection.setAdapter(bagTypArrayAdptr);

        ArrayAdapter<String> qntyParameter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quantityParamter);
        qtyParamterSlction.setAdapter(qntyParameter);

        ArrayAdapter<String> percentageIndicator = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, percentageTypes );
        percentageSelection.setAdapter(percentageIndicator);

//        ArrayAdapter<String> paymntTypes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, payment_types);
//        paymnt_types.setAdapter(paymntTypes);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFormTDataToAnotherActivity();
            }
        });
    }

    private void sendFormTDataToAnotherActivity() {
        String selectedFarmer = farmerSelection.getText().toString();
        String selectedBagType = bagTypeSelection.getText().toString();
        String selectedQuality = qtyParamterSlction.getText().toString();
        String percentage = percentageSelection.getText().toString();
        String tarWeight = tareWeight.getText().toString();
        String totalWeight = totlWeightCaptured.getText().toString();
        Intent intent = new Intent(getApplicationContext(), PayActivity.class);
        intent.putExtra("selectedFarmer", selectedFarmer);
        intent.putExtra("selectedBagType", selectedBagType);
        intent.putExtra("selectdQlty", selectedQuality);
        intent.putExtra("percentage", percentage);
        intent.putExtra("tareWeight", tarWeight);
        intent.putExtra("totalWeight", totalWeight);

        startActivity(intent);
    }
}