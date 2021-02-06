package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.1.2 Develop a symbol-table implementation ArrayST that uses an (unordered) array as the
 * underlying data structure to implement our basic symbol-table API.
 * <p>
 * 开发一个 ArrayST 符号表实现,使用一个 (无序)数组作为底层的数据结构来实现基础的符号表 API
 *
 * @author LeonChen
 * @since 1/29/20
 */
class E3_1_02 {


    /**
     * 官方解答
     */
    public static class ArrayST<Key, Value> {
        private static final int INIT_SIZE = 8;

        private Value[] vals;   // symbol table values
        private Key[] keys;     // symbol table keys
        private int n = 0;      // number of elements in symbol table

        public ArrayST() {
            keys = (Key[]) new Object[INIT_SIZE];
            vals = (Value[]) new Object[INIT_SIZE];
        }

        // return the number of key-value pairs in the symbol table
        public int size() {
            return n;
        }

        // is the symbol table empty?
        public boolean isEmpty() {
            return size() == 0;
        }

        // resize the parallel arrays to the given capacity
        private void resize(int capacity) {
            Key[] tempk = (Key[]) new Object[capacity];
            Value[] tempv = (Value[]) new Object[capacity];
            for (int i = 0; i < n; i++)
                tempk[i] = keys[i];
            for (int i = 0; i < n; i++)
                tempv[i] = vals[i];
            keys = tempk;
            vals = tempv;
        }

        // insert the key-value pair into the symbol table
        public void put(Key key, Value val) {

            // to deal with duplicates
            delete(key);

            // double size of arrays if necessary
            if (n >= vals.length) resize(2 * n);

            // add new key and value at the end of array
            vals[n] = val;
            keys[n] = key;
            n++;
        }

        public Value get(Key key) {
            for (int i = 0; i < n; i++)
                if (keys[i].equals(key)) return vals[i];
            return null;
        }

        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<Key>();
            for (int i = 0; i < n; i++)
                queue.enqueue(keys[i]);
            return queue;
        }

        // remove given key (and associated value)
        public void delete(Key key) {
            for (int i = 0; i < n; i++) {
                if (key.equals(keys[i])) {
                    keys[i] = keys[n - 1];
                    vals[i] = vals[n - 1];
                    keys[n - 1] = null;
                    vals[n - 1] = null;
                    n--;
                    if (n > 0 && n == keys.length / 4) resize(keys.length / 2);
                    return;
                }
            }
        }


        // 原先官方无法停止,增加停止字符"-"

        /***************************************************************************
         * Test routine.
         ***************************************************************************/
        public static void main(String[] args) {
            ArrayST<String, Integer> st = new ArrayST<String, Integer>();
            for (int i = 0; !StdIn.isEmpty(); i++) {
                String key = StdIn.readString();
                if (key.equals("-")) {
                    break;
                }
                st.put(key, i);
            }
            for (String s : st.keys())
                StdOut.println(s + " " + st.get(s));
        }
    }


}
