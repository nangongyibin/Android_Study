package com.ngyb.othertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/8/1 10:05
 */
public class ScrollActivity extends AppCompatActivity implements View.OnClickListener {
    private Button scrollto, scrollby;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        scrollby = findViewById(R.id.scrollby);
        scrollto = findViewById(R.id.scrollto);
        tv = findViewById(R.id.tv);
        scrollby.setOnClickListener(this);
        scrollto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scrollto:
                tv.scrollTo(-100, (int) tv.getY());
                break;
            case R.id.scrollby:
                tv.scrollBy(-200, (int) tv.getY());
                break;
        }
    }
}
