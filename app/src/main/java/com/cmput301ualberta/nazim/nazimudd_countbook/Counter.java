/*
 * Counter
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class Counter
 *
 * Represents a counter object, providing methods to modify those fields containing counter
 * information
 *
 * @author nbhuiyan
 * @version 1.0
 * @since 1.0
 */

public class Counter {

    private String name;
    private Date date;
    private int currentValue;
    private int initialValue;
    private String message;

    public Counter(String name, int initialValue, String message){
        this.name = name;
        this.date = new Date();
        this.currentValue = initialValue;
        this.initialValue = initialValue;
        this.message = message;
    }

    public Counter(String name, int initialValue){
        this.name = name;
        this.date = new Date();
        this.currentValue = initialValue;
        this.initialValue = initialValue;
        this.message = "";
    }

    /**
     * increment the current value by 1, updating the date
     */
    public void increment(){
        this.currentValue++;
        this.date = new Date();
    }

    /**
     * decrements the current value by 1, updating the date
     */
    public void decrement(){
        this.currentValue--;
        this.date = new Date();
    }

    /**
     * sets the current value to initial value
     */
    public void reset(){
        this.currentValue = this.initialValue;
    }

    /**
     * gets the name of the counter
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * sets the counters name to what is specified
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * changes the Date object to yyy/mm/dd format and returns as a string
     * @return
     */
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String formattedDate = dateFormat.format(this.date);
        return formattedDate;
    }

    /**
     * gets the current value of the counter
     * @return currentValue
     */
    public int getCurrentValue(){
        return this.currentValue;
    }

    /**
     * sets the current value of the counter to the specified val
     * @param val
     */
    public void setCurrentValue(int val) {
        this.currentValue = val;
    }

    /**
     * gets the initial value
     * @return initialValue
     */
    public int getInitialValue(){
        return this.initialValue;
    }

    /**
     * sets the initial value to the val specified
     * @param initialValue
     */
    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    /**
     * get the message in the counter
     * @return
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * sets the message in the counter
     * @param msg
     */
    public void setMessage(String msg) {
        this.message = msg;
    }

    /**
     * overrides the toString() method to return a formatted string method to be displayed in the
     * ListView
     * @return formattedString
     */
    @Override
    public String toString() {
        return "Counter Name: "+this.name+ "\nCurrent Value:" + this.currentValue +
                "\nLast Modified: "+ getDate();
    }
}