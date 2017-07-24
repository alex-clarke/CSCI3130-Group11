package com.csci3130_group11.csci3130_group11;

/**
 * Created by aclarke on 2017-06-06.
 */

public class Humidity extends Measurement {

    /**
     * Default constructor for humidity object
     */
    public Humidity(){
        super();
        deviceRangeLower=0;
        deviceRangeUpper=100;
        userInputedRangeLower=deviceRangeLower;
        userInputedRangeUpper=deviceRangeUpper;
    }

    /**
     * Constructor that takes in a current value for humidity
     * @param curr
     */
    public Humidity(double curr){
        super();
        deviceRangeLower=0;
        deviceRangeUpper=100;
        current=curr;
        userInputedRangeLower=deviceRangeLower;
        userInputedRangeUpper=deviceRangeUpper;
    }

    /**
     * Constructor that takes in a current value as well as low and high vales for the ranges.~
     * @param curr
     * @param low
     * @param high
     */
    public Humidity(double curr, double low, double high){
        super();
        deviceRangeLower=0;
        deviceRangeUpper=100;
        current=curr;
        userInputedRangeLower=low;
        userInputedRangeUpper=high;
    }
}
