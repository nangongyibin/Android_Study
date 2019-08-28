package com.ngyb.fourclickevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/8/28 15:23
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn2, mBtn3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn2:
                Toast.makeText(this, "弹了个窗", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                Intent intent = new Intent(this, ThirdActivity.class);
                startActivity(intent);
                break;
        }
    }
}
