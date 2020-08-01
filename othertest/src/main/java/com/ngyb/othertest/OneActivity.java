package com.ngyb.othertest;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/8/1 16:13
 */
public class OneActivity extends AppCompatActivity {
    private static final String TAG = "OneActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent);
    }

    public void open(View view) {
        Intent intent = new Intent(this, TwoActivity.class);
        MyApplication ma = (MyApplication) getApplication();
        ma.activity = this;
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        boolean close = intent.getBooleanExtra("close", false);
        Log.e(TAG, "onNewIntent: " + close);
    }

    public void close(View view) {
    }
}
