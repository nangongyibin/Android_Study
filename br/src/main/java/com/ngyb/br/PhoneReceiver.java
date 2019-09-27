package com.ngyb.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/9/16 06:54
 */
public class PhoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        SharedPreferences sp = context.getSharedPreferences("config", 0);
        String pre = sp.getString("phone", "5211314");
        if (resultData != null && resultData.startsWith("1")) {
            setResultData(pre + resultData);
        }
    }
}
