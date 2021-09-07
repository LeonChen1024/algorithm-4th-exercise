package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.*;

import java.util.Arrays;

/**
 * 3.4.37 Hybrid. Run experimental studies to determine the effect of using RedBlackBST
 * instead of SequentialSearchST to handle collisions in SeparateChainingHashST.
 * This solution carries the advantage of guaranteeing logarithmic performance even for
 * a bad hash function and the disadvantage of necessitating maintenance of two different
 * symbol-table implementations. What are the practical effects?
 * <p>
 * 混合.运行实验来对比使用 RedBlackBST 替代SequentialSearchST 来处理 SeparateChainingHashST中的
 * 碰撞.这个方案带来的好处是保证了就算在一个坏 hash 函数的情况下的对数级性能,缺点是需要维护两个不同的符号表
 * 实现.实际的效果是怎样呢?
 *
 * @author LeonChen
 * @since 8/20/21
 */
class E3_4_37 {
    private static final String HASH_BAD = "HASH_BAD";
    private static final String HASH_GOOD = "HASH_GOOD";

    private static final int HASH_BAD_RATIO = 10;

    /**
     * @formatter:off
     * 正常 hash 情况下
     * Operation |  Sequential Search time |  Red-black BST time
     *       Put                      0.74                  0.81
     *       Get                      0.26                  0.46
     *    Delete                      0.29                  0.85
     *
     * hash 较差的情况下
     * Operation |  Sequential Search time |  Red-black BST time
     *       Put                      1.83                  0.98
     *       Get                      1.23                  0.51
     *    Delete                      1.35                  1.05
     *
     * 在比较差的 hash 情况下,使用红黑树可以提高效率,但是在普通 hash 情况下,却会降低一些效率
     *
     * @formatter:on
     */
    public static void main(String[] args) {
        SeparateChainingHashSTHashOptions<Integer, Integer> separateChainingHashTable =
                new SeparateChainingHashSTHashOptions<>(HASH_BAD);

        SeparateChainingHashTableWithRedBlackBST<Integer, Integer> separateChainingHashTableWithRedBlackBST =
                new SeparateChainingHashTableWithRedBlackBST<>(HASH_BAD);

        StdOut.printf("%12s %20s %16s\n", "Operation | ", "Sequential Search time | ", "Red-black BST time");

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
        double timeSpentOnPutSequentialSearch = stopwatch.elapsedTime();

        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysPut.length; i++) {
            separateChainingHashTableWithRedBlackBST.put(randomKeysPut[i], randomKeysPut[i]);
        }
        double timeSpentOnPutRedBlackBST = stopwatch.elapsedTime();

        printResults("Put", timeSpentOnPutSequentialSearch, timeSpentOnPutRedBlackBST);

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
        double timeSpentOnGetSequentialSearch = stopwatch.elapsedTime();

        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysGet.length; i++) {
            separateChainingHashTableWithRedBlackBST.get(randomKeysGet[i]);
        }
        double timeSpentOnGetRedBlackBST = stopwatch.elapsedTime();

        printResults("Get", timeSpentOnGetSequentialSearch, timeSpentOnGetRedBlackBST);

        //Delete tests
        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysPut.length / 2; i++) {
            separateChainingHashTable.delete(randomKeysPut[i]);
        }
        double timeSpentOnDeleteSequentialSearch = stopwatch.elapsedTime();

        stopwatch = new Stopwatch();
        for (int i = 0; i < randomKeysPut.length / 2; i++) {
            separateChainingHashTableWithRedBlackBST.delete(randomKeysPut[i]);
        }
        double timeSpentOnDeleteRedBlackBST = stopwatch.elapsedTime();

        printResults("Delete", timeSpentOnDeleteSequentialSearch, timeSpentOnDeleteRedBlackBST);
    }

    private static class SeparateChainingHashSTHashOptions<Key, Value> extends SeparateChainingHashST<Key, Value> {

        private String hashMode = "";

        public SeparateChainingHashSTHashOptions(String hashBad) {
            super();
            this.hashMode = hashBad;
        }

        @Override
        protected int hash(Key key) {
            if (hashMode.equals(HASH_GOOD)) {
                return super.hash(key);
            } else {
                return key.hashCode() % (m / HASH_BAD_RATIO + 1);
            }
        }
    }


    private static class SeparateChainingHashTableWithRedBlackBST<Key extends Comparable<Key>, Value> {

        private int size;
        private int keysSize;
        RedBlackBST<Key, Value>[] symbolTable;
        private String hashMode = "";

        private static final int DEFAULT_HASH_TABLE_SIZE = 997;

        public SeparateChainingHashTableWithRedBlackBST() {
            this(DEFAULT_HASH_TABLE_SIZE);
        }

        public SeparateChainingHashTableWithRedBlackBST(String hashMode) {
            this(DEFAULT_HASH_TABLE_SIZE);
            this.hashMode = hashMode;
        }

        public SeparateChainingHashTableWithRedBlackBST(int size) {
            this.size = size;
            symbolTable = new RedBlackBST[size];

            for (int i = 0; i < size; i++) {
                symbolTable[i] = new RedBlackBST<>();
            }
        }

        public int size() {
            return keysSize;
        }

        public boolean isEmpty() {
            return keysSize == 0;
        }

        private int hash(Key key) {
            int hash;
            if (hashMode.equals(HASH_GOOD)) {
                hash = key.hashCode() & 0x7fffffff;
            } else {
                hash = key.hashCode() % (size / HASH_BAD_RATIO + 1);
            }
            return hash % size;
        }

        public boolean contains(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to contains() cannot be null");
            }

            return get(key) != null;
        }

        public Value get(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to get() cannot be null");
            }

            return symbolTable[hash(key)].get(key);
        }

        public void put(Key key, Value value) {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            if (value == null) {
                delete(key);
                return;
            }

            int hashIndex = hash(key);
            int currentSize = symbolTable[hashIndex].size();
            symbolTable[hashIndex].put(key, value);

            if (currentSize < symbolTable[hashIndex].size()) {
                keysSize++;
            }
        }

        public void delete(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to delete() cannot be null");
            }

            if (isEmpty() || !contains(key)) {
                return;
            }

            symbolTable[hash(key)].delete(key);
            keysSize--;
        }

        public Iterable<Key> keys() {
            Queue<Key> keys = new Queue<>();

            for (RedBlackBST redBlackBST : symbolTable) {
                for (Object key : redBlackBST.keys()) {
                    keys.enqueue((Key) key);
                }
            }

            Key[] keysToBeSorted = (Key[]) new Comparable[keys.size()];
            for (int i = 0; i < keysToBeSorted.length; i++) {
                keysToBeSorted[i] = keys.dequeue();
            }

            Arrays.sort(keysToBeSorted);

            for (Key key : keysToBeSorted) {
                keys.enqueue(key);
            }

            return keys;
        }

    }

    private static void printResults(String operation, double sequentialSearchTime, double redBlackBSTTime) {
        StdOut.printf("%9s %25.2f %21.2f\n", operation, sequentialSearchTime, redBlackBSTTime);
    }


}
