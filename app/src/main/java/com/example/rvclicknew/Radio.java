package com.example.rvclicknew;

public class Radio {
    private String radioName;
    private String radioURL;

    private final String radioPic;


    public void setRadioURL(String radioURL) {
        this.radioURL = radioURL;
    }


    public Radio(String radioName,String radioURL,String radioPic) {
        this.radioName = radioName;
        this.radioURL=radioURL;
        this.radioPic=radioPic;
    }

    public void setRadioName(String radioName) {
        this.radioName = radioName;
    }
    public String getRadioPic(){return radioPic;}
    public String getRadioURL(){return radioURL;}
    public String getRadioName() {
        return radioName;
    }
}

