package com.example.crmapp.classes;

public class Driver {

    private String ID;
    private String f_name;
    private String l_name;
    private String email;
    private String phone_no;
    private String close;

    public Driver(String f_name, String l_name, String email, String phone_no, String close, String status) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.phone_no = phone_no;
        this.close = close;
        this.status = status;
    }

    private String status;



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
