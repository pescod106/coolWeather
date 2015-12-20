package com.pescod.coolweather.model;

/**
 * Created by Administrator on 2015/12/20.
 */
public class Province {
    private int id;
    private String proviceName;
    private String provinceCode;

    public void setId(int id) {
        this.id = id;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setProviceName(String proviceName) {
        this.proviceName = proviceName;
    }

    public int getId() {
        return id;
    }

    public String getProviceName() {
        return proviceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }
}
