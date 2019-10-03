package com.ngyb.animation;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIv = findViewById(R.id.iv);
    }

    public void click(View view) {
        mIv.setBackgroundResource(R.drawable.animation);
        AnimationDrawable animation = (AnimationDrawable) mIv.getBackground();
        animation.run();
//        animation.start();
    }
}
