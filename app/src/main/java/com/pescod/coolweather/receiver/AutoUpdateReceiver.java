package com.pescod.coolweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.pescod.coolweather.service.AutoUpdateService;

/**
 * Created by Administrator on 2015/12/22.
 */
public class AutoUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, AutoUpdateService.class);
        context.startActivity(intent1);
    }
}
