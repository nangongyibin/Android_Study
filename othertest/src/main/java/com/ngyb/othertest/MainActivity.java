package com.ngyb.othertest;

import android.content.Intent;
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
    private Button layout;
    private Button cl;
    private Button downloadOrUpload;

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
        decodeFile.setOnClickListener(this);
        layout.setOnClickListener(this);
        cl.setOnClickListener(this);
        downloadOrUpload.setOnClickListener(this);
    }

    private void initView() {
        decodeFile = findViewById(R.id.decodeFile);
        layout = findViewById(R.id.layout);
        cl = findViewById(R.id.cl);
        downloadOrUpload = findViewById(R.id.downloadOrUpload);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.decodeFile:
                decodeFile();
                break;
            case R.id.layout:
                Intent intent = new Intent(this, LayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.cl:
                Intent intent1 = new Intent(this, ClActivity.class);
                startActivity(intent1);
                break;
            case R.id.downloadOrUpload:
                Intent intent2 = new Intent(this, DownloadOrUploadActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private void decodeFile() {
        Bitmap bitmap = BitmapFactory.decodeFile("/storage/emulated/0/Pictures/favicon.png");
        if (bitmap != null) {
            Log.e(TAG, "decodeFile: yes");
        } else {
            Log.e(TAG, "decodeFile: no");
        }
    }
}
