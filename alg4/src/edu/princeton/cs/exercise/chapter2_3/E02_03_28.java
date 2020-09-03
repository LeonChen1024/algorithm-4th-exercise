package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.28 Recursion depth. Run empirical studies to determine the average recursive depth used by
 * quicksort with cutoff for arrays of size M, when sorting arrays of N distinct elements, for M=10,
 * 20, and 50 and N = 10^3, 10^4, 10^5, and 10^6.
 *
 * <p>递归深度.根据实践经验判断拥有N个不同元素的数组对一个大小为M的子数组进行插入排序的快排的平均递归深度是多少,其中<br>
 * M=10,20和50并且 N = 10^3, 10^4, 10^5, 和 10^6.
 *
 * @author LeonChen
 * @since 8/26/20
 */
class E02_03_28 {

  private static int M;
  private static int depth;

  /**
   * <code>
   *     超过内存调用深度
   * </code>
   */
  public static void main(String[] args) {

    int[] ms = new int[] {10, 20, 50};
    int[] ns =
        new int[] {
          (int) Math.pow(10, 3), (int) Math.pow(10, 4), (int) Math.pow(10, 5), (int) Math.pow(10, 6)
        };
    for (int i = 0; i < ns.length; i++) {
      int n = ns[i];
      for (int j = 0; j < ms.length; j++) {
        M = ms[j];
        depth = 0;
        Comparable[] a = new Comparable[n];

        for (int k = 0; k < n; k++) {
          a[k] = StdRandom.uniform(n);
        }

        cutoffSort(a);

        StdOut.println("n is " + n + " , cutoff M is " + M + ", recursion depth is " + depth);
      }
    }
  }

  private static void cutoffSort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
    assert isSorted(a);
  }

  public static void sort(Comparable[] a, int lo, int hi) {
    depth++;
    if (hi <= lo) return;
    if (hi - lo <= M) {
      Insertion.sort(a);
      return;
    }

    int j = partition(a, lo, hi);
    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
    assert isSorted(a, lo, hi);
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
