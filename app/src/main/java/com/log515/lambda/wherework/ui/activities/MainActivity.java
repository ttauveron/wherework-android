package com.log515.lambda.wherework.ui.activities;

import android.app.DatePickerDialog;
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
import android.widget.ListView;
import android.widget.Spinner;

import com.log515.lambda.wherework.R;
import com.log515.lambda.wherework.db.SQLiteHelper;
import com.log515.lambda.wherework.model.LocalOccupation;
import com.log515.lambda.wherework.ui.adapters.LocalOccupationAdapter;
import com.log515.lambda.wherework.utils.LocalOccupationComparator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText dateEt;
    private Spinner tempsSpinner;
    private Spinner pavillonSpinner;
    private Spinner etageSpinner;
    private Button serachBtn;
    private Spinner.OnItemSelectedListener mBuildingItemSelected;
    private Spinner.OnItemSelectedListener mFloorItemSelected;
    private ArrayAdapter<CharSequence> AFloorAdapter;
    private ArrayAdapter<CharSequence> BFloorAdapter;
    private ArrayAdapter<CharSequence> EFloorAdapter;

    private ListView localOccupationListView;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateEt = findViewById(R.id.date_edit_text);
        tempsSpinner = findViewById(R.id.temps_spinner);
        pavillonSpinner = findViewById(R.id.SpBuilding);
        etageSpinner = findViewById(R.id.SpFloor);
        serachBtn = findViewById(R.id.search_button);

        createAdaptersAndEvents();

        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        dateEt.setText(dateFormat.format(new Date()));

        pavillonSpinner.setOnItemSelectedListener(mBuildingItemSelected);

        etageSpinner.setAdapter(AFloorAdapter);
        etageSpinner.setOnItemSelectedListener(mFloorItemSelected);

        localOccupationListView = findViewById(R.id.listView_localoccupation);

        SQLiteHelper database = new SQLiteHelper(this);

        List<LocalOccupation> localOccupation = database.getLocalOccupation();
        Collections.sort(localOccupation,new LocalOccupationComparator());
        LocalOccupationAdapter adapter = new LocalOccupationAdapter(this, R.layout.row_local, localOccupation);

        localOccupationListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        serachBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int dayOfWeek = 0;
                try {
                    c.setTime(dateFormat.parse(dateEt.getText().toString()));
                    dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                List<LocalOccupation> localOccupation = database.getLocalOccupation(tempsSpinner.getSelectedItemPosition(), pavillonSpinner.getSelectedItemPosition(), etageSpinner.getSelectedItemPosition(), dayOfWeek);
                Collections.sort(localOccupation,new LocalOccupationComparator());
                adapter.clear();
                adapter.addAll(localOccupation);
            }
        });
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
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };

        mBuildingItemSelected = new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String sSelected = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG, "Building Selected : " + sSelected);

                switch (sSelected) {
                    case "A":
                        etageSpinner.setAdapter(AFloorAdapter);
                        break;
                    case "B":
                        etageSpinner.setAdapter(BFloorAdapter);
                        break;
                    case "E":
                        etageSpinner.setAdapter(EFloorAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
    }
}
