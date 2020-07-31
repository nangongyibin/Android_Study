package com.ngyb.othertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 10:16
 */
public class EventActivity extends AppCompatActivity {
    private static final String TAG = "EventActivity";
    private MyGroup mg;
    private MyView mv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mg = findViewById(R.id.mg);
        mv = findViewById(R.id.mv);
        mg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: mg");
            }
        });
        mv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: mv");
            }
        });
    }
}