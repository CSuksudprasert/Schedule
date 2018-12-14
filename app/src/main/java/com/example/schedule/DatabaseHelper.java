package com.example.schedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {



    private static final String DATABASE_NAME = "Schedule.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Schedule";
    public static final String COL_ID = "_id";
    public static final String COL_SUB = "Subject";
    public static final String COL_DAY = "Day";
    public static final String COL_TIME = "Time";
    public static final String COL_CLASS = "Classroom";


    private static final String SQL_CREATE_TABLE_SCHEDULE
            = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_SUB + " TEXT,"
            + COL_DAY + " TEXT,"
            + COL_TIME + " TEXT,"
            + COL_CLASS + "TEXT"
            + ")";

    public DatabaseHelper(Context context) { super(context,"Schedule.db",null,1); }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_SCHEDULE);

        ContentValues cv = new ContentValues();
        cv.put(COL_SUB,"ภาษาไทย");
        cv.put(COL_DAY, "Monday");
        cv.put(COL_TIME,"08.30-10.15");
        cv.put(COL_CLASS,"1240");
        db.insert(TABLE_NAME,null,cv);

        cv = new ContentValues();
        cv.put(COL_SUB,"ภาษาไทย");
        cv.put(COL_DAY, "Tuesday");
        cv.put(COL_TIME,"10.20-12.05");
        cv.put(COL_CLASS,"1240");
        db.insert(TABLE_NAME,null,cv);

        cv = new ContentValues();
        cv.put(COL_SUB,"ภาษาไทย");
        cv.put(COL_DAY, "Wednesday");
        cv.put(COL_TIME,"13.00-14.45");
        cv.put(COL_CLASS,"1240");
        db.insert(TABLE_NAME,null,cv);

        cv = new ContentValues();
        cv.put(COL_SUB,"ภาษาไทย");
        cv.put(COL_DAY, "Thursday");
        cv.put(COL_TIME,"14.50-16.35");
        cv.put(COL_CLASS,"1240");
        db.insert(TABLE_NAME,null,cv);

        cv = new ContentValues();
        cv.put(COL_SUB,"ภาษาไทย");
        cv.put(COL_DAY, "Friday");
        cv.put(COL_TIME,"08.30-10.15");
        cv.put(COL_CLASS,"1240");
        db.insert(TABLE_NAME,null,cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
