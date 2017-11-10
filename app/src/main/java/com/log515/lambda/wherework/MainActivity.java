package com.log515.lambda.wherework;

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
import com.log515.lambda.wherework.cron.WeeklyListener;
import com.log515.lambda.wherework.db.SQLiteHelper;
import com.log515.lambda.wherework.model.CoursHoraire;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private SQLiteHelper database = new SQLiteHelper(this);
    private Button syncButton;
    private ProgressBar progressBarLoading;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        syncButton = findViewById(R.id.btn_sync);
        progressBarLoading = findViewById(R.id.progressbar_loading);
        tvResult = findViewById(R.id.tv_result);
        tvResult.setMovementMethod(new ScrollingMovementMethod());

        syncButton.setOnClickListener(view -> {
            Log.d("MainActivity", "start");
            progressBarLoading.setVisibility(View.VISIBLE);
            database.syncDB()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(o -> progressBarLoading.setVisibility(View.GONE));
        });

        WakefulIntentService.scheduleAlarms(new WeeklyListener(), getApplicationContext(), false);

        database = new SQLiteHelper(this);
        Cursor cursor = database
                .getReadableDatabase()
                .rawQuery("SELECT * FROM salles_cours_session", null);

        while (cursor.moveToNext()) {
            tvResult.append(cursor.getString(cursor.getColumnIndex("titreCours"))+ "\n");
        }

        cursor.close();

    }

}
