package com.example.crmapp.responses;

import com.example.crmapp.classes.Driver;

import java.util.ArrayList;
import java.util.List;

public class DriverResponseList {
    private String Status;
    private String Message;
    private ArrayList<Driver> Data;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public ArrayList<Driver> getData() {
        return Data;
    }

    public void setData(ArrayList<Driver> Data) {
        this.Data = Data;
    }

}
