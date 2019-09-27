package com.ngyb.br;

import android.Manifest;
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
    private String[] str = {Manifest.permission.PROCESS_OUTGOING_CALLS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_SMS};
    private SpecialRadioReceiver mSpecialRadioReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEt = findViewById(R.id.et);
        init();
        AppReceiver appReceiver = new AppReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        registerReceiver(appReceiver, intentFilter);
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("com.ngyb.order.rice");
        registerReceiver(new province(), intentFilter1);
        registerReceiver(new city(), intentFilter1);
        registerReceiver(new county(), intentFilter1);
        registerReceiver(new village(), intentFilter1);
        registerReceiver(new farmer(), intentFilter1);
    }

    private void init() {
        if (ActivityCompat.checkSelfPermission(this, str[0]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,
                str[1]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, str, 7219);
        } else {
            PhoneReceiver myReceiver = new PhoneReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
            registerReceiver(myReceiver, filter);
        }
        if (ActivityCompat.checkSelfPermission(this, str[2]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,
                str[3]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, str, 9127);
        } else {
            startsms();
        }
    }

    public void startsms() {
        SmsReceiver myReceiver = new SmsReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(myReceiver, filter);
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
                PhoneReceiver myReceiver = new PhoneReceiver();
                IntentFilter filter = new IntentFilter();
                filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
                registerReceiver(myReceiver, filter);
            } else {
                Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 9127) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                if (permission.equals(Manifest.permission.READ_SMS)) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
                    }
                } else if (permission.equals(Manifest.permission.RECEIVE_SMS)) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            startsms();
        }
    }

    @Override
    public void onBackPressed() {

    }

    public void order(View view) {
        Intent intent = new Intent();
        intent.setAction("com.ngyb.order.rice");
//        sendOrderedBroadcast(intent,null);
        sendOrderedBroadcast(intent, null, new Final(), null, 1, "习大大发放了100斤大米", null);
    }

    public void disorder(View view) {
        Intent intent = new Intent(this, MyOrderReceiver.class);
        intent.setAction("com.ngyb.disorder");
        intent.putExtra("name", "我爱成青青一生一世");
        sendBroadcast(intent);
    }

    public void special(View view) {
        mSpecialRadioReceiver = new SpecialRadioReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(mSpecialRadioReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mSpecialRadioReceiver);
    }
}
