package com.example.crmapp.interfaces;
import com.example.crmapp.classes.Driver;
import com.example.crmapp.classes.ServerResponse;
import com.example.crmapp.classes.Vehicle;
import com.example.crmapp.responses.StatusResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    String Base_Url ="http://afrimac.earltech.co.ke/API/";
//    Add Farmer
    @FormUrlEncoded
    @POST("farmer.php/insert")
    Call<StatusResponse> insertFarmer(@FieldMap Map<String, String>Map);

    @FormUrlEncoded
    @POST("update")
    Call<StatusResponse> updateFarmer(@FieldMap Map<String, String> Map);

    @FormUrlEncoded
    @GET("delete")
    Call<StatusResponse> deleteFarmer(@Query("id")String id);


    @GET("get")
    Call<StatusResponse> getFarmer(@Query("id")String id);

    @GET("farmer.php/getall")
    Call<StatusResponse> getAllFarmers();

//driver - for Dispatch module

    @GET("driver.php/getall")
    Call<ArrayList<Driver>> getAllDrivers();

    @GET("vehicle.php/getall")
    Call<ArrayList<Vehicle>> callVehicle();

//    @GET("county.php/getall")
//    Call<ArrayList<County>> callCounty();

//    @GET("farmer.php/getall")
//    Call<ArrayList<Farmer>> callFarmer();

//    expense Module
    @FormUrlEncoded
    @POST("expenses.php/insert")
    Call<StatusResponse> insertExpenses(@FieldMap Map<String, String>Map);

//    CASH Module
    @FormUrlEncoded
    @POST("cash.php/insert")
    Call<StatusResponse> insertCash(@FieldMap Map<String, String>Map);

    //Login
    @FormUrlEncoded
    @POST("login.php")
    Call<ServerResponse>login_method(@Field("username")String username, @Field("password")String password);



}
