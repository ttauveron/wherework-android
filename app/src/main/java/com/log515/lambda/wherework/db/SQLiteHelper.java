package com.log515.lambda.wherework.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "wherework.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE salles_cours_session " +
                "(" +
                "   season INTEGER, " +
                "   year INTEGER, " +
                "   sigle TEXT, " +
                "   groupe INTEGER, " +
                "   jour INTEGER, " +
                "   journee TEXT, " +
                "   codeActivite TEXT, " +
                "   nomActivite TEXT, " +
                "   activitePrincipale TEXT, " +
                "   heureDebut TEXT, " +
                "   heureFin TEXT, " +
                "   local TEXT, " +
                "   titreCours TEXT " +
                ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS salles_cours_session;");
        onCreate(sqLiteDatabase);
    }
}
