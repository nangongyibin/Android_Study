package com.ngyb.listview;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<Person> mList;
    private ListView mLv;
    private String[] menu = new String[]{"java", "c", "web", "html", "mysql"};
    private List<Map<String, String>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLv = findViewById(R.id.lv);
        mList = new ArrayList<>();
//        myAdapter myAdapter = new myAdapter();
//        mLv.setAdapter(myAdapter);
//        MyAdapter myAdapter1 = new MyAdapter();
//        mLv.setAdapter(myAdapter1);
//        mLv.setAdapter(new ArrayAdapter<String>(this,R.layout.item,R.id.tv1,menu));
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "wangwu");
        map.put("phone", "18534867219");
        list.add(map);
        mLv.setAdapter(new SimpleAdapter(this, list, R.layout.item, new String[]{"name", "phone"}, new int[]{R.id.tv1, R.id.tv2}));
    }

    public void show(View view) {
        MySQLiteOpenHelper my = new MySQLiteOpenHelper(this);
        SQLiteDatabase db = my.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "lisi");
        contentValues.put("phone", "18534867219");
        db.insert("personinfo", null, contentValues);
        Cursor cursor = db.query("personinfo", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                int phone = cursor.getInt(2);
                Person person = new Person();
                person.setName(name);
                person.setPhone(phone);
                mList.add(person);
            }
            cursor.close();
        }
        db.close();
        my.close();
        mLv.setAdapter(new MyAdapter());
    }

    public class myAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            TextView textView = new TextView(MainActivity.this);
//            textView.setText("天道酬勤");
            TextView textView;
            if (convertView == null) {
                textView = new TextView(MainActivity.this);
            } else {
                textView = (TextView) convertView;
            }
            textView.setText("天道酬勤");
            return textView;
        }
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (mList == null) {
                return 0;
            } else {
                return mList.size();
            }
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
//                convertView =View.inflate(MainActivity.this,R.layout.item,null);
//                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item,null);
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.item, null);
            }
            Person person = mList.get(position);
            TextView tv1 = convertView.findViewById(R.id.tv1);
            TextView tv2 = convertView.findViewById(R.id.tv2);
            tv1.setText(person.getName());
            tv2.setText(person.getPhone() + "");
            return convertView;
        }
    }
}
