package com.example.covidtracker.Model;

public class StateModel {


    private String statename;

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getStatecase() {
        return statecase;
    }

    public void setStatecase(String statecase) {
        this.statecase = statecase;
    }

    private String statecase;

    public StateModel(String statename, String statecase) {
        this.statename = statename;
        this.statecase = statecase;
    }
}
