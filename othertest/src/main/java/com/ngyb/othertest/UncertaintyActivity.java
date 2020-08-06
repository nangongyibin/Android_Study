package com.ngyb.othertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/8/5 22:37
 */
public class UncertaintyActivity extends AppCompatActivity {

    private FlexibleRichTextView frtv31,frtv61,frtv71;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncertainty);
        initView();
        initData();
        loadFragment();
    }

    private void loadFragment() {
        FragmentManager manager = getSupportFragmentManager();
        NoHaveFragment noHaveFragment = new NoHaveFragment();
        manager.beginTransaction().replace(R.id.fl,noHaveFragment).commit();
    }

    private void initData() {
        frtv31.setText("$\\beta = \\frac{H_{S}}{H_{N}}$");
        frtv61.setText("$H_{S}$");
        frtv71.setText("$H_{N}$");
    }

    private void initView() {
        frtv31 = findViewById(R.id.frtv3_1);
        frtv61 = findViewById(R.id.frtv6_1);
        frtv71 = findViewById(R.id.frtv7_1);
    }
}
