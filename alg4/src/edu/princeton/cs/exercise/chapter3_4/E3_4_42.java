package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 3.4.42 Double hashing. Run experimental studies to evaluate the effectiveness of double
 * hashing (see Exercise 3.4.28).
 * <p>
 * 双重 hash.运行实验证明双重 hash 的效率(见练习 3.4.28)
 *
 * @author LeonChen
 * @since 8/24/21
 */
class E3_4_42 {

    /**
     * @formatter:off
     * Operation |  Linear-probing HT time |  Double hashing HT time
     *       Put                      0.21                      0.24
     *       Get                      0.03                      0.05
     *    Delete                      0.11                      0.08
     * @formatter:on
     */
    public static void main(String[] args) {

        LinearProbingHashST<Integer, Integer> linearProbingHashTable = new LinearProbingHashST<>(10);
        E3_4_28.DoubleHashingHashTable<Integer, Integer> doubleHashingHashTable = new E3_4_28.DoubleHashingHashTable<>(10);

        StdOut.printf("%12s %20s %20s\n", "Operation | ", "Linear-probing HT time | ", "Double hashing HT time");

        //Put tests
        int[] randomKeysPut = new int[1000000];

        for (int i = 0; i < randomKeysPut.length; i++) {
            int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
            randomKeysPut[i] = randomKey;
        }

        Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysPut.length; i++) {
            linearProbingHashTable.put(randomKeysPut[i], randomKeysPut[i]);
        }
        double timeSpentOnPutLinearProbing = stopwatch.elapsedTime();

        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysPut.length; i++) {
            doubleHashingHashTable.put(randomKeysPut[i], randomKeysPut[i]);
        }
        double timeSpentOnPutDoubleHashing = stopwatch.elapsedTime();

        printResults("Put", timeSpentOnPutLinearProbing, timeSpentOnPutDoubleHashing);

        //Get tests
        int[] randomKeysGet = new int[500000];

        for (int i = 0; i < randomKeysGet.length; i++) {
            int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
            randomKeysGet[i] = randomKey;
        }

        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysGet.length; i++) {
            linearProbingHashTable.get(randomKeysGet[i]);
        }
        double timeSpentOnGetLinearProbing = stopwatch.elapsedTime();

        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysGet.length; i++) {
            doubleHashingHashTable.get(randomKeysGet[i]);
        }
        double timeSpentOnGetDoubleHashing = stopwatch.elapsedTime();

        printResults("Get", timeSpentOnGetLinearProbing, timeSpentOnGetDoubleHashing);

        //Delete tests
        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysPut.length / 2; i++) {
            linearProbingHashTable.delete(randomKeysPut[i]);
        }
        double timeSpentOnDeleteLinearProbing = stopwatch.elapsedTime();

        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysPut.length / 2; i++) {
            doubleHashingHashTable.delete(randomKeysPut[i]);
        }
        double timeSpentOnDeleteDoubleHashing = stopwatch.elapsedTime();

        printResults("Delete", timeSpentOnDeleteLinearProbing, timeSpentOnDeleteDoubleHashing);


    }

    private static void printResults(String operation, double linearProbingTime, double doubleHashingTime) {
        StdOut.printf("%9s %25.2f %25.2f\n", operation, linearProbingTime, doubleHashingTime);
    }

}
