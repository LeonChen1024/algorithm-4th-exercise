package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.exercise.util.ArrayGenUtil;

/**
 * 3.2.43 Put/get ratio. Determine empirically the ratio of the amount of time that
 * BST spends on put() operations to the time that it spends on get() operations when
 * FrequencyCounter is used to find the frequency of occurrence of values in 1 million
 * randomly-generated integers.
 * <p>
 * Put/get 比例.凭经验判断BST 在FrequencyCounter 中 1 百万随机生成整数中找值出现频率的过程中花在put()
 * 操作和 get()操作的时间比例.
 *
 * @author LeonChen
 * @since 5/8/21
 */
class E3_2_43 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        int numberOfKeys = 1000000;
        StdOut.printf("%15s %20s %20s %8s\n", "Number of Keys | ", "Running Time Put | ", "Running Time Get | ", "Ratio");

        int[] randomValues = ArrayGenUtil.genRandIntArr(numberOfKeys, 0,
                Integer.MAX_VALUE - 1);
        double[] result = frequencyCounter(randomValues);

        StdOut.printf("%14d %20.2f %20.2f %11.2f\n", numberOfKeys, result[0], result[1], result[2]);

    }


    private static double[] frequencyCounter(int[] values) {

        double totalTimeSpentInPut = 0;
        double totalTimeSpentInGet = 0;
        Stopwatch timer;

        BST<Integer, Integer> bst = new BST<>();

        for (Integer value : values) {

            timer = new Stopwatch();
            boolean containsValue = bst.contains(value); //contains() uses get() internally
            totalTimeSpentInGet += timer.elapsedTime();

            if (!containsValue) {
                timer = new Stopwatch();
                bst.put(value, 1);
                totalTimeSpentInPut += timer.elapsedTime();
            } else {
                timer = new Stopwatch();
                int currentFrequency = bst.get(value);
                totalTimeSpentInGet += timer.elapsedTime();

                timer = new Stopwatch();
                bst.put(value, currentFrequency + 1);
                totalTimeSpentInPut += timer.elapsedTime();
            }
        }

        int max = 0;
        timer = new Stopwatch();
        bst.put(max, 0);
        totalTimeSpentInPut += timer.elapsedTime();

        for (Integer value : bst.keys()) {
            timer = new Stopwatch();
            if (bst.get(value) > bst.get(max)) {
                totalTimeSpentInGet += timer.elapsedTime();
                max = value;
            }
        }

        timer = new Stopwatch();
        String maxFrequency = max + " " + bst.get(max);
        totalTimeSpentInGet += timer.elapsedTime();

        double ratio = totalTimeSpentInPut / totalTimeSpentInGet;

        return new double[]{totalTimeSpentInPut, totalTimeSpentInGet, ratio};
    }

}
