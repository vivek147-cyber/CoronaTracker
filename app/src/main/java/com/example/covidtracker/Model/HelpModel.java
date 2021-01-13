package com.example.covidtracker.Model;

public class HelpModel {

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getHelpno() {
        return helpno;
    }

    public void setHelpno(String helpno) {
        this.helpno = helpno;
    }

    private String stateName;
    private String helpno;

    public HelpModel(String stateName, String helpno) {
        this.stateName = stateName;
        this.helpno = helpno;
    }
}
