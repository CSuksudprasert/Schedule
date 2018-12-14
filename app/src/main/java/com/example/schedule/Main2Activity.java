package com.example.schedule;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static com.example.schedule.DatabaseHelper.COL_CLASS;
import static com.example.schedule.DatabaseHelper.COL_DAY;
import static com.example.schedule.DatabaseHelper.COL_SUB;
import static com.example.schedule.DatabaseHelper.COL_TIME;
import static com.example.schedule.DatabaseHelper.TABLE_NAME;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getName();

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;

    Spinner daySpin;
    Spinner timeS;

    String arrday[] = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
    String arrtime[] = {"08.30-10.15","10.20-12.05","13.00-14.45","14.50-16.35"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        daySpin =  (Spinner) findViewById(R.id.day1_spinner);
        timeS =  (Spinner) findViewById(R.id.timeStart_spinner);
        mHelper = new DatabaseHelper(this);
        mDb = mHelper.getWritableDatabase();

        ArrayAdapter<String> adapterDay = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrday);
        daySpin.setAdapter(adapterDay);

        ArrayAdapter<String> adapterTimeS = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrtime);
        timeS.setAdapter(adapterTimeS);

        Button addbutton = findViewById(R.id.add_button);
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
        EditText subject1editText = findViewById(R.id.subject1_editText);
        Spinner day1spinner = findViewById(R.id.day1_spinner);
        Spinner timeStartpinner =findViewById(R.id.timeStart_spinner);

        String subject = subject1editText.getText().toString();
        String day1 = day1spinner.toString();
        String time = timeStartpinner.toString();


        ContentValues cv = new ContentValues();
        cv.put(COL_SUB, subject);
        cv.put(COL_DAY , day1);
        cv.put(COL_TIME , time);
        mDb.insert(TABLE_NAME ,null, cv);

        finish();
    }
}
