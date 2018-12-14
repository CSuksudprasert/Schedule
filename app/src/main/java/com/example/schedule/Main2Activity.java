package com.example.schedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Main2Activity extends AppCompatActivity {

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


        ArrayAdapter<String> adapterDay = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrday);
        daySpin.setAdapter(adapterDay);

        ArrayAdapter<String> adapterTimeS = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrtime);
        timeS.setAdapter(adapterTimeS);

    }
}
