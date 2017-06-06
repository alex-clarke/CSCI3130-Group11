package com.csci3130_group11.csci3130_group11;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *  HumidityUnitTests
 *  @author aclarke
 */
public class HumidityUnitTests {
    @Test
    public void checkHumidityRange() throws Exception {
        Humidity h = new Humidity();
        assertTrue(h.checkMeasurement(25));
        assertFalse(h.checkMeasurement(80));
        assertFalse(h.checkMeasurement(0));
    }
    @Test
    public void checkInputtedHumidityRange() throws Exception {
        Humidity h = new Humidity();
        assertTrue(h.checkUserInputtedRange(20,40));
        assertTrue(h.checkUserInputtedRange(0,20));
        assertTrue(h.checkUserInputtedRange(60,100));
        assertFalse(h.checkUserInputtedRange(-10,20));
        assertFalse(h.checkUserInputtedRange(80,110));
        assertFalse(h.checkUserInputtedRange(-5,120));

    }
}