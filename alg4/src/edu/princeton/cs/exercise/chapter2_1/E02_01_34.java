package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.34 Corner cases. Write a client that runs sort() on difficult or pathological cases that
 * might turn up in practical applications. Examples include arrays that are already in order,
 * arrays in reverse order, arrays where all keys are the same, arrays consisting of only two
 * distinct values, and arrays of size 0 or 1.
 *
 * <p>边缘情况.编写一个客户端对实际中可能出现的困难的和极端情况的数组进行排序. 举例包括已经有序的数组,倒序的数组,<br>
 * 所有键都相同的数组,只包含两种值的数组,大小为0或者1的数组.
 *
 * @author LeonChen
 * @since 5/28/20
 */
class E02_01_34 {

  private static final String INSERTION = "INSERTION";
  private static final String SELECTION = "SELECTION";
  private static final String SHELL = "SHELL";

  /**
   * <code>
   *     ======================= 有序数组 ===============================
   * when sort is INSERTION , used time is 0.0018000000000000002
   * when sort is SELECTION , used time is 0.9251999999999999
   * when sort is SHELL , used time is 0.0048000000000000004
   *
   *     ======================= 倒序数组 ===============================
   * when sort is INSERTION , used time is 5.2825999999999995
   * when sort is SELECTION , used time is 1.6442
   * when sort is SHELL , used time is 0.0094
   *
   *     ======================= 所有键都相同的数组 ===============================
   * when sort is INSERTION , used time is 0.0024000000000000002
   * when sort is SELECTION , used time is 0.9006000000000001
   * when sort is SHELL , used time is 0.006
   *
   *     ======================= 只包含两种值的数组 ===============================
   * when sort is INSERTION , used time is 1.4756
   * when sort is SELECTION , used time is 1.5375999999999999
   * when sort is SHELL , used time is 0.005399999999999999
   *
   *     ======================= 大小为0的数组 ===============================
   * when sort is INSERTION , used time is 0.0
   * when sort is SELECTION , used time is 2.0E-4
   * when sort is SHELL , used time is 6.000000000000001E-4
   *
   *     ======================= 大小为1的数组 ===============================
   * when sort is INSERTION , used time is 2.0E-4
   * when sort is SELECTION , used time is 2.0E-4
   * when sort is SHELL , used time is 0.0
   *
   * </code>
   *
   * @param args
   */
  public static void main(String[] args) {
    double usedTime = 0;
    String type = SHELL;
    int N = 50000;
    int x = 0;
    while (x < 5) {
      Comparable[] a = getSize1Arr(N);

      Stopwatch stopwatch = new Stopwatch();
      switch (type) {
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
    StdOut.println(" when sort is " + type + " , used time is " + usedTime / x);
  }

  private static Comparable[] getSize1Arr(int n) {
    return new Comparable[] {1};
  }

  private static Comparable[] getSize0Arr(int n) {
    return new Comparable[0];
  }

  private static Comparable[] getTwoValueKeyArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = StdRandom.uniform() < 0.5 ? 1 : 2;
    }
    return a;
  }

  private static Comparable[] getAllSameKeyArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = 1;
    }
    return a;
  }

  private static Comparable[] getReverseSortedArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = n - i;
    }
    return a;
  }

  /**
   * 得到有序数组
   *
   * @param n
   * @return
   */
  private static Comparable[] getSortedArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = i;
    }
    return a;
  }
}
