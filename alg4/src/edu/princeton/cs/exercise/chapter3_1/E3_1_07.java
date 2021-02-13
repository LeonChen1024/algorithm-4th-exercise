package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.FrequencyCounter;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 3.1.7 What is the average number of distinct keys that FrequencyCounter will find
 * among N random nonnegative integers less than 1,000, for N=10, 10^2, 10^3, 10^4, 10^5, and
 * 10^6?
 * FrequencyCounter 平均能从 N 个随机小于 1000的非负整数找到多少个不同的键,其中 N=10, 10^2, 10^3, 10^4,
 * 10^5, 和 10^6
 *
 * @author LeonChen
 * @since 2/3/20
 */
class E3_1_07 {

    /**
     * 在官方的 FrequencyCounter 类中增加一个根据给定数组计算的方法
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 10; i < Math.pow(10, 6); i = i * 10) {
            String[] a = new String[i];
            for (int j = 0; j < i; j++) {
                a[j] = String.valueOf(StdRandom.uniform(1000));
            }
            StdOut.println("N = " + i);
            FrequencyCounter.count(a);

        }

    }


}
