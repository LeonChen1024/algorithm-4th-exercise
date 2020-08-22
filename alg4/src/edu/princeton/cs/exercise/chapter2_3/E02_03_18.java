package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.3.18 Median-of-3 partitioning. Add median-of-3 partitioning to quicksort, as described in the
 * text (see page 296). Run doubling tests to determine the effectiveness of the change.
 *
 * <p>使用3个数中的中位数进行分割.添加选取3个数中的中位数分割到快排中, 像正文中描述的那样(见296页).<br>
 * 运行倍率测试来判断这个改变的效果.
 *
 * @author LeonChen
 * @since 8/17/20
 */
public class E02_03_18 {

  /**
   * <code>
   * n is 1000 , original quicksort use 0.003 seconds , Median-of-3 quicksort use 0.002 seconds
   * n is 2000 , original quicksort use 0.001 seconds , Median-of-3 quicksort use 0.001 seconds
   * n is 4000 , original quicksort use 0.002 seconds , Median-of-3 quicksort use 0.002 seconds
   * n is 8000 , original quicksort use 0.005 seconds , Median-of-3 quicksort use 0.004 seconds
   * n is 16000 , original quicksort use 0.005 seconds , Median-of-3 quicksort use 0.011 seconds
   * n is 32000 , original quicksort use 0.009 seconds , Median-of-3 quicksort use 0.008 seconds
   * n is 64000 , original quicksort use 0.03 seconds , Median-of-3 quicksort use 0.015 seconds
   * n is 128000 , original quicksort use 0.046 seconds , Median-of-3 quicksort use 0.028 seconds
   * n is 256000 , original quicksort use 0.102 seconds , Median-of-3 quicksort use 0.073 seconds
   * n is 512000 , original quicksort use 0.178 seconds , Median-of-3 quicksort use 0.209 seconds
   * n is 1024000 , original quicksort use 0.458 seconds , Median-of-3 quicksort use 0.363 seconds
   * </code>
   *
   * @param args
   */
  public static void main(String[] args) {

    for (int n = 1000; n < 2000000; n = n * 2) {
      Comparable[] a = new Comparable[n];

      for (int i = 0; i < n; i++) {
        a[i] = StdRandom.uniform(1000);
      }
      Comparable[] b = new Comparable[n];
      System.arraycopy(a, 0, b, 0, a.length);

      Stopwatch timer = new Stopwatch();
      Quick.sort(a);
      double originTime = timer.elapsedTime();

      Stopwatch ntimer = new Stopwatch();
      quicksortMedianof3(b);
      double newTime = ntimer.elapsedTime();

      StdOut.println(
          "n is "
              + n
              + " , original quicksort use "
              + originTime
              + " seconds , Median-of-3 quicksort use "
              + newTime
              + " seconds");
    }
  }

  public static void quicksortMedianof3(Comparable[] b) {
    StdRandom.shuffle(b);

    sort(b, 0, b.length - 1);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    if (hi == lo + 1) {
      if (less(a[hi], a[lo])) {
        exch(a, lo, hi);
      }
      return;
    }

    int j = partition(a, lo, hi);
    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
    assert isSorted(a, lo, hi);
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;

    if (less(a[lo + 1], a[lo])) exch(a, lo + 1, lo);
    if (less(a[lo + 2], a[lo])) exch(a, lo + 2, lo);
    if (less(a[lo + 2], a[lo + 1])) exch(a, lo + 1, lo + 2);
    exch(a, lo, lo + 1); // 中位数放最左侧
    exch(a, hi, lo + 2); // 较大的值放最右侧作为哨兵

    Comparable v = a[lo];
    while (true) {

      // find item on lo to swap
      while (less(a[++i], v)) {}

      // find item on hi to swap
      while (less(v, a[--j])) {}

      // check if pointers cross
      if (i >= j) break;

      exch(a, i, j);
    }

    // put partitioning item v at a[j]
    exch(a, lo, j);

    // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    return j;
  }

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
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
