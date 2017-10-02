/*
 * EditNumberActivity
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
import android.widget.EditText;

/**
 * class EditNumberActivity
 *
 * Activity responsible for handling the editing of numerical fields of a counter
 *
 * @author nbhuiyan
 * @version 1.0
 * @since 1.0
 */

public class EditNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_number);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Intent intent = getIntent();
        final Context context = getApplicationContext();
        int index = intent.getIntExtra("pos", 0);
        final String whatField = intent.getStringExtra("what");
        final CounterContainer counterContainer = new CounterContainer();
        counterContainer.loadFromFile(context);
        final Counter counter = counterContainer.getCounter(index);

        final EditText editText = (EditText) findViewById(R.id.directEditBox);

        Button confirmButton = (Button) findViewById(R.id.directEditConfirmButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputString = editText.getText().toString();
                if (inputString.length() == 0){
                    issueWarning("You cannot leave this field empty!");
                }
                else{
                    int userInput = Integer.parseInt(inputString);
                    if (userInput < 0){
                        issueWarning("Your input value cannot be less than 0!");
                    }
                    if (whatField.equals("currentValue")){
                        counter.setCurrentValue(userInput);
                        counterContainer.saveToFile(context);
                        finish();
                    }
                    if (whatField.equals("initialValue")){
                        counter.setInitialValue(userInput);
                        counterContainer.saveToFile(context);
                        finish();
                    }
                }
            }
        });
    }

    /**
     * handles the displaying of warnings for this activity
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
