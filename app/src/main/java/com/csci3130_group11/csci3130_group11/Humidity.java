package com.csci3130_group11.csci3130_group11;

/**
 * Created by aclarke on 2017-06-06.
 */

public class Humidity extends Measurement {
    public Humidity(){
        super();
        deviceRangeLower=0;
        deviceRangeUpper=100;
    }
    public Humidity(double curr){
        super();
        deviceRangeLower=0;
        deviceRangeUpper=100;
        current=curr;
        userInputedRangeLower=deviceRangeLower;
        userInputedRangeUpper=deviceRangeUpper;
    }
    public Humidity(double curr, double low, double high){
        super();
        deviceRangeLower=0;
        deviceRangeUpper=100;
        current=curr;
        userInputedRangeLower=low;
        userInputedRangeUpper=high;
    }
}
