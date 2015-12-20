package com.pescod.coolweather.model;

/**
 * Created by Administrator on 2015/12/20.
 */
public class City {
    private int id;
    private String cityName;
    private String cityCode;
    private int provinceID;

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public int getId() {
        return id;
    }

    public int getProvinceID() {
        return provinceID;
    }
}
