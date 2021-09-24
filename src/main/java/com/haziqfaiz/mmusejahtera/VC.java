package com.haziqfaiz.mmusejahtera;

public class VC {

    private String name;
    private String password;
    private String capacity;
    private String vaccineStock;

    public VC(){}

    public VC(String name, String password,String capacity, String vaccineStock){
        this.name = name;
        this.password = password;
        this.capacity = capacity;
        this.vaccineStock = vaccineStock;
    }

    public void setName(String name){this.name = name;}
    public void setPassword(String password){this.password = password;}
    public void setCapacity(String capacity){this.capacity = capacity;}
    public void setVaccineStock(String vaccineStock){this.vaccineStock = vaccineStock;}

    public String getVC_NAME(){return this.name;}
    public String getVC_PASSWORD(){return this.password;}
    public String getVC_CAPACITY(){return this.capacity;}
    public String getVC_VACCINE_STOCK(){return this.vaccineStock;}
}
