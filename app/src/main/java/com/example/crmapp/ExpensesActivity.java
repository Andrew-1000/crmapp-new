package com.example.crmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crmapp.classes.ApiClient;
import com.example.crmapp.interfaces.ApiInterface;
import com.example.crmapp.responses.StatusResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesActivity extends AppCompatActivity {
    TextInputEditText selectedDate;
    SimpleDateFormat simpleDateFormat;
    String datetime;

    MaterialButton saveExpenseButton,cancel_button;
    EditText pymnetPurpose,inputAmount;

    ApiInterface apiInterface;
    Activity activity;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        activity = ExpensesActivity.this;
        context = ExpensesActivity.this;
        apiInterface = ApiClient.getMainClient().create(ApiInterface.class);

        selectedDate = findViewById(R.id.dateOfPayment);
        pymnetPurpose = findViewById(R.id.pymnetPurpose);
        inputAmount = findViewById(R.id.inputAmount);

        MaterialButton showDateDialog = findViewById(R.id.saveExpenseButton);

        MaterialDatePicker.Builder<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("SELECT A DATE");
        final MaterialDatePicker<Long> materialDatePicker = materialDateBuilder.build();

        selectedDate.setOnClickListener(view -> {

            materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");

        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
//                simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aaa z", Locale.CANADA);
//                datetime = simpleDateFormat.format(materialDatePicker.getHeaderText());
//                Log.d("Here i the time", materihalDatePicker.getHeaderText());
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis(selection);
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String formattedDate  = format.format(calendar.getTime());
                selectedDate.setText(formattedDate);
            }

        });

        saveExpenseButton = findViewById(R.id.saveExpenseButton);
        cancel_button = findViewById(R.id.cancel_button);

        saveExpenseButton.setOnClickListener(view -> submitData());

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SplashScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    private void submitData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("expenseDate", selectedDate.getText().toString());
        map.put("pymnetPurpose", pymnetPurpose.getText().toString());
        map.put("amount", inputAmount.getText().toString());

        Call<StatusResponse> call=apiInterface.insertExpenses(map);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ExpensesActivity.this,  "Expense Recorded", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ExpensesActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(ExpensesActivity.this, "Some error occurred while calling the API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Toast.makeText(ExpensesActivity.this, "Some error occurred while calling the API [2]", Toast.LENGTH_SHORT).show();

            }
        });


    }
}