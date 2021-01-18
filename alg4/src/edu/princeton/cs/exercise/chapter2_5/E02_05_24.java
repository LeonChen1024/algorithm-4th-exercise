package edu.princeton.cs.exercise.chapter2_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * 2.5.24 Stable priority queue. Develop a stable priority-queue implementation (which
 * returns duplicate keys in the same order in which they were inserted).
 * <p>
 * 稳定的优先队列.开发一个稳定的优先队列实现(会将重复的 key 按照原始顺序排列)
 *
 * @author LeonChen
 * @since 1/6/20
 */
class E02_05_24 {

    /**
     * 新增一个原始顺序列表,在比较大小的同时比较一下原始顺序即可.以原始最大优先队列为例,基础结构可以继承原来
     * 的 MaxPQ 只需要添加索引的跟踪即可
     */
    public static void main(String[] args) {
    }

    public static class MaxPQStable<Key> extends edu.princeton.cs.algs4.MaxPQ<Key> implements Iterable<Key> {

        private Integer[] indexs;
        private int curIndex;

        /**
         * Initializes an empty priority queue with the given initial capacity.
         *
         * @param initCapacity the initial capacity of this priority queue
         */
        public MaxPQStable(int initCapacity) {
            pq = (Key[]) new Object[initCapacity + 1];
            n = 0;

            indexs = new Integer[initCapacity + 1];
            curIndex = 0;
        }

        /**
         * Initializes an empty priority queue.
         */
        public MaxPQStable() {
            this(1);
        }

        /**
         * Initializes an empty priority queue with the given initial capacity, using the given
         * comparator.
         *
         * @param initCapacity the initial capacity of this priority queue
         * @param comparator   the order in which to compare the keys
         */
        public MaxPQStable(int initCapacity, Comparator<Key> comparator) {
            this.comparator = comparator;
            pq = (Key[]) new Object[initCapacity + 1];
            n = 0;

            indexs = new Integer[initCapacity + 1];
            curIndex = 0;
        }

        /**
         * Initializes an empty priority queue using the given comparator.
         *
         * @param comparator the order in which to compare the keys
         */
        public MaxPQStable(Comparator<Key> comparator) {
            this(1, comparator);
        }

        /**
         * Initializes a priority queue from the array of keys. Takes time proportional to the number of
         * keys, using sink-based heap construction.
         *
         * @param keys the array of keys
         */
        public MaxPQStable(Key[] keys) {
            n = keys.length;
            curIndex = 0;
            pq = (Key[]) new Object[keys.length + 1];
            indexs = new Integer[keys.length + 1];
            for (int i = 0; i < n; i++) {
                pq[i + 1] = keys[i];
                indexs[i + 1] = curIndex++;
            }
            for (int k = n / 2; k >= 1; k--) {
                sink(k);
            }
            assert isMaxHeap();
        }


        // helper function to double the size of the heap array
        @Override
        protected void resize(int capacity) {
            assert capacity > n;
            Key[] temp = (Key[]) new Object[capacity];
            Integer[] tempIndexs = new Integer[capacity];
            for (int i = 1; i <= n; i++) {
                temp[i] = pq[i];
                tempIndexs[i] = indexs[i];
            }
            pq = temp;
            indexs = tempIndexs;
        }

        /**
         * Adds a new key to this priority queue.
         *
         * @param x the new key to add to this priority queue
         */
        @Override
        public void insert(Key x) {

            // double size of array if necessary
            if (n == pq.length - 1) {
                resize(2 * pq.length);
            }

            // add x, and percolate it up to maintain heap invariant
            pq[++n] = x;
            indexs[++n] = curIndex++;
            swim(n);
            assert isMaxHeap();
        }

        /**
         * Removes and returns a largest key on this priority queue.
         *
         * @return a largest key on this priority queue
         * @throws NoSuchElementException if this priority queue is empty
         */
        @Override
        public Key delMax() {
            if (isEmpty()) {
                throw new NoSuchElementException("Priority queue underflow");
            }
            Key max = pq[1];
            exch(1, n--);
            sink(1);
            pq[n + 1] = null; // to avoid loitering and help with garbage collection
            indexs[n + 1] = null;
            if ((n > 0) && (n == (pq.length - 1) / 4)) {
                resize(pq.length / 2);
            }
            assert isMaxHeap();
            return max;
        }


        /**
         * ************************************************************************* Helper functions for
         * compares and swaps. *************************************************************************
         */
        @Override
        protected boolean less(int i, int j) {
            if (comparator == null) {
                if (((Comparable<Key>) pq[i]).compareTo(pq[j]) == 0) {
                    return indexs[i].compareTo(indexs[j]) < 0;
                } else {
                    return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
                }
            } else {
                if (comparator.compare(pq[i], pq[j]) == 0) {
                    return indexs[i].compareTo(indexs[j]) < 0;
                } else {
                    return comparator.compare(pq[i], pq[j]) < 0;
                }
            }
        }

        @Override
        protected void exch(int i, int j) {
            Key swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
            Integer temp = indexs[i];
            indexs[i] = indexs[j];
            indexs[j] = temp;
        }


        /**
         * Unit tests the {@code MaxPQ} data type.
         *
         * @param args the command-line arguments
         */
        public static void main(String[] args) {
            edu.princeton.cs.algs4.MaxPQ<String> pq = new edu.princeton.cs.algs4.MaxPQ<String>();
            while (!StdIn.isEmpty()) {
                String item = StdIn.readString();
                if (!item.equals("-")) {
                    pq.insert(item);
                } else if (!pq.isEmpty()) {
                    StdOut.print(pq.delMax() + " ");
                }
            }
            StdOut.println("(" + pq.size() + " left on pq)");
        }
    }


}
