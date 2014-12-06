package com.arjungopisetty.trackyourstack;

/**
 * Created by ArjunGopisetty on 12/6/14.
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
        return "Budget{" +
                "mName='" + mName + '\'' +
                ", mValue=" + mValue +
                '}';
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
