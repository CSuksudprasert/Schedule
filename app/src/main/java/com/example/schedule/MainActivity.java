package com.example.schedule;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.schedule.adapter.ClassListAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.schedule.DatabaseHelper.COL_CLASS;
import static com.example.schedule.DatabaseHelper.COL_DAY;
import static com.example.schedule.DatabaseHelper.COL_ID;
import static com.example.schedule.DatabaseHelper.COL_SUB;
import static com.example.schedule.DatabaseHelper.COL_TIME;
import static com.example.schedule.DatabaseHelper.TABLE_NAME;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<Item_class> mclassItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(MainActivity.this);
        mDb = mHelper.getWritableDatabase();

        Button addPhoneItemButton = findViewById(R.id.add1_button);
        addPhoneItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadClassData();
        setupListView();
    }

    private void loadClassData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        mclassItemList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String subject = c.getString(c.getColumnIndex(COL_SUB));
            String day = c.getString(c.getColumnIndex(COL_DAY));
            String time = c.getString(c.getColumnIndex(COL_TIME));
            String classroom = c.getString(c.getColumnIndex(COL_CLASS));

            Item_class item = new Item_class(id, subject, day, time , classroom);
            mclassItemList.add(item);
        }
        c.close();
    }

    private void setupListView() {
        ClassListAdapter adapter = new ClassListAdapter(
                MainActivity.this,
                R.layout.activity_item_class,
                mclassItemList
        );
        ListView lv = findViewById(R.id.result_list_view);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Item_class item = mclassItemList.get(position);

                Toast t = Toast.makeText(MainActivity.this, item.time, Toast.LENGTH_SHORT);
                t.show();

                Intent intent = new Intent(MainActivity.this,MainActivity.class);

                intent.putExtra("subject" , item.subject);
                intent.putExtra("day" , item.day);
                intent.putExtra("time" , item.time);
                intent.putExtra("classroom" , item.classroom);
                startActivity(intent);


//                Intent intent = new Intent(
//                        Intent.ACTION_DIAL,
//                        Uri.parse("TIME:" + item.time)
//                );
//                startActivity(intent);

            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                String[] items = new String[]{
                        "Edit",
                        "Delete"
                };

                new AlertDialog.Builder(MainActivity.this)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final Item_class classItem = mclassItemList.get(position);

                                switch (i) {
                                    case 0: // Edit
                                        Intent intent = new Intent(MainActivity.this, Editclass.class);
                                        intent.putExtra("subject",classItem.subject);
                                        intent.putExtra("day", classItem.day);
                                        intent.putExtra("time", classItem.time);
                                        intent.putExtra("classroom", classItem.classroom);
                                        startActivity(intent);
                                        break;
                                    case 1: // Delete
                                        new AlertDialog.Builder(MainActivity.this)
                                                .setMessage("ต้องการลบข้อมูลเบอร์โทรนี้ ใช่หรือไม่")
                                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        mDb.delete(
                                                                TABLE_NAME,
                                                                COL_ID + " = ?",
                                                                new String[]{String.valueOf(classItem._id)}
                                                        );
                                                        loadClassData();
                                                        setupListView();
                                                    }
                                                })
                                                .setNegativeButton("No", null)
                                                .show();
                                        break;
                                }
                            }
                        })
                        .show();

                return true;
            }
        });
    }
}