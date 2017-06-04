package com.csci3130_group11.csci3130_group11;

/**
 * Created by Kyle on 2017-06-04.
 */


//TODO Finish this
public class Measurement {

    protected double userInputedRangeLower;
    protected double userInputedRangeUpper;
    protected double deviceRangeLower;
    protected double deviceRangeUpper;

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

    protected boolean checkUserInputedRange(){
        //TODO
    }




}
