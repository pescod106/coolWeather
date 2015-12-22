package com.pescod.coolweather.util;

/**
 * Created by Administrator on 2015/12/20.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
