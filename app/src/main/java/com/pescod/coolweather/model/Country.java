package com.pescod.coolweather.model;

/**
 * Created by Administrator on 2015/12/20.
 */
public class Country {
    private int id;
    private String countryName;
    private String countryCode;
    private int cityID;

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityID() {
        return cityID;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getId() {
        return id;
    }
}
