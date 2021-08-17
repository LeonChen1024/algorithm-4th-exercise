package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.4.18 Add a constructor to SeparateChainingHashST that gives the client the ability
 * to specify the average number of probes to be tolerated for searches. Use array resizing
 * to keep the average list size less than the specified value, and use the technique described
 * on page 478 to ensure that the modulus for hash() is prime.
 * <p>
 * 添加一个构造器到 SeparateChainingHashST 中使得给定客户端有能力来制定可以允许的搜索线性探查的平均数量.
 * 使用数组扩容来保证平均列表大小小于指定数字,并且使用 478 页的技术来确保使用取模来 hash() 是质数
 *
 * @author LeonChen
 * @since 8/4/21
 */
class E3_4_18 {

    /**
     * @formatter:off
     *
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
        SeparateChainingHashTableResize<Integer, Integer> separateChainingHashTableResize =
                new SeparateChainingHashTableResize<>(5, 2);

        for (int i = 0; i < 20; i++) {
            separateChainingHashTableResize.put(i, i);
        }

        StdOut.println("Expected: Resize - doubling hash table size 2x");

        for (int i = 0; i < 10; i++) {
            separateChainingHashTableResize.delete(i);
        }

        StdOut.println("Expected: Resize - shrinking hash table size");

        for (int i = 10; i < 15; i++) {
            separateChainingHashTableResize.delete(i);
        }

        StdOut.println("Expected: Resize - shrinking hash table size");
    }

    private static class SeparateChainingHashTableResize<Key, Value> extends
            SeparateChainingHashST<Key, Value> {

        private int averageListSize;

        //The largest prime <= 2^i for i = 1 to 31
        //Used to distribute keys uniformly in the hash table after resizes
        //PRIMES[n] = 2^k - Ak where k is the power of 2 and Ak is the value to subtract to reach the previous prime number
        private final int[] PRIMES = {
                1, 1, 3, 7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
                32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
                8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
                536870909, 1073741789, 2147483647
        };

        //The lg of the hash table size
        //Used in combination with PRIMES[] to distribute keys uniformly in the hash function after resizes
        private int lgM;

        public SeparateChainingHashTableResize(int initialSize, int averageListSize) {
            super(initialSize);

            this.averageListSize = averageListSize;

            lgM = (int) (Math.log(m) / Math.log(2));
        }

        protected int hash(Key key) {
            int hash = key.hashCode() & 0x7fffffff;

            if (lgM < 26) {
                hash = hash % PRIMES[lgM + 5];
            }

            return hash % m;
        }

        public void resize(int newSize) {
            SeparateChainingHashST<Key, Value> separateChainingHashTableTemp =
                    new SeparateChainingHashST<>(newSize);

            for (Key key : keys()) {
                separateChainingHashTableTemp.put(key, get(key));
            }

            st = separateChainingHashTableTemp.st;
            m = separateChainingHashTableTemp.m;
            n = separateChainingHashTableTemp.size();
        }

        public void put(Key key, Value value) {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            if (value == null) {
                delete(key);
                return;
            }

            int hashIndex = hash(key);
            int currentSize = st[hashIndex].size();
            st[hashIndex].put(key, value);

            if (currentSize < st[hashIndex].size()) {
                n++;
            }

            if (getLoadFactor() >= averageListSize) {
                StdOut.println("Resize - doubling hash table size");

                resize(size() * 2);
                lgM++;
            }
        }

        private int getLoadFactor() {
            return n / m;
        }

        public void delete(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to delete() cannot be null");
            }

            if (isEmpty() || !contains(key)) {
                return;
            }

            st[hash(key)].delete(key);
            n--;

            if (size() > 1 && getLoadFactor() <= averageListSize / (double) 4) {
                StdOut.println("Resize - shrinking hash table size");

                resize(size() / 2);
                lgM--;
            }
        }
    }


}
