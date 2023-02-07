package com.example.crmapp.responses;

import com.example.crmapp.classes.Driver;

import java.util.ArrayList;

public class StatusResponse {

    /**
     * Status : Success
     * Message : Record Inserted Successfully
     * Data : 3
     */

    private String Status;
    private String Message;
    private Object Data;

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

    public Object getData() {
        return Data;
    }

    public void setData(Object Data) {
        this.Data = Data;
    }


}