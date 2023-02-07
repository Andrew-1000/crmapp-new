package com.example.crmapp.classes;

public class Vehicle {

    private String ID;
    private String number_plate;


    public Vehicle(String number_plate) {
        this.number_plate = number_plate;
    }

    private String status;



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNumber_plate() {
        return number_plate;
    }

    public void setNumber_plate(String number_plate) {
        this.number_plate = number_plate;
    }


}