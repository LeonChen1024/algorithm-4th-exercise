package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 3.4.30 Chi-square statistic. Add a method to SeparateChainingST to compute the X^2
 * statistic for the hash table. With N keys and table size M, this number is defined by
 * the equation
 * X^2 = (M/N)((f_0-N/M)^2+(f_1-N/M)^2+ ... (f_{M-1}-N/M)^2)
 * where f_i is the number of keys with hash value i. This statistic is one way of
 * checking our assumption that the hash function produces random values. If so, this
 * statistic, for N > cM, should be between M - √M and M+√M with probability 1-1/c.
 * <p>
 * 卡方分析.添加一个方法到 SeparateChainingST 来计算 hash 表的 X^2 分析. 使用 N 个键和表大小 M,这个数字
 * 是通过等式
 * X^2 = (M/N)((f_0-N/M)^2+(f_1-N/M)^2+ ... (f_{M-1}-N/M)^2)
 * 定义的,其中 f_i 是键的数量并且 hash 值为 i.这个分析是校验我们 hash 函数是否产生随机值得一种方式.
 * 如果是的话,这个分析对于N > cM,结果应该在 M - √M 和 M+√M 之间并且概率为 1-1/c.
 *
 * @author LeonChen
 * @since 8/14/21
 */
class E3_4_30 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        SeparateChainingHashTableChiSquare<Integer, Integer> separateChainingHashTableChiSquare =
                new SeparateChainingHashTableChiSquare<>(100);

        for (int key = 0; key < 10000; key++) {
            int randomIntegerKey = StdRandom.uniform(Integer.MAX_VALUE);
            separateChainingHashTableChiSquare.put(randomIntegerKey, randomIntegerKey);
        }

        double lowerBound =
                separateChainingHashTableChiSquare.m - Math.sqrt(separateChainingHashTableChiSquare.m);
        double upperBound =
                separateChainingHashTableChiSquare.m + Math.sqrt(separateChainingHashTableChiSquare.m);

        double constant =
                separateChainingHashTableChiSquare.n / (double) separateChainingHashTableChiSquare.m;
        double probability = 1 - (1 / constant);

        double chiSquareStatisticValue = separateChainingHashTableChiSquare.computeChiSquareStatistic();

        StdOut.println("M - sqrt(M) = " + String.format("%.2f", lowerBound));
        StdOut.println("M + sqrt(M) = " + String.format("%.2f", upperBound));
        StdOut.println("Chi square statistic = " + String.format("%.2f", chiSquareStatisticValue));
        StdOut.println("Probability = " + String.format("%.2f%%", probability * 100));

        StdOut.print("Produces random values: ");
        if (lowerBound <= chiSquareStatisticValue && chiSquareStatisticValue <= upperBound) {
            StdOut.print("True");
        } else {
            StdOut.print("False");
        }
    }

    static class SeparateChainingHashTableChiSquare<Key, Value> extends SeparateChainingHashST<Key,
            Value> {

        SeparateChainingHashTableChiSquare(int initialSize) {
            super(initialSize);
        }

        double computeChiSquareStatistic() {
            double sizeOverKeyCount = m / (double) n;
            double keyCountOverSize = n / (double) m;

            int[] keyCountByHashValue = new int[st.length];
            for (int bucket = 0; bucket < st.length; bucket++) {
                keyCountByHashValue[bucket] = st[bucket].size();
            }

            double fSum = 0;
            for (int i = 0; i < keyCountByHashValue.length; i++) {
                fSum += Math.pow(keyCountByHashValue[i] - keyCountOverSize, 2);
            }

            return sizeOverKeyCount * fSum;
        }
    }

}
