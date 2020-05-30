package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.1.11 Implement a version of shellsort that keeps the increment sequence in an array, rather
 * than computing it.
 *
 * <p>实现一个 shellsort 版本将实时计算递增序列(也就是h-sort间隔的值)改为预先计算并存储在一个数组中.
 *
 * @author LeonChen
 * @since 5/15/20
 */
class E02_01_11 {

  /** 只需要修改一下原来的shellsort即可,就是将h的计算提前算好,放入数组. */
  public static void main(String[] args) {

    String[] arr =
        new String[] {
          "E", "A", "S", "Y", "S", "H", "E", "L", "L", "S", "O", "R", "T", "Q", "U", "E", "S", "T",
          "I", "O", "N"
        };
    sort(arr);
  }

  /**
   * Rearranges the array in ascending order, using the natural order.
   *
   * @param a the array to be sorted
   */
  public static void sort(Comparable[] a) {
    int n = a.length;

    int incrementTime = 1;
    int incrementNum = 1;

    while ((incrementNum * 3 + 1) < n) {
      incrementTime++;
      incrementNum = incrementNum * 3 + 1;
    }

    int[] incrementArr = new int[incrementTime];
    int index = 0;
    while (incrementNum > 0) {
      incrementArr[index] = incrementNum;
      incrementNum--;
      incrementNum = incrementNum / 3;
      index++;
    }

    // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
    index = 0;
    int h;
    show(a);
    StdOut.println();
    do {
      h = incrementArr[index++];
      // h-sort the array
      for (int i = h; i < n; i++) {
        for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
          exch(a, j, j - h);
        }
      }
      show(a);
      StdOut.println();
      assert isHsorted(a, h);
    } while (index < incrementArr.length);
    assert isSorted(a);
  }

  /**
   * ************************************************************************* Helper sorting
   * functions. *************************************************************************
   */

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

  /**
   * ************************************************************************* Check if array is
   * sorted - useful for debugging.
   * *************************************************************************
   */
  private static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  // is the array h-sorted?
  private static boolean isHsorted(Comparable[] a, int h) {
    for (int i = h; i < a.length; i++) if (less(a[i], a[i - h])) return false;
    return true;
  }

  // print array to standard output
  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.print(a[i] + " ");
    }
  }
}
