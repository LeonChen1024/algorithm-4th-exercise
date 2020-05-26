package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.1.9 Show, in the style of the example trace with Algorithm 2.3, how shellsort sorts the array E
 * A S Y S H E L L S O R T Q U E S T I O N.
 *
 * <p>展示出和 Algorithm 2.3 中示例样式相同的跟踪图,表明 shellsort 是怎么排序数组 <br>
 * E A S Y S H E L L S O R T Q U E S T I O N 的.
 *
 * @author LeonChen
 * @since 5/14/20
 */
class E02_01_09 {

  /**
   * <code>
   * E A S Y S H E L L S O R T Q U E S T I O N
   * E A E S S H E L L S O R T Q U S Y T I O N
   * E A E L L H E O N Q I R S S O S T T U S Y
   * A E E E H I L L N O O Q R S S S S T T U Y
   * </code>
   */
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

    // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
    int h = 1;
    while (h < n / 3) h = 3 * h + 1;
    show(a);
    StdOut.println();
    while (h >= 1) {
      // h-sort the array
      for (int i = h; i < n; i++) {
        for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
          exch(a, j, j - h);
        }
      }
      show(a);
      StdOut.println();
      assert isHsorted(a, h);
      h /= 3;
    }
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
