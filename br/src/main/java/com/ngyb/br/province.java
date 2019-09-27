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
 * 日期：2019/9/27 22:20
 */
public class province extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        Toast.makeText(context, "省:"+resultData, Toast.LENGTH_SHORT).show();
        Log.e("省:", resultData );
        setResultData("习大大发放了大米50斤");
    }
}
