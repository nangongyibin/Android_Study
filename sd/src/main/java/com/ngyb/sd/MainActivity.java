package com.ngyb.sd;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //请求权限
        requestPermission();
    }


    /**
     * 请求权限
     */
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 7219);
        } else {
            //回显
            getSize();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 7219 && permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Log.e(TAG, "onRequestPermissionsResult: " + grantResults[0]);
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限申请通过
                getSize();
            }
        } else {
            Toast.makeText(this, "请给予读写SD卡的权限", Toast.LENGTH_SHORT).show();
        }
    }


    private void getSize() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path);
            long totalSpace = file.getTotalSpace();//总空间
            long usableSpace = file.getUsableSpace();//可用空间
            String totalSize = Formatter.formatFileSize(this, totalSpace);
            Log.e(TAG, "getSize: totalSize" + totalSize);
            String usableSize = Formatter.formatFileSize(this, usableSpace);
            Log.e(TAG, "getSize: " + usableSize);
        }
    }
}
