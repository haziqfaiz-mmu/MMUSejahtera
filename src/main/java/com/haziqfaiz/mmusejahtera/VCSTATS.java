package com.haziqfaiz.mmusejahtera;

public class VCSTATS {

    private String name;
    private String date;
    private String appointment;
    private String firstDose;
    private String secondDose;
    private String capacity;

    public VCSTATS(){}

    public VCSTATS(String name, String date,String appointment, String firstDose, String secondDose, String capacity){

        this.name = name;
        this.date = date;
        this.appointment = appointment;
        this.firstDose = firstDose;
        this.secondDose = secondDose;
        this.capacity = capacity;
    }
}
