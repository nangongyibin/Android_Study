package com.ngyb.othertest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 10:22
 */
public class MyView extends View {
    private static final String TAG = "MyView";

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 事件处理
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = false;
        if (result) {
            Log.e(TAG, "onTouchEvent: 事件处理");
        } else {
            Log.e(TAG, "onTouchEvent: 事件不处理");
        }
        return result;
    }


    /**
     * 事件消费
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int i = 2;
        switch (i) {
            case 0:
                //事件消费
                Log.e(TAG, "dispatchTouchEvent: 事件消费");
                return true;
            case 1:
                Log.e(TAG, "dispatchTouchEvent: 事件不消费，事件回传给父类的onTouchEvent方法");
                return false;
            case 2:
                Log.e(TAG, "dispatchTouchEvent: 事件向下传递");
                return super.dispatchTouchEvent(event);
            default:
                return super.dispatchTouchEvent(event);
        }
    }
}
