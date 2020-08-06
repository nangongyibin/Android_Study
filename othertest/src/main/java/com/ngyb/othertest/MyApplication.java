package com.ngyb.othertest;

import android.app.Activity;
import android.app.Application;
import android.os.Process;

import org.scilab.forge.jlatexmath.core.AjLatexMath;

import io.github.kbiakov.codeview.classifier.CodeProcessor;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/8/1 16:06
 */
public class MyApplication extends Application {
    public Activity activity = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                Process.killProcess(Process.myPid());
            }
        });

        AjLatexMath.init(this);
        CodeProcessor.init(this);
    }
}
