package com.ngyb.leaks;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/3 13:53
 */
public class MyApplication extends Application {
    public static Context ngyb;
    private RefWatcher watcher;

    public static RefWatcher getWatcher(Context context){
        MyApplication ma = (MyApplication) context.getApplicationContext();
        return ma.watcher;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ngyb = this;
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        watcher = LeakCanary.install(this);
    }
}
