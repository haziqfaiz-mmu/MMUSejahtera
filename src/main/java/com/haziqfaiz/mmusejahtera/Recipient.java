package com.haziqfaiz.mmusejahtera;

public class Recipient {

    private String id;
    private String name;
    private String password;
    private String firstDoseDate;
    private String firstDoseStatus;
    private String secondDoseDate;
    private String secondDoseStatus;
    private String vc;

    Recipient(){}
    Recipient(String id,String name, String password,String firstDoseDate, String firstDoseStatus, String secondDoseDate, String secondDoseStatus){

        this.id = id;
        this.name = name;
        this.password = password;
        this. firstDoseDate = firstDoseDate;
        this.firstDoseStatus = firstDoseStatus;
        this.secondDoseDate = secondDoseDate;
        this.secondDoseStatus = secondDoseStatus;

    }

    public String getRECIPIENT_ID(){
        return this.id;
    }
    public String getRECIPIENT_NAME(){
        return this.name;
    }
    public String getRECIPIENT_PASSWORD(){
        return this.password;
    }
    public String getFIRST_DOSE_DATE(){
        return this.firstDoseDate;
    }
    public String getFIRST_DOSE_STATUS(){
        return this.firstDoseStatus;
    }
    public String getSECOND_DOSE_DATE(){
        return this.secondDoseDate;
    }
    public String getSECOND_DOSE_STATUS(){
        return this.secondDoseStatus;
    }
    public String getVC_NAME(){return this.vc;}

    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setFirstDoseDate(String firstDoseDate){this.firstDoseDate = firstDoseDate;}
    public void setFirstDoseStatus(String firstDoseStatus){
        this.firstDoseStatus = firstDoseStatus;
    }
    public void setSecondDoseDate(String secondDoseDate){
        this.secondDoseDate = secondDoseDate;
    }
    public void setSecondDoseStatus(String secondDoseStatus){
        this.secondDoseStatus = secondDoseStatus;
    }
    public void setVc(String vc){
        this.vc = vc;
    }

}
