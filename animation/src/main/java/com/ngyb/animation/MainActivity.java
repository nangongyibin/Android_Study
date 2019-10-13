package com.ngyb.animation;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIv = findViewById(R.id.iv);
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "你点不到我");
            }
        });
    }

    public void click(View view) {
        mIv.setBackgroundResource(R.drawable.animation);
        AnimationDrawable animation = (AnimationDrawable) mIv.getBackground();
        animation.run();
//        animation.start();
    }

    public void click1(View view) {
        Log.e(TAG, "click0: 平移");
        TranslateAnimation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 1.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT,
                1.0f);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatCount(2);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        mIv.startAnimation(translateAnimation);
    }

    public void click2(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, ScaleAnimation.RELATIVE_TO_SELF);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(2);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        mIv.startAnimation(scaleAnimation);
    }

    public void click3(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setRepeatCount(2);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        mIv.startAnimation(alphaAnimation);
    }

    public void click4(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF,
                RotateAnimation.RELATIVE_TO_SELF);
        TranslateAnimation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 1.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT,
                1.0f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, ScaleAnimation.RELATIVE_TO_SELF);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(2000);
        animationSet.setRepeatCount(2);
        animationSet.setRepeatMode(Animation.REVERSE);
        mIv.startAnimation(animationSet);
    }

    public void click0(View view) {
        Log.e(TAG, "click0: 旋转");
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF,
                RotateAnimation.RELATIVE_TO_SELF);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        mIv.startAnimation(rotateAnimation);
    }
}
