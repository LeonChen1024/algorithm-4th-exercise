package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 3.1.24 Interpolation search. Suppose that arithmetic operations are allowed on keys
 * (for example, they may be Double or Integer values). Write a version of binary search
 * that mimics the process of looking near the beginning of a dictionary when the word
 * begins with a letter near the beginning of the alphabet. Specifically, if k_x is the key
 * value sought, k_lo is the key value of the first key in the table, and k_hi is the key value
 * of the last key in the table, look first ⎣(k_x - k_lo)/(k_hi - k_lo)⎦-way through the table,
 * not halfway. Test your implementation against BinarySearchST for FrequencyCounter using
 * SearchCompare.
 * <p>
 * 插值搜索.假设键允许对数级操作(比如,他们可能是Double 或者 Integer的值).编写一个版本的二分搜索模拟方法当目标词
 * 是使用接近字母表开始的字母的时候从字典的起始部分开始查找.其中, 如果 k_x 是查找的键,k_lo 是表中的第一个键得值,
 * k_hi 是表中最后一个键的值,先查找表中  ⎣(k_x - k_lo)/(k_hi - k_lo)⎦-way 的值,而不是先查找中间值.使用
 * SearchCompare 的FrequencyCounter测试你的实现和 BinarySearchST的对比.
 *
 * @author LeonChen
 * @since 2/17/21
 */
class E3_1_24 {

    public static class InterpolationST
            extends BinarySearchST<Integer, Integer> {
        public InterpolationST() {
            super(INIT_CAPACITY);
            keys = new Integer[INIT_CAPACITY];
            vals = new Integer[INIT_CAPACITY];

        }

        // resize the underlying arrays
        @Override
        protected void resize(int capacity) {
            assert capacity >= n;
            Integer[] tempk = (Integer[]) new Integer[capacity];
            Integer[] tempv = (Integer[]) new Integer[capacity];
            for (int i = 0; i < n; i++) {
                tempk[i] = keys[i];
                tempv[i] = vals[i];
            }
            vals = tempv;
            keys = tempk;
        }

        @Override
        public int rank(Integer key) {
            if (key == null)
                throw new IllegalArgumentException("argument to rank() is null");

            int lo = 0, hi = n - 1;

            int mid;
            while (lo <= hi) {
                if (keys[hi] - keys[lo] == 0) {
                    //Low and high have the same value - avoid division by zero
                    mid = lo;
                } else {
                    //Interpolation search
                    mid = lo + ((key - keys[lo]) * (hi - lo) / (keys[hi] - keys[lo]));
                }

                if (mid > hi) {
                    mid = hi;
                }

                compares++;
                int cmp = key.compareTo(keys[mid]);
                if (cmp < 0) hi = mid - 1;
                else if (cmp > 0) lo = mid + 1;
                else return mid;
            }
            return lo;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        frequencyCounter(new int[]{4, 3, 5, 7, 2, 7, 9, 7, 6, 5, 3, 2, 67, 75, 6, 5, 74, 4, 3, 4, 52});
    }

    private static void frequencyCounter(int[] values) {

        Stopwatch stopwatch = new Stopwatch();

        int distinct = 0, words = 0;
        BinarySearchST<Integer, Integer> binarySearchST = new BinarySearchST<Integer, Integer>();

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

        stopwatch = new Stopwatch();

        distinct = 0;
        words = 0;
        InterpolationST interpolationST = new InterpolationST();
        for (int key : values) {
            words++;
            if (interpolationST.contains(key)) {
                interpolationST.put(key, interpolationST.get(key) + 1);
            } else {
                interpolationST.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        max = -1;
        interpolationST.put(max, 0);
        for (Integer num : interpolationST.keys()) {
            if (interpolationST.get(num) > interpolationST.get(max))
                max = num;
        }

        v = stopwatch.elapsedTime();
        StdOut.println("========= BinarySearchST ==========");
        StdOut.println(max + " " + binarySearchST.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("usetime    = " + v);

    }

}
