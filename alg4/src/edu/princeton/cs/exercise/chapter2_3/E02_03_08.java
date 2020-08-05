package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.8 About how many compares will Quick.sort() make when sorting an array of N items that are
 * all equal?
 *
 * <p>当对一个所有项都相等的包含N个项的元素使用 Quick.sort() 进行排序会进行多少次对比?
 *
 * @author LeonChen
 * @since 8/4/20
 */
class E02_03_08 {

  private static int compareTime;

  /**
   * <code>
   * when N = 200 , compare 1324 times
   * when N = 400 , compare 4368 times
   * when N = 800 , compare 11252 times
   * when N = 1600 , compare 26616 times
   * when N = 3200 , compare 60540 times
   * when N = 6400 , compare 134784 times
   * when N = 12800 , compare 296068 times
   * when N = 25600 , compare 644232 times
   * when N = 51200 , compare 1391756 times
   * </code> <br>
   * 每次切分都会把数组平分，共切分 logN 次(二分法),每次切分比较 N 次（i 和 j 从两边向中间每次走一位). <br>
   * 得到 NlogN .
   *
   * @param args
   */
  public static void main(String[] args) {
    for (int i = 200; i < 100000; i = i * 2) {

      Comparable[] a = new Comparable[i];
      for (int j = 0; j < i; j++) {
        a[j] = 3;
      }
      quickSort(a);
      StdOut.println("when N = " + i + " , compare " + compareTime + " times");
    }
  }

  private static void quickSort(Comparable[] a) {
    StdRandom.shuffle(a);

    sort(a);
  }

  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
    assert isSorted(a);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    int j = partition(a, lo, hi);
    int leftSubArraySize = j - lo;
    int rightSubArraySize = hi - j;

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

  private static boolean less(Comparable v, Comparable w) {
    compareTime++;
    if (v == w) return false; // optimization when reference equals
    return v.compareTo(w) < 0;
  }

  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length - 1);
  }

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }
}
