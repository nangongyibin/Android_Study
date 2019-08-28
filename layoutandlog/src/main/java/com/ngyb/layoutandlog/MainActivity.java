package com.ngyb.layoutandlog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ll);
        mBtn = findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("111111");
                Log.v(TAG, "onClick: 222222");
                Log.d(TAG, "onClick: 333333");
                Log.i(TAG, "onClick: 444444");
                Log.w(TAG, "onClick: 555555");
                Log.e(TAG, "onClick: 666666");
            }
        });
    }
}
