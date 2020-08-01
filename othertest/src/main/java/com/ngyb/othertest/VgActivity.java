package com.ngyb.othertest;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/8/1 17:01
 */
public class VgActivity extends AppCompatActivity {
    private static final String TAG = "VgActivity";
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vg);
        tv = findViewById(R.id.tv);
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(200);
                int width = tv.getWidth();
                int height = tv.getHeight();
                Log.e(TAG, "run: " + width + "===" + height);
                int measuredWidth = tv.getMeasuredWidth();
                int measuredHeight = tv.getMeasuredHeight();
                Log.e(TAG, "run: " + measuredWidth + "====" + measuredHeight);
            }
        }.start();
    }
}
