/*
 * CounterDetailsActivity
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
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * class CounterDetailsActivity
 *
 * Represents the Activity responsible for handling the viewing of the details of a counter.
 *
 * @author nbhuiyan
 * @version 1.0
 * @since 1.0
 */


public class CounterDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_details);
    }
    @Override
    protected void onStart(){
        super.onStart();
        final Context context = getApplicationContext();

        final Intent intent = getIntent();
        final int counterIndex = intent.getIntExtra("pos", 0);
        final CounterContainer counterContainer = new CounterContainer();
        counterContainer.loadFromFile(context);
        final Counter counter = counterContainer.getCounter(counterIndex);

        /*Initialize the TextViews*/
        final TextView counterNameTextView = (TextView) findViewById(R.id.detailCounterName);
        final TextView counterCurrentValueTextView = (TextView) findViewById(R.id.detailCounterValue);
        final TextView counterInitialValueTextView = (TextView) findViewById(R.id.detailInitialValue);
        final TextView counterMessageTextView = (TextView) findViewById(R.id.detailMessage);
        final TextView counterDateTextView = (TextView) findViewById(R.id.detailDate);

        /*populate the textViews*/
        updateCounterNameTextView(counter, counterNameTextView);
        updateCurrentValueTextView(counter, counterCurrentValueTextView);
        updateInitialValueTextView(counter, counterInitialValueTextView);
        updateDateTextView(counter, counterDateTextView);
        updateMessageTextView(counter, counterMessageTextView);

        /*make TextViews clickable(except date)*/
        counterNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(CounterDetailsActivity.this, EditTextActivity.class);
                intent1.putExtra("pos", counterIndex);
                intent1.putExtra("what", "name");
                startActivity(intent1);
                updateCounterNameTextView(counter, counterNameTextView);
            }
        });

        counterCurrentValueTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(CounterDetailsActivity.this, EditNumberActivity.class);
                intent2.putExtra("pos", counterIndex);
                intent2.putExtra("what", "currentValue");
                startActivity(intent2);
                updateCurrentValueTextView(counter, counterCurrentValueTextView);
                updateDateTextView(counter, counterDateTextView);
            }
        });

        counterInitialValueTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(CounterDetailsActivity.this, EditNumberActivity.class);
                intent3.putExtra("pos", counterIndex);
                intent3.putExtra("what", "initialValue");
                startActivity(intent3);
                updateInitialValueTextView(counter, counterInitialValueTextView);
            }
        });

        counterMessageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(CounterDetailsActivity.this, EditTextActivity.class);
                intent4.putExtra("pos" , counterIndex);
                intent4.putExtra("what", "message");
                startActivity(intent4);
                updateMessageTextView(counter, counterMessageTextView);
            }
        });

        /*Initialize the buttons and assign their onClick functionality*/
        Button decreaseButton = (Button) findViewById(R.id.detailDecrease);
        Button increaseButton = (Button) findViewById(R.id.detailIncrease);
        final Button resetButton = (Button) findViewById(R.id.detailReset);
        Button deleteButton = (Button) findViewById(R.id.detailDelete);

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter.getCurrentValue() == 0){
                    issueWarning("You cannot decrement beyond this point!");
                }
                else{
                    counter.decrement();
                    counterContainer.saveToFile(context);
                    updateCurrentValueTextView(counter, counterCurrentValueTextView);
                    updateDateTextView(counter, counterDateTextView);
                }
            }
        });

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.increment();
                counterContainer.saveToFile(context);
                updateCurrentValueTextView(counter, counterCurrentValueTextView);
                updateDateTextView(counter, counterDateTextView);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.reset();
                counterContainer.saveToFile(context);
                updateCurrentValueTextView(counter, counterCurrentValueTextView);
                updateDateTextView(counter, counterDateTextView);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterContainer.deleteCounter(counterIndex);
                counterContainer.saveToFile(context);
                finish();
            }
        });

    }

    /**
     * update the TextView containing the counter name
     * @param counter
     * @param counterNameTextView
     */

    private void updateCounterNameTextView(Counter counter, TextView counterNameTextView){
        counterNameTextView.setText(("Counter: "+counter.getName()));
    }

    /**
     * update the TextView containing the current value
     * @param counter
     * @param currentValueTextView
     */

    private void updateCurrentValueTextView(Counter counter, TextView currentValueTextView){
        currentValueTextView.setText(("Current Value: "+counter.getCurrentValue()));
    }

    /**
     * update the TextView containing the initial value
     * @param counter
     * @param initialValueTextView
     */

    private void updateInitialValueTextView(Counter counter, TextView initialValueTextView){
        initialValueTextView.setText(("Starting Value: "+counter.getInitialValue()));

    }

    /**
     * update the TextView containing the date
     * @param counter
     * @param dateTextView
     */
    private void updateDateTextView(Counter counter, TextView dateTextView){
        dateTextView.setText(("Last Modified: "+counter.getDate()));
    }

    /**
     * update the TextView containing the message
     * @param counter
     * @param messageTextView
     */
    private void updateMessageTextView(Counter counter, TextView messageTextView){
        if(counter.getMessage().length() == 0){
            messageTextView.setText(("Tap to add a note for this counter"));
        }
        else{
            messageTextView.setText(("Message: "+counter.getMessage()));
        }
    }

    /**
     * handles displaying of warning for this activity
     * @param message
     */

    private void issueWarning(String message){
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(this, R.style.AlertDialogStyle);
        alertDialog.setTitle("Warning");
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();

    }
}