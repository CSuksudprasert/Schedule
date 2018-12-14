package com.example.schedule;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static com.example.schedule.DatabaseHelper.COL_ID;
import static com.example.schedule.DatabaseHelper.COL_SUB;
import static com.example.schedule.DatabaseHelper.COL_TIME;
import static com.example.schedule.DatabaseHelper.TABLE_NAME;

public class Editclass extends AppCompatActivity {

    private EditText msubjectedittext;
    private Spinner mtimeSSpinner;
    private Button madd2Button;

    private long mid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editclass);

        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        String time = intent.getStringExtra("time");
        mid = intent.getLongExtra("id" , 0);

        msubjectedittext = findViewById(R.id.subject1_editText);
        mtimeSSpinner = findViewById(R.id.timeS_spinner);
        madd2Button = findViewById(R.id.add2_button);

        msubjectedittext.setText(subject);

        madd2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: บันทึกข้อมูลใหม่ลง db
                DatabaseHelper helper = new DatabaseHelper(Editclass.this);
                SQLiteDatabase db = helper.getWritableDatabase();

                String newSubject = msubjectedittext.getText().toString().trim();
                String newTime = mtimeSSpinner.toString().trim();

                ContentValues cv = new ContentValues();
                cv.put(COL_SUB, newSubject);
                cv.put(COL_TIME, newTime);

                db.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(mid)}
                );
                finish();
            }
        });
    }
}
