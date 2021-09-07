package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.exercise.util.Constants;

/**
 * 3.4.35 Chi-square test. Use your solution from Exercise 3.4.30 to check the assumption
 * that the hash functions for commonly-used key types produce random values.
 * <p>
 * 卡方测试.使用练习 3.4.30 的解决方案来校验通常使用的键类型的 hash 函数是否会产生随机值
 *
 * @author LeonChen
 * @since 8/18/21
 */
class E3_4_35 {


    private static class SeparateChainingHashTableChiSquareTest<Key, Value> extends E3_4_30.SeparateChainingHashTableChiSquare<Key, Value> {

        SeparateChainingHashTableChiSquareTest(int initialSize) {
            super(initialSize);
        }

        public int hash(Key key) {
            int hash = key.hashCode() & 0x7fffffff;
            return hash % m;
        }
    }

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {

        int numberOfKeys = 1000000;

        StdOut.println("Integer key type hash function");
        SeparateChainingHashTableChiSquareTest<Integer, Integer> separateChainingHashTableIntegerChiSquareTest =
                new SeparateChainingHashTableChiSquareTest<>(20);

        for (int i = 0; i < numberOfKeys; i++) {
            int randomIntegerKey = StdRandom.uniform(Integer.MAX_VALUE);
            separateChainingHashTableIntegerChiSquareTest.put(randomIntegerKey, randomIntegerKey);
        }

        double chiSquareStatistic1 = separateChainingHashTableIntegerChiSquareTest.computeChiSquareStatistic();
        double lowerBound1 =
                separateChainingHashTableIntegerChiSquareTest.m - Math.sqrt(separateChainingHashTableIntegerChiSquareTest.m);
        double upperBound1 =
                separateChainingHashTableIntegerChiSquareTest.m + Math.sqrt(separateChainingHashTableIntegerChiSquareTest.m);

        double constant1 =
                separateChainingHashTableIntegerChiSquareTest.n / (double) separateChainingHashTableIntegerChiSquareTest.m;
        double probability1 = 1 - (1 / constant1);

        StdOut.print("Produces random values: ");
        if (lowerBound1 <= chiSquareStatistic1 && chiSquareStatistic1 <= upperBound1) {
            StdOut.println("True");
        } else {
            StdOut.println("False");
        }
        StdOut.println("Probability = " + String.format("%.2f%%", probability1 * 100));

        StdOut.println("\nDouble key type hash function");
        SeparateChainingHashTableChiSquareTest<Double, Double> separateChainingHashTableDoubleChiSquareTest =
                new SeparateChainingHashTableChiSquareTest<>(20);

        for (int i = 0; i < numberOfKeys; i++) {
            double randomDoubleKey = StdRandom.uniform();
            separateChainingHashTableDoubleChiSquareTest.put(randomDoubleKey, randomDoubleKey);
        }

        double chiSquareStatistic2 = separateChainingHashTableDoubleChiSquareTest.computeChiSquareStatistic();
        double lowerBound2 =
                separateChainingHashTableDoubleChiSquareTest.m - Math.sqrt(separateChainingHashTableDoubleChiSquareTest.m);
        double upperBound2 =
                separateChainingHashTableDoubleChiSquareTest.m + Math.sqrt(separateChainingHashTableDoubleChiSquareTest.m);

        double constant2 =
                separateChainingHashTableDoubleChiSquareTest.n / (double) separateChainingHashTableDoubleChiSquareTest.m;
        double probability2 = 1 - (1 / constant2);

        StdOut.print("Produces random values: ");
        if (lowerBound2 <= chiSquareStatistic2 && chiSquareStatistic2 <= upperBound2) {
            StdOut.println("True");
        } else {
            StdOut.println("False");
        }
        StdOut.println("Probability = " + String.format("%.2f%%", probability2 * 100));

        StdOut.println("\nString key type hash function");
        SeparateChainingHashTableChiSquareTest<String, String> separateChainingHashTableStringChiSquareTest =
                new SeparateChainingHashTableChiSquareTest<>(20);

        for (int i = 0; i < numberOfKeys; i++) {
            StringBuilder string = new StringBuilder();

            for (int c = 0; c < 10; c++) {
                //Generate random char between 'A' and 'z'
                char currentChar = (char) StdRandom.uniform(Constants.UPPER_LETTER_INITIAL_INDEX,
                        Constants.LOWER_LETTER_FINAL_INDEX + 1);
                string.append(currentChar);
            }

            String stringKey = string.toString();
            separateChainingHashTableStringChiSquareTest.put(stringKey, stringKey);
        }

        double chiSquareStatistic3 = separateChainingHashTableStringChiSquareTest.computeChiSquareStatistic();
        double lowerBound3 =
                separateChainingHashTableStringChiSquareTest.m - Math.sqrt(separateChainingHashTableStringChiSquareTest.m);
        double upperBound3 =
                separateChainingHashTableStringChiSquareTest.m + Math.sqrt(separateChainingHashTableStringChiSquareTest.m);

        double constant3 =
                separateChainingHashTableStringChiSquareTest.n / (double) separateChainingHashTableStringChiSquareTest.m;
        double probability3 = 1 - (1 / constant3);

        StdOut.print("Produces random values: ");
        if (lowerBound3 <= chiSquareStatistic3 && chiSquareStatistic3 <= upperBound3) {
            StdOut.println("True");
        } else {
            StdOut.println("False");
        }
        StdOut.println("Probability = " + String.format("%.2f%%", probability3 * 100));
    }

}
