package com.log515.lambda.wherework;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText dateEt;
    private EditText timeEt;
    private Spinner mBuilding;
    private Spinner mFloor;
    private Button serachBtn;
    private Spinner.OnItemSelectedListener mDayTimeItemSelected;
    private Spinner.OnItemSelectedListener mBuildingItemSelected;
    private Spinner.OnItemSelectedListener mFloorItemSelected;
    private ArrayAdapter<CharSequence> AFloorAdapter;
    private ArrayAdapter<CharSequence> BFloorAdapter;
    private ArrayAdapter<CharSequence> EFloorAdapter;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateEt = findViewById(R.id.date_edit_text);
        timeEt = findViewById(R.id.time_edit_text);
        mBuilding = findViewById(R.id.SpBuilding);
        mFloor = findViewById(R.id.SpFloor);
        serachBtn = findViewById(R.id.search_button);

        createAdaptersAndEvents();

        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        dateEt.setText(dateFormat.format(new Date()));

        Calendar now = Calendar.getInstance();
        showTime(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE));

        mBuilding.setOnItemSelectedListener(mBuildingItemSelected);

        mFloor.setAdapter(AFloorAdapter);
        mFloor.setOnItemSelectedListener(mFloorItemSelected);

    }

    public void showDatePicker(View view) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                MainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void showTime(int hourOfDay, int minute) {
        String timeStr = hourOfDay + ":" + minute;
        timeEt.setText(timeStr);
    }

    public void showTimePicker(View view) {
        Calendar now = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                showTime(hourOfDay, minute);
            }
        }, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), true);
        dialog.show();
    }

    private void createAdaptersAndEvents() {

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1; // January is 0
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);
                Date date = cal.getTime();
                DateFormat dateFormat = SimpleDateFormat.getDateInstance();
                String dateStr = dateFormat.format(date);
                dateEt.setText(dateStr);
            }
        };


        mDayTimeItemSelected = new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String sSelected = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG, "DayTime Selected : " + sSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        };

        AFloorAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.AFloorArray,
                android.R.layout.simple_spinner_item);
        BFloorAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.BFloorArray,
                android.R.layout.simple_spinner_item);
        EFloorAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.EFloorArray,
                android.R.layout.simple_spinner_item);

        AFloorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BFloorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EFloorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mFloorItemSelected = new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String sSelected = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG, "Floor Selected : " + sSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        };

        mBuildingItemSelected = new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String sSelected = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG, "Building Selected : " + sSelected);

                switch(sSelected) {
                    case "A": mFloor.setAdapter(AFloorAdapter);
                        break;
                    case "B": mFloor.setAdapter(BFloorAdapter);
                        break;
                    case "E": mFloor.setAdapter(EFloorAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        };
    }
}
