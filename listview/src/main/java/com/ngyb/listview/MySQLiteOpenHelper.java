package com.ngyb.listview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/9/2 21:26
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "MySQLiteOpenHelper";

    public MySQLiteOpenHelper(Context context) {
        super(context, "ngyb.bd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table personinfo(id int primary key ,name varchar(20),phone int)");
        Log.e(TAG, "onCreate: 数据库创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
