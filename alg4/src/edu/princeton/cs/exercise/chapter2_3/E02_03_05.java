package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.3.5 Give a code fragment that sorts an array that is known to consist of items having just two
 * distinct keys.
 *
 * <p>给出一个代码片段对一个已知只有两个不同的key组成的数组进行排序.
 *
 * @author LeonChen
 * @since 7/31/20
 */
class E02_03_05 {

  // rearranges a[] in ascending order assuming a[] has at most 3 distinct values
  public static void sort(Comparable[] a) {
    int lt = 0, gt = a.length - 1;
    int i = 0;
    while (i <= gt) {
      int cmp = a[i].compareTo(a[lt]);
      if (cmp < 0) exch(a, lt++, i++);
      else if (cmp > 0) exch(a, i, gt--);
      else i++;
    }
  }

  // exchange a[i] and a[j]
  private static void exch(Comparable[] a, int i, int j) {
    Comparable swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  /**
   * 只需要将i从lo开始不断对比放到lt或者gt就可以了.
   *
   * @param args
   */
  public static void main(String[] args) {
    Integer[] a = {1, 2, 1, 1, 1, 2, 2, 2, 1, 2, 2, 2, 1, 1, 1};

    // sort a print results
    sort(a);
    for (int i = 0; i < a.length; i++) StdOut.print(a[i] + " ");
    StdOut.println();
  }
}
