package com.cmput301ualberta.nazim.nazimudd_countbook;

import java.util.Date;

/**
 * Created by naz_t on 9/30/2017.
 */

public class Counter {

    private String name;
    private Date date;
    private Integer currentValue;
    private Integer initialValue;
    private String message;

    public Counter(String name, Integer initialValue, String message){
        this.name = name;
        this.date = new Date();
        this.currentValue = initialValue;
        this.initialValue = initialValue;
        this.message = message;
    }

    public Counter(String name, Integer initialValue){
        this.name = name;
        this.date = new Date();
        this.currentValue = initialValue;
        this.initialValue = initialValue;
        this.message = "";
    }

    public boolean increment(){
        if(this.currentValue>=0){
            this.currentValue++;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean decrement(){
        if(this.currentValue>0){
            this.currentValue--;
            return true;
        }
        else{
            return false;
        }
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

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    public String setMessage() {
        return message;
    }

    public void setMessage(String comment) {
        this.message = comment;
    }
}
