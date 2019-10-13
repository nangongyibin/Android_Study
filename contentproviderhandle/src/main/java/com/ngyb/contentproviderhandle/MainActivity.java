package com.ngyb.contentproviderhandle;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String[] permissions = new String[]{Manifest.permission.READ_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS};
    private MyAdapter mMyAdapter;
    private ListView mLv;
    private List<Contract> mContracts;
    private EditText mEt, mEt1, mEt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, permissions[1]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, permissions[3]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, permissions[4]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 7219);
        } else {
            Toast.makeText(this, "已有读取短信数据的权限", Toast.LENGTH_SHORT).show();
        }
        mLv = findViewById(R.id.lv);
        mEt = findViewById(R.id.et);
        mEt1 = findViewById(R.id.et1);
        mEt2 = findViewById(R.id.et2);
    }

    public void add(View view) {
        Uri uri = Uri.parse("content://ngyb.ltz/insert");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "小闫");
        contentValues.put("phone", "17582809533");
        contentValues.put("money", "0.001");
        Uri insert = getContentResolver().insert(uri, contentValues);
        Toast.makeText(this, "插入成功" + insert, Toast.LENGTH_SHORT).show();
    }

    public void delete(View view) {
        Uri uri = Uri.parse("content://ngyb.ltz/delete");
        int delete = getContentResolver().delete(uri, "name=?", new String[]{"小闫"});
        Toast.makeText(this, "删除成功" + delete, Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        Uri uri = Uri.parse("content://ngyb.ltz/update");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "闫梦楠");
        int update = getContentResolver().update(uri, contentValues, "name=?", new String[]{"小闫"});
        Toast.makeText(this, "更新成功" + update, Toast.LENGTH_SHORT).show();
    }

    public void query(View view) {
        Uri uri = Uri.parse("content://ngyb.ltz/query");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.e(TAG, "query: " + cursor.getInt(0) + "==" + cursor.getString(1) + "==" + cursor.getString(2) + "==" + cursor.getString(3));
            }
            cursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 7219) {
            if (permissions[0] == Manifest.permission.READ_SMS) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
                }
            }
            if (permissions[1] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
                }
            }
            if (permissions[2] == Manifest.permission.READ_EXTERNAL_STORAGE) {
                if (grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
                }
            }
            if (permissions[3] == Manifest.permission.READ_CONTACTS) {
                if (grantResults[3] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
                }
            }
            if (permissions[4] == Manifest.permission.WRITE_CONTACTS) {
                if (grantResults[4] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void backupsms(View view) {
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, permissions[1]) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "权限获取失败，请手动给予权限", Toast.LENGTH_SHORT).show();
        } else {
            backup();
        }
    }

    private void backup() {
        try {
            XmlSerializer xmlSerializer = Xml.newSerializer();
            String path = getFilesDir().getAbsoluteFile() + File.separator + "sms.xml";
            Log.e(TAG, "backup: " + path);
            File file = new File(path);
            FileOutputStream fos = new FileOutputStream(file);
            xmlSerializer.setOutput(fos, "UTF-8");
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "smss");
            Uri uri = Uri.parse("content://sms/");
            Cursor cursor = getContentResolver().query(uri, new String[]{"address", "body", "date"}, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    xmlSerializer.startTag(null, "sms");
                    xmlSerializer.startTag(null, "address");
                    String address = cursor.getString(0);
                    if (address == null || address.equals("") || address.equals("null")) {
                        xmlSerializer.text(" ");
                    } else {
                        xmlSerializer.text(address);
                    }
                    xmlSerializer.endTag(null, "address");
                    xmlSerializer.startTag(null, "body");
                    String body = cursor.getString(1);
                    if (body == null || body.equals("") || body.equals("null")) {
                        xmlSerializer.text(" ");
                    } else {
                        xmlSerializer.text(body);
                    }
                    xmlSerializer.endTag(null, "body");
                    xmlSerializer.startTag(null, "date");
                    String date = cursor.getString(2);
                    if (date == null || date.equals("") || date.equals("null")) {
                        xmlSerializer.text(" ");
                    } else {
                        xmlSerializer.text(date);
                    }
                    xmlSerializer.endTag(null, "date");

                    xmlSerializer.endTag(null, "sms");
                }
            }
            xmlSerializer.endTag(null, "smss");
            xmlSerializer.endDocument();
            Toast.makeText(this, "备份成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "备份失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void make(View view) {
        try {
            Uri uri = Uri.parse("content://sms/");
            ContentValues values = new ContentValues();
            values.put("address", "18534867219");
            values.put("body", "天道酬勤，追梦无疆");
            values.put("type", "1");
            values.put("date", System.currentTimeMillis());
            Uri insert = getContentResolver().insert(uri, values);
            Log.e(TAG, "make: " + insert.toString());
        } catch (Exception e) {
            Log.e(TAG, "make: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void queryContract(View view) {
        if (mContracts != null && mContracts.size() > 0) {
            mContracts.clear();
        } else {
            mContracts = new ArrayList<>();
        }
        mContracts.addAll(ContractUtils.queryContract(this));
        if (mMyAdapter == null) {
            mMyAdapter = new MyAdapter(this);
            mMyAdapter.setData(mContracts);
            mLv.setAdapter(mMyAdapter);
        } else {
            mMyAdapter.notifyDataSetChanged();
        }
    }

    public void insertContract(View view) {
        String name = mEt.getText().toString().trim();
        String phone = mEt1.getText().toString().trim();
        String email = mEt2.getText().toString().trim();
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri uri1 = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = getContentResolver().query(uri, new String[]{"contact_id"}, null, null, null);
        int count = cursor.getCount()+1;
        ContentValues value0 = new ContentValues();
        value0.put("contact_id",count);
        getContentResolver().insert(uri, value0);
        ContentValues value1 = new ContentValues();
        value1.put("raw_contact_id",count);
        value1.put("data1",name);
        value1.put("mimetype","vnd.android.cursor.item/name");
        getContentResolver().insert(uri1,value1);
        ContentValues value2 = new ContentValues();
        value2.put("raw_contact_id",count);
        value2.put("data1",phone);
        value2.put("mimetype","vnd.android.cursor.item/phone_v2");
        getContentResolver().insert(uri1,value2);
        ContentValues value3 = new ContentValues();
        value3.put("raw_contact_id",count);
        value3.put("data1",email);
        value3.put("mimetype","vnd.android.cursor.item/email_v2");
        getContentResolver().insert(uri1,value3);
    }
}
