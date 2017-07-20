package com.csci3130_group11.csci3130_group11;

/**
 * Created by sr on 2017-06-08.
 */



import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Util extends AppCompatActivity {

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

    /**
     * Method ot create Data Objects (Light, Temperature, Humidity) one set per Application Context
     */
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


    /**
     * Retrieves stored information
     */
    public static Context retrieveSavedRanges(Context context){


        /*
        Creates objects if they are null... Only during background process.
         */
        if (l==null||t==null||h==null){
            createDataObjects();
        }

        SharedPreferences savedData = PreferenceManager.getDefaultSharedPreferences(context);

        String teLow = savedData.getString("TEMPERATURE_LOW", Double.toString(t.getUserInputedRangeLower()));
        String teHigh = savedData.getString("TEMPERATURE_HIGH", Double.toString(t.getUserInputedRangeUpper()));
        String huLow = savedData.getString("HUMIDITY_LOW", Double.toString(h.getUserInputedRangeLower()));
        String huHigh = savedData.getString("HUMIDITY_HIGH", Double.toString(h.getUserInputedRangeUpper()));
        String liLow  = savedData.getString("LIGHT_LOW", Double.toString(l.getUserInputedRangeLower()));
        String liHigh =  savedData.getString("LIGHT_HIGH", Double.toString(l.getUserInputedRangeUpper()));

        // Parsing from String to Double
        double hMax = Double.parseDouble(huHigh);
        double hMin = Double.parseDouble(huLow );
        double tMax = Double.parseDouble(teHigh);
        double tMin = Double.parseDouble(teLow );
        double lMax = Double.parseDouble(liHigh);
        double lMin = Double.parseDouble(liLow );

        h.setUserInputedRangeLower(hMin);
        l.setUserInputedRangeLower(lMin);
        t.setUserInputedRangeLower(tMin);
        h.setUserInputedRangeUpper(hMax);
        l.setUserInputedRangeUpper(lMax);
        t.setUserInputedRangeUpper(tMax);

        return context;

    }
}
