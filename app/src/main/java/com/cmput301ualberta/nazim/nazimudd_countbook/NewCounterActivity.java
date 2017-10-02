package com.cmput301ualberta.nazim.nazimudd_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewCounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_counter);

        //probably dont need it
        Intent intent = getIntent();


    }


    public void addCounter(View view){

        EditText newName = (EditText) findViewById(R.id.newNameEditText);
        EditText newInitVal = (EditText) findViewById(R.id.newInitValueEditText);
        EditText newMessage = (EditText) findViewById(R.id.newMessageEditText);

        String valString = newInitVal.toString();
        int initVal = Integer.parseInt(valString);

        //ensure initiVal is in range
        if (initVal < 0){
            finish();
        }

        //ensure name is not empty
        if (newName.toString().length() == 0){
            finish();
        }

        if (newMessage.length() == 0){
            Counter counter = new Counter(newName.toString(), initVal, newMessage.toString());

        }
        else{
            Counter counter = new Counter(newName.toString(), initVal, newMessage.toString());
        }
    }
}
