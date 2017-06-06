package com.csci3130_group11.csci3130_group11;

/**
 * Created by Kyle on 2017-06-04.
 */


public class Measurement {

    protected double userInputedRangeLower;
    protected double userInputedRangeUpper;
    protected double deviceRangeLower;
    protected double deviceRangeUpper;
    protected double current;

    protected Measurement(){

    }

    protected double getRangeLower(){
        return userInputedRangeLower;
    }
    protected double getRangeUpper(){
        return userInputedRangeUpper;
    }

    protected void setRangeLower(double lowerVal){
        userInputedRangeLower = lowerVal;
    }
    protected void setRangeUpper(double upperVal){
        userInputedRangeUpper = upperVal;
    }

    protected boolean checkMeasurement(double val){
        if(val>userInputedRangeLower && val<userInputedRangeUpper)
            return true;
        else
            return false;
    }

    //AClarke
    protected boolean checkUserInputtedRange(double lower, double upper){
        return (lower<upper&&lower>=deviceRangeLower&&upper<=deviceRangeUpper);
    }

    //Getters and Setters - AClarke
    public double getDeviceRangeLower() {
        return deviceRangeLower;
    }

    public void setDeviceRangeLower(double deviceRangeLower) {
        this.deviceRangeLower = deviceRangeLower;
    }

    public double getDeviceRangeUpper() {
        return deviceRangeUpper;
    }

    public void setDeviceRangeUpper(double deviceRangeUpper) {
        this.deviceRangeUpper = deviceRangeUpper;
    }

    public double getUserInputedRangeLower() {
        return userInputedRangeLower;
    }

    public void setUserInputedRangeLower(double userInputedRangeLower) {
        this.userInputedRangeLower = userInputedRangeLower;
    }

    public double getUserInputedRangeUpper() {
        return userInputedRangeUpper;
    }

    public void setUserInputedRangeUpper(double userInputedRangeUpper) {
        this.userInputedRangeUpper = userInputedRangeUpper;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }
}
