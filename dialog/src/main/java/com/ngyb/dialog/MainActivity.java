package com.ngyb.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar pb = findViewById(R.id.pb);
        pb.setMax(100);
        pb.setProgress(33);
    }

    public void click(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e(TAG, "onClick: 否");
            }
        });
        dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e(TAG, "onClick: 是");
            }
        });
        dialog.show();
    }

    public void click1(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        final String[] str1 = {"成青青", "南宫燚滨"};
        builder1.setSingleChoiceItems(str1, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s = str1[which];
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder1.show();

    }

    public void click2(View view) {
        final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        final String[] str2 = {"android", "java", "c", "web", "linux"};
        final boolean[] check2 = {true, false, false, false, false};
        builder2.setMultiChoiceItems(str2, check2, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });
        builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < str2.length; i++) {
                    if (check2[i]) {
                        stringBuilder.append(str2[i] + " ");
                    }
                }
                Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder2.show();
    }

    public void click3(View view) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("正在加载中.......");
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dialog.setMax(100);
                for (int i = 0; i < 100; i++) {
                    dialog.setProgress(i);
                    SystemClock.sleep(50);
                }
                dialog.dismiss();
            }
        }).start();
    }
}
