package com.cmput301ualberta.nazim.nazimudd_countbook;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by naz_t on 10/1/2017.
 */

public class CounterContainer {
    private ArrayList<Counter> counterArrayList;

    //save file to store and retrieve date
    private String saveFile = "CountBook_save_file.sav";

    public CounterContainer(){
        this.counterArrayList = new ArrayList<Counter>();
    }

    public void loadFromFile(Context context){
        try{
            FileInputStream ifStream = context.openFileInput(saveFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ifStream));
            Gson gson = new Gson();
            Type counterArrayListType = new TypeToken<ArrayList<Counter>>(){}.getType();
            this.counterArrayList = gson.fromJson(bufferedReader, counterArrayListType);
        }
        //create a new array list if a file does not already exist
        catch (FileNotFoundException e){ //TODO Check which one works
            //this.counterArrayList = new ArrayList<Counter>();
            this.counterArrayList = new ArrayList<>();
        }
    }

    public void saveToFile(Context context){
        try{
            FileOutputStream ofStream = context.openFileOutput(saveFile, Context.MODE_PRIVATE);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(ofStream));
            Gson gson = new Gson();
            gson.toJson(this.counterArrayList, bufferedWriter);
            bufferedWriter.flush();
            ofStream.close();
        }
        catch (FileNotFoundException e){
            //shouldn't really happen, since a file not found would create a new file.
            throw new RuntimeException("does this defy the laws of physics?");
        }
        catch (IOException e){
            throw new RuntimeException();
        }
    }

    public ArrayList<Counter> getContainerAsArray(){
        return this.counterArrayList;
    }

    public void addNewCounter(Counter counter){
        this.counterArrayList.add(counter);
    }

    public void deleteCounter(int index){
        this.counterArrayList.remove(index);
    }

    public void incrementCounter(int index){
        Counter counter = this.counterArrayList.get(index);
        counter.increment();
    }

    public void decrementCounter(int index){
        Counter counter = this.counterArrayList.get(index);
        counter.decrement();
    }

    public void editInitVal(int index, int val){
        Counter counter = this.counterArrayList.get(index);
        counter.setInitialValue(val);
    }

    public void editCurrentVal(int index, int val){
        Counter counter = this.counterArrayList.get(index);
        counter.setCurrentValue(val);
    }

    public void editName(int index, String name){
        Counter counter = this.counterArrayList.get(index);
        counter.setName(name);

    }

    public void editMessage(int index, String msg){
        Counter counter = this.counterArrayList.get(index);
        counter.setMessage(msg);

    }

    public int getSize(){
        return this.counterArrayList.size();
    }

    public int getInitVal(int index){
        Counter counter = this.counterArrayList.get(index);
        return counter.getInitialValue();
    }

    public int getCurrentVal(int index){
        Counter counter = this.counterArrayList.get(index);
        return counter.getCurrentValue();

    }

    public String getName(int index){
        Counter counter = this.counterArrayList.get(index);
        return counter.getName();
    }

    public String getMessage(int index){
        Counter counter = this.counterArrayList.get(index);
        return counter.getMessage();
    }

    public Counter getCounter(int index){
        return this.counterArrayList.get(index);
    }
}
