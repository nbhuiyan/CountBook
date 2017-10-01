package com.cmput301ualberta.nazim.nazimudd_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<Counter> counterArrayAdapter;
    private ListView counterListView;
    private static final String SAVE_FILE = "save_file.sav";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

    }


    @Override
    protected void onStart(){
        super.onStart();
        context = getApplicationContext();

    }



    public void creteNewCounter(View view){
        Intent intent = new Intent(this, NewCounterActivity.class);
        startActivity(intent);
    }

    public void addCounter(Counter newCounter){
        counterArrayAdapter.add(newCounter);
    }



}


