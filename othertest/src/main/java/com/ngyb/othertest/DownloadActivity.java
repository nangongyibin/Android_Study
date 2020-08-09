package com.ngyb.othertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/8/9 19:31
 */
public class DownloadActivity extends AppCompatActivity {
    private static final String TAG = "DownloadActivity";
    private EditText et;
    private ProgressBar pb;
    private Button download;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        et = findViewById(R.id.et);
        pb = findViewById(R.id.pb);
        download = findViewById(R.id.download);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = et.getText().toString().trim();
                HttpUtils httpUtils = new HttpUtils();
                httpUtils.download(path, getFileName(path), true, new RequestCallBack<File>() {
                    @Override
                    public void onSuccess(ResponseInfo<File> responseInfo) {
                        Toast.makeText(DownloadActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        Log.e(TAG, "onLoading: "+total+"==="+current );
                        pb.setMax((int) total);
                        pb.setProgress((int) current);
                    }
                });
            }
        });
    }

    private String getFileName(String path) {
        String[] arr = path.split("/");
        int length = arr.length;
        String data = getFilesDir().getPath();
        String filename = data + "/" + arr[length - 1];
        Log.e(TAG, "getFileName: "+filename );
        return filename;
    }
}
