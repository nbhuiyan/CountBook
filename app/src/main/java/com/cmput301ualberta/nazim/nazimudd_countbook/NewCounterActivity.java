/*
 * NewCounterActivity
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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * class NewCounterActivity
 *
 * Represents the activity responsible for allowing the user to add a new counter.
 *
 * @author nbhuiyan
 * @version 1.0
 * @since 1.0
 */

public class NewCounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_counter);

        Button createButton = (Button) findViewById(R.id.newCreateButton);
        createButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText newName = (EditText) findViewById(R.id.newNameEditText);
                EditText newInitVal = (EditText) findViewById(R.id.newInitValueEditText);
                EditText newMessage = (EditText) findViewById(R.id.newMessageEditText);
                String valString = newInitVal.getText().toString();
                if (valString.length() == 0){
                    issueWarning("You cannot leave the Initial Value field empty!");
                }
                else{
                    int initVal = Integer.parseInt(valString);
                    if (initVal < 0){
                        issueWarning("Initial value cannot be less than zero!");
                    }

                    if (newName.toString().length() == 0){
                        issueWarning("Name field cannot be left empty!");
                    }

                    if (newMessage.getText().toString().length() == 0){
                        Counter counter = new Counter(newName.getText().toString(), initVal);
                        addCounter(counter);
                        finish();
                    }
                    else{
                        Counter counter = new Counter(newName.getText().toString(), initVal,
                                newMessage.getText().toString());
                        addCounter(counter);
                        finish();
                    }
                }
            }
        });
    }

    /**
     * adds a counter to the counter container, and makes it save to file
     * @param counter
     */
    private void addCounter(Counter counter){
        Context context = getApplicationContext();
        CounterContainer counterContainer = new CounterContainer();
        counterContainer.loadFromFile(context);
        counterContainer.addNewCounter(counter);
        counterContainer.saveToFile(context);
    }

    /**
     * warning dialog handler
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
