package com.csci3130_group11.csci3130_group11;

/**
 * Created by sr on 2017-06-08.
 */

//lalalala

import java.io.Serializable;
import java.math.BigDecimal;

public class Util implements Serializable{

    /*
    Variables for objects
     */
    private static Light l; //Light
    private static Temperature t; // temperature
    private static Humidity h; //humidity

    public static double increase(double d) {
        BigDecimal b1 = new BigDecimal(Double.toString(d));

        BigDecimal b2 = new BigDecimal("1");

        return b1.add(b2).doubleValue();
    }

    public static double decrease(double d) {
        BigDecimal b1 = new BigDecimal(Double.toString(d));
        BigDecimal b2 = new BigDecimal("1");
        return b1.subtract(b2).doubleValue();
    }

    public static void createDataObjects(){

        l = new Light();
        t = new Temperature();
        h = new Humidity();

    }


    //Getters and Setters - AClarke
    /**
     *
     * @return h
     */
    public static Humidity getHumidity() {
        return h;
    }

    /**
     *
     * @return l
     */
    public static Light getLight() {
        return l;
    }

    /**
     *
     * @return t
     */
    public static Temperature getTemperature() {
        return t;
    }

    /**
     *
     * @param humidity
     */
    public static void setHumidity(Humidity humidity) {
        h = humidity;
    }

    /**
     *
     * @param light
     */
    public static void setLight(Light light) {
        l = light;
    }

    /**
     *
     * @param temperature
     */
    public static void setTemperature(Temperature temperature) {
        t = temperature;
    }
}
