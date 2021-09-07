package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.exercise.util.FileUtil;

/**
 * 3.4.40 Plots. Instrument LinearProbingHashST and SeparateChainingHashST to
 * produce plots like the ones shown in the text.
 * <p>
 * 图表.使用 LinearProbingHashST 和 SeparateChainingHashST 来产生一个类似正文中的图表
 *
 * @author LeonChen
 * @since 8/23/21
 */
class E3_4_40 {

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
        String[] wordsInTale = FileUtil.getAllStrFromFile(TALE_FILE_PATH);
        int minLength = 8; //Same as the book analysis
        frequencyCounter(wordsInTale, minLength);
    }

    private static class LinearProbingHashTableCost<Key, Value> extends LinearProbingHashST<Key, Value> {

        LinearProbingHashTableCost(int size) {
            super(size);
        }

        private int costOfPutCompares;

        public int putAndComputeCost(Key key, Value value, boolean resetCostOfPutCompares) {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            if (value == null) {
                delete(key);
                return 0;
            }

            if (resetCostOfPutCompares) {
                costOfPutCompares = 0;
            }

            if (n >= m / (double) 2) {
                resize(m * 2);
            }

            costOfPutCompares++;

            int tableIndex;
            for (tableIndex = hash(key); keys[tableIndex] != null; tableIndex =
                    (tableIndex + 1) % m) {
                costOfPutCompares++;

                if (keys[tableIndex].equals(key)) {
                    vals[tableIndex] = value;
                    return costOfPutCompares;
                }
            }

            keys[tableIndex] = key;
            vals[tableIndex] = value;
            n++;

            return costOfPutCompares;
        }

        private void resize(int newSize) {
            LinearProbingHashTableCost<Key, Value> tempHashTable = new LinearProbingHashTableCost<>(newSize);

            for (int i = 0; i < m; i++) {
                if (keys[i] != null) {
                    tempHashTable.putAndComputeCost(keys[i], vals[i], false);
                }
            }

            keys = tempHashTable.keys;
            vals = tempHashTable.vals;
            m = tempHashTable.m;

            costOfPutCompares += tempHashTable.costOfPutCompares;
        }

    }

    private static final String TALE_FILE_PATH = "src/edu/princeton/cs/algs4-data/tale.txt";

    private static String frequencyCounter(String[] words, int minLength) {

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 18000);
        StdDraw.setYscale(0, 25);
        StdDraw.setPenRadius(0.005);
        LinearProbingHashTableCost<String, Integer> linearProbingHashTableCost =
                new LinearProbingHashTableCost<>(10);

        int x = 0;
        for (String word : words) {

            if (word.length() < minLength) {
                continue;
            }

            int cost;
            if (!linearProbingHashTableCost.contains(word)) {
                cost = linearProbingHashTableCost.putAndComputeCost(word, 1, true);
            } else {
                cost = linearProbingHashTableCost.
                        putAndComputeCost(word, linearProbingHashTableCost.get(word) + 1, true);
            }
            StdDraw.point(x++, cost);
        }

        String max = "";
        int cost = linearProbingHashTableCost.putAndComputeCost(max, 0, true);
        StdDraw.point(x++, cost);

        for (String word : linearProbingHashTableCost.keys()) {
            if (linearProbingHashTableCost.get(word) > linearProbingHashTableCost.get(max)) {
                max = word;
            }
        }


        return max + " " + linearProbingHashTableCost.get(max);
    }


}
