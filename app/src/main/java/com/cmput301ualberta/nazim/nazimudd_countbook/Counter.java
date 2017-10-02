package com.cmput301ualberta.nazim.nazimudd_countbook;

import java.util.Date;

/**
 * Created by naz_t on 9/30/2017.
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

    public void increment(){
        this.currentValue++;
    }

    public void decrement(){
        this.currentValue--;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public int getCurrentValue(){
        return this.currentValue;
    }

    public void setCurrentValue(int val) {
        this.currentValue = val;
    }

    public int getInitialValue(){
        return this.initialValue;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }
}
