package com.ngyb.call;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.drm.DrmStore;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPhone = findViewById(R.id.phone);
    }

    public void click(View view) {
        //动态申请权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 7219);
        } else {
            call();
        }
    }

    /**
     * 打电话的逻辑处理
     */
    private void call() {
        String phone = mPhone.getText().toString().trim();
        if (phone == null) {
            Toast.makeText(this, "phone is null", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phone));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 7219) {
            if (permissions[0].equals(Manifest.permission.CALL_PHONE)) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                }
            }
        } else {
            Toast.makeText(this, "请给予打电话的权限", Toast.LENGTH_SHORT).show();
        }
    }
}
