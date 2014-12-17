package com.arjungopisetty.trackyourstack;

/**
 * Created by Hareet on 12/6/2014.
 */
public class Budget {

    private String mName;
    private double mValue;

    public Budget(String name, double value) {
        mName = name;
        mValue = value;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toString() {
        return
                "Name='" + mName + '\'' +
                ", Value=" + mValue
                ;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getValue() {
        return mValue;
    }

    public void setValue(double value) {
        mValue = value;
    }
}