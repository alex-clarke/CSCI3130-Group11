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

    protected boolean checkMeasurement(double val){
        if(val>userInputedRangeLower && val<userInputedRangeUpper)
            return true;
        else
            return false;
    }

    //AClarke

    /**
     *
     * @param lower
     * @param upper
     * @return inDeviceRange
     */
    protected boolean checkUserInputtedRange(double lower, double upper){
        return (lower<upper&&lower>=deviceRangeLower&&upper<=deviceRangeUpper);
    }

    //Getters and Setters - AClarke

    /**
     *
     * @return deviceRangeLower
     */
    public double getDeviceRangeLower() {
        return deviceRangeLower;
    }

    /**
     *
     * @param deviceRangeLower
     */
    public void setDeviceRangeLower(double deviceRangeLower) {
        this.deviceRangeLower = deviceRangeLower;
    }

    /**
     *
     * @return deviceRangeUpper
     */
    public double getDeviceRangeUpper() {
        return deviceRangeUpper;
    }

    /**
     *
     * @param deviceRangeUpper
     */
    public void setDeviceRangeUpper(double deviceRangeUpper) {
        this.deviceRangeUpper = deviceRangeUpper;
    }

    /**
     *
     * @return userInputtedRangeLower
     */
    public double getUserInputedRangeLower() {
        return userInputedRangeLower;
    }

    /**
     *
     * @param userInputedRangeLower
     */
    public void setUserInputedRangeLower(double userInputedRangeLower) {
        this.userInputedRangeLower = userInputedRangeLower;
    }

    /**
     *
     * @return userInputtedRangeUpper
     */
    public double getUserInputedRangeUpper() {
        return userInputedRangeUpper;
    }

    /**
     *
     * @param userInputedRangeUpper
     */
    public void setUserInputedRangeUpper(double userInputedRangeUpper) {
        this.userInputedRangeUpper = userInputedRangeUpper;
    }

    /**
     *
     * @return current
     */
    public double getCurrent() {
        return current;
    }

    /**
     *
     * @param current
     */
    public void setCurrent(double current) {
        this.current = current;
    }
}