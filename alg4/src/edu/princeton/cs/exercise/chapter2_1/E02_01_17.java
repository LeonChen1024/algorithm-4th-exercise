package edu.princeton.cs.exercise.chapter2_1;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.1.17 Animation. Add code to Insertion and Selection to make them draw the array contents as
 * vertical bars like the visual traces in this section, redrawing the bars after each pass, to
 * produce an animated effect, ending in a “sorted” picture where the bars appear in order of their
 * height. Hint : Use a client like the one in the text that generates random Double values, insert
 * calls to show() as appropriate in the sort code, and implement a show() method that clears the
 * canvas and draws the bars.
 *
 * <p>动画.添加代码到Insertion 和 Selection 来使得他们使用条形画出数组内容就像本节中的可视化追踪一样,在每次通过<br>
 * 的时候重新绘制条形图,来制造一个动画的效果,以一个按照高度排序的有序图片作为结束.提示:使用一个如同正文中生成随机<br>
 * Double 值的客户端,在排序代码的适当位置调用 show() 方法,并且实现一个 show() 方法来清除画布并绘制条形图.
 *
 * @author LeonChen
 * @since 5/19/20
 */
class E02_01_17 {
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

    insertSort(array);
    //    selectSort(array);
  }

  private static void redraw(Double[] a, int j, int k) {
    StdDraw.clear();
    for (int i = 0; i < a.length; i++) {

      if (i == j || i == k) {
        StdDraw.setPenColor(StdDraw.BOOK_RED);
      } else {
        StdDraw.setPenColor(StdDraw.BLACK);
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
      redraw(a, i, min);
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
        redraw(a, j, j - 1);
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
