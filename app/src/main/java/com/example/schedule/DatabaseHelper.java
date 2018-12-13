package com.example.schedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Schedule.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Schedule";
    public static final String COL_SUB = "Subject";
    public static final String COL_DAY = "title";
    public static final String COL_TIMESTART = "number";
    public static final String COL_TIMEFINISH = "image";

    private static final String SQL_CREATE_TABLE_SCHEDULE
            = "CREATE TABLE " + TABLE_NAME + "("
            + COL_SUB + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_DAY + " TEXT,"
            + COL_TIMESTART + " TEXT,"
            + COL_TIMEFINISH + " TEXT "
            + ")";

    public DatabaseHelper(Context context) { super(context,"Schedule.db",null,1); }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_SCHEDULE);

        ContentValues cv = new ContentValues();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
