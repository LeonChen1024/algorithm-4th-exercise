package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * 3.1.12 Modify BinarySearchST to maintain one array of Item objects that contain
 * keys and values, rather than two parallel arrays. Add a constructor that takes an array of
 * Item values as argument and uses mergesort to sort the array.
 * <p>
 * 修改 BinarySearchST 使他维护一个包含了键值对的 Item 对象数组,而不是使用两个分隔的数组.添加一个构造器接收
 * 一个 Item 数组作为参数并且使用合并排序来排列数组.
 *
 * @author LeonChen
 * @since 2/8/21
 */
class E3_1_12 {


    private static class Item<K extends Comparable<K>, V> implements Comparable<Item<K, V>> {
        K key;
        V value;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Item<K, V> o) {
            return key.compareTo(o.key);
        }
    }

    /**
     * Item 使用 key 排序,所以 key 要支持 CompareTo , 修改原 BSST 只需要将原本两个数组的访问,改为同一
     * 数组中对于 key value 的访问即可
     *
     * @param args
     */
    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST();
        Item<String, Integer> item = new Item<>("E", 0);
        st.put(item);
//        StdOut.println(st.compares);
        println(st);
        item = new Item<>("A", 1);
        st.put(item);
//        StdOut.println(st.compares);
        println(st);
        item = new Item<>("S", 2);
        st.put(item);
//        StdOut.println(st.compares);
        println(st);
        item = new Item<>("Y", 3);
        st.put(item);
//        StdOut.println(st.compares);
        println(st);
        item = new Item<>("Q", 4);
        st.put(item);
//        StdOut.println(st.compares);
        println(st);
        item = new Item<>("U", 5);
        st.put(item);
//        StdOut.println(st.compares);
        println(st);
        item = new Item<>("E", 6);
        st.put(item);
//        StdOut.println(st.compares);
        println(st);
        item = new Item<>("S", 7);
        st.put(item);
//        StdOut.println(st.compares);
        println(st);
        item = new Item<>("T", 8);
        st.put(item);
//        StdOut.println(st.compares);
        println(st);

        item = new Item<>("I", 9);
        st.put(item);
//        StdOut.println(st.compares);
        println(st);

        item = new Item<>("O", 10);
        st.put(item);
//        StdOut.println(st.compares);
        println(st);

        item = new Item<>("N", 11);
        st.put(item);
//        StdOut.println(st.compares);
        println(st);
    }

    private static void println(BinarySearchST<String, Integer> st) {
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println("=======================");
    }

    public static class BinarySearchST<K extends Comparable<K>, V> {
        private static final int INIT_CAPACITY = 2;
        private Item<K, V>[] items;
        private int n = 0;
        public int compares;

        /**
         * Initializes an empty symbol table.
         */
        public BinarySearchST() {
            this(INIT_CAPACITY);
        }

        /**
         * Initializes an empty symbol table with the specified initial capacity.
         *
         * @param capacity the maximum capacity
         */
        public BinarySearchST(int capacity) {
            items = new Item[capacity];
        }

        // resize the underlying arrays
        private void resize(int capacity) {
            assert capacity >= n;
            Item[] temp = new Item[capacity];
            for (int i = 0; i < n; i++) {
                temp[i] = items[i];
            }
            items = temp;
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
         * Does this symbol table contain the given key?
         *
         * @param key the key
         * @return {@code true} if this symbol table contains {@code key} and
         * {@code false} otherwise
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public boolean contains(K key) {
            if (key == null)
                throw new IllegalArgumentException("argument to contains() is null");
            return get(key) != null;
        }

        /**
         * Returns the value associated with the given key in this symbol table.
         *
         * @param key the key
         * @return the value associated with the given key if the key is in the symbol table
         * and {@code null} if the key is not in the symbol table
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public V get(K key) {
            if (key == null)
                throw new IllegalArgumentException("argument to get() is null");
            if (isEmpty()) return null;
            int i = rank(key);
            if (i < n && items[i].key.compareTo(key) == 0) return items[i].value;
            return null;
        }

        /**
         * Returns the number of keys in this symbol table strictly less than {@code key}.
         *
         * @param key the key
         * @return the number of keys in the symbol table strictly less than {@code key}
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public int rank(K key) {
            if (key == null)
                throw new IllegalArgumentException("argument to rank() is null");

            int lo = 0, hi = n - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                compares++;
                int cmp = key.compareTo(items[mid].key);
                if (cmp < 0) hi = mid - 1;
                else if (cmp > 0) lo = mid + 1;
                else return mid;
            }
            return lo;
        }


        /**
         * Inserts the specified key-value pair into the symbol table, overwriting the old
         * value with the new value if the symbol table already contains the specified key.
         * Deletes the specified key (and its associated value) from this symbol table
         * if the specified value is {@code null}.
         *
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void put(Item<K, V> item) {
            if (item.key == null)
                throw new IllegalArgumentException("first argument to put() is " +
                        "null");

            if (item.value == null) {
                delete(item.key);
                return;
            }

            int i = rank(item.key);

            // key is already in table
            if (i < n && items[i].key.compareTo(item.key) == 0) {
                items[i].value = item.value;
                return;
            }

            // insert new key-value pair
            if (n == items.length) resize(2 * items.length);

            for (int j = n; j > i; j--) {
                items[j] = items[j - 1];
            }
            items[i] = item;
            n++;

            assert check();
        }

        /**
         * Removes the specified key and associated value from this symbol table
         * (if the key is in the symbol table).
         *
         * @param key the key
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void delete(K key) {
            if (key == null)
                throw new IllegalArgumentException("argument to delete() is null");
            if (isEmpty()) return;

            // compute rank
            int i = rank(key);

            // key not in table
            if (i == n || items[i].key.compareTo(key) != 0) {
                return;
            }

            for (int j = i; j < n - 1; j++) {
                items[j] = items[j + 1];
            }

            n--;
            items[n] = null;  // to avoid loitering

            // resize if 1/4 full
            if (n > 0 && n == items.length / 4) resize(items.length / 2);

            assert check();
        }

        /**
         * Removes the smallest key and associated value from this symbol table.
         *
         * @throws NoSuchElementException if the symbol table is empty
         */
        public void deleteMin() {
            if (isEmpty())
                throw new NoSuchElementException("Symbol table underflow error");
            delete(min());
        }

        /**
         * Removes the largest key and associated value from this symbol table.
         *
         * @throws NoSuchElementException if the symbol table is empty
         */
        public void deleteMax() {
            if (isEmpty())
                throw new NoSuchElementException("Symbol table underflow error");
            delete(max());
        }


        /***************************************************************************
         *  Ordered symbol table methods.
         ***************************************************************************/

        /**
         * Returns the smallest key in this symbol table.
         *
         * @return the smallest key in this symbol table
         * @throws NoSuchElementException if this symbol table is empty
         */
        public K min() {
            if (isEmpty())
                throw new NoSuchElementException("called min() with empty symbol table");
            return items[0].key;
        }

        /**
         * Returns the largest key in this symbol table.
         *
         * @return the largest key in this symbol table
         * @throws NoSuchElementException if this symbol table is empty
         */
        public K max() {
            if (isEmpty())
                throw new NoSuchElementException("called max() with empty symbol table");
            return items[n - 1].key;
        }

        /**
         * Return the kth smallest key in this symbol table.
         *
         * @param k the order statistic
         * @return the {@code k}th smallest key in this symbol table
         * @throws IllegalArgumentException unless {@code k} is between 0 and
         *                                  <em>n</em>–1
         */
        public K select(int k) {
            if (k < 0 || k >= size()) {
                throw new IllegalArgumentException("called select() with invalid argument: " + k);
            }
            return items[k].key;
        }

        /**
         * Returns the largest key in this symbol table less than or equal to {@code key}.
         *
         * @param key the key
         * @return the largest key in this symbol table less than or equal to {@code key}
         * @throws NoSuchElementException   if there is no such key
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public K floor(K key) {
            if (key == null)
                throw new IllegalArgumentException("argument to floor() is null");
            int i = rank(key);
            if (i < n && key.compareTo(items[i].key) == 0) return items[i].key;
            if (i == 0) return null;
            else return items[i - 1].key;
        }

        /**
         * Returns the smallest key in this symbol table greater than or equal to {@code key}.
         *
         * @param key the key
         * @return the smallest key in this symbol table greater than or equal to {@code key}
         * @throws NoSuchElementException   if there is no such key
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public K ceiling(K key) {
            if (key == null)
                throw new IllegalArgumentException("argument to ceiling() is null");
            int i = rank(key);
            if (i == n) return null;
            else return items[i].key;
        }

        /**
         * Returns the number of keys in this symbol table in the specified range.
         *
         * @param lo minimum endpoint
         * @param hi maximum endpoint
         * @return the number of keys in this symbol table between {@code lo}
         * (inclusive) and {@code hi} (inclusive)
         * @throws IllegalArgumentException if either {@code lo} or {@code hi}
         *                                  is {@code null}
         */
        public int size(K lo, K hi) {
            if (lo == null)
                throw new IllegalArgumentException("first argument to size() is null");
            if (hi == null)
                throw new IllegalArgumentException("second argument to size() is null");

            if (lo.compareTo(hi) > 0) return 0;
            if (contains(hi)) return rank(hi) - rank(lo) + 1;
            else return rank(hi) - rank(lo);
        }

        /**
         * Returns all keys in this symbol table as an {@code Iterable}.
         * To iterate over all of the keys in the symbol table named {@code st},
         * use the foreach notation: {@code for (Key key : st.keys())}.
         *
         * @return all keys in this symbol table
         */
        public Iterable<K> keys() {
            return keys(min(), max());
        }

        /**
         * Returns all keys in this symbol table in the given range,
         * as an {@code Iterable}.
         *
         * @param lo minimum endpoint
         * @param hi maximum endpoint
         * @return all keys in this symbol table between {@code lo}
         * (inclusive) and {@code hi} (inclusive)
         * @throws IllegalArgumentException if either {@code lo} or {@code hi}
         *                                  is {@code null}
         */
        public Iterable<K> keys(K lo, K hi) {
            if (lo == null)
                throw new IllegalArgumentException("first argument to keys() is null");
            if (hi == null)
                throw new IllegalArgumentException("second argument to keys() is null");

            Queue<K> queue = new Queue<K>();
            if (lo.compareTo(hi) > 0) return queue;
            for (int i = rank(lo); i < rank(hi); i++)
                queue.enqueue(items[i].key);
            if (contains(hi)) queue.enqueue(items[rank(hi)].key);
            return queue;
        }


        /***************************************************************************
         *  Check internal invariants.
         ***************************************************************************/

        private boolean check() {
            return isSorted() && rankCheck();
        }

        // are the items in the array in ascending order?
        private boolean isSorted() {
            for (int i = 1; i < size(); i++)
                if (items[i].key.compareTo(items[i - 1].key) < 0) return false;
            return true;
        }

        // check that rank(select(i)) = i
        private boolean rankCheck() {
            for (int i = 0; i < size(); i++)
                if (i != rank(select(i))) return false;
            for (int i = 0; i < size(); i++)
                if (items[i].key.compareTo(select(rank(items[i].key))) != 0) return false;
            return true;
        }

    }


}
