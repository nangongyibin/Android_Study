package com.ngyb.encryption;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ngyb.encryption.utils.AsciiActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ascii(View view) {
        Intent intent = new Intent(this, AsciiActivity.class);
        startActivity(intent);
    }
}
