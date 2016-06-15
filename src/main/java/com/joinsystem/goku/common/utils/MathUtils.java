package com.joinsystem.goku.common.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

/**
 * 提供一些基础运算的工具类.
 * <p/>
 * <p>Date : 16/6/12</p>
 * <p>Time : 上午8:57</p>
 *
 * @author jerry
 */
public class MathUtils {

    /**
     * 求一组数字的平均值.
     * TODO 以后可能要做一个多种数据类型的方法Hash
     *
     * @param numbers           计算数组
     * @param isAllowNull       是否允许Null值, isAllowNull 把Null转换成 0; !isAllowNull 把Null元素删除
     * @return                  平均值
     */
    public static Double mean(Double[] numbers, boolean isAllowNull) {
        Double[] temp;
        if (ArrayUtils.isNotEmpty(numbers)) {
            int counter;
            if (!isAllowNull) {
                temp = deleteInvalid(numbers);
            } else {
                temp = convertNullToZero(numbers);
            }
            counter = temp.length;
            return sum(temp) / counter;
        }

        return 0d;
    }

    /**
     * 求一组数字的合计值.
     * TODO 以后可能要做一个多种数据类型的方法Hash
     *
     * @param numbers           计算数组
     * @return                  合计值
     */
    public static Double summation(Double[] numbers) {
        if (ArrayUtils.isNotEmpty(numbers)) {
            return sum(convertNullToZero(numbers));
        }
        
        return 0D;
    }

    /**
     * 求一组数字中的最大值
     *
     * @param numbers           计算数组
     * @return                  最大值
     */
    public static Double maximum(Double[] numbers) {
        if (ArrayUtils.isNotEmpty(numbers)) {
            double[] tempNums = convertArrayType(deleteInvalid(numbers));
            return NumberUtils.max(tempNums);
        }
        
        return 0d;
    }

    /**
     * 求一组数字中的最小值
     *
     * @param numbers           计算数组
     * @return                  最小值
     */
    public static Double minimum(Double[] numbers) {
        if (ArrayUtils.isNotEmpty(numbers)) {
            double[] tempNums = convertArrayType(deleteInvalid(numbers));
            return NumberUtils.min(tempNums);
        }

        return 0d;
    }

    private static Double[] deleteInvalid(Double[] numbers) {
        List<Double> tempList = Lists.newArrayList();
        Double[] rets = new Double[0];
        for (int i = 0, l = numbers.length; i < l; i++) {
            if (numbers[i] == null) {
                ArrayUtils.removeElement(numbers, i);
            } else {
                tempList.add(numbers[i]);
            }
        }
        return tempList.toArray(rets);
    }

    private static Double[] convertNullToZero(Double[] numbers) {
        for (int i = 0, l = numbers.length; i < l; i++) {
            if (numbers[i] == null) {
                numbers[i] = 0d;
            }
        }

        return numbers;
    }

    private static Double sum(Double[] numbers) {
        double total = 0;
        for (Double number : numbers) {
            total += number;
        }
        return total;
    }

    /**
     * Double[]转double[]
     *
     * @param numbers       计算数组
     * @return              转换后的double数组
     */
    public static double[] convertArrayType(Double[] numbers) {
        double[] ret = new double[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            ret[i] = numbers[i];
        }
        return ret;
    }
}
