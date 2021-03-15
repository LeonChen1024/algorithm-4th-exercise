package edu.princeton.cs.exercise.util;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 数组生成工具
 *
 * @author LeonChen
 * @since 2021/2/25
 */
public class ArrayGenUtil {

    public static int[] genRandIntArr(int length, int lowerBound, int upperBound) {
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = StdRandom.uniform(lowerBound, upperBound);
        }

        return array;
    }

    public static Comparable[] genRandomArr2Values(int length) {
        Comparable[] array = new Comparable[length];

        for (int i = 0; i < length; i++) {
            array[i] = StdRandom.uniform(2);
        }

        return array;
    }

    public static Comparable[] genArrAllSameKey(int length) {
        Comparable[] array = new Comparable[length];

        for (int i = 0; i < length; i++) {
            array[i] = 0;
        }

        return array;
    }

    public static Comparable[] genReverseOrderedArr(int length) {
        Comparable[] array = new Comparable[length];

        for (int i = 0; i < length; i++) {
            array[i] = length - 1 - i;
        }

        return array;
    }

    public static Comparable[] genOrderedArr(int length) {
        Comparable[] array = new Comparable[length];

        for (int i = 0; i < length; i++) {
            array[i] = i;
        }

        return array;
    }

    public static String[] genRandomStrArr(int length, int minStringLength, int maxStringLength) {
        String[] array = new String[length];

        for (int i = 0; i < length; i++) {
            array[i] = genRandomStr(minStringLength, maxStringLength);
        }

        return array;
    }

    public static String genRandomStr(int minStringLength, int maxStringLength) {

        StringBuilder randomString = new StringBuilder();

        int stringSize = StdRandom.uniform(minStringLength, maxStringLength + 1);

        for (int i = 0; i < stringSize; i++) {
            char randomChar = (char) StdRandom.uniform(Constants.UPPER_LETTER_INITIAL_INDEX,
                    Constants.LOWER_LETTER_FINAL_INDEX + 1);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

}
