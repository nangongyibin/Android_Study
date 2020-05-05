package com.ngyb.encryption;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ngyb.utils.StreamUtils;
import com.ngyb.utils.encryption.DES;
import com.ngyb.utils.encryption.MD5;
import com.ngyb.utils.encryption.SHA1;
import com.ngyb.utils.encryption.SHA256;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private TextView result;
    private static final String TAG = "MainActivity";
    private InputStream is;
    private FileOutputStream fos;
    private InputStream ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        result = findViewById(R.id.result);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        copy();
    }

    private void copy() {
        try {
            File file = new File(getFilesDir(), Constant.tomcat);
            is = getAssets().open(Constant.tomcat);
            fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Log.e(TAG, "copy: ");
            if (is != null && fos != null) {
                try {
                    is.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void ascii(View view) {
        String str = et.getText().toString().trim();
        char[] chars = str.toCharArray();
        String vs = "";
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int value = c;
            vs = vs + value + "";
            result.setText(vs);
        }
    }

    public void bytebit(View view) {
        try {
            String str1 = "A";
            String str2 = "我爱你";
            byte[] bytes = str1.getBytes();
            Log.e(TAG, "bytebit: " + bytes.length);
            byte[] bytes1 = str2.getBytes();
            Log.e(TAG, "bytebit: " + bytes1.length);
            byte[] bytes2 = str2.getBytes("GBK");
            Log.e(TAG, "bytebit: " + bytes2.length);
            char[] array = str2.toCharArray();
            for (char c : array) {
                int value = c;
                Log.e(TAG, "bytebit: " + value);
                String binaryString = Integer.toBinaryString(value);
                Log.e(TAG, "bytebit: " + binaryString);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void message(View view) {
        String path = getFilesDir().getAbsolutePath() + File.separator + Constant.tomcat;
        String md5 = MD5.Md5File("MD5", path);
        Log.e(TAG, "message: " + md5);
        String sha1File = SHA1.SHA1File("SHA-1", path);
        Log.e(TAG, "message: " + sha1File);
        String sha256File = SHA256.SHA256File("SHA-256", path);
        Log.e(TAG, "message: " + sha256File);
    }

    public void server(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL("http://it.nangongyibin.com:8080/resource/a.txt").openConnection();
                    Log.e(TAG, "server: " + (conn == null));
                    Log.e(TAG, "server: " + (conn.getResponseCode()));
                    Log.e(TAG, "server: " + (conn == null) + "====" + conn.getResponseCode());
                    Log.e(TAG, "server: " + conn.getResponseCode());
                    ins = conn.getInputStream();
                    File file = new File(getFilesDir().getAbsolutePath() + File.separator + "f.txt");
                    String result = StreamUtils.convertStreamToString(ins);
                    Log.e(TAG, "server: " + result);
                    FileWriter write = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(write);
                    bw.write(DES.desEncrypt("DES/CBC/PKCS5Padding", "DES", "12345678", result));
                    bw.close();

                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    String str = br.readLine();
                    Log.e(TAG, "server: " + str);
                    String des = DES.desDecrypt("DES/CBC/PKCS5Padding", "DES", "12345678", str);
                    Log.e(TAG, "server: " + des);
                } catch (IOException e) {
                    Log.e(TAG, "server: " + e.getLocalizedMessage().toString());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void login(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LRUtils.login6();
            }
        }).start();

    }
}
