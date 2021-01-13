package com.example.covidtracker.Model;

public class CountryModel {

    private String countryname;

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCountrycase() {
        return countrycase;
    }

    public void setCountrycase(String countrycase) {
        this.countrycase = countrycase;
    }

    private String countrycase;

    public CountryModel(String countryname, String countrycase) {
        this.countryname = countryname;
        this.countrycase = countrycase;
    }
}
