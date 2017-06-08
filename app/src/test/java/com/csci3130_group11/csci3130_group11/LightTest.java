package com.csci3130_group11.csci3130_group11;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LightTest {

    Light l = new Light();
    @Test
    public void checkLightRange() throws Exception {
        int lightPerfect=50;
        int lightLow=0;
        int lightHigh=100;
        assertTrue(l.checkMeasurement(lightPerfect)&&!l.checkMeasurement(lightHigh)&&!l.checkMeasurement(lightLow));
    }
    @Test
    public void checkLightInputtedRange() throws Exception {

        int limitLow=0;
        int limitUp=100;
        int betweenUp=10;
        int betweenLow=80;
        int outsideUp=110;
        int outsideLow=-10;

        assertTrue(l.checkUserInputtedRange(limitLow,limitUp)&&
                        l.checkUserInputtedRange(betweenLow,betweenUp)&&
                        !l.checkUserInputtedRange(outsideLow,outsideUp)&&
                        !l.checkUserInputtedRange(outsideLow,betweenUp)&&
                        !l.checkUserInputtedRange(betweenLow,outsideUp));

    }

}

