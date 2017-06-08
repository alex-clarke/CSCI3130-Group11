package com.csci3130_group11.csci3130_group11;

/**
 * Created by Jason Parsons and Kyle Tilbury on 04/06/2017.
 *
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TemperatureTests {  //Low:-40  High:125
    @Test
    public void withinInputtedRange() throws Exception {
        Temperature t = new Temperature();
        assertEquals(t.checkUserInputtedRange(-50, 130), false);
        assertEquals(t.checkUserInputtedRange(-50, 40), false);
        assertEquals(t.checkUserInputtedRange(-20, 130), false);
        assertEquals(t.checkUserInputtedRange(20, 40), true);
        assertEquals(t.checkUserInputtedRange(-40, -125), true);
    }

    @Test
    public void withinMeasurement() throws Exception {
        Temperature t = new Temperature();
        assertEquals(t.checkMeasurement(0), false);
        assertEquals(t.checkMeasurement(40), false);
        assertEquals(t.checkMeasurement(20), true);
    }
}
