package com.pescod.coolweather.util;

import android.text.TextUtils;

import com.pescod.coolweather.db.CoolWeatherDB;
import com.pescod.coolweather.model.City;
import com.pescod.coolweather.model.Country;
import com.pescod.coolweather.model.Province;

/**
 * Created by Administrator on 2015/12/20.
 */
public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProviceResponse(
            CoolWeatherDB coolWeatherDB,String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if (allProvinces!=null&&allProvinces.length>0){
                for(String p:allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProviceName(array[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public synchronized static boolean handleCityResponse(
            CoolWeatherDB coolWeatherDB,String response,int provinceID){
        if(!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if(allCities!=null&&allCities.length>0){
                for(String c:allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityName(array[1]);
                    city.setCityCode(array[0]);
                    city.setProvinceID(provinceID);
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public synchronized static boolean handleCountryResponse(
            CoolWeatherDB coolWeatherDB,String response,int cityID){
        if (!TextUtils.isEmpty(response)){
            String[] allCountries = response.split(",");
            if(allCountries!=null&&allCountries.length>0){
                for(String c:allCountries){
                    String[] array = c.split("\\|");
                    Country country = new Country();
                    country.setCountryName(array[1]);
                    country.setCountryCode(array[0]);
                    country.setCityID(cityID);
                    coolWeatherDB.saveCountry(country);
                }
                return true;
            }
        }
        return false;
    }
}
