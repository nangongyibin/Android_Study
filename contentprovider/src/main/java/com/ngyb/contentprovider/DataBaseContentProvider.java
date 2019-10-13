package com.ngyb.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/10/4 10:42
 */
public class DataBaseContentProvider extends ContentProvider {
    private static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private MyOpenHelper mMyOpenHelper;
    private static int INSERT_SUCCESS = 12;
    private static int DELETE_SUCCESS = 13;
    private static int UPDATE_SUCCESS = 14;
    private static int QUERY_SUCCESS = 15;

    static {
        mUriMatcher.addURI("ngyb.ltz", "insert", INSERT_SUCCESS);
        mUriMatcher.addURI("ngyb.ltz", "delete", DELETE_SUCCESS);
        mUriMatcher.addURI("ngyb.ltz", "update", UPDATE_SUCCESS);
        mUriMatcher.addURI("ngyb.ltz", "query", QUERY_SUCCESS);
    }

    @Override
    public boolean onCreate() {
        mMyOpenHelper = new MyOpenHelper(getContext());
        return false;
    }

    @androidx.annotation.Nullable
    @Override
    public Cursor query(@androidx.annotation.NonNull Uri uri, @androidx.annotation.Nullable String[] projection,
                        @androidx.annotation.Nullable String selection, @androidx.annotation.Nullable String[] selectionArgs,
                        @androidx.annotation.Nullable String sortOrder) {
        int code = mUriMatcher.match(uri);
        if (code == QUERY_SUCCESS) {
            SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();
            Cursor user = db.query("user", projection, selection, selectionArgs, null, null, null);
            getContext().getContentResolver().notifyChange(uri, null);
            return user;
        } else {
            throw new IllegalArgumentException("路径错误");
        }

//        return null;
    }

    @androidx.annotation.Nullable
    @Override
    public String getType(@androidx.annotation.NonNull Uri uri) {
        return null;
    }

    @androidx.annotation.Nullable
    @Override
    public Uri insert(@androidx.annotation.NonNull Uri uri, @androidx.annotation.Nullable ContentValues values) {
        int code = mUriMatcher.match(uri);
        if (code == INSERT_SUCCESS) {
            SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();
            long user = db.insert("user", null, values);
            getContext().getContentResolver().notifyChange(uri, null);
            return Uri.parse(uri + "/" + user);
        } else {
            throw new IllegalArgumentException("路径错误");
        }
//        return null;
    }

    @Override
    public int delete(@androidx.annotation.NonNull Uri uri, @androidx.annotation.Nullable String selection,
                      @androidx.annotation.Nullable String[] selectionArgs) {
        int code = mUriMatcher.match(uri);
        if (code == DELETE_SUCCESS) {
            SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();
            int user = db.delete("user", selection, selectionArgs);
            if (user > 0) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            return user;
        } else {
            throw new IllegalArgumentException("路径错误");
        }

//        return 0;
    }

    @Override
    public int update(@androidx.annotation.NonNull Uri uri, @androidx.annotation.Nullable ContentValues values,
                      @androidx.annotation.Nullable String selection, @androidx.annotation.Nullable String[] selectionArgs) {
        int code = mUriMatcher.match(uri);
        if (code == UPDATE_SUCCESS) {
            SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();
            int user = db.update("user", values, selection, selectionArgs);
            if (user > 0) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            return user;
        } else {
            throw new IllegalArgumentException("路径错误");
        }
//        return 0;
    }
}
