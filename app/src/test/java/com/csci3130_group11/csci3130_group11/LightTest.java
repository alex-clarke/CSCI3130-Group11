package com.csci3130_group11.csci3130_group11;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LightTest {
    @Test
    public void checkLightRange() throws Exception {
        int lightPerfect=50;
        int lightLow=0;
        int lightHigh=100;
        assertTrue(Light.checkMeasurement(lightPerfect)&&!Light.checkMeasurement(lightHigh)&&!Light.checkMeasurement(lightLow));
    }
    @Test
    public void checkLightInputtedRange() throws Exception {

        int limitLow=0;
        int limitUp=100;
        int betweenUp=10;
        int betweenLow=80;
        int outsideUp=110;
        int outsideLow=-10;

        assertTrue(Light.checkUserInputtedRange(limitLow,limitUp)&&
                        Light.checkUserInputtedRange(betweenLow,betweenUp)&&
                        !Light.checkUserInputtedRange(outsideLow,outsideUp)&&
                        !Light.checkUserInputtedRange(outsideLow,betweenUp)&&
                        !Light.checkUserInputtedRange(betweenLow,outsideUp));

    }

}

