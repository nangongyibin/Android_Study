package com.ngyb.encryption.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.ngyb.encryption.R;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/3 17:39
 */
public class AsciiActivity extends AppCompatActivity {

    private EditText et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ascii);
        et = findViewById(R.id.et);
    }

    public void ascii(View view) {
        String str = et.getText().toString().trim();
        char[] chars = str.toCharArray();
        String vs = "";
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int value = c;
            vs = vs + value + "";
            et.setText(vs);
        }
    }
}
