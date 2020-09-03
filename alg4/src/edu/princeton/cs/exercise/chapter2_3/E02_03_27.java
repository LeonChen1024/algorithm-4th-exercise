package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.QuickX;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.3.27 Ignore small subarrays. Run experiments to compare the following strategy for dealing with
 * small subarrays with the approach described in Exercise 2.3.25: Simply ignore the small subarrays
 * in quicksort, then run a single insertion sort after the quicksort completes. Note : You may be
 * able to estimate the size of your computer’s cache memory with these experiments, as the
 * performance of this method is likely to degrade when the array does not fit in the cache.
 *
 * <p>忽略小的子数组. 运行试验来比较下面的策略和练习2.3.25中对于解决小子数组的情况: 直接忽略快排中小的子数组,<br>
 * 然后在快排结束后直接运行一次插入排序. 注意:在这些试验中你可以忽略你电脑的缓存大小,这种方法的性能在数组<br>
 * 和缓存不匹配的时候会降级.
 *
 * @author LeonChen
 * @since 8/26/20
 */
class E02_03_27 {

  private static int M = 8;
  /**
   * <code>
   * n is 1000 , cutoff 0.006 seconds , ignore subarray 0.002 seconds
   * n is 2000 , cutoff 0.001 seconds , ignore subarray 0.001 seconds
   * n is 4000 , cutoff 0.001 seconds , ignore subarray 0.003 seconds
   * n is 8000 , cutoff 0.004 seconds , ignore subarray 0.009 seconds
   * n is 16000 , cutoff 0.016 seconds , ignore subarray 0.014 seconds
   * n is 32000 , cutoff 0.025 seconds , ignore subarray 0.013 seconds
   * n is 64000 , cutoff 0.028 seconds , ignore subarray 0.027 seconds
   * n is 128000 , cutoff 0.021 seconds , ignore subarray 0.073 seconds
   * n is 256000 , cutoff 0.064 seconds , ignore subarray 0.177 seconds
   * n is 512000 , cutoff 0.125 seconds , ignore subarray 0.312 seconds
   * n is 1024000 , cutoff 0.226 seconds , ignore subarray 0.445 seconds
   * </code>
   */
  public static void main(String[] args) {

    for (int n = 1000; n < 2000000; n = n * 2) {
      Comparable[] a = new Comparable[n];

      for (int i = 0; i < n; i++) {
        a[i] = StdRandom.uniform(n);
      }
      Comparable[] b = new Comparable[n];
      System.arraycopy(a, 0, b, 0, a.length);

      Stopwatch timer = new Stopwatch();
      QuickX.sort(a);
      double cutoffTime = timer.elapsedTime();

      timer = new Stopwatch();
      quicksortIgnoreSubarray(b);
      Insertion.sort(b);
      double ignoreSubTime = timer.elapsedTime();

      StdOut.println(
          "n is "
              + n
              + " , cutoff "
              + cutoffTime
              + " seconds , ignore subarray "
              + ignoreSubTime
              + " seconds ");
    }
  }

  private static void quicksortIgnoreSubarray(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
    assert isSorted(a);
  }

  public static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    if (hi - lo <= M) {
      return;
    }

    int j = partition(a, lo, hi);
    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
    //    assert isSorted(a, lo, hi);
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo, j = hi + 1;
    Comparable v = a[lo];
    while (true) {
      while (less(a[++i], v)) if (i == hi) break;
      while (less(v, a[--j])) if (j == lo) break;
      if (i >= j) break;
      exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
  }

  /** ************************** Helper sorting functions **************************** */

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    if (v == w) return false; // optimization when reference equal
    return v.compareTo(w) < 0;
  }

  // does v == w ?
  private static boolean eq(Comparable v, Comparable w) {
    if (v == w) return true; // optimization when reference equal
    return v.compareTo(w) == 0;
  }

  // exchange a[i] and a[j]
  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  /**
   * ************************************************************************* Check if array is
   * sorted - useful for debugging.
   * *************************************************************************
   */
  private static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  // print array to standard output
  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }
}
