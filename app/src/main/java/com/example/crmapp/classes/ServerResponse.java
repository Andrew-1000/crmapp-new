package com.example.crmapp.classes;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    @SerializedName("status")
    String status;

    @SerializedName("resultCode")
    int resultCode;

    @SerializedName("fullname")
    String fullname;

    @SerializedName("customer_number")
    String customer_number;

    public String getStatus() {
        return status;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getFullname() {
        return fullname;
    }

    public String getCustomer_number() {
        return customer_number;
    }
}