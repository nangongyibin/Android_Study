package com.ngyb.animation;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ImageView mIv;
    private NotificationManager mNm;

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
//        TranslateAnimation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
//                TranslateAnimation.RELATIVE_TO_PARENT, 1.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT,
//                1.0f);
//        translateAnimation.setDuration(2000);
//        translateAnimation.setRepeatCount(2);
//        translateAnimation.setRepeatMode(Animation.REVERSE);
//        mIv.startAnimation(translateAnimation);
        ObjectAnimator oa = ObjectAnimator.ofFloat(mIv, "translationX", 0.0f, 10.0f, 20.0f, 30.0f);
        oa.setDuration(2000);
        oa.start();
    }

    public void click2(View view) {
//        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, ScaleAnimation.RELATIVE_TO_SELF);
//        scaleAnimation.setDuration(2000);
//        scaleAnimation.setRepeatCount(2);
//        scaleAnimation.setRepeatMode(Animation.REVERSE);
//        mIv.startAnimation(scaleAnimation);
        ObjectAnimator oa = ObjectAnimator.ofFloat(mIv, "scaleX", 1.0f, 2.0f);
        oa.setDuration(2000);
        oa.start();
    }

    public void click3(View view) {
//        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
//        alphaAnimation.setDuration(2000);
//        alphaAnimation.setRepeatCount(2);
//        alphaAnimation.setRepeatMode(Animation.REVERSE);
//        mIv.startAnimation(alphaAnimation);
        ObjectAnimator oa = ObjectAnimator.ofFloat(mIv, "alpha", 0.0f, 1.0f);
        oa.setDuration(2000);
        oa.start();
    }

    public void click4(View view) {
//        AnimationSet animationSet = new AnimationSet(true);
////        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF,
////                RotateAnimation.RELATIVE_TO_SELF);
////        TranslateAnimation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
////                TranslateAnimation.RELATIVE_TO_PARENT, 1.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT,
////                1.0f);
////        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, ScaleAnimation
// .RELATIVE_TO_SELF);
////        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
////        animationSet.addAnimation(rotateAnimation);
////        animationSet.addAnimation(translateAnimation);
////        animationSet.addAnimation(scaleAnimation);
////        animationSet.addAnimation(alphaAnimation);
////        animationSet.setDuration(2000);
////        animationSet.setRepeatCount(2);
////        animationSet.setRepeatMode(Animation.REVERSE);
////        mIv.startAnimation(animationSet);
        ObjectAnimator oa = ObjectAnimator.ofFloat(mIv, "rotation", 0.0f, 90.0f, 180.0f, 270.0f, 360.0f);
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(mIv, "alpha", 0.0f, 1.0f);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(mIv, "scaleX", 1.0f, 2.0f);
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(mIv, "translationX", 0.0f, 10.0f, 20.0f, 30.0f);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(oa,oa1,oa2,oa3);
        set.setDuration(2000);
        set.setTarget(mIv);
        set.start();
    }

    public void click0(View view) {
        Log.e(TAG, "click0: 旋转");
//        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF,
//                RotateAnimation.RELATIVE_TO_SELF);
//        rotateAnimation.setDuration(2000);
//        rotateAnimation.setRepeatCount(2);
//        rotateAnimation.setRepeatMode(Animation.REVERSE);
//        mIv.startAnimation(rotateAnimation);
        ObjectAnimator oa = ObjectAnimator.ofFloat(mIv, "rotation", 0.0f, 90.0f, 180.0f, 270.0f, 360.0f);
        oa.setDuration(2000);
        oa.start();
    }

    public void click5(View view) {
        mNm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri uri = Uri.parse("tel:" + 10010);
        intent.setData(uri);
        PendingIntent pi = PendingIntent.getActivity(this, 7219, intent, PendingIntent.FLAG_ONE_SHOT);
        Notification build = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            build = new Notification.Builder(this)
                    .setContentTitle("小青")
                    .setContentText("老地方见")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentIntent(pi)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .build();
            mNm.notify(9127,build);
        }
    }

    public void click6(View view) {
        mNm.cancel(9127);
    }
}
