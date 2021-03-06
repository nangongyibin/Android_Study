package com.ngyb.othertest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button decodeFile;
    private Button layout;
    private Button cl;
    private Button downloadOrUpload;
    private Button event;
    private Button level;
    private Button multipleentries;
    private Button scroll;
    private Button testIntent;
    private Button vg;
    private Button menu;
    private Button asynctask;
    private Button edit;
    private Button uncertainty;
    private Button download;

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
        event.setOnClickListener(this);
        level.setOnClickListener(this);
        multipleentries.setOnClickListener(this);
        scroll.setOnClickListener(this);
        testIntent.setOnClickListener(this);
        vg.setOnClickListener(this);
        menu.setOnClickListener(this);
        asynctask.setOnClickListener(this);
        edit.setOnClickListener(this);
        uncertainty.setOnClickListener(this);
        download.setOnClickListener(this);
    }

    private void initView() {
        decodeFile = findViewById(R.id.decodeFile);
        layout = findViewById(R.id.layout);
        cl = findViewById(R.id.cl);
        downloadOrUpload = findViewById(R.id.downloadOrUpload);
        event = findViewById(R.id.event);
        level = findViewById(R.id.level);
        multipleentries = findViewById(R.id.multipleentries);
        scroll = findViewById(R.id.scroll);
        testIntent = findViewById(R.id.testIntent);
        vg = findViewById(R.id.vg);
        menu = findViewById(R.id.menu);
        asynctask = findViewById(R.id.asynctask);
        edit = findViewById(R.id.edit);
        uncertainty = findViewById(R.id.uncertainty);
        download = findViewById(R.id.download);
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
            case R.id.event:
                Intent intent3 = new Intent(this, EventActivity.class);
                startActivity(intent3);
                break;
            case R.id.level:
                Intent intent4 = new Intent(this, LevelActivity.class);
                startActivity(intent4);
                break;
            case R.id.multipleentries:
                Intent intent5 = new Intent(this, MultipleEntriesActivity.class);
                startActivity(intent5);
                break;
            case R.id.scroll:
                Intent intent6 = new Intent(this, ScrollActivity.class);
                startActivity(intent6);
                break;
            case R.id.testIntent:
                Intent intent7 = new Intent(this, OneActivity.class);
                startActivity(intent7);
                break;
            case R.id.vg:
                Intent intent8 = new Intent(this, VgActivity.class);
                startActivity(intent8);
                break;
            case R.id.menu:
                Intent intent9 = new Intent(this, MenuActivity.class);
                startActivity(intent9);
                break;
            case R.id.asynctask:
                Intent intent10 = new Intent(this, AsyncTaskActivity.class);
                startActivity(intent10);
                break;
            case R.id.edit:
                Intent intent11 = new Intent(this, EditActivity.class);
                startActivity(intent11);
                break;
            case R.id.uncertainty:
                Intent intent12 = new Intent(this, UncertaintyActivity.class);
                startActivity(intent12);
                break;
            case R.id.download:
                Intent intent13 = new Intent(this, DownloadActivity.class);
                startActivity(intent13);
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
