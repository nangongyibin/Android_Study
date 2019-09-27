package com.ngyb.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/9/27 22:22
 */
public class county extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        Toast.makeText(context, "县:"+resultData, Toast.LENGTH_SHORT).show();
        Log.e("县:", resultData );
        setResultData("习大大发放了12.5斤大米");
    }
}
