package com.ngyb.leaks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.leakcanary.RefWatcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpUtils.getInstance(MyApplication.ngyb);
//        int i = 1/0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = MyApplication.getWatcher(this);
        watcher.watch(this);
    }
}
