package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 3.5.30 Duplicates (revisited). Redo Exercise 2.5.31 using the Dedup filter given on
 * page 490. Compare the running times of the two approaches. Then use Dedup to run the
 * experiments for N = 10^7, 10^8, and10^9, repeat the experiments for random long values
 * and discuss the results.
 * <p>
 * 重复项(再访问). 重做练习 2.5.31 使用490 页给出的 Dedup 过滤.对比两种方式的运行时间.然后使用 Dedup 来
 * 运行实验,其中 N = 10^7, 10^8, 和10^9,在使用随机 long 值实验并讨论结果
 *
 * @author LeonChen
 * @since 9/15/21
 */
class E3_5_30 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        StdOut.printf("%21s %15s %15s %15s %10s\n", "Method | ", "Values type | ", "Values Generated | ",
                "Max Value | ", "Time spent");

        int[] initialValues = {1000, 10000, 100000, 1000000};
        int[] bigValues = {10000000, 100000000, 1000000000};

        // Generating integer values
        countDistinctValues(false, false, initialValues);
        countDistinctValues(true, false, initialValues);
        countDistinctValues(true, false, bigValues);

        // Generating long values
        countDistinctValues(false, true, initialValues);
        countDistinctValues(true, true, initialValues);
        countDistinctValues(true, true, bigValues);

    }

    private static class DistinctCounter {
        public int deDupWithIntegerValues(int numberOfValues, int maxValue) {
            // Using Java's super optimized Set and HashSet since we are dealing with very large numbers
            // and every optimization counts
            java.util.Set<Integer> set = new java.util.HashSet<>();

            for (int i = 0; i < numberOfValues; i++) {
                int key = StdRandom.uniform(maxValue);

                if (!set.contains(key)) {
                    set.add(key);
                }
            }

            return set.size();
        }

        public long deDupWithLongValues(int numberOfValues, long maxValue) {
            // Using Java's super optimized Set and HashSet since we are dealing with very large numbers
            // and every optimization counts
            java.util.Set<Long> set = new java.util.HashSet<>();

            for (int i = 0; i < numberOfValues; i++) {
                long key = ThreadLocalRandom.current().nextLong(maxValue);

                if (!set.contains(key)) {
                    set.add(key);
                }
            }

            return set.size();
        }

        private int countDistinctIntegerValuesUsingIndex(int numberOfValues, int maxValue) {
            int[] values = new int[maxValue];

            for (int i = 0; i < numberOfValues; i++) {
                int generatedValue = StdRandom.uniform(maxValue);
                values[generatedValue]++;
            }

            int distinctValues = 0;

            for (int value : values) {
                if (value != 0) {
                    distinctValues++;
                }
            }

            return distinctValues;
        }

        private long countDistinctLongValuesUsingIndex(int numberOfValues, int maxValue) {
            long[] values = new long[maxValue];

            for (int i = 0; i < numberOfValues; i++) {
                long key = ThreadLocalRandom.current().nextLong(maxValue);
                values[(int) key]++; //Ok to cast since the max value is 2x 10^9
            }

            int distinctValues = 0;

            for (long value : values) {
                if (value != 0) {
                    distinctValues++;
                }
            }

            return distinctValues;
        }
    }


    public static void countDistinctValues(boolean useDedup, boolean generateLongValues, int[] values) {
        int numberOfTrials = 10;

        String method;
        String valuesType;

        if (useDedup) {
            method = "DeDup";
        } else {
            method = "Array index count";
        }

        if (!generateLongValues) {
            valuesType = "Integer";
        } else {
            valuesType = "Long";
        }

        /**
         * T = 10
         * N = 10^3, 10^4, 10^5, 10^6, 10^7, 10^8, 10^9
         * M = N/2, N, 2N
         */

        DistinctCounter distinctCounter = new DistinctCounter();

        for (int n = 0; n < values.length; n++) {
            for (int m = 0; m < 3; m++) {
                int numberOfValues = values[n];

                int maxValue = 0;
                if (m == 0) {
                    maxValue = numberOfValues / 2;
                } else if (m == 1) {
                    maxValue = numberOfValues;
                } else if (m == 2) {
                    maxValue = 2 * numberOfValues;
                }

                Stopwatch stopwatch = new Stopwatch();

                for (int trial = 0; trial < numberOfTrials; trial++) {

                    // Count the distinct values, but will not be used in this exercise since we are interested
                    // only in the running time
                    long distinctValues;

                    if (!useDedup) {
                        if (!generateLongValues) {
                            distinctValues = distinctCounter.countDistinctIntegerValuesUsingIndex(numberOfValues, maxValue);
                        } else {
                            distinctValues = distinctCounter.countDistinctLongValuesUsingIndex(numberOfValues, maxValue);
                        }
                    } else {
                        if (!generateLongValues) {
                            distinctValues = distinctCounter.deDupWithIntegerValues(numberOfValues, maxValue);
                        } else {
                            distinctValues = distinctCounter.deDupWithLongValues(numberOfValues, maxValue);
                        }
                    }
                }

                double timeSpent = stopwatch.elapsedTime();
                printResults(method, valuesType, numberOfValues, maxValue, timeSpent);
            }
        }
    }

    private static void printResults(String method, String valuesType, int numberOfValues, int maxValue, double timeSpent) {
        StdOut.printf("%18s %15s %19d %15d %13.2f\n", method, valuesType, numberOfValues, maxValue, timeSpent);
    }


}
