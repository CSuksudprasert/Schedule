package com.example.schedule;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.schedule.DatabaseHelper.COL_DAY;
import static com.example.schedule.DatabaseHelper.COL_SUB;
import static com.example.schedule.DatabaseHelper.COL_TIME;
import static com.example.schedule.DatabaseHelper.TABLE_NAME;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getName();

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;

    EditText subject1editText;
    EditText dayeditText;
    EditText timeeditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mHelper = new DatabaseHelper(this);
        mDb = mHelper.getWritableDatabase();



        Button addbutton = findViewById(R.id.add2_button);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doInsertItem_class();
            }
        });


    }

    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    private void doInsertItem_class() {
        subject1editText = findViewById(R.id.subject1_editText);
        dayeditText =  findViewById(R.id.day2_editText);
        timeeditText = findViewById(R.id.time2_editText);

        String subject = subject1editText.getText().toString();
        String day1 = dayeditText.toString();
        String time = timeeditText.toString();


        ContentValues cv = new ContentValues();
        cv.put(COL_SUB, subject);
        cv.put(COL_DAY , day1);
        cv.put(COL_TIME , time);
        mDb.insert(TABLE_NAME ,null, cv);

        finish();
    }
}
