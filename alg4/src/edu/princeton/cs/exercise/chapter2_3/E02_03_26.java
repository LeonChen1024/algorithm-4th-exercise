package edu.princeton.cs.exercise.chapter2_3;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.26 Subarray sizes. Write a program that plots a histogram of the subarray sizes left for
 * insertion sort when you run quicksort for an array of size N with a cutoff for subarrays of size
 * less than M. Run your program for M=10, 20, and 50 and N = 105.
 *
 * <p>子数组大小. 编写一个程序绘制一个大小的N的数组对子数组大小<M的子数组使用插入排序的情况下使用插入排序的<br>
 * 子数组大小直方图.对 M=10,20,和50 并且 N = 10^5 的条件下运行你的程序.
 *
 * @author LeonChen
 * @since 8/25/20
 */
class E02_03_26 {

  private static int M;
  private static HashMap<Integer, Integer> sizeMap = new HashMap<>();
  /**
   * <code>
   * </code>
   */
  public static void main(String[] args) {
    StdDraw.setCanvasSize(900, 900);
    StdDraw.setXscale(0, 51);
    StdDraw.setYscale(0, 1500);
    StdDraw.setPenRadius(0.01);
    Color[] pens = new Color[] {Color.black, Color.blue, Color.green, Color.red};
    double[] hws = new double[] {0.4, 0.2, 0.05};
    int[] Ms =
        new int[] {
          10, 20, 50,
        };
    int N = (int) Math.pow(10, 5);
    for (int i = 0; i < Ms.length; i++) {
      M = Ms[i];
      sizeMap.clear();

      StdDraw.setPenColor(pens[i]);
      Integer[] a = new Integer[N];
      for (int j = 0; j < N; j++) {
        a[j] = StdRandom.uniform(N);
      }
      sort(a);
      for (Map.Entry<Integer, Integer> entry : sizeMap.entrySet()) {
        StdDraw.filledRectangle(entry.getKey(), entry.getValue() / 2, hws[i], entry.getValue() / 2);
      }
    }
  }

  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
    assert isSorted(a);
  }

  public static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    if (hi - lo <= M) {
      int size = hi - lo;
      if (sizeMap.containsKey(size)) {
        sizeMap.put(size, sizeMap.get(size) + 1);
      } else {
        sizeMap.put(size, 1);
      }
      Insertion.sort(a, lo, hi);
      assert isSorted(a, lo, hi);
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
