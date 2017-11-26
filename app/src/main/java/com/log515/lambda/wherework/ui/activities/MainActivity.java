package com.log515.lambda.wherework.ui.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.log515.lambda.wherework.R;
import com.log515.lambda.wherework.db.SQLiteHelper;
import com.log515.lambda.wherework.model.LocalOccupation;
import com.log515.lambda.wherework.ui.adapters.LocalOccupationAdapter;
import com.log515.lambda.wherework.utils.LocalOccupationComparator;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.log515.lambda.wherework.db.SQLiteHelper.LAST_SYNC_DATE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private Spinner tempsSpinner;
    private Spinner pavillonSpinner;
    private Spinner etageSpinner;
    private Spinner jourSpinner;
    private Button searchBtn;
    private ProgressBar syncProgressBar;
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
        syncProgressBar = findViewById(R.id.sync_progress_bar);

        searchBtn.getBackground().setColorFilter(getResources().getColor(R.color.medium_gray), PorterDuff.Mode.MULTIPLY);
        searchBtn.setTextColor(Color.BLACK);

        createAdaptersAndEvents();

        pavillonSpinner.setOnItemSelectedListener(mBuildingItemSelected);

        etageSpinner.setAdapter(AFloorAdapter);
        etageSpinner.setOnItemSelectedListener(mFloorItemSelected);

        localOccupationListView = findViewById(R.id.listView_localoccupation);

        SQLiteHelper database = new SQLiteHelper(this);

        List<LocalOccupation> localOccupation = database.getLocalOccupation();
        Collections.sort(localOccupation, new LocalOccupationComparator());
        LocalOccupationAdapter adapter = new LocalOccupationAdapter(this, R.layout.row_local, localOccupation);

        localOccupationListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        searchBtn.setOnClickListener(v -> {
            int dayOfWeek = jourSpinner.getSelectedItemPosition();
            List<LocalOccupation> localOccupation1 = database.getLocalOccupation(tempsSpinner.getSelectedItemPosition(), pavillonSpinner.getSelectedItemPosition(), etageSpinner.getSelectedItemPosition(), dayOfWeek);
            Collections.sort(localOccupation1, new LocalOccupationComparator());
            adapter.clear();
            adapter.addAll(localOccupation1);
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
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

    @Override
    public void onBackPressed() {
        if (slidingUpPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main, menu);

        return true;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiInfo != null && wifiInfo.isConnected()) || (mobileInfo != null && mobileInfo.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sync_menu_item) {

            if(!isConnected(this)) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                        getString(R.string.internet_required), Snackbar.LENGTH_LONG);

                View view = snackbar.getView();
                TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                view.setBackgroundColor(Color.RED);
                snackbar.show();
                return false;
            }

            SharedPreferences sharedPref = getSharedPreferences(LAST_SYNC_DATE, MODE_PRIVATE);
            long lastSync = sharedPref.getLong(LAST_SYNC_DATE, 0);
            DateTime lastSyncDateTime = new DateTime(lastSync);
            int daysSinceLastSync = Days.daysBetween(lastSyncDateTime, new DateTime()).getDays();

            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.sync))
                    .setMessage(getString(R.string.sync_conf, ""+daysSinceLastSync))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            SQLiteHelper database = new SQLiteHelper(MainActivity.this);

                            syncProgressBar.setVisibility(View.VISIBLE);
                            slidingUpPanelLayout.setVisibility(View.GONE);
                            item.setEnabled(false);

                            database.syncDB()
                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(o -> {
                                        syncProgressBar.setVisibility(View.GONE);
                                        slidingUpPanelLayout.setVisibility(View.VISIBLE);
                                        item.setEnabled(true);
                                    });
                        }

                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();


            return true;
        } else
            return super.onOptionsItemSelected(item);
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
