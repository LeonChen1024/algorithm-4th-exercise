package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.25 Insertion sort without exchanges. Develop an implementation of insertion sort that moves
 * larger elements to the right one position with one array access per entry, rather than using
 * exch(). Use SortCompare to evaluate the effectiveness of doing so.
 *
 * <p>不使用交换的插入排序.开发一个插入排序的实现通过移动较大元素到右边一位只需要以此数组访问,而不是使用 exch()<br>
 * 使用 SortCompare 来评估这么做的效率.
 *
 * @author LeonChen
 * @since 5/25/20
 */
class E02_01_25 {

  /** @param args */
  public static void main(String[] args) {

    int size = 100000;
    int trial = 3;
    int avgTime = 0;

    Comparable[] a = new Comparable[size];
    for (int j = 0; j < trial; j++) {
      for (int i = 0; i < size; i++) {
        a[i] = StdRandom.uniform();
      }
      Stopwatch timer = new Stopwatch();
      insertionSortWithoutExch(a);
      avgTime += timer.elapsedTime();
    }
  }

  private static void insertionSortWithoutExch(Comparable[] a) {
    int n = a.length;
    for (int i = 1; i < n; i++) {
      Comparable cur = a[i];
      int j;
      for (j = i; j > 0 && less(cur, a[j - 1]); j--) {
        a[j] = a[j - 1];
      }
      a[j] = cur;
      assert isSorted(a, 0, i);
    }
    assert isSorted(a);
  }

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length);
  }
  // is the array a[lo..hi) sorted
  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i < hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }
}
