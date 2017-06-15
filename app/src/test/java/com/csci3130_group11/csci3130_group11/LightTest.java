package com.csci3130_group11.csci3130_group11;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Seyd and HongCheng
 */
public class LightTest {

    Light l = new Light();
    @Test
    public void checkLightRange() throws Exception {
        int lightPerfect=50;
        int lightLow=-1;
        int lightHigh=110;
        assertTrue(l.checkMeasurement(lightPerfect)&&!l.checkMeasurement(lightHigh)&&!l.checkMeasurement(lightLow));
    }
    @Test
    public void checkLightInputtedRange() throws Exception {

        int limitLow=0;
        int limitUp=100;
        int betweenUp=80;
        int betweenLow=10;
        int outsideUp=110;
        int outsideLow=-10;

        assertTrue(l.checkUserInputtedRange(limitLow,limitUp)&&
                        l.checkUserInputtedRange(betweenLow,betweenUp)&&
                        !l.checkUserInputtedRange(outsideLow,outsideUp)&&
                        !l.checkUserInputtedRange(outsideLow,betweenUp)&&
                        !l.checkUserInputtedRange(betweenLow,outsideUp));

    }

}

