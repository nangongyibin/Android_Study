package com.ngyb.zxing;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private String[] str = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, str, 1);
    }

    public void g(View view) {
        Intent intent = new Intent(this, GZxingActivity.class);
        startActivity(intent);
    }

    public void s(View view) {
        Intent intent = new Intent(this, SZxingActivity.class);
        startActivity(intent);
    }
}
