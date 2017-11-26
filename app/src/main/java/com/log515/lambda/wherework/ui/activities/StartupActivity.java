package com.log515.lambda.wherework.ui.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.log515.lambda.wherework.R;
import com.log515.lambda.wherework.cron.WeeklyListener;
import com.log515.lambda.wherework.db.SQLiteHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StartupActivity extends AppCompatActivity {

    private static String ALARM_SCHEDULED_PREF_KEY = "AlarmScheduled";

    private SQLiteHelper database = new SQLiteHelper(this);
    private ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_startup);
        progressBarLoading = findViewById(R.id.progressbar_loading);

    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        boolean alarmScheduled = sharedPref.getBoolean(ALARM_SCHEDULED_PREF_KEY, false);


        if (!alarmScheduled) {
            if (isConnected(this)) {
                progressBarLoading.setVisibility(View.VISIBLE);
                database.syncDB()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(o -> {
                            WakefulIntentService.scheduleAlarms(new WeeklyListener(), getApplicationContext(), false);

                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putBoolean(ALARM_SCHEDULED_PREF_KEY, true);
                            editor.apply();

                            progressBarLoading.setVisibility(View.GONE);

                            launchActivity();
                        });
            }
        } else {
            launchActivity();
        }
    }

    private void launchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiInfo != null && wifiInfo.isConnected()) || (mobileInfo != null && mobileInfo.isConnected())) {
            return true;
        } else {
            showDialog();
            return false;
        }
    }

    private void showDialog() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(getString(R.string.wifi_required))
                .setPositiveButton(getString(R.string.connect), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton(getString(R.string.quit), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        StartupActivity.this.finish();
                    }
                })
                .show();


    }
}
