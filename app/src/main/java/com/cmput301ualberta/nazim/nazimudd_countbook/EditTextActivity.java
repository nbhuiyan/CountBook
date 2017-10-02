/*
 * EditTextActivity
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
 * class EditTextActivity
 *
 * Represents the activity responsible for handling the editing of alphanumerical fields of a
 * counter
 *
 * @author nbhuiyan
 * @version 1.0
 * @since 1.0
 */

public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
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
                String userInput = editText.getText().toString();
                if (whatField.equals("name")) {
                    if (userInput.length() == 0) {
                        issueWarning("You cannot leave the name field blank!");
                    } else if (userInput.length() > 30) {
                        issueWarning("The name is too long! Please enter a shorter name!");
                    } else {
                        counter.setName(userInput);
                        counterContainer.saveToFile(context);
                        finish();
                    }
                }
                if (whatField.equals("message")) {
                    if (userInput.length() > 140) {
                        issueWarning("The text is too long! Please try again with a shorter input text!");
                    } else {
                        counter.setMessage(userInput);
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
