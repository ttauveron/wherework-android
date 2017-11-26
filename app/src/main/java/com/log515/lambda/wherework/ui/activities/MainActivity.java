package com.log515.lambda.wherework.ui.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.log515.lambda.wherework.R;
import com.log515.lambda.wherework.db.SQLiteHelper;
import com.log515.lambda.wherework.model.LocalOccupation;
import com.log515.lambda.wherework.ui.adapters.LocalOccupationAdapter;
import com.log515.lambda.wherework.utils.LocalOccupationComparator;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private Spinner tempsSpinner;
    private Spinner pavillonSpinner;
    private Spinner etageSpinner;
    private Spinner jourSpinner;
    private Button searchBtn;
    private Spinner.OnItemSelectedListener mBuildingItemSelected;
    private Spinner.OnItemSelectedListener mFloorItemSelected;
    private ArrayAdapter<CharSequence> AFloorAdapter;
    private ArrayAdapter<CharSequence> BFloorAdapter;
    private ArrayAdapter<CharSequence> EFloorAdapter;

    private ListView localOccupationListView;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    private ImageButton slidingUpPanelButton;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempsSpinner = findViewById(R.id.temps_spinner);
        jourSpinner = findViewById(R.id.jour_spinner);
        pavillonSpinner = findViewById(R.id.SpBuilding);
        etageSpinner = findViewById(R.id.SpFloor);
        searchBtn = findViewById(R.id.search_button);
        slidingUpPanelLayout = findViewById(R.id.sliding_layout);
        slidingUpPanelButton = findViewById(R.id.sliding_panel_btn);

        createAdaptersAndEvents();

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

        searchBtn.setOnClickListener(v -> {
            int dayOfWeek = jourSpinner.getSelectedItemPosition();
            List<LocalOccupation> localOccupation1 = database.getLocalOccupation(tempsSpinner.getSelectedItemPosition(), pavillonSpinner.getSelectedItemPosition(), etageSpinner.getSelectedItemPosition(), dayOfWeek);
            Collections.sort(localOccupation1,new LocalOccupationComparator());
            adapter.clear();
            adapter.addAll(localOccupation1);
        });

        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                if (slideOffset > 0) {
                    slidingUpPanelButton.setImageResource(R.drawable.ic_close_black_24dp);
                } else {
                    slidingUpPanelButton.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

            }
        });
    }

    public void toggleSlidingPanel(View view) {
        if (slidingUpPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        else
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
    }

    private void createAdaptersAndEvents() {

        ArrayAdapter<CharSequence> pavillonAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.BuildingArray,
                R.layout.spinner_item);
        pavillonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pavillonSpinner.setAdapter(pavillonAdapter);

        ArrayAdapter<CharSequence> tempsSpinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.DayTimeArray,
                R.layout.spinner_item);
        tempsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tempsSpinner.setAdapter(tempsSpinnerAdapter);

        ArrayAdapter<CharSequence> jourSpinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.JourArray,
                R.layout.spinner_item);
        jourSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jourSpinner.setAdapter(jourSpinnerAdapter);

        AFloorAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.AFloorArray,
                R.layout.spinner_item);
        BFloorAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.BFloorArray,
                R.layout.spinner_item);
        EFloorAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.EFloorArray,
                R.layout.spinner_item);


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
