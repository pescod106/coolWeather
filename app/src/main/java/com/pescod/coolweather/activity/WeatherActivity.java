package com.pescod.coolweather.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pescod.coolweather.R;
import com.pescod.coolweather.util.HttpCallbackListener;
import com.pescod.coolweather.util.HttpUtil;
import com.pescod.coolweather.util.Utility;

/**
 * Created by Administrator on 2015/12/22.
 */
public class WeatherActivity extends Activity {
    private LinearLayout weatherInfoLayout;
    //用于显示城市名
    private TextView cityNameText;
    //用于显示发布时间
    private TextView publishText;
    //用于显示天气描述信息
    private TextView weatherDespText;
    //用于显示气温1
    private TextView temp1Text;
    //用于显示气温2
    private TextView temp2Text;
    //用于显示当前日期
    private TextView currentDateText;
    //切换城市按钮
    private Button switchCity;
    //更新天气按钮
    private Button refeshWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weather_layout);

        //初始化各控件
        weatherInfoLayout = (LinearLayout)findViewById(R.id.weather_info_layout);
        cityNameText = (TextView)findViewById(R.id.city_name);
        publishText = (TextView)findViewById(R.id.publish_text);
        weatherDespText = (TextView)findViewById(R.id.weather_desp);
        temp1Text = (TextView)findViewById(R.id.temp1);
        temp2Text = (TextView)findViewById(R.id.temp2);
        currentDateText = (TextView)findViewById(R.id.current_data);
        switchCity = (Button)findViewById(R.id.switch_city);
        refeshWeather = (Button)findViewById(R.id.refesh_weather);

        String countryCode = getIntent().getStringExtra("country_code");
        if(!TextUtils.isEmpty(countryCode)){
            publishText.setText("同步中...");
            weatherInfoLayout.setVisibility(View.INVISIBLE);
            cityNameText.setVisibility(View.INVISIBLE);
            queryWeatherCode(countryCode);
        }else{
            // 没有县级代号时就直接显示本地天气s
            showWeather();
        }
    }

    /**
     * 点击home按钮
     * @param view
     */
    public void btn_home(View view){
        Intent intent = new Intent(WeatherActivity.this,ChooseAreaActivity.class);
        intent.putExtra("from_weather_activity",true);
        startActivity(intent);
        finish();
    }

    /**
     * 点击刷新按钮
     * @param view
     */
    public void btn_refesh(View view){
        publishText.setText("同步中...");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this);
        String weatherCode = preferences.getString("country_code","");
        if(!TextUtils.isEmpty(weatherCode)){
            queryWeatherInfo(weatherCode);
        }
    }

    /**
     * 查询县级代号所对应的天气代号。
     * @param weatherCode
     */
    private void queryWeatherCode(String countyCode){
        String address = "http://www.weather.com.cn/data/list3/city" +
                countyCode + ".xml";
        queryFromServer(address,"countryCode");
    }

    /**
     * 查询天气代号所对应的天气。
     * @param weatherCode
     */
    private void queryWeatherInfo(String weatherCode){
        String address = "http://www.weather.com.cn/data/cityinfo/" +
                weatherCode + ".html";
        queryFromServer(address,"weatherCode");
    }

    /**
     * 根据传入的地址和类型去向服务器查询天气代号或者天气信息。
     * @param address
     * @param type
     */
    private void queryFromServer(final String address,final String type){
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                if ("countryCode".equals(type)){
                    if (!TextUtils.isEmpty(response)){
                        // 从服务器返回的数据中解析出天气代号
                        String[] array = response.split("\\|");
                        if (array!=null&&array.length==2){
                            String weatherCode = array[1];
                            queryWeatherInfo(weatherCode);
                        }
                    }
                }else if ("weatherCode".equals(type)){
                    Utility.handleWeatherReponse(WeatherActivity.this,response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showWeather();
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        publishText.setText("同步失败");
                    }
                });
            }
        });
    }

    /**
     * 从SharedPreferences文件中读取存储的天气信息，并显示到界面上。
     */
    private void showWeather(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        cityNameText.setText(preferences.getString("city_name",""));
        temp1Text.setText(preferences.getString("temp1",""));
        temp2Text.setText(preferences.getString("temp2",""));
        weatherDespText.setText(preferences.getString("weather_desp",""));
        publishText.setText("今天"+preferences.getString("publish_time","")+"发布");
        currentDateText.setText(preferences.getString("current_date",""));
        weatherInfoLayout.setVisibility(View.VISIBLE);
        cityNameText.setVisibility(View.VISIBLE);
    }
}
