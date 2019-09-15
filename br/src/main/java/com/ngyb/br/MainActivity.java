package com.ngyb.br;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEt;
    private String[] str = {Manifest.permission.PROCESS_OUTGOING_CALLS, Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEt = findViewById(R.id.et);
        init();
    }

    private void init() {
        if (ActivityCompat.checkSelfPermission(this, str[0]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,
                str[1]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, str, 7219);
        } else {
            MyReceiver myReceiver = new MyReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
            registerReceiver(myReceiver, filter);
        }
    }

    public void call(View view) {
        String phone = mEt.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences sp = getSharedPreferences("config", 0);
            sp.edit().putString("phone", phone).apply();
            Toast.makeText(this, "您可以去打电话了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 7219) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                MyReceiver myReceiver = new MyReceiver();
                IntentFilter filter = new IntentFilter();
                filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
                registerReceiver(myReceiver, filter);
            } else {
                Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
