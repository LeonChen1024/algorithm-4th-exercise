package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 3.4.28 Double hashing. Modify LinearProbingHashST to use a second hash function
 * to define the probe sequence. Specifically, replace (i + 1) % M (both occurrences) by
 * (i + k) % M where k is a nonzero key-dependent integer that is relatively prime to M.
 * Note : You may meet the last condition by assuming that M is prime. Give a trace of the
 * process of inserting the keys E A S Y Q U T I O N in that order into an initially empty
 * table of size M =11, using the hash functions described in the previous exercise. Give
 * the average number of probes for random search hit and search miss in this table.
 * <p>
 * 双重 hash.修改 LinearProbingHashST 来使用一个双重 hash 函数来定义探测序列.具体就是,使用(i + k) % M
 * 替换(i + 1) % M(所有使用的地方) 其中 k 是一个非零依赖于键的整数是与 M互质.
 * 注意: 你可能会碰到假设 M 是质数的情况.给出跟踪按序插入 E A S Y Q U T I O N 到初始为空 M=11 的表的
 * 过程,使用前面练习中提到的 hash 函数.给出随机搜索命中和搜索丢失的平均探测数
 *
 * @author LeonChen
 * @since 8/12/21
 */
class E3_4_28 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {

        DoubleHashingHashTable<TestClass, Integer> doubleHashingHashTable = new DoubleHashingHashTable<>(7);
        for (int i = 0; i < 4; i++) {
            doubleHashingHashTable.put(new TestClass(i), i);
        }

        StdOut.println("Size: " + doubleHashingHashTable.size() + " Expected: 4");
        doubleHashingHashTable.delete(new TestClass(2));
        StdOut.println("Size: " + doubleHashingHashTable.size() + " Expected: 3");
        doubleHashingHashTable.delete(new TestClass(0));
        StdOut.println("Size: " + doubleHashingHashTable.size() + " Expected: 2");

        for (int i = 4; i < 100; i++) {
            doubleHashingHashTable.put(new TestClass(i), i);
        }

        doubleHashingHashTable.delete(new TestClass(3));
        doubleHashingHashTable.delete(new TestClass(4));
        doubleHashingHashTable.delete(new TestClass(5));
        StdOut.println("Put 3");
        doubleHashingHashTable.put(new TestClass(3), 3);
        doubleHashingHashTable.delete(new TestClass(6));
        doubleHashingHashTable.delete(new TestClass(7));
        StdOut.println("Size: " + doubleHashingHashTable.size() + " Expected: 94");
    }

    protected static class DoubleHashingHashTable<Key, Value> {

        protected int keysSize;
        protected int size;
        protected Key[] keys;
        protected Value[] values;

        int tombstoneItemCount;

        // The largest prime <= 2^i for i = 1 to 31
        // Used to distribute keys uniformly in the hash table after resizes
        // PRIMES[n] = 2^k - Ak where k is the power of 2 and Ak is the value to subtract to reach the previous prime number
        private final int[] PRIMES = {
                1, 1, 3, 7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
                32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
                8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
                536870909, 1073741789, 2147483647
        };

        // The lg of the hash table size
        // Used in combination with PRIMES[] to distribute keys uniformly in the hash function after resizes
        protected int lgM;

        DoubleHashingHashTable(int size) {
            this.size = size;
            keys = (Key[]) new Object[size];
            values = (Value[]) new Object[size];

            lgM = (int) (Math.log(size) / Math.log(2));
        }

        public int size() {
            return keysSize;
        }

        protected int hash(Key key) {
            int hash = key.hashCode() & 0x7fffffff;

            if (lgM < 26) {
                hash = hash % PRIMES[lgM + 5];
            }

            return hash % size;
        }

        protected int secondaryHash(Key key) {
            int hash2 = (key.hashCode() % size) & 0x7fffffff;
            hash2 = hash2 != 0 ? hash2 : size + 1;
            return hash2;
        }

        private double getLoadFactor() {
            return keysSize / (double) size;
        }

        protected void resize(int newSize) {
            StdOut.println("Deleting tombstone items");
            tombstoneItemCount = 0;

            DoubleHashingHashTable<Key, Value> tempHashTable = new DoubleHashingHashTable<>(newSize);

            for (int i = 0; i < size; i++) {
                if (values[i] != null) {
                    tempHashTable.put(keys[i], values[i]);
                }
            }

            keys = tempHashTable.keys;
            values = tempHashTable.values;
            size = tempHashTable.size;
        }

        public boolean contains(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to contains() cannot be null");
            }

            return get(key) != null;
        }

        public Value get(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to get() cannot be null");
            }

            for (int tableIndex = hash(key); keys[tableIndex] != null; tableIndex = (tableIndex + secondaryHash(key)) % size) {
                if (keys[tableIndex].equals(key)) {
                    return values[tableIndex];
                }
            }

            return null;
        }

        public void put(Key key, Value value) {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            if (value == null) {
                delete(key);
                return;
            }

            if (keysSize + tombstoneItemCount >= size / (double) 2 && size != PRIMES[PRIMES.length - 1]) {
                resize(PRIMES[lgM + 2]);
                lgM++;
            }

            int tableIndex;
            for (tableIndex = hash(key); keys[tableIndex] != null; tableIndex = (tableIndex + secondaryHash(key)) % size) {
                if (keys[tableIndex].equals(key)) {

                    if (values[tableIndex] == null) {
                        tombstoneItemCount--;
                        keysSize++;
                    }

                    values[tableIndex] = value;
                    return;
                }
            }

            keys[tableIndex] = key;
            values[tableIndex] = value;
            keysSize++;
        }

        public void delete(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to delete() cannot be null");
            }

            if (!contains(key)) {
                return;
            }

            int tableIndex = hash(key);
            while (!keys[tableIndex].equals(key)) {
                tableIndex = (tableIndex + secondaryHash(key)) % size;
            }

            keysSize--;

            values[tableIndex] = null;
            tombstoneItemCount++;

            if (keysSize <= size / (double) 8) {
                resize(PRIMES[lgM]);
                lgM--;
            }
        }

        public Iterable<Key> keys() {
            Queue<Key> keySet = new Queue<>();

            for (Object key : keys) {
                if (key != null) {
                    keySet.enqueue((Key) key);
                }
            }

            if (!keySet.isEmpty() && keySet.peek() instanceof Comparable) {
                Key[] keysToBeSorted = (Key[]) new Comparable[keySet.size()];
                for (int i = 0; i < keysToBeSorted.length; i++) {
                    keysToBeSorted[i] = keySet.dequeue();
                }

                Arrays.sort(keysToBeSorted);

                for (Key key : keysToBeSorted) {
                    keySet.enqueue(key);
                }
            }

            return keySet;
        }
    }

    // Test class to force collisions and the use of double hashing
    private static class TestClass {
        int key;

        TestClass(int key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object that) {
            if (!(that instanceof TestClass)) {
                return false;
            }
            return this.key == ((TestClass) that).key;
        }

        @Override
        public int hashCode() {
            return 4;
        }
    }


}
