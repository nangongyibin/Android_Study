package com.ngyb.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/9/23 11:39
 */
public class AppReceiver extends BroadcastReceiver {
    private static final String TAG = "AppReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.intent.action.PACKAGE_ADDED")) {
            Log.e(TAG, "onReceive: 应用安装了");
        }
        if (action.equals("android.intent.action.PACKAGE_REMOVED")) {
            Log.e(TAG, "onReceive: 应用卸载了");
        }
    }
}
