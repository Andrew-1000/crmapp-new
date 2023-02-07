package com.example.crmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.crmapp.classes.ApiClient;
import com.example.crmapp.classes.ServerResponse;
import com.example.crmapp.interfaces.ApiInterface;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextInputEditText username,password;
    MaterialButton login;
    ProgressDialog progressDialog;

    //create/session shared preference
    String SHARED_PREF_XML="user_details_xml";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize views
        username=(TextInputEditText) findViewById(R.id.username);
        password=(TextInputEditText) findViewById(R.id.password);
        login=findViewById(R.id.loginButton);
        progressDialog= new ProgressDialog(this);

        //if user has already login then skip this login

        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF_XML,MODE_PRIVATE);
        String customerNumber=sharedPreferences.getString("customer_number",null);
        if(customerNumber!=null)
        {
            Intent intent=new Intent(getApplicationContext(),SplashScreen.class);
            startActivity(intent);
            finish();
        }

        //when button is clicked

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().equals(""))
                {
                    username.setError("username is required");
                }
                else if(password.getText().toString().trim().equals(""))
                {
                    password.setError("password is required");
                }
                else
                {
                    //call login method
                    progressDialog.setTitle("AUTHENTICATE");
                    progressDialog.setMessage("please wait...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    Log.d("tag","check here" );
                    login_method();
                }
            }
        });
    }
    public  void login_method()
    {
        String username2=username.getText().toString();
        String password2=password.getText().toString();

        Call<ServerResponse> call= ApiClient.getMainClient().create(ApiInterface.class).login_method(username2,password2);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                //check the user
                if(response.code()==200)
                {
                    //connection is there
                    if(response.body().getStatus().equals("ok"))
                    {
                        if(response.body().getResultCode()==1)
                        {
                            //save user to shared pref
                            SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF_XML,MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();

                            // save the user
                            editor.putString("customer_number",response.body().getCustomer_number());
                            editor.putString("fullname",response.body().getFullname());
                            editor.apply();

                            progressDialog.dismiss();

                            Toast.makeText(MainActivity.this, "Welcome ", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),SplashScreen.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            //username or password is not correct
                            Toast.makeText(getApplicationContext(),"incorrect username or password",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                    else
                    {
                        //status is not OK
                        Toast.makeText(getApplicationContext(),"server error",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                    }
                }
                else
                {
                    //no connection
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }}