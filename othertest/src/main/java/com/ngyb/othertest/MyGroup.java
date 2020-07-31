package com.ngyb.othertest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 10:19
 */
public class MyGroup extends RelativeLayout {
    private static final String TAG = "MyGroup";

    public MyGroup(Context context, AttributeSet attrs) {
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
        boolean result = true;
        if (result) {
            Log.e(TAG, "onTouchEvent: 事件处理");
        } else {
            Log.e(TAG, "onTouchEvent: 事件不处理");
        }
        return result;
    }

    /**
     * 事件分发
     *
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
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
                return super.dispatchTouchEvent(ev);
            default:
                return super.dispatchTouchEvent(ev);
        }
    }

    /**
     * 拦截事件
     *
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = false;
        if (result) {
            Log.e(TAG, "onInterceptTouchEvent: 事件拦截");
        } else {
            Log.e(TAG, "onInterceptTouchEvent: 事件不拦截");
        }
        return result;
    }
}
