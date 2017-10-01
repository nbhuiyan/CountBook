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
        EditText newName = (EditText) findViewById(R.id.newName);
        EditText newInitVal = (EditText) findViewById(R.id.newInitVal);
        EditText newMessage = (EditText) findViewById(R.id.newMessage);

        Intent intent = getIntent();

    }
}
