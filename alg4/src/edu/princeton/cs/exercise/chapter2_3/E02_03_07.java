package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.7 Find the expected number of subarrays of size 0, 1, and 2 when quicksort is used to sort an
 * array of N items with distinct keys. If you are mathematically inclined, do the math; if not, run
 * some experiments to develop hypotheses.
 *
 * <p>计算出对一个包含N个不同key的项的数组进行快速排序的情况下分割时子数组的大小是 0,1,2的数量. 如果你偏向于<br>
 * 使用数学,就用数学的方式;如果不是,就进行一些试验来验证这个假说.
 *
 * @author LeonChen
 * @since 8/3/20
 */
class E02_03_07 {

  private static int subArraysOfSize0;
  private static int subArraysOfSize1;
  private static int subArraysOfSize2;

  /**
   * 使用试验的方式可以得出 <code>
   * when N = 200 , subArraysOfSize0 = 63 , subArraysOfSize1 = 69 , subArraysOfSize2 = 32
   * when N = 400 , subArraysOfSize0 = 127 , subArraysOfSize1 = 137 , subArraysOfSize2 = 75
   * when N = 800 , subArraysOfSize0 = 233 , subArraysOfSize1 = 284 , subArraysOfSize2 = 129
   * when N = 1600 , subArraysOfSize0 = 471 , subArraysOfSize1 = 565 , subArraysOfSize2 = 266
   * when N = 3200 , subArraysOfSize0 = 941 , subArraysOfSize1 = 1130 , subArraysOfSize2 = 527
   * when N = 6400 , subArraysOfSize0 = 1789 , subArraysOfSize1 = 2306 , subArraysOfSize2 = 1036
   * when N = 12800 , subArraysOfSize0 = 3623 , subArraysOfSize1 = 4589 , subArraysOfSize2 = 2080
   * when N = 25600 , subArraysOfSize0 = 7355 , subArraysOfSize1 = 9123 , subArraysOfSize2 = 4105
   * when N = 51200 , subArraysOfSize0 = 14675 , subArraysOfSize1 = 18263 , subArraysOfSize2 = 8326
   *
   * Subarray size 0: ~1/3 N
   * Subarray size 1: ~1/3 N
   * Subarray size 2: ~1/6 N
   * </code>
   *
   * @param args
   */
  public static void main(String[] args) {
    for (int i = 200; i < 100000; i = i * 2) {

      Comparable[] a = new Comparable[i];
      for (int j = 0; j < i; j++) {
        a[j] = StdRandom.uniform(i);
      }
      subArraysOfSize0 = 0;
      subArraysOfSize1 = 0;
      subArraysOfSize2 = 0;
      quickSort(a);
      StdOut.println(
          "when N = "
              + i
              + " , subArraysOfSize0 = "
              + subArraysOfSize0
              + " , subArraysOfSize1 = "
              + subArraysOfSize1
              + " , subArraysOfSize2 = "
              + subArraysOfSize2);

      //      show(a);
    }
  }

  private static void quickSort(Comparable[] a) {
    StdRandom.shuffle(a);

    sort(a);
  }

  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
    assert isSorted(a);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    int j = partition(a, lo, hi);
    int leftSubArraySize = j - lo;
    int rightSubArraySize = hi - j;
    checkSize(leftSubArraySize);
    checkSize(rightSubArraySize);

    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
    assert isSorted(a, lo, hi);
  }

  private static void checkSize(int subArraySize) {
    if (subArraySize == 0) {
      subArraysOfSize0++;
    } else if (subArraySize == 1) {
      subArraysOfSize1++;
    } else if (subArraySize == 2) {
      subArraysOfSize2++;
    }
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    Comparable v = a[lo];
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

  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length - 1);
  }

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }
}
