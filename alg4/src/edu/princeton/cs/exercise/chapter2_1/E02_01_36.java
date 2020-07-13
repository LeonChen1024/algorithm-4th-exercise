package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.36 Nonuniform data. Write a client that generates test data that is not uniform, including
 * the following:<br>
 * ■ Half the data is 0s, half 1s.<br>
 * ■ Half the data is 0s, half the remainder is 1s, half the remainder is 2s, and so forth.<br>
 * ■ Half the data is 0s, half random int values. <br>
 * Develop and test hypotheses about the effect of such input on the performance of the algorithms
 * in this section.
 *
 * <p>不均匀的数据.编写一个客户端生成不均匀的测试数据,包含以下:<br>
 * - 一半是0,一半是1<br>
 * - 一半是0,剩下的一半是1 ,剩下的一半是2,以此类推 <br>
 * - 一半是0,一半是随机整数值<br>
 * 开发并测试本章节中关于输入对于算法性能的影响的假说
 *
 * @author LeonChen
 * @since 5/29/20
 */
class E02_01_36 {

  private static final String INSERTION = "INSERTION";
  private static final String SELECTION = "SELECTION";
  private static final String SHELL = "SHELL";

  private static final String ARR_TYPE_RANDOM = "ARR_TYPE_RANDOM";
  private static final String ARR_TYPE_H0H1 = "ARR_TYPE_H0H1";
  private static final String ARR_TYPE_RECURSIVE_HALF = "ARR_TYPE_RECURSIVE_HALF";
  private static final String ARR_TYPE_H0HR = "ARR_TYPE_H0HR";

  /**
   * <code>
   * when arr type is ARR_TYPE_RANDOM when sort is SELECTION , used time is 2.1866
   * when arr type is ARR_TYPE_RANDOM when sort is INSERTION , used time is 3.6236000000000006
   * when arr type is ARR_TYPE_RANDOM when sort is SHELL , used time is 0.014199999999999999
   *
   * when arr type is ARR_TYPE_H0H1 when sort is SELECTION , used time is 1.4032
   * when arr type is ARR_TYPE_H0H1 when sort is INSERTION , used time is 2.0E-4
   * when arr type is ARR_TYPE_H0H1 when sort is SHELL , used time is 0.0014
   *
   * when arr type is ARR_TYPE_RECURSIVE_HALF when sort is SELECTION , used time is 1.423
   * when arr type is ARR_TYPE_RECURSIVE_HALF when sort is INSERTION , used time is 2.0E-4
   * when arr type is ARR_TYPE_RECURSIVE_HALF when sort is SHELL , used time is 0.0014
   *
   * when arr type is ARR_TYPE_H0HR when sort is SELECTION , used time is 1.5163999999999997
   * when arr type is ARR_TYPE_H0HR when sort is INSERTION , used time is 0.7906000000000001
   * when arr type is ARR_TYPE_H0HR when sort is SHELL , used time is 0.0058
   *
   * </code> 选择排序的耗时与输入值的内容无关。对于插入排序，除了随机数之外都是重复值较多的情况，<br>
   * 插入排序的速度会比选择排序快. 希尔排序在h0h1,和RECURSIVE_HALF这种大量有序的情况上会比插入排序慢,<br>
   * 其他情况速度更快。
   *
   * @param args
   */
  public static void main(String[] args) {

    String[] types = new String[] {SELECTION, INSERTION, SHELL};

    String[] arrTypes =
        new String[] {ARR_TYPE_RANDOM, ARR_TYPE_H0H1, ARR_TYPE_RECURSIVE_HALF, ARR_TYPE_H0HR};

    for (String arrType : arrTypes) {
      for (String sortType : types) {
        timeCost(arrType, sortType);
      }
      StdOut.println();
    }
  }

  private static void timeCost(String arrType, String sortType) {
    double usedTime = 0;

    int N = 50000;
    int x = 0;
    while (x < 5) {
      Comparable[] a;
      switch (arrType) {
        case ARR_TYPE_RANDOM:
          a = getRandomArr(N);
          break;
        case ARR_TYPE_H0H1:
          a = getH0H1Arr(N);
          break;
        case ARR_TYPE_RECURSIVE_HALF:
          a = getRecursiveHalfArr(N);
          break;
        case ARR_TYPE_H0HR:
          a = getH0HrArr(N);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + arrType);
      }

      Stopwatch stopwatch = new Stopwatch();
      switch (sortType) {
        case INSERTION:
          Insertion.sort(a);
          break;
        case SELECTION:
          Selection.sort(a);
          break;
        case SHELL:
          Shell.sort(a);
      }
      usedTime += stopwatch.elapsedTime();
      x++;
    }
    StdOut.println(
        "when arr type is "
            + arrType
            + " when sort is "
            + sortType
            + " , used time is "
            + usedTime / x);
  }

  private static Comparable[] getRandomArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = StdRandom.uniform(n);
    }
    return a;
  }

  private static Comparable[] getH0H1Arr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n / 2; i++) {
      a[i] = 0;
    }
    for (int i = n / 2; i < n; i++) {
      a[i] = 1;
    }
    return a;
  }

  private static Comparable[] getRecursiveHalfArr(int n) {
    Comparable[] a = new Comparable[n];
    halfSet(a, 0, 0, (n + 1) / 2);
    return a;
  }

  private static Comparable[] halfSet(Comparable[] a, int start, int value, int len) {
    if (len == 0 || start >= a.length) {
      return a;
    }

    for (int i = start; (i < (start + len)) && i < a.length; i++) {
      a[i] = value;
    }

    return halfSet(a, start + len, value + 1, (len + 1) / 2);
  }

  private static Comparable[] getH0HrArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n / 2; i++) {
      a[i] = 0;
    }

    for (int i = n / 2; i < n; i++) {
      a[i] = StdRandom.uniform(n);
    }
    return a;
  }
}
