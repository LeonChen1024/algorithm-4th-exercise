package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.1.28 Ordered insertions. Modify BinarySearchST so that inserting a key that is larger
 * than all keys in the table takes constant time (so that building a table by calling put()
 * for keys that are in order takes linear time).
 * <p>
 * 有序插入.修改 BinarySearchST 使得插入一个比表中所有键都大的键的时候使用常量级时间(也就是说使用有序键调用 put()
 * 方法构建一个表使用线性时间)
 *
 * @author LeonChen
 * @since 2/23/21
 */
class E3_1_28 {


    /**
     * 只需要在 put() 中将扩容提前,并且判断是否是最大 key 并操作即可
     *
     * @param args
     */
    public static void main(String[] args) {
        OrderedBinST<Integer, Integer> st = new OrderedBinST<Integer, Integer>();
        Integer[] a = new Integer[]{4, 2, 6, 8, 3, 2, 3, 5, 6, 7, 89, 4, 3, 2, 6, 57, 3,
                4, 2, 3, 564, 67, 43, 2, 4, 36,};
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
        for (Integer s : st.keys())
            StdOut.println(s + " " + st.get(s));

    }

    private static class OrderedBinST<Key extends Comparable<Key>, Value>
            extends BinarySearchST<Key, Value> {

        public OrderedBinST() {
            super();
        }

        @Override
        public void put(Key key, Value val) {
            if (key == null)
                throw new IllegalArgumentException("first argument to put() is null");

            if (val == null) {
                delete(key);
                return;
            }
            // insert new key-value pair
            if (n == keys.length) resize(2 * keys.length);

            //判断是否是最大的 key并处理
            if (n > 0 && keys[n - 1].compareTo(key) < 0) {
                keys[n] = key;
                vals[n] = val;
                n++;
                return;
            }

            int i = rank(key);

            // key is already in table
            if (i < n && keys[i].compareTo(key) == 0) {
                vals[i] = val;
                return;
            }


            for (int j = n; j > i; j--) {
                keys[j] = keys[j - 1];
                vals[j] = vals[j - 1];
            }
            keys[i] = key;
            vals[i] = val;
            n++;

            assert check();
        }
    }

}
