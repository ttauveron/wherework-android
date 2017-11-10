package com.log515.lambda.wherework;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.log515.lambda.wherework.model.CoursHoraire;
import com.log515.lambda.wherework.model.ListeCoursHoraire;
import com.log515.lambda.wherework.soap.IServiceEvents;
import com.log515.lambda.wherework.soap.OperationResult;
import com.log515.lambda.wherework.soap.SignetsMobileSoap;
import com.log515.lambda.wherework.db.SQLiteHelper;

public class MainActivity extends AppCompatActivity implements IServiceEvents {


    private SQLiteHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Test Database
        database = new SQLiteHelper(this);
        Cursor cursor = database
                .getReadableDatabase()
                .rawQuery("SELECT * FROM salles_cours_session WHERE 0", null);

        for (String s : cursor.getColumnNames()) {
            System.out.println(s);
        }
        cursor.close();


        //Test Signets API
        SignetsMobileSoap signetsMobileSoap = new SignetsMobileSoap(this);

        signetsMobileSoap.lireHoraireAsync("A2017", "LOG");

    }
    @Override
    public void Starting() {
        Log.i("SignetsWebServices", "START : SOAP request starting");
    }

    @Override
    public void Completed(OperationResult result) {
        if(result.Result instanceof ListeCoursHoraire) {
            ListeCoursHoraire liste = (ListeCoursHoraire)result.Result;
            for (CoursHoraire listeCour : liste.listeCours) {
                System.out.println(listeCour.sigle);
            }

        }

    }
}
