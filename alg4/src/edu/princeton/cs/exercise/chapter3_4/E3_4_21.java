package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 3.4.21 Add a method to LinearProbingHashST that computes the average cost of a
 * search miss in the table, assuming a random hash function. Note : You do not have to
 * compute any hash functions to solve this problem.
 * <p>
 * 添加一个方法到 LinearProbingHashST 计算表中搜索丢失的平均成本,假设使用一个随机 hash 函数.注意:
 * 你不需要计算 hash 函数来解决这个问题
 *
 * @author LeonChen
 * @since 8/5/21
 */
class E3_4_21 {

    /**
     * @formatter:off
     * 通过公式计算, 也可以根据实际计算成本来
     * @formatter:on
     */
    public static void main(String[] args) {
        LinearProbingHashTableAvgSearchMissCost<Integer, Integer> linearProbingHashTableAvgSearchMissCost =
                new LinearProbingHashTableAvgSearchMissCost<>(1000000);

        for (int i = 0; i < 500000; i++) {
            int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
            linearProbingHashTableAvgSearchMissCost.put(randomKey, randomKey);
        }

        StdOut.printf("Average cost of search miss: %.2f", linearProbingHashTableAvgSearchMissCost.getAverageCostOfSearchMiss());

    }


    private static class LinearProbingHashTableAvgSearchMissCost<Key, Value> extends
            LinearProbingHashST<Key, Value> {

        LinearProbingHashTableAvgSearchMissCost(int size) {
            super(size);
        }

        //Average cost of search miss = ~1/2 * (1 + (1 / (1 - a)^2))
        public double getAverageCostOfSearchMiss() {
            double loadFactor = n * 1.0 / m;
            return 0.5 * (1 + (1 / Math.pow(1 - loadFactor, 2)));
        }
    }


}
