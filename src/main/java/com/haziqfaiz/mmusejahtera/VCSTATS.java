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

    public void setName(String name) {this.name = name;}
    public void setDate(String date) {this.date = date;}
    public void setAppointment(String appointment) {this.appointment = appointment;}
    public void setFirstDose(String firstDose) {this.firstDose = firstDose;}
    public void setSecondDose(String secondDose) {this.secondDose = secondDose;}
    public void setCapacity(String capacity) {this.capacity = capacity;}

    public String getVC_NAME(){return name;}
    public String getDATE(){return date;}
    public String getAPPOINTMENT(){return appointment;}
    public String getFIRST_DOSE(){return firstDose;}
    public String getSECOND_DOSE(){return secondDose;}
    public String getCAPACITY(){return capacity;}
}