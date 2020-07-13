package edu.princeton.cs.exercise.chapter2_1;

import java.util.HashSet;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.37 Partially sorted. Write a client that generates partially sorted arrays, including the
 * following:<br>
 * ■ 95 percent sorted, last percent random values<br>
 * ■ All entries within 10 positions of their final place in the array <br>
 * ■ Sorted except for 5 percent of the entries randomly dispersed throughout the array <br>
 * Develop and test hypotheses about the effect of such input on the performance of the algorithms
 * in this section.
 *
 * <p>部分有序.编写一个客户生成部分有序数组,包含以下:<br>
 * - 95% 有序,剩下的部分随机<br>
 * - 所有条目和他们最终的位置都不超过10个位置<br>
 * - 除了5%的条目随机分散在数组中<br>
 * 开发并且测试本章中有关这些输入对于算法性能影响的假说
 *
 * @author LeonChen
 * @since 6/2/20
 */
class E02_01_37 {

  private static final String INSERTION = "INSERTION";
  private static final String SELECTION = "SELECTION";
  private static final String SHELL = "SHELL";

  private static final String ARR_TYPE_95_SORTED = "ARR_TYPE_95_SORTED";
  private static final String ARR_TYPE_10_OFFSET = "ARR_TYPE_10_OFFSET";
  private static final String ARR_TYPE_5_UNSORTED = "ARR_TYPE_5_UNSORTED";

  /**
   * <code>
   * when arr type is ARR_TYPE_95_SORTED when sort is SELECTION , used time is 1.6618
   * when arr type is ARR_TYPE_95_SORTED when sort is INSERTION , used time is 0.191
   * when arr type is ARR_TYPE_95_SORTED when sort is SHELL , used time is 0.0078
   *
   * when arr type is ARR_TYPE_10_OFFSET when sort is SELECTION , used time is 1.5110000000000001
   * when arr type is ARR_TYPE_10_OFFSET when sort is INSERTION , used time is 0.0020000000000000005
   * when arr type is ARR_TYPE_10_OFFSET when sort is SHELL , used time is 0.003
   *
   * when arr type is ARR_TYPE_5_UNSORTED when sort is SELECTION , used time is 1.6545999999999998
   * when arr type is ARR_TYPE_5_UNSORTED when sort is INSERTION , used time is 0.2102
   * when arr type is ARR_TYPE_5_UNSORTED when sort is SHELL , used time is 0.0086
   * </code> 选择排序的耗时与输入值的内容无关。对于插入排序，除了随机数之外部分有序的情况下，<br>
   * 插入排序的速度会比选择排序快. 希尔排序在某种程度上有序或者是顺序错乱不大情况上会比插入排序慢,<br>
   * 其他情况速度更快。
   *
   * @param args
   */
  public static void main(String[] args) {

    String[] types = new String[] {SELECTION, INSERTION, SHELL};

    String[] arrTypes = new String[] {ARR_TYPE_95_SORTED, ARR_TYPE_10_OFFSET, ARR_TYPE_5_UNSORTED};

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
        case ARR_TYPE_95_SORTED:
          a = get95SortedArr(N);
          break;
        case ARR_TYPE_10_OFFSET:
          a = get10OffsetArr(N);
          break;
        case ARR_TYPE_5_UNSORTED:
          a = get5UnsortArr(N);
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

  private static Comparable[] get5UnsortArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = i;
    }
    HashSet usedSet = new HashSet();

    while (usedSet.size() < (n * 0.05)) {
      int src = StdRandom.uniform(n);
      int des = StdRandom.uniform(n);
      Comparable temp = a[src];
      a[src] = a[des];
      a[des] = temp;
      usedSet.add(src);
      usedSet.add(des);
    }
    return a;
  }

  private static Comparable[] get10OffsetArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = i;
    }
    HashSet usedSet = new HashSet();
    // 交换位置
    for (int i = 0; i < n; i++) {
      if (usedSet.contains(i)) {
        continue;
      }
      int offset = StdRandom.uniform(10);
      int index = (i + offset) >= n ? n - 1 : (i + offset);
      Comparable temp = a[i];
      a[i] = a[index];
      a[index] = temp;
      // 只需要把i没循环到的位置放进来就可以了
      usedSet.add(index);
    }

    return a;
  }

  private static Comparable[] get95SortedArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < (n * 0.95); i++) {
      a[i] = i;
    }

    for (int i = (int) (n * 0.95); i < n; i++) {
      a[i] = StdRandom.uniform(n);
    }

    return a;
  }
}
