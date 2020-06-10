package edu.princeton.cs.exercise.chapter2_1;

import java.util.Comparator;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.24 Insertion sort with sentinel. Develop an implementation of insertion sort that eliminates
 * the j>0 test in the inner loop by first putting the smallest item into position. Use SortCompare
 * to evaluate the effectiveness of doing so. Note : It is often possible to avoid an
 * index-out-of-bounds test in this way—the element that enables the test to be eliminated is known
 * as a sentinel.
 *
 * <p>带有哨兵的插入排序.开发一个插入排序的实现通过先将最小的项放到最小的位置来消除内部循环的 j>0 的判断.<br>
 * 使用 SortCompare 来估算这么做效率的变化.注意:通过这种方式来避免索引越界测试是很常见的,这个可以消除边界测试的<br>
 * 元素叫做哨兵.
 *
 * @author LeonChen
 * @since 5/21/20
 */
class E02_01_24 {

  /**
   * the sort used about 20seconds <br>
   * the sort with sentinel used about 15seconds
   *
   * <p>扩展:除了这种方式,还有一种方式是直接增加一个最小值在开始,然后排序剩余的元素.
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
        a[i] = StdRandom.uniform();
      }
      Stopwatch timer = new Stopwatch();
      Insertion.sort(a);
      avgTime += timer.elapsedTime();
    }
    StdOut.println(" the sort used about " + avgTime / 3 + "seconds");

    avgTime = 0;
    trial = 3;

    for (int j = 0; j < trial; j++) {
      for (int i = 0; i < size; i++) {
        a[i] = StdRandom.uniform();
      }
      Stopwatch timer = new Stopwatch();
      insertionSortSentinel(a);
      avgTime += timer.elapsedTime();
    }
    StdOut.println(" the sort with sentinel used about " + avgTime / 3 + "seconds");
  }

  private static void insertionSortSentinel(Comparable[] a) {
    // find minimal num
    int minIndex = 0;
    for (int i = 1; i < a.length; i++) {
      if (a[i].compareTo(a[minIndex]) < 0) {
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

  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length);
  }

  // is the array a[lo..hi) sorted
  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i < hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }
  // exchange a[i] and a[j]
  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  // is the array a[lo..hi) sorted
  private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
    for (int i = lo + 1; i < hi; i++) if (less(a[i], a[i - 1], comparator)) return false;
    return true;
  }

  // is v < w ?
  private static boolean less(Object v, Object w, Comparator comparator) {
    return comparator.compare(v, w) < 0;
  }
}
