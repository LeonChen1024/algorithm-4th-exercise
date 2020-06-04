package edu.princeton.cs.exercise.chapter2_1;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.1.18 Visual trace. Modify your solution to the previous exercise to make Insertion and
 * Selection produce visual traces such as those depicted in this section. Hint : Judicious use of
 * setYscale() makes this problem easy. Extra credit : Add the code necessary to produce red and
 * gray color accents such as those in our figures.
 *
 * <p>可视轨迹.修改你上一个练习的解决方案来制作 Insertion 和 Selection 的可视轨迹,如同正文中描述的那样.提示:使用<br>
 * setYscale()来简化这道题是明智的.附加题:给代码添加图形中必要的红色和灰色
 *
 * @author LeonChen
 * @since 5/20/20
 */
class E02_01_18 {
  private static final String INSERT = "insert";
  private static final String SELECT = "select";
  private static int barWidth = 30;
  private static int barHeight = 10;

  /** */
  public static void main(String[] args) {
    int arraySize = 10;
    Double[] array = new Double[arraySize];

    for (int i = 0; i < array.length; i++) {
      double value = StdRandom.uniform(10);
      array[i] = value;
    }

    StdDraw.setCanvasSize(barWidth * (arraySize + 1), barHeight * (arraySize + 1));
    StdDraw.setXscale(-1, barWidth * (arraySize + 1));
    StdDraw.setYscale(0, barHeight * (arraySize + 1));

    //    insertSort(array);
    selectSort(array);
  }

  private static void redraw(Double[] a, int j, int k, String method) {
    StdDraw.clear();
    for (int i = 0; i < a.length; i++) {

      if (i == j || i == k) {
        StdDraw.setPenColor(StdDraw.BOOK_RED);
      } else {
        StdDraw.setPenColor(StdDraw.BLACK);
      }

      if (method.equals(INSERT)) {
        if (i > j) {
          StdDraw.setPenColor(StdDraw.GRAY);
        }
      } else if (method.equals(SELECT)) {
        if (i < j) {
          StdDraw.setPenColor(StdDraw.GRAY);
        }
      }

      StdDraw.filledRectangle(
          i * barWidth + barWidth / 2, a[i] / 2 * barHeight, barWidth / 2, a[i] / 2 * barHeight);
    }
  }

  /**
   * Rearranges the array in ascending order, using the natural order.
   *
   * @param a the array to be sorted
   */
  public static void selectSort(Double[] a) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      int min = i;
      for (int j = i + 1; j < n; j++) {
        if (less(a[j], a[min])) min = j;
      }
      exch(a, i, min);
      redraw(a, i, min, SELECT);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      assert isSorted(a, 0, i);
    }
    assert isSorted(a);
  }

  /**
   * Rearranges the array in ascending order, using the natural order.
   *
   * @param a the array to be sorted
   */
  public static void insertSort(Double[] a) {
    int n = a.length;
    for (int i = 1; i < n; i++) {
      for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
        exch(a, j, j - 1);
        redraw(a, j, j - 1, INSERT);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      assert isSorted(a, 0, i);
    }
    assert isSorted(a);
  }

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  // is v < w ?
  private static boolean less(Object v, Object w, Comparator comparator) {
    return comparator.compare(v, w) < 0;
  }

  // exchange a[i] and a[j]
  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  // exchange a[i] and a[j]  (for indirect sort)
  private static void exch(int[] a, int i, int j) {
    int swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length);
  }

  // is the array a[lo..hi) sorted
  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i < hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  private static boolean isSorted(Object[] a, Comparator comparator) {
    return isSorted(a, 0, a.length, comparator);
  }

  // is the array a[lo..hi) sorted
  private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
    for (int i = lo + 1; i < hi; i++) if (less(a[i], a[i - 1], comparator)) return false;
    return true;
  }

  // print array to standard output
  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }
}
