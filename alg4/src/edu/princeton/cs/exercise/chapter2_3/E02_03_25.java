package edu.princeton.cs.exercise.chapter2_3;

import java.awt.*;
import java.util.HashMap;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.3.25 Cutoff to insertion sort. Implement quicksort with a cutoff to insertion sort for
 * subarrays with less than M elements, and empirically determine the value of M for which quicksort
 * runs fastest in your computing environment to sort random arrays of N doubles, for N = 10^3,
 * 10^4, 10^5, and 10^6. Plot average running times for M from 0 to 30 for each value of M. Note :
 * You need to add a three-argument sort() method to Algorithm 2.2 for sorting subarrays such that
 * the call Insertion.sort(a, lo, hi) sorts the subarray a[lo..hi].
 *
 * <p>对小数组使用插入排序. 实现一个对大小小于M个元素的数组使用插入排序的快速排序,并且根据经验判断在你的计算环境<br>
 * 下 M 的大小是多少的时候排序一个随机 N 个double元素的数组时最快, N = 10^3,10^4, 10^5, 和 10^6. 绘制出当<br>
 * M的值从0到30区间的平均值. 注意:你需要添加一个3个参数的sort()方法到算法2.2中来通过调用Insertion.sort(a, lo, hi)<br>
 * 来排序子数组 a[lo..hi].
 *
 * @author LeonChen
 * @since 8/24/20
 */
class E02_03_25 {

  private static int M;
  /**
   * <code>
   * </code>
   */
  public static void main(String[] args) {
    StdDraw.setCanvasSize(700, 700);
    StdDraw.setXscale(0, 35);
    StdDraw.setYscale(-0.1, 0.6);
    StdDraw.setPenRadius(0.03);
    Color[] pens = new Color[] {Color.black, Color.blue, Color.green, Color.red};
    HashMap<Integer, Double> timeM = new HashMap<>();
    Integer[] ns =
        new Integer[] {
          (int) Math.pow(10, 3), (int) Math.pow(10, 4), (int) Math.pow(10, 5), (int) Math.pow(10, 6)
        };
    for (int i = 0; i < ns.length; i++) {
      StdDraw.setPenColor(pens[i]);
      int N = ns[i];
      for (M = 0; M <= 30; M++) {
        Integer[] a = new Integer[N];
        for (int j = 0; j < N; j++) {
          a[j] = StdRandom.uniform(N);
        }
        Stopwatch stopwatch = new Stopwatch();
        sort(a);
        double time = stopwatch.elapsedTime();
        StdDraw.point(M, time);
        if (timeM.containsKey(M)) {
          timeM.put(M, timeM.get(M) + time);
        } else {
          timeM.put(M, time);
        }
        StdOut.println("N = " + N + ", M = " + M + ", sort time = " + time + " seconds");
      }
    }
    int minM = 0;
    double minTime = Double.MAX_VALUE;
    StdDraw.setPenColor(Color.DARK_GRAY);
    for (int i = 0; i < 30; i++) {
      double avgTimeM = timeM.get(i) / 4;
      if (avgTimeM < minTime) {
        minM = i;
        minTime = avgTimeM;
      }
      StdDraw.point(i, avgTimeM);
    }
    StdOut.println(" the most fast M is " + minM);
  }

  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
    assert isSorted(a);
  }

  public static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    if (hi - lo <= M) {
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
