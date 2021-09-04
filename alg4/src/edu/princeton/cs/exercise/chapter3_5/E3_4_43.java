package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 3.4.43 Parking problem . (D. Knuth) Run experimental studies to validate the
 * hypothesis that the number of compares needed to insert M random keys into a
 * linear-probing table of size M is ~cM^{3/2}, where c = √{π/2}.
 * <p>
 * 停车问题.(D. Knuth)运行实验证明假说插入 M 个随机键到一个大小为 M 的线性探测表需要对比次数是
 * ~cM^{3/2}, 其中 c = √{π/2}
 *
 * @author LeonChen
 * @since 8/24/21
 */
class E3_4_43 {

    /**
     * @formatter:off
     * Hash table size |  Number of compares |  Expected number of compares
     *            1000                 13946                          39633
     *           10000                702363                        1253314
     *          100000              26251650                       39633272
     *         1000000             310748037                     1253314137
     * @formatter:on
     */
    public static void main(String[] args) {
        StdOut.printf("%12s %20s %20s\n", "Hash table size | ", "Number of compares | ", "Expected number of compares");

        int[] hashTableSizes = {1000, 10000, 100000, 1000000};
        for (int hashTableSize : hashTableSizes) {
            putKeysAndPrintResults(hashTableSize);
        }

    }

    private static class LinearProbingHashTableFixedSize<Key, Value> extends LinearProbingHashST<Key, Value> {

        LinearProbingHashTableFixedSize(int size) {
            super(size);
        }

        private int totalNumberOfCompares;

        public void put(Key key, Value value) {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            if (value == null) {
                delete(key);
            }

            totalNumberOfCompares++;

            int tableIndex;
            for (tableIndex = hash(key); keys[tableIndex] != null; tableIndex =
                    (tableIndex + 1) % m) {
                totalNumberOfCompares++;

                if (keys[tableIndex].equals(key)) {
                    vals[tableIndex] = value;
                    return;
                }
            }

            keys[tableIndex] = key;
            vals[tableIndex] = value;
            n++;
        }
    }

    private static void putKeysAndPrintResults(int hashTableSize) {

        LinearProbingHashTableFixedSize<Integer, Integer> linearProbingHashTable = new LinearProbingHashTableFixedSize<>(hashTableSize);

        for (int i = 0; i < hashTableSize; i++) {
            int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
            linearProbingHashTable.put(randomKey, randomKey);
        }

        long totalNumberOfCompares = linearProbingHashTable.totalNumberOfCompares;

        double constant = Math.sqrt(Math.PI / 2);
        long expectedTotalNumberOfCompares = (long) (constant * Math.pow(hashTableSize, 1.5));

        printResults(hashTableSize, totalNumberOfCompares, expectedTotalNumberOfCompares);
    }

    private static void printResults(int hashTableSize, long totalNumberOfCompares, long expectedTotalNumberOfCompares) {
        StdOut.printf("%15d %21d %30d\n", hashTableSize, totalNumberOfCompares, expectedTotalNumberOfCompares);
    }


}
