package com.ngyb.serve;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/9/28 17:43
 */
public class ScreenService extends Service {
    private SpecialRadioReceiver mSpecialRadioReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mSpecialRadioReceiver = new SpecialRadioReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(mSpecialRadioReceiver, intentFilter);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mSpecialRadioReceiver);
        super.onDestroy();
    }
}
