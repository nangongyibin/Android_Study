package com.ngyb.zxing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.activity.CaptureActivity;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/3 14:53
 */
public class SZxingActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szxing);
        tv = findViewById(R.id.tv);
    }

    public void s(View view) {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, 520);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 520) {
            String qrcode_result = data.getStringExtra("qrcode_result");
            tv.setText(qrcode_result);
            if (qrcode_result.contains("http")) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse("http:" + qrcode_result));
                startActivity(intent);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
