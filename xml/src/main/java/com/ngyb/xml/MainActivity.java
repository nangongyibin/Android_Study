package com.ngyb.xml;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<SmsBean> mSmsBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        genneratorData();
        //请求权限
        requestPermission();
    }


    /**
     * 请求权限
     */
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 7219);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 7219 && permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限申请通过
                Toast.makeText(this, "权限给予成功", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请给予读写SD卡的权限", Toast.LENGTH_SHORT).show();
        }
    }

    private void genneratorData() {
        mSmsBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SmsBean smsBean = new SmsBean();
            smsBean.setName(i + "ngyb");
            smsBean.setPhone("1853486721" + i);
            mSmsBeans.add(smsBean);
        }
    }

    public void click(View view) {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<Smss>");
        for (SmsBean smsBean : mSmsBeans) {
            sb.append("<Sms>");
            sb.append("<name>");
            sb.append(smsBean.getName());
            sb.append("</name>");
            sb.append("<phone>");
            sb.append(smsBean.getPhone());
            sb.append("</phone>");
            sb.append("</Sms>");
        }
        sb.append("</Smss>");
        String str = sb.toString();
        String path = Environment.getExternalStorageDirectory().getPath();
        File file = new File(path, "Confid.xml");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(str.getBytes());
            fos.close();
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void click1(View view) {
        try {
            XmlSerializer xs = Xml.newSerializer();
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path, "ConfigCopy.xml");
            FileOutputStream fos = new FileOutputStream(file);
            xs.setOutput(fos,"UTF-8");
            xs.startDocument("UTF-8",true);
            xs.startTag(null,"Smss");
            for (SmsBean smsBean : mSmsBeans) {
                xs.startTag(null,"Sms");
                xs.startTag(null,"name");
                xs.text(smsBean.getName());
                xs.endTag(null,"name");
                xs.startTag(null,"phone");
                xs.text(smsBean.getPhone());
                xs.endTag(null,"phone");
                xs.endTag(null,"Sms");
            }
            xs.endTag(null,"Smss");
            xs.endDocument();
            fos.close();
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }
}
