package com.log515.lambda.wherework.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class TestMainActivity extends AppCompatActivity {

    private static String ALARM_SCHEDULED_PREF_KEY = "AlarmScheduled";

    private SQLiteHelper database = new SQLiteHelper(this);
    private Button syncButton;
    private ProgressBar progressBarLoading;
    private Button btnLaunchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
        syncButton = findViewById(R.id.btn_sync);
        progressBarLoading = findViewById(R.id.progressbar_loading);
        btnLaunchActivity = findViewById(R.id.btn_launch_activity);

        syncButton.setOnClickListener(view -> {
            Log.d("TestMainActivity", "start");
            progressBarLoading.setVisibility(View.VISIBLE);
            database.syncDB()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(o -> progressBarLoading.setVisibility(View.GONE));
        });

        btnLaunchActivity.setOnClickListener(view -> {
            Intent intent = new Intent(TestMainActivity.this, TestUIActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_launch_activity2).setOnClickListener(view -> {
            Intent intent = new Intent(TestMainActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        boolean alarmScheduled = sharedPref.getBoolean(ALARM_SCHEDULED_PREF_KEY, false);

        if (!alarmScheduled) {
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
        } else
            launchActivity();
    }

    private void launchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
