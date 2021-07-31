package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;

/**
 * 3.4.3 Modify your implementation of the previous exercise to include an integer field
 * for each key-value pair that is set to the number of entries in the table at the
 * time that pair is inserted. Then implement a method that deletes all keys (and
 * associated values) for which the field is greater than a given integer k. Note :
 * This extra functionality is useful in implementing the symbol table for a compiler.
 * <p>
 * 修改你上个练习的实现使得每个键值对包含一个整数字段在他们插入的时候.然后实现一个方法删除所有整数字段大于给定
 * k 的键(以及关联值).注意:这个额外的功能对编译器来说是很有用的
 *
 * @author LeonChen
 * @since 7/23/21
 */
class E3_4_03 {

    /**
     * @formatter:off
     *
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }

    public static class SeparateChainingHashST<Key, Value> {
        private static final int INIT_CAPACITY = 4;

        private class Node {
            Key key;
            Value value;
            int k;

            public Node(Key key, Value value, int k) {
                this.key = key;
                this.value = value;
                this.k = k;
            }
        }

        private int n;                                // number of key-value pairs
        private int m;                                // hash table size
        private LinkedList<Node>[] st;  // array of linked-list symbol tables


        /**
         * Initializes an empty symbol table.
         */
        public SeparateChainingHashST() {
            this(INIT_CAPACITY);
        }

        /**
         * Initializes an empty symbol table with {@code m} chains.
         *
         * @param m the initial number of chains
         */
        public SeparateChainingHashST(int m) {
            this.m = m;
            st = (LinkedList<Node>[]) new LinkedList[m];
            for (int i = 0; i < m; i++)
                st[i] = new LinkedList<Node>();
        }

        // resize the hash table to have the given number of chains,
        // rehashing all of the keys
        private void resize(int chains) {
            SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
            for (int i = 0; i < m; i++) {
                for (Node node : st[i]) {
                    temp.put(node.key, node.value, node.k);
                }
            }
            this.m = temp.m;
            this.n = temp.n;
            this.st = temp.st;
        }

        public void deleteNodesWithIntK(int k) {
            if (k < 0) {
                throw new IllegalArgumentException("K cannot be negative");
            }

            if (isEmpty()) {
                return;
            }

            Queue<Key> keysToDelete = new Queue<>();

            for (int i = 0; i < st.length; i++) {
                if (st[i] != null) {
                    for (Node node : st[i]) {
                        if (node.k > k) {
                            keysToDelete.enqueue(node.key);

                        }
                    }
                }
            }

            for (Key key : keysToDelete) {
                delete(key);
            }
        }

        // hash value between 0 and m-1
        private int hash(Key key) {
            return (key.hashCode() & 0x7fffffff) % m;
        }

        /**
         * Returns the number of key-value pairs in this symbol table.
         *
         * @return the number of key-value pairs in this symbol table
         */
        public int size() {
            return n;
        }

        /**
         * Returns true if this symbol table is empty.
         *
         * @return {@code true} if this symbol table is empty;
         * {@code false} otherwise
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        /**
         * Returns true if this symbol table contains the specified key.
         *
         * @param key the key
         * @return {@code true} if this symbol table contains {@code key};
         * {@code false} otherwise
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public boolean contains(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to contains() is null");
            return get(key) != null;
        }

        /**
         * Returns the value associated with the specified key in this symbol table.
         *
         * @param key the key
         * @return the value associated with {@code key} in the symbol table;
         * {@code null} if no such value
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public Value get(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to get() is null");
            int i = hash(key);
            Value v = null;
            for (Node node : st[i]) {
                if (node.key.equals(key)) {
                    v = node.value;
                }
            }
            return v;
        }

        /**
         * Inserts the specified key-value pair into the symbol table, overwriting the old
         * value with the new value if the symbol table already contains the specified key.
         * Deletes the specified key (and its associated value) from this symbol table
         * if the specified value is {@code null}.
         *
         * @param key the key
         * @param val the value
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void put(Key key, Value val, int k) {
            if (key == null)
                throw new IllegalArgumentException("first argument to put() is null");
            if (val == null) {
                delete(key);
                return;
            }

            // double table size if average length of list >= 10
            if (n >= 10 * m) resize(2 * m);

            int i = hash(key);
            if (!st[i].contains(key)) n++;
            st[i].add(new Node(key, val, k));
        }

        /**
         * Removes the specified key and its associated value from this symbol table
         * (if the key is in this symbol table).
         *
         * @param key the key
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void delete(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to delete() is null");

            int i = hash(key);
            if (st[i].contains(key)) n--;
            for (Node node : st[i]) {
                if (node.key.equals(key)) {
                    st[i].remove(node);
                    break;
                }
            }

            // halve table size if average length of list <= 2
            if (m > INIT_CAPACITY && n <= 2 * m) resize(m / 2);
        }

        // return keys in symbol table as an Iterable
        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<Key>();
            for (int i = 0; i < m; i++) {
                for (Node node : st[i]) {
                    queue.enqueue(node.key);
                }
            }
            return queue;
        }


        /**
         * Unit tests the {@code SeparateChainingHashST} data type.
         *
         * @param args the command-line arguments
         */
        public static void main(String[] args) {
            SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
//            for (int i = 0; !StdIn.isEmpty(); i++) {
//                String key = StdIn.readString();
//                st.put(key, i);
//            }
            String[] s = new String[]{"r", "y", "q", "y", "p", "v", "s", "m"};
            for (int i = 0; i < s.length; i++) {
                int uniform = StdRandom.uniform(10);
                st.put(s[i], uniform, uniform);
            }
            // print keys
            for (String t : st.keys())
                StdOut.println(t + " " + st.get(t));
            StdOut.println(" ========================== ");

            st.deleteNodesWithIntK(5);
            for (String key : st.keys()) {
                StdOut.println(key + " " + st.get(key));
            }

        }

    }

}
