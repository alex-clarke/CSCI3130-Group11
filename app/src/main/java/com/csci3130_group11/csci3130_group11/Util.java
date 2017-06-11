package com.csci3130_group11.csci3130_group11;

/**
 * Created by sr on 2017-06-08.
 */

//lalalala

import android.content.Intent;

import java.io.Serializable;
import java.math.BigDecimal;

public class Util implements Serializable{

    /*
    Variables for objects
     */
    Light l; //Light
    Temperature t; // temperature
    Humidity h; //humidity

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

    public static void assignObjects(Temperature temperature, Humidity humidity, Light light){
        temperature = t;
        humidity = h;
        light = l;
    }
}
