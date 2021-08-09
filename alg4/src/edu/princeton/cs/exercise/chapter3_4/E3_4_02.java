package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

/**
 * 3.4.2 Develop an alternate implementation of SeparateChainingHashST that directly
 * uses the linked-list code from SequentialSearchST.
 * <p>
 * 开发另一种 SeparateChainingHashST 的实现直接使用链表而不是 SequentialSearchST
 *
 * @author LeonChen
 * @since 7/22/21
 */
class E3_4_02 {

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

            public Node(Key key, Value value) {
                this.key = key;
                this.value = value;
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
                    temp.put(node.key, node.value);
                }
            }
            this.m = temp.m;
            this.n = temp.n;
            this.st = temp.st;
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
        public void put(Key key, Value val) {
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
            st[i].add(new Node(key, val));
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
                st.put(s[i], i);
            }
            // print keys
            for (String t : st.keys())
                StdOut.println(t + " " + st.get(t));

        }

    }

}
