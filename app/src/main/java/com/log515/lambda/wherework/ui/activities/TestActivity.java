package com.log515.lambda.wherework.ui.activities;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.log515.lambda.wherework.R;
import com.log515.lambda.wherework.db.SQLiteHelper;
import com.log515.lambda.wherework.model.LocalOccupation;
import com.log515.lambda.wherework.ui.adapters.LocalOccupationAdapter;
import com.log515.lambda.wherework.utils.LocalOccupationComparator;

import java.util.Collections;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private ListView localOccupationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        localOccupationListView = findViewById(R.id.listView_localoccupation);

        SQLiteHelper database = new SQLiteHelper(this);

        List<LocalOccupation> localOccupation = database.getLocalOccupation();
        Collections.sort(localOccupation,new LocalOccupationComparator());
        LocalOccupationAdapter adapter = new LocalOccupationAdapter(this, R.layout.row_local, localOccupation);

        localOccupationListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }

}
