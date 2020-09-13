package com.example.user.feedbackform;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 15-02-2017.
 */
public class DBAdapter extends SQLiteOpenHelper {

     static int Database_Version=1;
    static String DBname="mydb432";
static String ct="Create table feedbacktable(name text,date text,address text,email text,phone text,dob text,comments text,image text );";
    public DBAdapter(Context context)
    {
        super(context,DBname,null,Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(ct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("Drop table if exists feedbacktable");
        onCreate(db);
    }
}
