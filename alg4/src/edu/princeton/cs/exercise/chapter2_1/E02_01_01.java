package edu.princeton.cs.exercise.chapter2_1;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.1.1 Show, in the style of the example trace with Algorithm 2.1, how selection sort sorts the
 * array E A S Y Q U E S T I O N.
 *
 * <p>展示出 Algorithm 2.1 中示例的样式,表达出选择排序数组 E A S Y Q U E S T I O N 的结果.
 *
 * @author LeonChen
 * @since 5/13/20
 */
class E02_01_01 {

  /**
   * <code>
   * AESYQUESTION
   * AESYQUESTION
   * AEEYQUSSTION
   * AEEIQUSSTYON
   * AEEINUSSTYOQ
   * AEEINOSSTYUQ
   * AEEINOQSTYUS
   * AEEINOQSTYUS
   * AEEINOQSSYUT
   * AEEINOQSSTUY
   * AEEINOQSSTUY
   * AEEINOQSSTUY
   * </code>
   */
  public static void main(String[] args) {
    String[] arr = new String[] {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};
    sort(arr);
  }

  /**
   * Rearranges the array in ascending order, using the natural order.
   *
   * @param a the array to be sorted
   */
  public static void sort(Comparable[] a) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      int min = i;
      for (int j = i + 1; j < n; j++) {
        if (less(a[j], a[min])) min = j;
      }
      exch(a, i, min);
      for (int j = 0; j < n; j++) {
        System.out.print(a[j]);
      }
      System.out.println();
      assert isSorted(a, 0, i);
    }
    assert isSorted(a);
  }

  /**
   * Rearranges the array in ascending order, using a comparator.
   *
   * @param a the array
   * @param comparator the comparator specifying the order
   */
  public static void sort(Object[] a, Comparator comparator) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      int min = i;
      for (int j = i + 1; j < n; j++) {
        if (less(comparator, a[j], a[min])) min = j;
      }
      exch(a, i, min);
      assert isSorted(a, comparator, 0, i);
    }
    assert isSorted(a, comparator);
  }

  /**
   * ************************************************************************* Helper sorting
   * functions. *************************************************************************
   */

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  // is v < w ?
  private static boolean less(Comparator comparator, Object v, Object w) {
    return comparator.compare(v, w) < 0;
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

  // is the array a[] sorted?
  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length - 1);
  }

  // is the array sorted from a[lo] to a[hi]
  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  // is the array a[] sorted?
  private static boolean isSorted(Object[] a, Comparator comparator) {
    return isSorted(a, comparator, 0, a.length - 1);
  }

  // is the array sorted from a[lo] to a[hi]
  private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) if (less(comparator, a[i], a[i - 1])) return false;
    return true;
  }

  // print array to standard output
  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }
}
