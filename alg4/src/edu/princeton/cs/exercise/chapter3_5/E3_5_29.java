package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.exercise.chapter1_3.E01_03_35;

/**
 * 3.5.29 Symbol table with random access. Create a data type that supports inserting a
 * key-value pair, searching for a key and returning the associated value, and deleting and
 * returning a random key. Hint : Combine a symbol table and a randomized queue.
 * <p>
 * 随机访问的符号表.创建一个数据类型支持插入一个键值对,搜索 key 并返回关联值,并且删除然后返回一个随机键.
 * 提示: 组合一个符号表和一个随机队列
 *
 * @author LeonChen
 * @since 9/14/21
 */
class E3_5_29 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        SymbolTableWithRandomAccess<Integer, Integer> symbolTableWithRandomAccess =
                new SymbolTableWithRandomAccess();

        symbolTableWithRandomAccess.insert(2, 2);
        symbolTableWithRandomAccess.insert(3, 3);
        symbolTableWithRandomAccess.insert(5, 5);
        symbolTableWithRandomAccess.insert(7, 7);
        symbolTableWithRandomAccess.insert(11, 11);
        symbolTableWithRandomAccess.insert(13, 13);
        symbolTableWithRandomAccess.insert(17, 17);

        StdOut.println("Get 7: " + symbolTableWithRandomAccess.get(7) + " Expected: 7");
        StdOut.println("Get 2: " + symbolTableWithRandomAccess.get(2) + " Expected: 2");
        StdOut.println("Get 17: " + symbolTableWithRandomAccess.get(17) + " Expected: 17");
        StdOut.println("Get 20: " + symbolTableWithRandomAccess.get(20) + " Expected: null");

        StdOut.println("\nRandom key deletes");
        for (int i = 0; i < 7; i++) {
            StdOut.println("Random key deleted: " + symbolTableWithRandomAccess.deleteRandomKey());
        }

        StdOut.println("Expected deleted values: 2 3 5 7 11 13 17 - Not necessarily in this order");

    }

    private static class SymbolTableWithRandomAccess<Key, Value> {

        private SeparateChainingHashST<Key, Value> separateChainingHashTable;
        private E01_03_35.RandomQueue<Key> randomQueue;

        SymbolTableWithRandomAccess() {
            separateChainingHashTable = new SeparateChainingHashST<>();
            randomQueue = new E01_03_35.RandomQueue<>();
        }

        public void insert(Key key, Value value) {
            separateChainingHashTable.put(key, value);
            randomQueue.enqueue(key);
        }

        public Value get(Key key) {
            return separateChainingHashTable.get(key);
        }

        public Key deleteRandomKey() {
            if (separateChainingHashTable.isEmpty()) {
                throw new RuntimeException("Empty symbol table");
            }

            Key randomKey = randomQueue.dequeue();
            separateChainingHashTable.delete(randomKey);

            return randomKey;
        }

    }


}
