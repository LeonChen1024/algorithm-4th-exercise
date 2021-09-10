package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.5.4 Develop classes HashSTint and HashSTdouble for maintaining sets of keys of
 * primitive int and double types, respectively. (Convert generics to primitive types in
 * the code of LinearProbingHashST.)
 * <p>
 * 开发一个 HashSTint 和 HashSTdouble 类分别用来维护原始 int 和 double 类型(将 LinearProbingHashST
 * 中的类型转为原始类型)
 *
 * @author LeonChen
 * @since 9/4/21
 */
public class E3_5_04 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        HashSTint<Integer> hashSTint = new HashSTint<>();
        hashSTint.put(3, 1);
        hashSTint.put(67, 1);
        hashSTint.put(4, 1);
        hashSTint.put(6, 1);
        hashSTint.put(7, 1);
        hashSTint.put(6, 4);

        StdOut.println("test keys ");
        for (Integer key : hashSTint.keys()) {
            StdOut.print(key + ",");
        }
        StdOut.println(" expect: 3,67,4,6,7 not ordered");

        StdOut.println("test contains  ");
        StdOut.print(hashSTint.contains(3));
        StdOut.println(" expect: true");

        StdOut.println("test delete  ");
        hashSTint.delete(3);
        for (Integer key : hashSTint.keys()) {
            StdOut.print(key + ",");
        }
        StdOut.println(" expect: 67,4,6,7 not ordered");

        StdOut.println("test get");
        StdOut.println(hashSTint.get(6) + " expect: 4");

        HashSTdouble<Integer> hashSTDouble = new HashSTdouble<>();
        hashSTDouble.put(3, 1);
        hashSTDouble.put(67, 1);
        hashSTDouble.put(4, 1);
        hashSTDouble.put(6, 1);
        hashSTDouble.put(7, 1);
        hashSTDouble.put(6, 4);

        StdOut.println("test keys ");
        for (double key : hashSTDouble.keys()) {
            StdOut.print(key + ",");
        }
        StdOut.println(" expect: 3.0,67.0,4.0,6.0,7.0 not ordered");

        StdOut.println("test contains  ");
        StdOut.print(hashSTDouble.contains(3));
        StdOut.println(" expect: true");

        StdOut.println("test delete  ");
        hashSTDouble.delete(3);
        for (double key : hashSTDouble.keys()) {
            StdOut.print(key + ",");
        }
        StdOut.println(" expect: 67.0,4.0,6.0,7.0 not ordered");

        StdOut.println("test get");
        StdOut.println(hashSTDouble.get(6) + " expect: 4");


    }

    public static class HashSTint<Value> {
        private static final int INIT_CAPACITY = 4;

        public int n;           // number of key-value pairs in the symbol table
        public int m;           // size of linear probing table
        public int[] keys;      // the keys
        public Value[] vals;    // the values

        public final static int EMPTY_KEY = Integer.MIN_VALUE;


        /**
         * Initializes an empty symbol table.
         */
        public HashSTint() {
            this(INIT_CAPACITY);
        }

        /**
         * Initializes an empty symbol table with the specified initial capacity.
         *
         * @param capacity the initial capacity
         */
        public HashSTint(int capacity) {
            m = capacity;
            n = 0;
            keys = new int[m];
            for (int i = 0; i < m; i++) {
                keys[i] = EMPTY_KEY;
            }
            vals = (Value[]) new Object[m];
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
        public boolean contains(int key) {
            if (key == EMPTY_KEY)
                throw new IllegalArgumentException("argument to contains() is null");
            return get(key) != null;
        }

        // hash function for keys - returns value between 0 and M-1
        protected int hash(int key) {
            return (key & 0x7fffffff) % m;
        }

        // resizes the hash table to the given capacity by re-hashing all of the keys
        private void resize(int capacity) {
            HashSTint<Value> temp = new HashSTint<Value>(capacity);
            for (int i = 0; i < m; i++) {
                if (keys[i] != EMPTY_KEY) {
                    temp.put(keys[i], vals[i]);
                }
            }
            keys = temp.keys;
            vals = temp.vals;
            m = temp.m;
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
        public void put(int key, Value val) {
            if (key == EMPTY_KEY)
                throw new IllegalArgumentException("first argument to put() is null");

            if (val == null) {
                delete(key);
                return;
            }

            // double table size if 50% full
            if (n >= m / 2) resize(2 * m);

            int i;
            for (i = hash(key); keys[i] != EMPTY_KEY; i = (i + 1) % m) {
                if (keys[i] == key) {
                    vals[i] = val;
                    return;
                }
            }
            keys[i] = key;
            vals[i] = val;
            n++;
        }

        /**
         * Returns the value associated with the specified key.
         *
         * @param key the key
         * @return the value associated with {@code key};
         * {@code null} if no such value
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public Value get(int key) {
            if (key == EMPTY_KEY)
                throw new IllegalArgumentException("argument to get() is null");
            for (int i = hash(key); keys[i] != EMPTY_KEY; i = (i + 1) % m)
                if (keys[i] == key)
                    return vals[i];
            return null;
        }

        /**
         * Removes the specified key and its associated value from this symbol table
         * (if the key is in this symbol table).
         *
         * @param key the key
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void delete(int key) {
            if (key == EMPTY_KEY)
                throw new IllegalArgumentException("argument to delete() is null");
            if (!contains(key)) return;

            // find position i of key
            int i = hash(key);
            while (key != (keys[i])) {
                i = (i + 1) % m;
            }

            // delete key and associated value
            keys[i] = EMPTY_KEY;
            vals[i] = null;

            // rehash all keys in same cluster
            i = (i + 1) % m;
            while (keys[i] != EMPTY_KEY) {
                // delete keys[i] an vals[i] and reinsert
                int keyToRehash = keys[i];
                Value valToRehash = vals[i];
                keys[i] = EMPTY_KEY;
                vals[i] = null;
                n--;
                put(keyToRehash, valToRehash);
                i = (i + 1) % m;
            }

            n--;

            // halves size of array if it's 12.5% full or less
            if (n > 0 && n <= m / 8) resize(m / 2);

            assert check();
        }

        /**
         * Returns all keys in this symbol table as an {@code Iterable}.
         * To iterate over all of the keys in the symbol table named {@code st},
         * use the foreach notation: {@code for (Key key : st.keys())}.
         *
         * @return all keys in this symbol table
         */
        public Iterable<Integer> keys() {
            Queue<Integer> queue = new Queue<>();
            for (int i = 0; i < m; i++)
                if (keys[i] != EMPTY_KEY) queue.enqueue(keys[i]);
            return queue;
        }

        // integrity check - don't check after each put() because
        // integrity not maintained during a delete()
        private boolean check() {

            // check that hash table is at most 50% full
            if (m < 2 * n) {
                System.err.println("Hash table size m = " + m + "; array size n = " + n);
                return false;
            }

            // check that each key in table can be found by get()
            for (int i = 0; i < m; i++) {
                if (keys[i] == EMPTY_KEY) continue;
                else if (get(keys[i]) != vals[i]) {
                    System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                    return false;
                }
            }
            return true;
        }


        /**
         * Unit tests the {@code LinearProbingHashST} data type.
         *
         * @param args the command-line arguments
         */
        public static void main(String[] args) {
            LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
            for (int i = 0; !StdIn.isEmpty(); i++) {
                String key = StdIn.readString();
                st.put(key, i);
            }

            // print keys
            for (String s : st.keys())
                StdOut.println(s + " " + st.get(s));
        }

    }

    public static class HashSTdouble<Value> {
        private static final int INIT_CAPACITY = 4;

        public int n;           // number of key-value pairs in the symbol table
        public int m;           // size of linear probing table
        public double[] keys;      // the keys
        public Value[] vals;    // the values

        public final static double EMPTY_KEY = Double.MIN_VALUE;


        /**
         * Initializes an empty symbol table.
         */
        public HashSTdouble() {
            this(INIT_CAPACITY);
        }

        /**
         * Initializes an empty symbol table with the specified initial capacity.
         *
         * @param capacity the initial capacity
         */
        public HashSTdouble(int capacity) {
            m = capacity;
            n = 0;
            keys = new double[m];
            for (int i = 0; i < m; i++) {
                keys[i] = EMPTY_KEY;
            }
            vals = (Value[]) new Object[m];
        }

        /**
         * Returns the number of key-value pairs in this symbol table.
         *
         * @return the number of key-value pairs in this symbol table
         */
        public double size() {
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
        public boolean contains(double key) {
            if (key == EMPTY_KEY)
                throw new IllegalArgumentException("argument to contains() is null");
            return get(key) != null;
        }

        // hash function for keys - returns value between 0 and M-1
        protected int hash(double key) {
            return (int) (key % m);
        }

        // resizes the hash table to the given capacity by re-hashing all of the keys
        private void resize(int capacity) {
            HashSTdouble<Value> temp = new HashSTdouble<Value>(capacity);
            for (int i = 0; i < m; i++) {
                if (keys[i] != EMPTY_KEY) {
                    temp.put(keys[i], vals[i]);
                }
            }
            keys = temp.keys;
            vals = temp.vals;
            m = temp.m;
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
        public void put(double key, Value val) {
            if (key == EMPTY_KEY)
                throw new IllegalArgumentException("first argument to put() is null");

            if (val == null) {
                delete(key);
                return;
            }

            // double table size if 50% full
            if (n >= m / 2) resize(2 * m);

            int i;
            for (i = hash(key); keys[i] != EMPTY_KEY; i = (i + 1) % m) {
                if (keys[i] == key) {
                    vals[i] = val;
                    return;
                }
            }
            keys[i] = key;
            vals[i] = val;
            n++;
        }

        /**
         * Returns the value associated with the specified key.
         *
         * @param key the key
         * @return the value associated with {@code key};
         * {@code null} if no such value
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public Value get(double key) {
            if (key == EMPTY_KEY)
                throw new IllegalArgumentException("argument to get() is null");
            for (int i = hash(key); keys[i] != EMPTY_KEY; i = (i + 1) % m)
                if (keys[i] == key)
                    return vals[i];
            return null;
        }

        /**
         * Removes the specified key and its associated value from this symbol table
         * (if the key is in this symbol table).
         *
         * @param key the key
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void delete(double key) {
            if (key == EMPTY_KEY)
                throw new IllegalArgumentException("argument to delete() is null");
            if (!contains(key)) return;

            // find position i of key
            int i = hash(key);
            while (key != (keys[i])) {
                i = (i + 1) % m;
            }

            // delete key and associated value
            keys[i] = EMPTY_KEY;
            vals[i] = null;

            // rehash all keys in same cluster
            i = (i + 1) % m;
            while (keys[i] != EMPTY_KEY) {
                // delete keys[i] an vals[i] and reinsert
                double keyToRehash = keys[i];
                Value valToRehash = vals[i];
                keys[i] = EMPTY_KEY;
                vals[i] = null;
                n--;
                put(keyToRehash, valToRehash);
                i = (i + 1) % m;
            }

            n--;

            // halves size of array if it's 12.5% full or less
            if (n > 0 && n <= m / 8) resize(m / 2);

            assert check();
        }

        /**
         * Returns all keys in this symbol table as an {@code Iterable}.
         * To iterate over all of the keys in the symbol table named {@code st},
         * use the foreach notation: {@code for (Key key : st.keys())}.
         *
         * @return all keys in this symbol table
         */
        public Iterable<Double> keys() {
            Queue<Double> queue = new Queue<>();
            for (int i = 0; i < m; i++)
                if (keys[i] != EMPTY_KEY) queue.enqueue(keys[i]);
            return queue;
        }

        // integrity check - don't check after each put() because
        // integrity not maintained during a delete()
        private boolean check() {

            // check that hash table is at most 50% full
            if (m < 2 * n) {
                System.err.println("Hash table size m = " + m + "; array size n = " + n);
                return false;
            }

            // check that each key in table can be found by get()
            for (int i = 0; i < m; i++) {
                if (keys[i] == EMPTY_KEY) continue;
                else if (get(keys[i]) != vals[i]) {
                    System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                    return false;
                }
            }
            return true;
        }


        /**
         * Unit tests the {@code LinearProbingHashST} data type.
         *
         * @param args the command-line arguments
         */
        public static void main(String[] args) {
            LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
            for (int i = 0; !StdIn.isEmpty(); i++) {
                String key = StdIn.readString();
                st.put(key, i);
            }

            // print keys
            for (String s : st.keys())
                StdOut.println(s + " " + st.get(s));
        }

    }


}
