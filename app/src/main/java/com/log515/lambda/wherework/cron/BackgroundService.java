package com.log515.lambda.wherework.cron;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.log515.lambda.wherework.db.SQLiteHelper;

/**
 * Created by ttauveron on 09/11/17.
 */

public class BackgroundService extends WakefulIntentService {


    public BackgroundService() {
        super("Background Service");
    }

    @Override
    protected void doWakefulWork(Intent intent) {
        Log.d("App Service", "Started syncing");

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        sqLiteHelper.syncDB().blockingSubscribe();

        Log.d("App Service", "Ended syncing");

    }
}
