package com.ngyb.sms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.drm.DrmStore;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mEtContent;
    private EditText mEtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 921);
        }
        setContentView(R.layout.activity_main);
        mEtContent = findViewById(R.id.et_content);
        mEtPhone = findViewById(R.id.et_phone);
    }


    public void send(View view) {
        String content = mEtContent.getText().toString().trim();
        String phone = mEtPhone.getText().toString().trim();
        SmsManager sms = SmsManager.getDefault();
        List<String> contents = sms.divideMessage(content);
        for (String s : contents) {
            sms.sendTextMessage(phone,null,s,null,null);
        }
        Toast.makeText(this, "短信发送成功", Toast.LENGTH_SHORT).show();
    }

    public void insert_content(View view) {
        Intent intent = new Intent(this, SmsActivity.class);
        startActivityForResult(intent, 9127);
    }

    public void addContract(View view) {
        Intent intent = new Intent(this, PhoneActivity.class);
        startActivityForResult(intent, 7219);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 521 && data != null) {
            String phone = data.getStringExtra("phone");
            mEtPhone.setText(phone);
        }
        if (resultCode == 520 && data != null) {
            String content = data.getStringExtra("content");
            mEtContent.setText(content);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "发送短信的权限获取成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "发送短信的权限获取失败", Toast.LENGTH_SHORT).show();
        }
    }
}
