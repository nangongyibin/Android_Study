package com.ngyb.orocle;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private EditText et;
    private Button insert;
    private Button query;
    private String pwd = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        insert = findViewById(R.id.insert);
        query = findViewById(R.id.query);
        SQLiteDatabase.loadLibs(this);
        insert.setOnClickListener(this);
        query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert:
                insert();
                break;
            case R.id.query:
                query();
                break;
        }
    }

    private void query() {
        Orocle orocle = new Orocle(this);
        SQLiteDatabase db = orocle.getWritableDatabase(pwd);
        Cursor cursor = db.query("ngyb", new String[]{"username"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            Log.e(TAG, "query: " + name);
        }
        cursor.close();
        db.close();
    }

    private void insert() {
        String name = et.getText().toString().trim();
        Orocle orocle = new Orocle(this);
        SQLiteDatabase db = orocle.getWritableDatabase(pwd);
        ContentValues values = new ContentValues();
        values.put("username", name);
        db.insert("ngyb", null, values);
        db.close();
        et.setText("");
    }
}
