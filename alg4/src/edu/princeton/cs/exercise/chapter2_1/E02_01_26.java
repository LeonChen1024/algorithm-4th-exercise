package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.26 Primitive types. Develop a version of insertion sort that sorts arrays of int values and
 * compare its performance with the implementation given in the text (which sorts Integer values and
 * implicitly uses autoboxing and auto-unboxing to convert).
 *
 * <p>原始类型.开发一个插入排序的版本可以排序 int 类型的值并且对比它和正文中实现的性能(排序 Integer 类型并且隐式的<br>
 * 的使用了自动装箱和自动拆箱来转换)
 *
 * @author LeonChen
 * @since 5/25/20
 */
class E02_01_26 {

  /**
   * the sort used about 27seconds <br>
   * the sort with sentinel used about 2seconds<br>
   * 可以看到明确的类型还是带来了大量的效率的提高.
   *
   * @param args
   */
  public static void main(String[] args) {

    int size = 100000;
    int trial = 3;
    int avgTime = 0;

    Comparable[] a = new Comparable[size];
    for (int j = 0; j < trial; j++) {
      for (int i = 0; i < size; i++) {
        a[i] = StdRandom.uniform(size);
      }
      Stopwatch timer = new Stopwatch();
      Insertion.sort(a);
      avgTime += timer.elapsedTime();
    }
    StdOut.println(" the sort used about " + avgTime / 3 + "seconds");

    avgTime = 0;
    trial = 3;

    int[] b = new int[size];
    for (int j = 0; j < trial; j++) {
      for (int i = 0; i < size; i++) {
        b[i] = StdRandom.uniform(size);
      }
      Stopwatch timer = new Stopwatch();
      insertionSortPrimitive(b);
      avgTime += timer.elapsedTime();
    }
    StdOut.println(" the sort with sentinel used about " + avgTime / 3 + "seconds");
  }

  private static void insertionSortPrimitive(int[] a) {
    // find minimal num
    int minIndex = 0;
    for (int i = 1; i < a.length; i++) {
      if (a[i] < (a[minIndex])) {
        minIndex = i;
      }
    }

    exch(a, minIndex, 0);

    int n = a.length;
    for (int i = 1; i < n; i++) {
      for (int j = i; less(a[j], a[j - 1]); j--) {
        exch(a, j, j - 1);
      }
      assert isSorted(a, 0, i);
    }
    assert isSorted(a);
  }

  private static boolean isSorted(int[] a) {
    return isSorted(a, 0, a.length);
  }

  // exchange a[i] and a[j]
  private static void exch(int[] a, int i, int j) {
    int swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  // is the array a[lo..hi) sorted
  private static boolean isSorted(int[] a, int lo, int hi) {
    for (int i = lo + 1; i < hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  // is v < w ?
  private static boolean less(int v, int w) {
    return v < w;
  }
}
