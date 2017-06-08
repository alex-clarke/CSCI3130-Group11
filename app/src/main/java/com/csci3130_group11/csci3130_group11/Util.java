package com.csci3130_group11.csci3130_group11;

import java.math.BigDecimal;

/**
 * Created by xuhongcheng on 2017-06-07.
 */

public class Util {

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
}