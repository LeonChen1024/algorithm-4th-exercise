package edu.princeton.cs.exercise.chapter2_5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.5.23 Sampling for selection. Investigate the idea of using sampling to improve selection.
 * Hint: Using the median may not always be helpful.
 * <p>
 * 采样选择排序.调查使用采样来优化选择排序的想法.提示:使用中间值不总是有效的
 *
 * @author LeonChen
 * @since 1/5/21
 */
class E02_05_23 {

    /**
     * 当我们需要的是找到第 k 大或者第 k 小的元素的时候,采样是会提高效率的,我们只需要每次都选择 k 为分割点即可
     */
    public static void main(String[] args) {
        int n = 1000;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        StdRandom.shuffle(a);

        int k = 60;
        int lo = 0;
        int hi = n - 1;
        while (true) {
            int partition = partition(a, lo, hi, k);
            if (partition == k) {
                break;
            } else if (partition > k) {
                hi = partition;
            } else {
                lo = partition;
            }
        }
        StdOut.println(a[k]);

    }

    private static int partition(Comparable[] a, int lo, int hi, int k) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[k];
        exch(a, k, lo);
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo) break; // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false; // optimization when reference equals
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
