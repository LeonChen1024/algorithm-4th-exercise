package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Arrays;

/**
 * 3.4.38 Separate-chaining distribution. Write a program that inserts 10^5 random
 * non-negative integers less than 10^6 into a table of size 10^5 using linear probing,
 * and that plots the total number of probes used for each 10^3 consecutive insertions.
 * Discuss the extent to which your results validate Proposition K.
 * <p>
 * 独立链表分布.编写一个程序插入 10^5 个随机非负不大于 10^6 的整数到一个使用线性探测大小为10^5的表中,并且
 * 绘制出连续10^3插入时总探测次数.讨论并扩展命题 K.
 *
 * @author LeonChen
 * @since 8/21/21
 */
class E3_4_38 {

    /**
     * @formatter:off
     * Total cost for 10^3 inserts |  Average cost for each insert |  Expected number of keys in list
     *                           6                           0.006                              0.010
     *                           9                           0.009                              0.020
     *                          28                           0.028                              0.030
     *                          27                           0.027                              0.040
     *                          44                           0.044                              0.050
     *                          42                           0.042                              0.060
     *                          63                           0.063                              0.070
     *                          78                           0.078                              0.080
     *                          72                           0.072                              0.090
     *                          88                           0.088                              0.100
     *                          97                           0.097                              0.109
     *                         133                           0.133                              0.119
     *                         128                           0.128                              0.129
     *                         135                           0.135                              0.139
     *                         139                           0.139                              0.149
     *                         153                           0.153                              0.159
     *                         173                           0.173                              0.169
     *                         155                           0.155                              0.178
     *                         203                           0.203                              0.188
     *                         193                           0.193                              0.198
     *                         206                           0.206                              0.208
     *                         204                           0.204                              0.218
     *                         240                           0.240                              0.227
     *                         224                           0.224                              0.237
     *                         247                           0.247                              0.247
     *                         252                           0.252                              0.257
     *                         264                           0.264                              0.267
     *                         269                           0.269                              0.277
     *                         233                           0.233                              0.286
     *                         306                           0.306                              0.296
     *                         291                           0.291                              0.306
     *                         307                           0.307                              0.315
     *                         296                           0.296                              0.325
     *                         317                           0.317                              0.335
     *                         353                           0.353                              0.344
     *                         351                           0.351                              0.354
     *                         362                           0.362                              0.364
     *                         377                           0.377                              0.373
     *                         416                           0.416                              0.383
     *                         393                           0.393                              0.392
     *                         378                           0.378                              0.402
     *                         403                           0.403                              0.412
     *                         464                           0.464                              0.421
     *                         463                           0.463                              0.431
     *                         425                           0.425                              0.440
     *                         425                           0.425                              0.450
     *                         477                           0.477                              0.459
     *                         438                           0.438                              0.469
     *                         449                           0.449                              0.478
     *                         473                           0.473                              0.488
     *                         470                           0.470                              0.497
     *                         522                           0.522                              0.507
     *                         503                           0.503                              0.516
     *                         481                           0.481                              0.526
     *                         560                           0.560                              0.535
     *                         511                           0.511                              0.545
     *                         570                           0.570                              0.554
     *                         506                           0.506                              0.564
     *                         559                           0.559                              0.573
     *                         556                           0.556                              0.582
     *                         565                           0.565                              0.592
     *                         564                           0.564                              0.601
     *                         591                           0.591                              0.611
     *                         593                           0.593                              0.620
     *                         634                           0.634                              0.629
     *                         643                           0.643                              0.639
     *                         640                           0.640                              0.648
     *                         666                           0.666                              0.657
     *                         653                           0.653                              0.667
     *                         695                           0.695                              0.676
     *                         655                           0.655                              0.685
     *                         669                           0.669                              0.695
     *                         709                           0.709                              0.704
     *                         724                           0.724                              0.713
     *                         651                           0.651                              0.722
     *                         693                           0.693                              0.732
     *                         683                           0.683                              0.741
     *                         706                           0.706                              0.750
     *                         705                           0.705                              0.760
     *                         736                           0.736                              0.769
     *                         722                           0.722                              0.778
     *                         749                           0.749                              0.787
     *                         793                           0.793                              0.796
     *                         750                           0.750                              0.806
     *                         844                           0.844                              0.815
     *                         758                           0.758                              0.824
     *                         868                           0.868                              0.833
     *                         866                           0.866                              0.842
     *                         866                           0.866                              0.851
     *                         794                           0.794                              0.860
     *                         823                           0.823                              0.870
     *                         822                           0.822                              0.879
     *                         831                           0.831                              0.888
     *                         869                           0.869                              0.897
     *                         836                           0.836                              0.906
     *                         918                           0.918                              0.915
     *                         874                           0.874                              0.924
     *                         902                           0.902                              0.934
     *                         883                           0.883                              0.942
     *                         886                           0.886                              0.951
     * 所有平均成本都在 N/M 常量内,证明了命题 K
     * @formatter:on
     */
    public static void main(String[] args) {
        SeparateChainingHashTableFixedSizeCost<Integer, Integer> separateChainingHashTableFixedSizeCost =
                new SeparateChainingHashTableFixedSizeCost<>(100000);

        int maxInt = 1000000;

        StdOut.printf("%20s %20s %25s\n", "Total cost for 10^3 inserts | ", "Average cost for each insert | ", "Expected number of keys in list");

        double maxNumberOfOperations = 100000;
        double maxCost = 1500;

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.blue);
        StdDraw.setXscale(0, maxNumberOfOperations);
        StdDraw.setYscale(0, maxCost);

        long totalCostOfPutCompares = 0;

        for (int operation = 1; operation <= 100000; operation++) {
            int randomKey = StdRandom.uniform(maxInt);
            separateChainingHashTableFixedSizeCost.put(randomKey, randomKey);

            totalCostOfPutCompares += separateChainingHashTableFixedSizeCost.costOfPutCompares;

            if (operation % 1000 == 0) {
                double averageCostOfInsert = (totalCostOfPutCompares / (double) 1000);
                double expectedNumberOfKeysInList =
                        separateChainingHashTableFixedSizeCost.keysSize / (double) separateChainingHashTableFixedSizeCost.size;

                printResults(totalCostOfPutCompares, averageCostOfInsert, expectedNumberOfKeysInList);

                StdDraw.point(operation, totalCostOfPutCompares);
                totalCostOfPutCompares = 0;
            }
        }
    }

    private static class SeparateChainingHashTableFixedSizeCost<Key, Value> {

        private class SequentialSearchSymbolTable<Key, Value> {

            private class Node {
                Key key;
                Value value;
                Node next;

                public Node(Key key, Value value, Node next) {
                    this.key = key;
                    this.value = value;
                    this.next = next;
                }
            }

            private Node first;
            private int size;

            public int size() {
                return size;
            }

            public boolean isEmpty() {
                return size == 0;
            }

            public boolean contains(Key key) {
                return get(key) != null;
            }

            public Value get(Key key) {
                for (Node node = first; node != null; node = node.next) {
                    if (key.equals(node.key)) {
                        return node.value;
                    }
                }

                return null;
            }

            public void put(Key key, Value value) {
                costOfPutCompares = 0;

                for (Node node = first; node != null; node = node.next) {
                    costOfPutCompares++;

                    if (key.equals(node.key)) {
                        node.value = value;
                        return;
                    }
                }

                first = new Node(key, value, first);
                size++;
            }

            public void delete(Key key) {
                if (first.key.equals(key)) {
                    first = first.next;
                    size--;
                    return;
                }

                for (Node node = first; node != null; node = node.next) {
                    if (node.next != null && node.next.key.equals(key)) {
                        node.next = node.next.next;
                        size--;
                        return;
                    }
                }
            }

            public Iterable<Key> keys() {
                Queue<Key> keys = new Queue<>();

                for (Node node = first; node != null; node = node.next) {
                    keys.enqueue(node.key);
                }

                return keys;
            }

        }

        private int size;
        private int keysSize;
        private SequentialSearchSymbolTable<Key, Value>[] symbolTable;

        private int costOfPutCompares;

        public SeparateChainingHashTableFixedSizeCost(int size) {
            this.size = size;
            symbolTable = new SequentialSearchSymbolTable[size];

            for (int i = 0; i < size; i++) {
                symbolTable[i] = new SequentialSearchSymbolTable<>();
            }
        }

        public int size() {
            return keysSize;
        }

        public boolean isEmpty() {
            return keysSize == 0;
        }

        private int hash(Key key) {
            int hash = key.hashCode() & 0x7fffffff;
            hash = (31 * hash) & 0x7fffffff;
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
            int currentSize = symbolTable[hashIndex].size;
            symbolTable[hashIndex].put(key, value);

            if (currentSize < symbolTable[hashIndex].size) {
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

            for (SequentialSearchSymbolTable<Key, Value> sequentialSearchST : symbolTable) {
                for (Key key : sequentialSearchST.keys()) {
                    keys.enqueue(key);
                }
            }

            if (!keys.isEmpty() && keys.peek() instanceof Comparable) {
                Key[] keysToBeSorted = (Key[]) new Comparable[keys.size()];
                for (int i = 0; i < keysToBeSorted.length; i++) {
                    keysToBeSorted[i] = keys.dequeue();
                }

                Arrays.sort(keysToBeSorted);

                for (Key key : keysToBeSorted) {
                    keys.enqueue(key);
                }
            }

            return keys;
        }

    }

    private static void printResults(long totalCostOfPutCompares, double averageCostOfInsert, double expectedNumberOfKeysInList) {
        StdOut.printf("%27s %31.3f %34.3f\n", totalCostOfPutCompares, averageCostOfInsert, expectedNumberOfKeysInList);
    }

}
