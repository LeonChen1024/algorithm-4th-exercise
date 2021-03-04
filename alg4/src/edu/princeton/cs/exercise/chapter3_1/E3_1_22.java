package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.1.22 Self-organizing search. A self-organizing search algorithm is one that rearranges
 * items to make those that are accessed frequently likely to be found early in the search.
 * Modify your search implementation for Exercise 3.1.2 to perform the following action
 * on every search hit: move the key-value pair found to the beginning of the list, moving
 * all pairs between the beginning of the list and the vacated position to the right one
 * position. This procedure is called the move-to-front heuristic.
 * <p>
 * 自组织搜索.一个自组织搜索算法会重分配项使得搜索的时候近期频繁查找的元素容易被搜索到.修改你练习 3.1.2的实现来
 * 在每次搜索执行以下操作:移动找到的键值对到列表头,移动所有从开始到找到的位置间的元素向右一位.这个流程叫做启发式
 * 前移
 *
 * @author LeonChen
 * @since 2/15/20
 */
class E3_1_22 {
    public static class SelfOrganizeArrayST<Key, Value> {
        private static final int INIT_SIZE = 8;

        private Value[] vals;   // symbol table values
        private Key[] keys;     // symbol table keys
        private int n = 0;      // number of elements in symbol table

        public SelfOrganizeArrayST() {
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
            for (int i = 0; i < n; i++) {

                if (keys[i].equals(key)) {
                    // 修改这里即可
                    Key tempK = keys[i];
                    Value tempV = vals[i];
                    for (int j = i; j > 0; j--) {
                        keys[j] = keys[j - 1];
                        vals[j] = vals[j - 1];

                    }
                    keys[0] = tempK;
                    vals[0] = tempV;
                    return tempV;
                }
            }
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
            E3_1_02.ArrayST<String, Integer> st = new E3_1_02.ArrayST<String, Integer>();
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

    /**
     * 修改 get 方法即可
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}
