package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.*;

/**
 * 3.5.35 Primitive types. Evaluate the utility of using primitive types for Integer and
 * Double values, for LinearProbingHashST and RedBlackBST. How much space and
 * time are saved, for large numbers of searches in large tables?
 * <p>
 * 原始类型.估算 LinearProbingHashST 和 RedBlackBST 使用原始类型替换 Integer 和 Double 带来的收益.
 * 在大表中进行大量的搜索时会节省多少的空间和时间,
 *
 * @author LeonChen
 * @since 9/18/21
 */
class E3_5_35 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        int[] numberOfSizesAndQueries = {100000, 1000000, 10000000, 100000000};
        int maxSize = numberOfSizesAndQueries[numberOfSizesAndQueries.length - 1];

        int[] randomIntKeys = new int[maxSize];
        double[] randomDoubleKeys = new double[maxSize];

        int[] queriesInt = new int[maxSize];
        double[] queriesDouble = new double[maxSize];

        //Generate random keys
        for (int i = 0; i < maxSize; i++) {
            int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
            randomIntKeys[i] = randomKey;
        }

        for (int i = 0; i < maxSize; i++) {
            double randomKey = StdRandom.uniform();
            randomDoubleKeys[i] = randomKey;
        }

        //Generate queries
        for (int i = 0; i < maxSize; i++) {
            //Randomly chooses if this will be a key hit or a key miss query
            int keyHit = StdRandom.uniform(2);
            boolean isKeyHit = keyHit == 1;

            if (!isKeyHit) {
                int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
                queriesInt[i] = randomKey;
            } else {
                int existentKeyIndex = StdRandom.uniform(maxSize);
                queriesInt[i] = randomIntKeys[existentKeyIndex];
            }
        }

        for (int i = 0; i < maxSize; i++) {
            //Randomly chooses if this will be a key hit or a key miss query
            int keyHit = StdRandom.uniform(2);
            boolean isKeyHit = keyHit == 1;

            if (!isKeyHit) {
                double randomKey = StdRandom.uniform();
                queriesDouble[i] = randomKey;
            } else {
                int existentKeyIndex = StdRandom.uniform(maxSize);
                queriesDouble[i] = randomDoubleKeys[existentKeyIndex];
            }
        }

        doExperiment(numberOfSizesAndQueries, randomIntKeys, randomDoubleKeys, queriesInt, queriesDouble);

    }


    private static final String LINEAR_PROBING_SYMBOL_TABLE_TYPE = "Linear probing";
    private static final String RED_BLACK_BST_SYMBOL_TABLE_TYPE = "Red-black BST";
    private static final String INTEGER_KEY_TYPE = "Integer";
    private static final String PRIMITIVE_INT_KEY_TYPE = "int";
    private static final String DOUBLE_KEY_TYPE = "Double";
    private static final String PRIMITIVE_DOUBLE_KEY_TYPE = "double";


    private static void doExperiment(int[] numberOfSizesAndQueries, int[] randomIntKeys, double[] randomDoubleKeys,
                                     int[] queriesInt, double[] queriesDouble) {
        StdOut.printf("%17s %12s %20s %10s\n", "Symbol table | ", "Key type | ", "Size and queries | ", "Time spent");

        //Linear probing hash table with Integer keys test
        for (int i = 0; i < numberOfSizesAndQueries.length; i++) {
            LinearProbingHashST<Integer, Integer> linearProbingHashTableInteger =
                    new LinearProbingHashST<>(997);

            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                linearProbingHashTableInteger.put(randomIntKeys[j], randomIntKeys[j]);
            }

            Stopwatch stopwatch = new Stopwatch();
            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                linearProbingHashTableInteger.get(queriesInt[j]);
            }
            double timeSpent = stopwatch.elapsedTime();

            printResults(LINEAR_PROBING_SYMBOL_TABLE_TYPE, INTEGER_KEY_TYPE, numberOfSizesAndQueries[i], timeSpent);
        }

        //Linear probing hash table with int keys test
        for (int i = 0; i < numberOfSizesAndQueries.length; i++) {
            E3_5_04.HashSTint<Integer> linearProbingHashTableint = new E3_5_04.HashSTint<>(997);

            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                linearProbingHashTableint.put(randomIntKeys[j], randomIntKeys[j]);
            }

            Stopwatch stopwatch = new Stopwatch();
            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                linearProbingHashTableint.get(queriesInt[j]);
            }
            double timeSpent = stopwatch.elapsedTime();

            printResults(LINEAR_PROBING_SYMBOL_TABLE_TYPE, PRIMITIVE_INT_KEY_TYPE, numberOfSizesAndQueries[i], timeSpent);
        }

        //Linear probing hash table with Double keys test
        for (int i = 0; i < numberOfSizesAndQueries.length; i++) {
            LinearProbingHashST<Double, Double> linearProbingHashTableDouble =
                    new LinearProbingHashST<>(997);

            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                linearProbingHashTableDouble.put(randomDoubleKeys[j], randomDoubleKeys[j]);
            }

            Stopwatch stopwatch = new Stopwatch();
            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                linearProbingHashTableDouble.get(queriesDouble[j]);
            }
            double timeSpent = stopwatch.elapsedTime();

            printResults(LINEAR_PROBING_SYMBOL_TABLE_TYPE, DOUBLE_KEY_TYPE, numberOfSizesAndQueries[i], timeSpent);
        }

        //Linear probing hash table with double keys test
        for (int i = 0; i < numberOfSizesAndQueries.length; i++) {
            E3_5_04.HashSTdouble<Double> linearProbingHashTabledouble = new E3_5_04.HashSTdouble<>(997);

            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                linearProbingHashTabledouble.put(randomDoubleKeys[j], randomDoubleKeys[j]);
            }

            Stopwatch stopwatch = new Stopwatch();
            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                linearProbingHashTabledouble.get(queriesDouble[j]);
            }
            double timeSpent = stopwatch.elapsedTime();

            printResults(LINEAR_PROBING_SYMBOL_TABLE_TYPE, PRIMITIVE_DOUBLE_KEY_TYPE, numberOfSizesAndQueries[i], timeSpent);
        }

        //Red-black BST with Integer keys test
        for (int i = 0; i < numberOfSizesAndQueries.length; i++) {
            RedBlackBST<Integer, Integer> redBlackBSTInteger = new RedBlackBST<>();

            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                redBlackBSTInteger.put(randomIntKeys[j], randomIntKeys[j]);
            }

            Stopwatch stopwatch = new Stopwatch();
            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                redBlackBSTInteger.get(queriesInt[j]);
            }
            double timeSpent = stopwatch.elapsedTime();

            printResults(RED_BLACK_BST_SYMBOL_TABLE_TYPE, INTEGER_KEY_TYPE, numberOfSizesAndQueries[i], timeSpent);
        }

        //Red-black BST with int keys test
        for (int i = 0; i < numberOfSizesAndQueries.length; i++) {
            E3_5_05.STint<Integer> redBlackBSTint = new E3_5_05.STint<>();

            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                redBlackBSTint.put(randomIntKeys[j], randomIntKeys[j]);
            }

            Stopwatch stopwatch = new Stopwatch();
            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                redBlackBSTint.get(queriesInt[j]);
            }
            double timeSpent = stopwatch.elapsedTime();

            printResults(RED_BLACK_BST_SYMBOL_TABLE_TYPE, PRIMITIVE_INT_KEY_TYPE, numberOfSizesAndQueries[i], timeSpent);
        }

        //Red-black BST with Double keys test
        for (int i = 0; i < numberOfSizesAndQueries.length; i++) {
            RedBlackBST<Double, Double> redBlackBSTDouble = new RedBlackBST<>();

            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                redBlackBSTDouble.put(randomDoubleKeys[j], randomDoubleKeys[j]);
            }

            Stopwatch stopwatch = new Stopwatch();
            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                redBlackBSTDouble.get(queriesDouble[j]);
            }
            double timeSpent = stopwatch.elapsedTime();

            printResults(RED_BLACK_BST_SYMBOL_TABLE_TYPE, DOUBLE_KEY_TYPE, numberOfSizesAndQueries[i], timeSpent);
        }

        //Red-black BST with double keys test
        for (int i = 0; i < numberOfSizesAndQueries.length; i++) {
            E3_5_05.STdouble<Double> redBlackBSTdouble = new E3_5_05.STdouble<>();

            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                redBlackBSTdouble.put(randomDoubleKeys[j], randomDoubleKeys[j]);
            }

            Stopwatch stopwatch = new Stopwatch();
            for (int j = 0; j < numberOfSizesAndQueries[i]; j++) {
                redBlackBSTdouble.get(queriesDouble[j]);
            }
            double timeSpent = stopwatch.elapsedTime();

            printResults(RED_BLACK_BST_SYMBOL_TABLE_TYPE, PRIMITIVE_DOUBLE_KEY_TYPE, numberOfSizesAndQueries[i], timeSpent);
        }
    }

    private static void printResults(String symbolTable, String keyType, int numberOfQueriesAndSize, double timeSpent) {
        StdOut.printf("%14s %12s %20d %13.2f\n", symbolTable, keyType, numberOfQueriesAndSize, timeSpent);
    }


}
