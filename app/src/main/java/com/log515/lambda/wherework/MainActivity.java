package com.log515.lambda.wherework;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView.OnClickListener mDateClickListener;

    private Spinner mDayTime;
    private Spinner mBuilding;
    private Spinner mFloor;
    private Spinner.OnItemSelectedListener mDayTimeItemSelected;
    private Spinner.OnItemSelectedListener mBuildingItemSelected;
    private Spinner.OnItemSelectedListener mFloorItemSelected;
    private ArrayAdapter<CharSequence> BuildingAdapter;
    private ArrayAdapter<CharSequence> DayTimeAdapter;
    private ArrayAdapter<CharSequence> AFloorAdapter;
    private ArrayAdapter<CharSequence> BFloorAdapter;
    private ArrayAdapter<CharSequence> EFloorAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDisplayDate = findViewById(R.id.TvDate);
        mDayTime = findViewById(R.id.SpDayTime);
        mBuilding = findViewById(R.id.SpBuilding);
        mFloor = findViewById(R.id.SpFloor);

        CreateAdaptersAndEvents();

        // Set the default value to today
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        mDisplayDate.setText(FormatDate(day, month, year));
        mDisplayDate.setOnClickListener(mDateClickListener);

        mDayTime.setAdapter(DayTimeAdapter);
        mDayTime.setOnItemSelectedListener(mDayTimeItemSelected);

        mBuilding.setAdapter(BuildingAdapter);
        mBuilding.setOnItemSelectedListener(mBuildingItemSelected);

        mFloor.setAdapter(AFloorAdapter);
        mFloor.setOnItemSelectedListener(mFloorItemSelected);

    }

    private void CreateAdaptersAndEvents() {

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1; // January is 0
                String date = FormatDate(day, month, year);
                mDisplayDate.setText(date);
                Log.d(TAG, "Date Set : dd/mm/yyyy: " + date);
            }
        };

        mDateClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        };


        DayTimeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.DayTimeArray,
                android.R.layout.simple_spinner_item);
        DayTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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


        BuildingAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.BuildingArray,
                android.R.layout.simple_spinner_item);
        BuildingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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

    private String FormatDate(int day, int month, int year) {
        return day + "/" + month + "/" + year;
    }
}
