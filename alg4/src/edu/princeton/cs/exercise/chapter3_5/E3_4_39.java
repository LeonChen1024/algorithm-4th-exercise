package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 3.4.39 Linear-probing distribution. Write a program that inserts N/2 random int keys
 * into a table of size N using linear probing, then computes the average cost of a search
 * miss in the resulting table from the cluster lengths, for N = 10^3, 10^4, 10^5, 10^6
 * . Discuss the extent to which your results validate Proposition M.
 * <p>
 * 线性探测分布.编写一个程序插入 N/2 个随机整数键到一个大小为 N 使用线性探测的表中,然后计算在表中搜索丢失的
 * 平均成本,其中  N = 10^3, 10^4, 10^5, 10^6.讨论并扩展你的结果来证明命题M
 *
 * @author LeonChen
 * @since 8/22/21
 */
class E3_4_39 {

    /**
     * @formatter:off
     * Table size |  Avg cost of search miss |  Expected avg cost of search miss
     *       1000                       2.36                                2.50
     *      10000                       2.50                                2.50
     *     100000                       2.48                                2.50
     *    1000000                       2.50                                2.50
     * @formatter:on
     */
    public static void main(String[] args) {
        StdOut.printf("%12s %20s %25s\n", "Table size | ", "Avg cost of search miss | ", "Expected avg cost of search miss");

        int[] hashTableSizes = {1000, 10000, 100000, 1000000};

        for (int hashTableSizeIndex = 0; hashTableSizeIndex < hashTableSizes.length; hashTableSizeIndex++) {
            int hashTableSize = hashTableSizes[hashTableSizeIndex];

            LinearProbingHashTableAvgSearchMissCost<Integer, Integer> linearProbingHashTableAvgSearchMissCost =
                    new LinearProbingHashTableAvgSearchMissCost(hashTableSize);

            for (int i = 0; i < hashTableSize / 2; i++) {
                int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
                linearProbingHashTableAvgSearchMissCost.put(randomKey, randomKey);
            }

            double averageCostOfSearchMissFromClusterLengths = linearProbingHashTableAvgSearchMissCost.getAverageCostOfSearchMissFromClusterLengths();
            double expectedAverageCostOfSearchMiss = linearProbingHashTableAvgSearchMissCost.getExpectedAverageCostOfSearchMiss();
            printResults(hashTableSize, averageCostOfSearchMissFromClusterLengths, expectedAverageCostOfSearchMiss);
        }
    }

    private static class LinearProbingHashTableAvgSearchMissCost<Key, Value> extends LinearProbingHashST<Key, Value> {

        LinearProbingHashTableAvgSearchMissCost(int size) {
            super(size);
        }

        public double getAverageCostOfSearchMissFromClusterLengths() {
            long totalCostOfSearchMisses = 0;

            //Simulates a search miss starting at each array position
            for (int i = 0; i < m; i++) {
                int index = i;
                totalCostOfSearchMisses++;

                while (keys[index] != null) {
                    totalCostOfSearchMisses++;
                    index = (index + 1) % m;
                }
            }

            return totalCostOfSearchMisses / (double) m;
        }

        //Proposition M
        //Expected average cost of search miss = ~1/2 * (1 + (1 / (1 - a)^2))
        public double getExpectedAverageCostOfSearchMiss() {
            double loadFactor = n / (float) m;
            return 0.5 * (1 + (1 / Math.pow(1 - loadFactor, 2)));
        }
    }


    private static void printResults(int hashTableSize, double averageCostOfSearchMiss, double expectedAverageCostOfSearchMiss) {
        StdOut.printf("%10d %26.2f %35.2f\n", hashTableSize, averageCostOfSearchMiss, expectedAverageCostOfSearchMiss);
    }


}
