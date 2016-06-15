package com.joinsystem.goku.common.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <p>Date : 16/6/12</p>
 * <p>Time : 上午11:22</p>
 *
 * @author jerry
 */
public class MathUtilsTest {

    private Double[] numbers;

    @Before
    public void setUp() throws Exception {
        numbers = new Double[]{17.8, 33.432, null, 0d, 55.5, 89.01, 11.32, null, 5.659, 67.918};
    }

    @Test
    public void testMean() throws Exception {
        System.out.println(MathUtils.mean(numbers, false));
    }

    @Test
    public void testSummation() throws Exception {
        System.out.println(MathUtils.summation(numbers));
    }

    @Test
    public void testMaximum() throws Exception {
        System.out.println(MathUtils.maximum(numbers));
    }

    @Test
    public void testMinimum() throws Exception {
        System.out.println(MathUtils.minimum(numbers));
    }

    @Test
    public void testConvertArrayType() throws Exception {
        System.out.println(MathUtils.convertArrayType(numbers).toString());
    }
}