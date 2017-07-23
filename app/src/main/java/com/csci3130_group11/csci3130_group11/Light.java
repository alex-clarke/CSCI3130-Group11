package com.csci3130_group11.csci3130_group11;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by aclarke on 2017-06-06.
 */

public class Light extends Measurement {

    /**
     * Default constructor for light object
     */
    public Light(){
        super();
        deviceRangeLower=0;
        deviceRangeUpper=100;
        userInputedRangeLower=deviceRangeLower;
        userInputedRangeUpper=deviceRangeUpper;
    }

    /**
     * Constructor that takes in a current value for light
     * @param curr
     */
    public Light(double curr){
        super();
        deviceRangeLower=0;
        deviceRangeUpper=100;
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
    public Light(double curr, double low, double high){
        super();
        deviceRangeLower=0;
        deviceRangeUpper=100;
        current=curr;
        userInputedRangeLower=low;
        userInputedRangeUpper=high;
    }

}
