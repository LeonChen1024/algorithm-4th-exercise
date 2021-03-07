package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.*;

import java.util.NoSuchElementException;

/**
 * 3.1.25 Software caching. Since the default implementation of contains() calls get(),
 * the inner loop of FrequencyCounter
 * <code>
 * if (!st.contains(word)) st.put(word, 1);
 * else st.put(word, st.get(word) + 1);
 * </code>
 * leads to two or three searches for the same key. To enable clear client code like this
 * without sacrificing efficiency, we can use a technique known as software caching, where
 * we save the location of the most recently accessed key in an instance variable. Modify
 * SequentialSearchST and BinarySearchST to take advantage of this idea.
 * <p>
 * 软件缓存.因为默认的 contains() 实现调用了 get(),FrequencyCounter 的内部循环
 * <code>
 * if (!st.contains(word)) st.put(word, 1);
 * else st.put(word, st.get(word) + 1);
 * </code>
 * 这会导致对相同 key 进行 2 到 3 次的查询.为了简化这样的代码并且不影响效率,我们可以使用一个软件缓存的概念,
 * 我们将最近访问的键保存到一个实例变量中.修改SequentialSearchST 和 BinarySearchST 来获取这么做的好处
 *
 * @author LeonChen
 * @since 2/18/20
 */
class E3_1_25 {

    private static class CacheSequentialST<Key, Value> {
        private int n;           // number of key-value pairs
        private Node first;      // the linked list of key-value pairs
        public int compares; //对比次数

        private Node cache = new Node(null, null, null);


        // a helper linked list data type
        private class Node {
            private Key key;
            private Value val;
            private Node next;

            public Node(Key key, Value val, Node next) {
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }

        /**
         * Initializes an empty symbol table.
         */
        public CacheSequentialST() {
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
            if (key == null) throw new IllegalArgumentException("argument to contains() is null");
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
        public Value get(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to get() is null");
            if (key.equals(cache.key)) {
                return cache.val;
            }
            for (Node x = first; x != null; x = x.next) {
                if (key.equals(x.key)) {
                    cache = x;
                    return x.val;
                }
            }
            return null;
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
            if (key == null) throw new IllegalArgumentException("first argument to put() is null");
            if (val == null) {
                delete(key);
                return;
            }

            if (key.equals(cache.key)) {
                cache.val = val;
                return;
            } else {
                for (Node x = first; x != null; x = x.next) {
                    compares++;
                    if (key.equals(x.key)) {
                        x.val = val;
                        return;
                    }
                }
            }

            first = new Node(key, val, first);
            n++;
        }

        /**
         * Removes the specified key and its associated value from this symbol table
         * (if the key is in this symbol table).
         *
         * @param key the key
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void delete(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to delete() is null");
            if (cache.key.equals(key)) {
                cache = new Node(null, null, null);
            }
            first = delete(first, key);
        }

        // delete key in linked list beginning at Node x
        // warning: function call stack too large if table is large
        private Node delete(Node x, Key key) {
            if (x == null) return null;

            if (key.equals(x.key)) {
                n--;
                return x.next;
            }
            x.next = delete(x.next, key);
            return x;
        }


        /**
         * Returns all keys in the symbol table as an {@code Iterable}.
         * To iterate over all of the keys in the symbol table named {@code st},
         * use the foreach notation: {@code for (Key key : st.keys())}.
         *
         * @return all keys in the symbol table
         */
        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<Key>();
            for (Node x = first; x != null; x = x.next)
                queue.enqueue(x.key);
            return queue;
        }

    }


    private static class CacheBinaryST<Key extends Comparable<Key>, Value> {
        protected static final int INIT_CAPACITY = 2;
        protected Key[] keys;
        protected Value[] vals;
        protected int n = 0;
        public int compares;

        private Key cacheKey;
        private int cacheRank;

        /**
         * Initializes an empty symbol table.
         */
        public CacheBinaryST() {
            this(INIT_CAPACITY);
        }

        /**
         * Initializes an empty symbol table with the specified initial capacity.
         *
         * @param capacity the maximum capacity
         */
        public CacheBinaryST(int capacity) {
            keys = (Key[]) new Comparable[capacity];
            vals = (Value[]) new Object[capacity];
        }

        // resize the underlying arrays
        protected void resize(int capacity) {
            assert capacity >= n;
            Key[] tempk = (Key[]) new Comparable[capacity];
            Value[] tempv = (Value[]) new Object[capacity];
            for (int i = 0; i < n; i++) {
                tempk[i] = keys[i];
                tempv[i] = vals[i];
            }
            vals = tempv;
            keys = tempk;
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
        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to contains() is null");
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
        public Value get(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to get() is null");
            if (isEmpty()) return null;
            if (key.equals(cacheKey)) {
                return vals[cacheRank];
            }
            int i = rank(key);
            if (i < n && keys[i].compareTo(key) == 0) {
                cacheKey = key;
                cacheRank = i;
                return vals[i];
            }
            return null;
        }

        /**
         * Returns the number of keys in this symbol table strictly less than {@code key}.
         *
         * @param key the key
         * @return the number of keys in the symbol table strictly less than {@code key}
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public int rank(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to rank() is null");

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
            if (key == null) throw new IllegalArgumentException("first argument to put() is null");

            if (val == null) {
                delete(key);
                return;
            }

            if (key.equals(cacheKey)) {
                vals[cacheRank] = val;
                return;
            }
            int i = rank(key);

            // key is already in table
            if (i < n && keys[i].compareTo(key) == 0) {
                vals[i] = val;
                return;
            }

            // insert new key-value pair
            if (n == keys.length) resize(2 * keys.length);

            for (int j = n; j > i; j--) {
                keys[j] = keys[j - 1];
                vals[j] = vals[j - 1];
            }
            keys[i] = key;
            vals[i] = val;
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
        public void delete(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to delete() is null");
            if (isEmpty()) return;

            if (key.equals(cacheKey)) {
                cacheKey = null;
            }
            // compute rank
            int i = rank(key);

            // key not in table
            if (i == n || keys[i].compareTo(key) != 0) {
                return;
            }

            for (int j = i; j < n - 1; j++) {
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }

            n--;
            keys[n] = null;  // to avoid loitering
            vals[n] = null;

            // resize if 1/4 full
            if (n > 0 && n == keys.length / 4) resize(keys.length / 2);

            assert check();
        }

        /**
         * Removes the smallest key and associated value from this symbol table.
         *
         * @throws NoSuchElementException if the symbol table is empty
         */
        public void deleteMin() {
            if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
            delete(min());
        }

        /**
         * Removes the largest key and associated value from this symbol table.
         *
         * @throws NoSuchElementException if the symbol table is empty
         */
        public void deleteMax() {
            if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
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
            if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
            return keys[0];
        }

        /**
         * Returns the largest key in this symbol table.
         *
         * @return the largest key in this symbol table
         * @throws NoSuchElementException if this symbol table is empty
         */
        public Key max() {
            if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
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
            if (key == null) throw new IllegalArgumentException("argument to floor() is null");
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
            if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
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
            if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
            if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

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
            if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
            if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

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

        private boolean check() {
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


        /**
         * Unit tests the {@code BinarySearchST} data type.
         *
         * @param args the command-line arguments
         */
        public static void main(String[] args) {
            edu.princeton.cs.algs4.BinarySearchST<String, Integer> st = new edu.princeton.cs.algs4.BinarySearchST<String, Integer>();
            for (int i = 0; !StdIn.isEmpty(); i++) {
                String key = StdIn.readString();
                st.put(key, i);
            }
            for (String s : st.keys())
                StdOut.println(s + " " + st.get(s));
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        frequencyCounter(new int[]{4, 3, 5, 7, 2, 7, 9, 7, 6, 5, 3, 2, 67, 75, 6, 5, 74, 4, 3, 4, 52});
    }

    private static void frequencyCounter(int[] values) {

        //  BinarySearchST

        Stopwatch stopwatch = new Stopwatch();

        int distinct = 0, words = 0;
        BinarySearchST<Integer, Integer> binarySearchST = new BinarySearchST<>();

        for (int key : values) {
            words++;
            if (binarySearchST.contains(key)) {
                binarySearchST.put(key, binarySearchST.get(key) + 1);
            } else {
                binarySearchST.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        Integer max = -1;
        binarySearchST.put(max, 0);
        for (Integer num : binarySearchST.keys()) {
            if (binarySearchST.get(num) > binarySearchST.get(max))
                max = num;
        }

        double v = stopwatch.elapsedTime();
        StdOut.println("========= BinarySearchST ==========");
        StdOut.println(max + " " + binarySearchST.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("usetime    = " + v);

        // cache BinarySearchST

        stopwatch = new Stopwatch();

        distinct = 0;
        words = 0;
        CacheBinaryST<Integer, Integer> cacheBinarySt = new CacheBinaryST<>();
        for (int key : values) {
            words++;
            if (cacheBinarySt.contains(key)) {
                cacheBinarySt.put(key, cacheBinarySt.get(key) + 1);
            } else {
                cacheBinarySt.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        max = -1;
        cacheBinarySt.put(max, 0);
        for (Integer num : cacheBinarySt.keys()) {
            if (cacheBinarySt.get(num) > cacheBinarySt.get(max))
                max = num;
        }

        v = stopwatch.elapsedTime();
        StdOut.println("========= CacheBinaryST ==========");
        StdOut.println(max + " " + binarySearchST.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("usetime    = " + v);

        stopwatch = new Stopwatch();

        distinct = 0;
        words = 0;
        SequentialSearchST<Integer, Integer> sequentialSearchST = new SequentialSearchST();
        for (int key : values) {
            words++;
            if (sequentialSearchST.contains(key)) {
                sequentialSearchST.put(key, sequentialSearchST.get(key) + 1);
            } else {
                sequentialSearchST.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        max = -1;
        sequentialSearchST.put(max, 0);
        for (Integer num : sequentialSearchST.keys()) {
            if (sequentialSearchST.get(num) > sequentialSearchST.get(max))
                max = num;
        }

        v = stopwatch.elapsedTime();
        StdOut.println("========= SequentialSearchST ==========");
        StdOut.println(max + " " + binarySearchST.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("usetime    = " + v);

        stopwatch = new Stopwatch();

        distinct = 0;
        words = 0;
        CacheSequentialST<Integer, Integer> cacheSequentialST = new CacheSequentialST();
        for (int key : values) {
            words++;
            if (cacheSequentialST.contains(key)) {
                cacheSequentialST.put(key, cacheSequentialST.get(key) + 1);
            } else {
                cacheSequentialST.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        max = -1;
        cacheSequentialST.put(max, 0);
        for (Integer num : cacheSequentialST.keys()) {
            if (cacheSequentialST.get(num) > cacheSequentialST.get(max))
                max = num;
        }

        v = stopwatch.elapsedTime();
        StdOut.println("========= CacheSequentialST ==========");
        StdOut.println(max + " " + binarySearchST.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("usetime    = " + v);

    }

}
