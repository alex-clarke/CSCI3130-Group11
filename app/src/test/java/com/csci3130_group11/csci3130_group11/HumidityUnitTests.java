package com.csci3130_group11.csci3130_group11;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.csci3130_group11.csci3130_group11.Humidity;

/**
 *  HumidityUnitTests
 *  @author aclarke
 */
public class HumidityUnitTests {
    @Test
    public void checkHumidityRange() throws Exception {
        assertTrue(Humidity.checkMeasurement(25));
        assertFalse(Humidity.checkMeasurement(80));
        assertFalse(Humidity.checkMeasurement(0));
    }
    @Test
    public void checkInputtedHumidityRange() throws Exception {
        assertTrue(Humidity.checkUserInputtedRange(20,40));
        assertTrue(Humidity.checkUserInputtedRange(0,20));
        assertTrue(Humidity.checkUserInputtedRange(60,100));
        assertFalse(Humidity.checkUserInputtedRange(-10,20));
        assertFalse(Humidity.checkUserInputtedRange(80,110));
        assertFalse(Humidity.checkUserInputtedRange(-5,120));

    }
}