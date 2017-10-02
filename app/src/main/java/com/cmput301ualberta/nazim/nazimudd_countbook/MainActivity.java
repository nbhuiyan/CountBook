package com.cmput301ualberta.nazim.nazimudd_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    //private ArrayAdapter<Counter> counterArrayAdapter;
    private ListView counterListView;
    private Context context;
    Button newCounterButton;
    private CounterContainer counterContainer = new CounterContainer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        newCounterButton = (Button) findViewById(R.id.mainAddButton);
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
        ArrayAdapter<Counter> counterArrayAdapter = new ArrayAdapter<Counter>(this,
                R.layout.main_list_item, counterContainer.getContainerAsArray());
        counterListView.setAdapter(counterArrayAdapter);
    }
}