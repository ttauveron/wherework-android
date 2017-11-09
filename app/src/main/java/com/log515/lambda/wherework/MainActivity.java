package com.log515.lambda.wherework;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.log515.lambda.wherework.db.SQLiteHelper;

public class MainActivity extends AppCompatActivity {


    private SQLiteHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new SQLiteHelper(this);

        Cursor cursor = database
                .getReadableDatabase()
                .rawQuery("SELECT * FROM salles_cours_session WHERE 0", null);

        for (String s : cursor.getColumnNames()) {
            System.out.println(s);
        }


        cursor.close();


    }
}
