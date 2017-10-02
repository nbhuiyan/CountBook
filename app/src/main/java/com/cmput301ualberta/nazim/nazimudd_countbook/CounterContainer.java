/*
 * CounterContainer
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
 * class CounterContainer
 *
 * Represents a mechanism to manage the storing, loading, and managing an arry of Counter objects
 *
 * @author nbhuiyan
 * @version 1.0
 * @since 1.0
 */

public class CounterContainer {
    private ArrayList<Counter> counterArrayList;

    //save file to store and retrieve date
    private String saveFile = "CountBook_save_file.sav";

    public CounterContainer(){
        this.counterArrayList = new ArrayList<Counter>();
    }

    /**
     * loads ArrayList "Counter" Objects from file
     * @param context
     */
    public void loadFromFile(Context context){
        try{
            FileInputStream ifStream = context.openFileInput(saveFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ifStream));
            Gson gson = new Gson();
            Type counterArrayListType = new TypeToken<ArrayList<Counter>>(){}.getType();
            this.counterArrayList = gson.fromJson(bufferedReader, counterArrayListType);
            ifStream.close();
        }
        //create a new array list if a file does not already exist
        catch (FileNotFoundException e){
            this.counterArrayList = new ArrayList<Counter>();
        }
        catch (IOException e){
            throw new RuntimeException();
        }
    }

    /**
     * saves current ArrayList contents in file
     * @param context
     */

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
            throw new RuntimeException("Laws of nature defied!");
        }
        catch (IOException e){
            throw new RuntimeException();
        }
    }

    /**
     * method to obtain the entire ArrayList of the Counter objects
     * @return ArrayList"Counter"
     */
    public ArrayList<Counter> getContainerAsArray(){
        return this.counterArrayList;
    }

    /**
     * adds a new counter to the array list. does not save to file.
     * @param counter
     */
    public void addNewCounter(Counter counter){
        this.counterArrayList.add(counter);
    }

    /**
     * deletes the counter at the specified index. does not save state to file.
     * @param index
     */
    public void deleteCounter(int index){
        this.counterArrayList.remove(index);

    }

    /**
     * returns the number of Counter objects in the CounterContainer
     * @return
     */
    public int getSize(){
        return this.counterArrayList.size();
    }

    /**
     * returns the counter at the specified index
     * @param index
     * @return Counter
     */
    public Counter getCounter(int index){
        return this.counterArrayList.get(index);
    }
}
