package com.ngyb.othertest;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/8/1 16:16
 */
public class TwoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent);
    }

    public void open(View view) {
        MyApplication ma = (MyApplication) getApplication();
        ma.activity.finish();
        this.finish();
    }

    public void close(View view) {
//        int i = 1/0;
        Intent intent = new Intent(this, OneActivity.class);
        intent.putExtra("close",true);
        startActivity(intent);
    }
}
