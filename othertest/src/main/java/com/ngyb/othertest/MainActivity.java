package com.ngyb.othertest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button decodeFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initView();
        initListener();
    }

    private void initListener() {
        decodeFile.setOnClickListener(this
        );
    }

    private void initView() {
        decodeFile = findViewById(R.id.decodeFile);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.decodeFile:
                decodeFile();
                break;
        }
    }

    private void decodeFile() {
        Bitmap bitmap = BitmapFactory.decodeFile("/storage/emulated/0/Pictures/favicon.png");
        if (bitmap != null) {
            Log.e(TAG, "decodeFile: yes");
        } else {
            Log.e(TAG, "decodeFile: no" );
        }
    }
}
