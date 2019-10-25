package com.ngyb.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/10/17 15:24
 */
public class MyView extends View {
    private static final String TAG = "MyView";
    private int pg = 0;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "onMeasure: ");
        setMeasuredDimension(721, 912);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(TAG, "onLayout: ");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw: ");
        //初始化画笔
        Paint paint = new Paint();
        //初始化画笔的颜色
        paint.setColor(Color.RED);
        //画笔的宽度
        paint.setStrokeWidth(20f);
        //画线
//        canvas.drawLine(77f, 99f, 721f, 912f, paint);
        //画圆
//        canvas.drawCircle(100f,100f,79f,paint);
        //画图
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.haha);
//        canvas.drawBitmap(bitmap,new Matrix(),paint);
        //画三角形
//        Path path = new Path();
//        path.lineTo(10f, 10f);
//        path.lineTo(10f, 721f);
//        path.lineTo(721f, 10f);
//        canvas.drawPath(path, paint);
        //画弧
        RectF rectF = new RectF(0f, 0f, 195f, 195f);
        canvas.drawArc(rectF, 0, pg, false, paint);
        super.onDraw(canvas);
    }

    public void setProgress(int progress) {
        this.pg = progress;
        postInvalidate();
    }
}
