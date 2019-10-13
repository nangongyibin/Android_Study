package com.ngyb.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/10/4 10:33
 */
public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyOpenHelper(Context context) {
        super(context, "ngyb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(_id integer primary key autoincrement,name varchar(25),phone varchar(11),money integer)");
        db.execSQL("insert into user ('name','phone','money') values ('小成','15935416849',7219)");
        db.execSQL("insert into user ('name','phone','money') values ('小梁','18534867219',9127)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
