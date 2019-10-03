package com.ngyb.serve;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/9/27 23:17
 */
public class SpecialRadioReceiver extends BroadcastReceiver {
    private static final String TAG = "SpecialRadioReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.intent.action.SCREEN_ON")) {
            Log.e(TAG, "onReceive: 屏幕亮");
        } else if (action.equals("android.intent.action.SCREEN_OFF")) {
            Log.e(TAG, "onReceive: 屏幕暗");
        }
    }
}
