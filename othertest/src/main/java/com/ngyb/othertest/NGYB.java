package com.ngyb.othertest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/8/1 16:57
 */
public class NGYB extends ViewGroup {
    private static final String TAG = "NGYB";
    private int measuredHeight;
    private int measuredWidth;

    public NGYB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        View childAt = getChildAt(0);
        childAt.measure(0, 0);
        measuredWidth = childAt.getMeasuredWidth();
        measuredHeight = childAt.getMeasuredHeight();
        Log.e(TAG, "onMeasure: " + measuredWidth + "====" + measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childAt = getChildAt(0);
        childAt.layout(100, 10, measuredWidth + 100, measuredHeight + 10);
    }
}
