package com.csci3130_group11.csci3130_group11;

/**
 * Created by aclarke on 2017-06-06.
 */

public class Light extends Measurement {
    public Light(){
        super();
        deviceRangeLower=0;
        deviceRangeUpper=100;
        userInputedRangeLower=deviceRangeLower;
        userInputedRangeUpper=deviceRangeUpper;
    }
    public Light(double curr){
        super();
        deviceRangeLower=0;
        deviceRangeUpper=100;
        current=curr;
        userInputedRangeLower=deviceRangeLower;
        userInputedRangeUpper=deviceRangeUpper;
    }
    public Light(double curr, double low, double high){
        super();
        deviceRangeLower=0;
        deviceRangeUpper=100;
        current=curr;
        userInputedRangeLower=low;
        userInputedRangeUpper=high;
    }
}
