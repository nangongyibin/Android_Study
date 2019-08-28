package com.ngyb.login;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/8/28 16:50
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditText mUser;
    private EditText mPwd;
    private CheckBox mCb;
    private Button mLogin;
    private SharedPreferences mSp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //找控件
        mUser = findViewById(R.id.user);
        mPwd = findViewById(R.id.pwd);
        mCb = findViewById(R.id.cb);
        mLogin = findViewById(R.id.login);
        mSp = getSharedPreferences("Config", 0);
        //请求权限
        requestPermission();
        mLogin.setOnClickListener(this);
    }

    /**
     * 请求权限
     */
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 7219);
        } else {
            //回显
            LoginBeforeDeal();
        }
    }

    /**
     * 登陆之前的逻辑处理
     */
    private void LoginBeforeDeal() {
        boolean isCheck = mSp.getBoolean("是否记录密码", false);
        if (isCheck) {
            readFile();
        }
    }

    /**
     * 读信息
     */
    private void readFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = Environment.getExternalStorageDirectory();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file.getCanonicalPath() + "/logininfo.txt");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuffer sb = new StringBuffer();
                String line = null;
                if ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                final String[] split = sb.toString().split("##");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mUser.setText(split[0]);
                        mPwd.setText(split[1]);
                        //回显是否选中记录密码
                        mCb.setChecked(true);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                loginDeal();
                break;
        }
    }

    /**
     * 登陆逻辑处理
     */
    private void loginDeal() {
        String username = mUser.getText().toString().trim();
        String password = mPwd.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
        } else {
            boolean checked = mCb.isChecked();
            //保存是否记住密码
            mSp.edit().putBoolean("是否记录密码", checked).apply();
            if (checked) {
                //保存用户名和密码
                boolean b = writeFile(username, password);
                if (b) {
                    Toast.makeText(this, "信息保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "信息保存失败", Toast.LENGTH_SHORT).show();
                }
            }
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 7219 && permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Log.e(TAG, "onRequestPermissionsResult: " + grantResults[0]);
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限申请通过
                LoginBeforeDeal();
            }
        } else {
            Toast.makeText(this, "请给予读写SD卡的权限", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 写信息
     *
     * @param username
     * @param password
     * @return
     */
    private boolean writeFile(String username, String password) {
        String content = username + "##" + password;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File file = Environment.getExternalStorageDirectory();
                FileOutputStream fos = new FileOutputStream(file.getCanonicalPath() + "/logininfo.txt");
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(content);
                bw.close();
                osw.close();
                fos.close();
                return true;
            } catch (IOException e) {
                Log.e(TAG, "writeFile: " + e.getLocalizedMessage().toString());
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
