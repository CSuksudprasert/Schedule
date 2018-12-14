package com.example.schedule;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import static com.example.schedule.DatabaseHelper.COL_SUB;
import static com.example.schedule.DatabaseHelper.COL_TIME;

public class Editclass extends AppCompatActivity {

    private EditText mSubjectEditText;
    private EditText mTimeEditText;
    private Button mAddButton;

    private long mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editclass);

        Intent intent = getIntent();
        String subject = intent.getStringExtra("Subject");
        String time = intent.getStringExtra("Time");
        mId = intent.getLongExtra("id", 0);

        mSubjectEditText = findViewById(R.id.subject2_editText);
        mTimeEditText = findViewById(R.id.time2_editText);
        mAddButton = findViewById(R.id.add2_button);

        mSubjectEditText.setText(subject);
        mTimeEditText.setText(time);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: บันทึกข้อมูลใหม่ลง db
                DatabaseHelper helper = new DatabaseHelper(Editclass.this);
                SQLiteDatabase db = helper.getWritableDatabase();

                String newTitle = mSubjectEditText.getText().toString().trim();
                String newNumber = mTimeEditText.getText().toString().trim();

                ContentValues cv = new ContentValues();
                cv.put(COL_SUB, newTitle);
                cv.put(COL_TIME, newNumber);

                db.update(
                        DatabaseHelper.TABLE_NAME,
                        cv,
                        COL_SUB + " = ?",
                        new String[]{String.valueOf(mId)}
                );
                finish();
            }
        });
    }
}