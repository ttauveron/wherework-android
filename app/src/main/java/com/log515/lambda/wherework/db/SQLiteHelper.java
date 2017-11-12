package com.log515.lambda.wherework.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.log515.lambda.wherework.model.LocalOccupation;
import com.log515.lambda.wherework.model.signets.CoursHoraire;
import com.log515.lambda.wherework.soap.SignetsMobileSoap;
import com.log515.lambda.wherework.utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "wherework.db";
    public static final String TABLE_COURS = "salles_cours_session";
    private static final int DATABASE_VERSION = 1;


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_COURS + " (" +
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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COURS + ";");
        onCreate(sqLiteDatabase);
    }

    public void truncateCoursesTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_COURS);
    }

    public void insertListeCours(List<CoursHoraire> coursHoraires) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (CoursHoraire coursHoraire : coursHoraires) {
                values.put("sigle", coursHoraire.sigle);
                values.put("groupe", coursHoraire.groupe);
                values.put("jour", coursHoraire.jour);
                values.put("journee", coursHoraire.journee);
                values.put("codeActivite", coursHoraire.codeActivite);
                values.put("nomActivite", coursHoraire.nomActivite);
                values.put("activitePrincipale", coursHoraire.activitePrincipale);
                values.put("heureDebut", coursHoraire.heureDebut);
                values.put("heureFin", coursHoraire.heureFin);
                values.put("local", coursHoraire.local);
                values.put("titreCours", coursHoraire.titreCours);

                db.insert(TABLE_COURS, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public List<LocalOccupation> getLocalOccupation() {

        Cursor cursor = this.getReadableDatabase()
                .rawQuery("    SELECT " +
                        "    t2.local as local, " +
                        "    t2.jour as jour, " +
                        "    MAX(t2.morning) as occupied_morning, " +
                        "    MAX(t2.afternoon) as occupied_afternoon, " +
                        "    MAX(t2.evening) as occupied_evening " +
                        "    FROM ( " +
                        "            SELECT " +
                        "                    s.local, " +
                        "            s.jour, " +
                        "            CASE " +
                        "                    WHEN s.heureDebut < '12:00' " +
                        "                    THEN 1 " +
                        "                    ELSE 0 " +
                        "                    END AS morning, " +
                        "            CASE " +
                        "                    WHEN s.heureDebut >= '12:00' AND s.heureDebut <= '17:00' " +
                        "                    THEN 1 " +
                        "                    ELSE 0 " +
                        "                    END AS afternoon, " +
                        "            CASE " +
                        "                    WHEN s.heureDebut > '17:00' " +
                        "                    THEN 1 " +
                        "                    ELSE 0 " +
                        "                    END AS evening " +
                        "                    FROM salles_cours_session s " +
                        "                    WHERE s.local NOT LIKE '% %' " +
                        "    ) as t2 " +
                        "    GROUP BY t2.local,t2.jour;", null);

        List<LocalOccupation> localOccupationList = new ArrayList<>();

        while (cursor.moveToNext()) {
            LocalOccupation localOccupation = new LocalOccupation();

            boolean occupiedMorning = cursor.getInt(cursor.getColumnIndex("occupied_morning")) == 1;
            boolean occupiedAfternoon = cursor.getInt(cursor.getColumnIndex("occupied_afternoon")) == 1;
            boolean occupiedEvening = cursor.getInt(cursor.getColumnIndex("occupied_evening")) == 1;
            int dayOfWeek = cursor.getInt(cursor.getColumnIndex("jour"));
            String local = cursor.getString(cursor.getColumnIndex("local"));

            localOccupation.setMorning(occupiedMorning);
            localOccupation.setAfternoon(occupiedAfternoon);
            localOccupation.setEvening(occupiedEvening);
            localOccupation.setDayOfWeek(dayOfWeek);
            localOccupation.setLocal(local);

            localOccupationList.add(localOccupation);
        }

        cursor.close();

        return localOccupationList;
    }



    public Observable<List<CoursHoraire>> syncDB() {

        return Flowable.just(1)
                .observeOn(Schedulers.io())
                .flatMap(integer -> {
                    //Getting sigles of courses from planets
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://planets.etsmtl.ca/public/Versionpdf.aspx")
                            .get()
                            .build();
                    return Flowable.just(client.newCall(request).execute());
                })
                .flatMap(response -> {
                    String html = response.body().string();
                    Document doc = Jsoup.parseBodyFragment(html);
                    //Parsing html to get sigles
                    Elements select = doc.select("select[name*=Cours] > option");

                    //Remove duplicates
                    HashSet<String> sigles = new HashSet<>();
                    for (Element element : select) {
                        sigles.add(element.attr("value").substring(0, 3));
                    }
                    return Flowable.fromIterable(sigles);
                })
                //Requests to Signets in parallel
                .parallel()
                .runOn(Schedulers.io())
                .flatMap(sigle -> {
                    SignetsMobileSoap signetsMobileSoap = new SignetsMobileSoap();
                    return Flowable.fromIterable(signetsMobileSoap.lireHoraire(Utils.getCurrentSession(), sigle).listeCours);
                })
                .sequential()
                .toList()
                .toObservable()
                .doOnNext(coursHoraires -> {
                    //Update Database with result from signets
                    truncateCoursesTable();
                    insertListeCours(coursHoraires);
                });


    }

}
