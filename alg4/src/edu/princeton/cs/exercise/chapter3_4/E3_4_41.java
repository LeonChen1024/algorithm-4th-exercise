package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 3.4.41 Double probing. Run experimental studies to evaluate the effectiveness of double
 * probing (see Exercise 3.4.27).
 * <p>
 * 双重探测.运行实验证明双重探测的效率(见练习3.4.27)
 *
 * @author LeonChen
 * @since 8/23/21
 */
class E3_4_41 {

    /**
     * @formatter:off
     *   Operation      Separate-chaining HT time       Double probing HT time
     *    Delete                         0.24                      2.84
     * @formatter:on
     */
    public static void main(String[] args) {

        SeparateChainingHashST<Integer, Integer> separateChainingHashTable =
                new SeparateChainingHashST<>();
        E3_4_27.SeparateChainingHashTableDoubleProbing<Integer, Integer> doubleProbingHashTable = new E3_4_27.SeparateChainingHashTableDoubleProbing<>();

        StdOut.printf("%12s %20s %20s\n", "Operation | ", "Separate-chaining HT time | ", "Double probing HT time");

        //Put tests
        int[] randomKeysPut = new int[1000000];

        for (int i = 0; i < randomKeysPut.length; i++) {
            int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
            randomKeysPut[i] = randomKey;
        }

        Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysPut.length; i++) {
            separateChainingHashTable.put(randomKeysPut[i], randomKeysPut[i]);
        }
        double timeSpentOnPutSeparateChaining = stopwatch.elapsedTime();

        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysPut.length; i++) {
            doubleProbingHashTable.put(randomKeysPut[i], randomKeysPut[i]);
        }
        double timeSpentOnPutDoubleProbing = stopwatch.elapsedTime();

        printResults("Put", timeSpentOnPutSeparateChaining, timeSpentOnPutDoubleProbing);

        //Get tests
        int[] randomKeysGet = new int[500000];

        for (int i = 0; i < randomKeysGet.length; i++) {
            int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
            randomKeysGet[i] = randomKey;
        }

        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysGet.length; i++) {
            separateChainingHashTable.get(randomKeysGet[i]);
        }
        double timeSpentOnGetSeparateChaining = stopwatch.elapsedTime();

        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysGet.length; i++) {
            doubleProbingHashTable.get(randomKeysGet[i]);
        }
        double timeSpentOnGetDoubleProbing = stopwatch.elapsedTime();

        printResults("Get", timeSpentOnGetSeparateChaining, timeSpentOnGetDoubleProbing);

        //Delete tests
        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysPut.length / 2; i++) {
            separateChainingHashTable.delete(randomKeysPut[i]);
        }
        double timeSpentOnDeleteSeparateChaining = stopwatch.elapsedTime();

        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysPut.length / 2; i++) {
            doubleProbingHashTable.delete(randomKeysPut[i]);
        }
        double timeSpentOnDeleteDoubleProbing = stopwatch.elapsedTime();

        printResults("Delete", timeSpentOnDeleteSeparateChaining, timeSpentOnDeleteDoubleProbing);

    }


    private static void printResults(String operation, double separateChainingTime, double doubleProbingTime) {
        StdOut.printf("%9s %28.2f %25.2f\n", operation, separateChainingTime, doubleProbingTime);
    }


}
