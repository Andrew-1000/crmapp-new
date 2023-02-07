package com.example.crmapp.interfaces;
import com.example.crmapp.classes.Driver;
import com.example.crmapp.responses.DriverResponseList;
import com.example.crmapp.responses.StatusResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
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
    Call<ArrayList<StatusResponse>> getAllDriver();

//    expense Module
    @FormUrlEncoded
    @POST("expenses.php/insert")
    Call<StatusResponse> insertExpenses(@FieldMap Map<String, String>Map);

//    CASH Module
    @FormUrlEncoded
    @POST("cash.php/insert")
    Call<StatusResponse> insertCash(@FieldMap Map<String, String>Map);



}
