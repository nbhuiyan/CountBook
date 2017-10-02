/*
 * MainActivity
 *
 * Version 1.0
 *
 * October 2nd, 2017
 *
 * Copyright (c) Copyright (c) 2017 nbhuiyan, CMPUT301, University of Alberta - All Rights Reserved.
 * You may use distribute, or modify this code under terms and conditions of the Code of Student
 * Behaviour at University of Alberta. You can find a copy of the license in this project.
 * Otherwise, please contact nazimudd@ualberta.ca.
 */

package com.cmput301ualberta.nazim.nazimudd_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * class MainActivity
 *
 * Represents the "HomePage" activity of the app, where the list of counters along with their
 * summary is presented to the user.
 *
 * @author nbhuiyan
 * @version 1.0
 * @since 1.0
 */

public class MainActivity extends AppCompatActivity {
    private ListView counterListView;
    private Context context;
    private CounterContainer counterContainer = new CounterContainer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        FloatingActionButton newCounterButton = (FloatingActionButton) findViewById(R.id.mainAddButton);
        newCounterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, NewCounterActivity.class);
                startActivity(intent);

            }
        });

        counterListView = (ListView) findViewById(R.id.mainListView);
        this.counterContainer.loadFromFile(context);
        counterListView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(MainActivity.this, CounterDetailsActivity.class);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        context = getApplicationContext();
        counterContainer.loadFromFile(context);

        setHeading(counterContainer.getSize());

        ArrayAdapter<Counter> counterArrayAdapter = new ArrayAdapter<Counter>(this,
                R.layout.main_list_item, counterContainer.getContainerAsArray());
        counterListView.setAdapter(counterArrayAdapter);
    }

    /**
     * setHeading
     *
     * Handles the summary TextView display at the top of the main activity
     *
     * @param numCounters
     */

    private void setHeading(int numCounters){
        context = getApplicationContext();
        TextView headingTextView = (TextView) findViewById(R.id.mainHeadingTextView);
        if(numCounters == 0){
            headingTextView.setText(("You do not have any counters. Tap '+' to add a counter."));
        }
        else if(numCounters == 1){
            headingTextView.setText(("You only have one counter. Tap '+' to add more." +
            " Tapping on the counter will allow you to view details and update it."));
        }
        else{
            headingTextView.setText(("You have "+ numCounters +
                    " counter(s). Tap '+' to add more." +
                    "Tapping on one of the counters will allow you to view details and update it."));
        }

    }
}