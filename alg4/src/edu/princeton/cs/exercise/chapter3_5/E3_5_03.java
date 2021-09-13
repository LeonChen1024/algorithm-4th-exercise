package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * 3.5.3 Develop a SET implementation BinarySearchSET by starting with the code for
 * BinarySearchST and eliminating all of the code involving values.
 * <p>
 * 开发一个BinarySearchSET实现,从BinarySearchST的代码衍生并且忽略所有包含值得代码
 *
 * @author LeonChen
 * @since 9/4/21
 */
class E3_5_03 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        BinarySearchSET<Integer> sequentialSearchSET = new BinarySearchSET<>();
        sequentialSearchSET.put(3);
        sequentialSearchSET.put(67);
        sequentialSearchSET.put(4);
        sequentialSearchSET.put(6);
        sequentialSearchSET.put(7);
        sequentialSearchSET.put(6);

        StdOut.println("test keys ");
        for (Integer key : sequentialSearchSET.keys()) {
            StdOut.print(key + ",");
        }
        StdOut.println(" expect: 3,67,4,6,7 not ordered");

        StdOut.println("test contains  ");
        StdOut.print(sequentialSearchSET.contains(3));
        StdOut.println(" expect: true");

        StdOut.println("test delete  ");
        sequentialSearchSET.delete(3);
        for (Integer key : sequentialSearchSET.keys()) {
            StdOut.print(key + ",");
        }
        StdOut.println(" expect: 67,4,6,7 not ordered");

    }

    private static class BinarySearchSET<Key extends Comparable<Key>> {
        protected static final int INIT_CAPACITY = 2;
        protected Key[] keys;
        protected int n = 0;
        public int compares;

        public BinarySearchSET() {
            this(INIT_CAPACITY);
        }

        public BinarySearchSET(int capacity) {
            keys = (Key[]) new Comparable[capacity];
        }

        // resize the underlying arrays
        protected void resize(int capacity) {
            assert capacity >= n;
            Key[] tempk = (Key[]) new Comparable[capacity];
            for (int i = 0; i < n; i++) {
                tempk[i] = keys[i];
            }
            keys = tempk;
        }

        public int size() {
            return n;
        }

        public boolean isEmpty() {
            return size() == 0;
        }


        public boolean contains(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to contains() is null");
            if (isEmpty()) return false;
            int i = rank(key);
            return i < n && keys[i].compareTo(key) == 0;
        }


        public int rank(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to rank() is null");

            int lo = 0, hi = n - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                compares++;
                int cmp = key.compareTo(keys[mid]);
                if (cmp < 0) hi = mid - 1;
                else if (cmp > 0) lo = mid + 1;
                else return mid;
            }
            return lo;
        }


        public void put(Key key) {
            if (key == null)
                throw new IllegalArgumentException("first argument to put() is null");

            int i = rank(key);

            compares++;
            // key is already in table
            if (i < n && keys[i].compareTo(key) == 0) {
                return;
            }

            // insert new key-value pair
            if (n == keys.length) resize(2 * keys.length);

            for (int j = n; j > i; j--) {
                keys[j] = keys[j - 1];
            }
            keys[i] = key;
            n++;

            assert check();
        }

        public void delete(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to delete() is null");
            if (isEmpty()) return;

            // compute rank
            int i = rank(key);

            // key not in table
            if (i == n || keys[i].compareTo(key) != 0) {
                return;
            }

            for (int j = i; j < n - 1; j++) {
                keys[j] = keys[j + 1];
            }

            n--;
            keys[n] = null;  // to avoid loitering

            // resize if 1/4 full
            if (n > 0 && n == keys.length / 4) resize(keys.length / 2);

            assert check();
        }

        public void deleteMin() {
            if (isEmpty())
                throw new NoSuchElementException("Symbol table underflow error");
            delete(min());
        }

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
        public Key min() {
            if (isEmpty())
                throw new NoSuchElementException("called min() with empty symbol table");
            return keys[0];
        }

        /**
         * Returns the largest key in this symbol table.
         *
         * @return the largest key in this symbol table
         * @throws NoSuchElementException if this symbol table is empty
         */
        public Key max() {
            if (isEmpty())
                throw new NoSuchElementException("called max() with empty symbol table");
            return keys[n - 1];
        }

        /**
         * Return the kth smallest key in this symbol table.
         *
         * @param k the order statistic
         * @return the {@code k}th smallest key in this symbol table
         * @throws IllegalArgumentException unless {@code k} is between 0 and
         *                                  <em>n</em>–1
         */
        public Key select(int k) {
            if (k < 0 || k >= size()) {
                throw new IllegalArgumentException("called select() with invalid argument: " + k);
            }
            return keys[k];
        }

        /**
         * Returns the largest key in this symbol table less than or equal to {@code key}.
         *
         * @param key the key
         * @return the largest key in this symbol table less than or equal to {@code key}
         * @throws NoSuchElementException   if there is no such key
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public Key floor(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to floor() is null");
            int i = rank(key);
            if (i < n && key.compareTo(keys[i]) == 0) return keys[i];
            if (i == 0) return null;
            else return keys[i - 1];
        }

        /**
         * Returns the smallest key in this symbol table greater than or equal to {@code key}.
         *
         * @param key the key
         * @return the smallest key in this symbol table greater than or equal to {@code key}
         * @throws NoSuchElementException   if there is no such key
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public Key ceiling(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to ceiling() is null");
            int i = rank(key);
            if (i == n) return null;
            else return keys[i];
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
        public int size(Key lo, Key hi) {
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
        public Iterable<Key> keys() {
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
        public Iterable<Key> keys(Key lo, Key hi) {
            if (lo == null)
                throw new IllegalArgumentException("first argument to keys() is null");
            if (hi == null)
                throw new IllegalArgumentException("second argument to keys() is null");

            Queue<Key> queue = new Queue<Key>();
            if (lo.compareTo(hi) > 0) return queue;
            for (int i = rank(lo); i < rank(hi); i++)
                queue.enqueue(keys[i]);
            if (contains(hi)) queue.enqueue(keys[rank(hi)]);
            return queue;
        }


        /***************************************************************************
         *  Check internal invariants.
         ***************************************************************************/

        protected boolean check() {
            return isSorted() && rankCheck();
        }

        // are the items in the array in ascending order?
        private boolean isSorted() {
            for (int i = 1; i < size(); i++)
                if (keys[i].compareTo(keys[i - 1]) < 0) return false;
            return true;
        }

        // check that rank(select(i)) = i
        private boolean rankCheck() {
            for (int i = 0; i < size(); i++)
                if (i != rank(select(i))) return false;
            for (int i = 0; i < size(); i++)
                if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
            return true;
        }


    }


}
