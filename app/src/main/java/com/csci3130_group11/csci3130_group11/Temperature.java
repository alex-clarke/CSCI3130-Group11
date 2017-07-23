package com.csci3130_group11.csci3130_group11;

/**
 * Created by aclarke on 2017-06-06.
 */

public class Temperature extends Measurement {

    /**
     * Default constructor for Temperature object
     */
    public Temperature(){
        super();
        deviceRangeLower=-40;
        deviceRangeUpper=125;
        userInputedRangeLower=deviceRangeLower;
        userInputedRangeUpper=deviceRangeUpper;
    }
    /**
     * Constructor that takes in a current value for temperature
     * @param curr
     */
    public Temperature(double curr){
        super();
        deviceRangeLower=-40;
        deviceRangeUpper=125;
        current=curr;
        userInputedRangeLower=deviceRangeLower;
        userInputedRangeUpper=deviceRangeUpper;
    }
    /**
     * Constructor that takes in a current value as well as low and high vales for the ranges.
     * @param curr
     * @param low
     * @param high
     */
    public Temperature(double curr, double low, double high){
        super();
        deviceRangeLower=-40;
        deviceRangeUpper=125;
        current=curr;
        userInputedRangeLower=low;
        userInputedRangeUpper=high;
    }
}
