package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.3.17 Sentinels. Modify the code in Algorithm 2.5 to remove both bounds checks in the inner
 * while loops. The test against the left end of the subarray is redundant since the partitioning
 * item acts as a sentinel (v is never less than a[lo]). To enable removal of the other test, put an
 * item whose key is the largest in the whole array into a[length-1] just after the shuffle. This
 * item will never move (except possibly to be swapped with an item having the same key) and will
 * serve as a sentinel in all subarrays involving the end of the array. Note : When sorting interior
 * subarrays, the leftmost entry in the subarray to the right serves as a sentinel for the right end
 * of the subarray.
 *
 * <p>哨兵.修改算法 2.5 中的代码来消除内部while循环对两边的边界校验.将子数组和最左边的元素进行对比是多余的因为<br>
 * 分割项就如同一个哨兵一样(v永远小于 a[lo]).想要移除另外一边的测试,可以在打乱数组之后将一个键最大的项<br>
 * 放到 a[length-1].这个项永远不会移动(除非是和一个拥有相同键的项交换)并且在所有的子数组中会像一个哨兵一样.<br>
 * 注意: 当对内部子数组进行排序的时候,子数组最左边的项充当了右边项的哨兵
 *
 * @author LeonChen
 * @since 8/14/20
 */
class E02_03_17 {

  /**
   * 在数组较小的时候哨兵算法还是有一定优势的.
   *
   * @param args
   */
  public static void main(String[] args) {

    for (int n = 1000; n < 1000000; n = n * 2) {
      Comparable[] a = new Comparable[n];

      for (int i = 0; i < n; i++) {
        a[i] = StdRandom.uniform(1000);
      }
      Comparable[] b = new Comparable[n];
      System.arraycopy(a, 0, b, 0, a.length);

      Stopwatch timer = new Stopwatch();
      Quick.sort(a);
      double originTime = timer.elapsedTime();

      Stopwatch ntimer = new Stopwatch();
      quicksortWithNotSentinels(b);
      double newTime = ntimer.elapsedTime();

      StdOut.println(
          "when n is "
              + n
              + " , original quicksort use "
              + originTime
              + "seconds , without sentinels quicksort use "
              + newTime
              + "seconds");
    }
  }

  private static void quicksortWithNotSentinels(Comparable[] b) {

    StdRandom.shuffle(b);

    // Place biggest item on the right end
    Comparable maxValue = b[0];
    int maxValueIndex = 0;
    for (int i = 1; i < b.length; i++) {
      if (less(maxValue, b[i])) {
        maxValue = b[i];
        maxValueIndex = i;
      }
    }

    exch(b, maxValueIndex, b.length - 1);

    sort(b, 0, b.length - 1);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    int j = partition(a, lo, hi);
    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
    assert isSorted(a, lo, hi);
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    Comparable v = a[lo];
    while (true) {

      // find item on lo to swap
      while (less(a[++i], v)) {
        if (i == hi) break;
      }

      // find item on hi to swap
      while (less(v, a[--j])) {
        if (j == lo) break; // redundant since a[lo] acts as sentinel
      }

      // check if pointers cross
      if (i >= j) break;

      exch(a, i, j);
    }

    // put partitioning item v at a[j]
    exch(a, lo, j);

    // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    return j;
  }

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  private static boolean less(Comparable v, Comparable w) {
    if (v == w) return false; // optimization when reference equals
    return v.compareTo(w) < 0;
  }

  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }
}
