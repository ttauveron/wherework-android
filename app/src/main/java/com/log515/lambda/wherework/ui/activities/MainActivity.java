package com.log515.lambda.wherework.ui.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.log515.lambda.wherework.R;
import com.log515.lambda.wherework.cron.WeeklyListener;
import com.log515.lambda.wherework.db.SQLiteHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private SQLiteHelper database = new SQLiteHelper(this);
    private Button syncButton;
    private ProgressBar progressBarLoading;
    private Button btnLaunchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        syncButton = findViewById(R.id.btn_sync);
        progressBarLoading = findViewById(R.id.progressbar_loading);
        btnLaunchActivity = findViewById(R.id.btn_launch_activity);

        syncButton.setOnClickListener(view -> {
            Log.d("MainActivity", "start");
            progressBarLoading.setVisibility(View.VISIBLE);
            database.syncDB()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(o -> progressBarLoading.setVisibility(View.GONE));
        });

        //todo sharedpreference first app start to trigger wakefulintent
        WakefulIntentService.scheduleAlarms(new WeeklyListener(), getApplicationContext(), false);


        btnLaunchActivity.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            startActivity(intent);
        });


    }

}
