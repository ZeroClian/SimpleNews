package com.example.simplenews.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2019/12/1.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String Create_user = "create table user("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"mail text,"
            +"password text)";
    private Context mcontext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_user);
        Toast.makeText(mcontext, "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
