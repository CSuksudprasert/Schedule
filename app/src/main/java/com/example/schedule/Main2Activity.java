package com.example.schedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Main2Activity extends AppCompatActivity {

    Spinner daySpin;
    Spinner timeStartSpin;
    Spinner timeFinishSpin;

    String arrday[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    String arrtimeS[] = {"08.30","09.25","10.20","11.15","13.00","13.55","14.50","15.45","16.30","17.35","18.30","19.25"};
    String arrtimeF[] = {"09.20","10.15","11.10","12.05","13.50","14.45","15.40","16.35","17.30","18.25","19.20","20.15"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        daySpin =  (Spinner) findViewById(R.id.day_spinner);
        timeStartSpin =  (Spinner) findViewById(R.id.timeStart_spinner);
        timeFinishSpin =  (Spinner) findViewById(R.id.timeFinish_spinner);

        ArrayAdapter<String> adapterDay = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrday);
        daySpin.setAdapter(adapterDay);

        ArrayAdapter<String> adapterTimeS = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrtimeS);
        timeStartSpin.setAdapter(adapterTimeS);

        ArrayAdapter<String> adapterTimeF = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrtimeF);
        timeFinishSpin.setAdapter(adapterTimeF);
    }
}
