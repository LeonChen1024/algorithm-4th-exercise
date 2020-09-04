package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.3.29 Randomization. Run empirical studies to compare the effectiveness of the strategy of
 * choosing a random partitioning item with the strategy of initially randomizing the array (as in
 * the text). Use a cutoff for arrays of size M, and sort arrays of N distinct elements, for M=10,
 * 20, and 50 and N = 10^3, 10^4, 10^5, and 10^6.
 *
 * <p>随机处理. 运行实验对比使用一个随机分割项的策略和使用初始打乱数组的策略(如正文所示).对数组大小为M以下的使用<br>
 * 插入排序,并且排序数组有N个不同的元素.测试 M = 10, 20, and 50 and N = 10^3, 10^4, 10^5, and 10^6.
 *
 * @author LeonChen
 * @since 8/27/20
 */
class E02_03_29 {

  private static int M;

  /**
   * <code>
   * n is 1000 , cutoff M is 10, initial random time  is 0.018 , random partition item time is 0.003
   * n is 1000 , cutoff M is 20, initial random time  is 0.005 , random partition item time is 0.003
   * n is 1000 , cutoff M is 50, initial random time  is 0.003 , random partition item time is 0.002
   * n is 10000 , cutoff M is 10, initial random time  is 0.108 , random partition item time is 0.116
   * n is 10000 , cutoff M is 20, initial random time  is 0.21 , random partition item time is 0.124
   * n is 10000 , cutoff M is 50, initial random time  is 0.163 , random partition item time is 0.108
   * </code>
   */
  public static void main(String[] args) {

    int[] ms = new int[] {10, 20, 50};
    int[] ns =
        new int[] {
          (int) Math.pow(10, 3), (int) Math.pow(10, 4), (int) Math.pow(10, 5), (int) Math.pow(10, 6)
        };
    for (int i = 0; i < ns.length; i++) {
      int n = ns[i];
      for (int j = 0; j < ms.length; j++) {
        M = ms[j];

        Comparable[] a = new Comparable[n];

        for (int k = 0; k < n; k++) {
          a[k] = StdRandom.uniform(n);
        }
        Comparable[] b = new Comparable[n];
        System.arraycopy(a, 0, b, 0, a.length);

        Stopwatch timer = new Stopwatch();
        cutoffSort(a);
        double cutoffTime = timer.elapsedTime();

        timer = new Stopwatch();
        randomPartitionSort(b);
        double randomPtime = timer.elapsedTime();

        StdOut.println(
            "n is "
                + n
                + " , cutoff M is "
                + M
                + ", initial random time  is "
                + cutoffTime
                + " , random partition item time is "
                + randomPtime);
      }
    }
  }

  private static void randomPartitionSort(Comparable[] a) {
    rsort(a, 0, a.length - 1);
    assert isSorted(a);
  }

  private static void rsort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    if (hi - lo <= M) {
      Insertion.sort(a);
      return;
    }

    int j = rpartition(a, lo, hi);
    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
  }

  private static int rpartition(Comparable[] a, int lo, int hi) {
    int i = lo, j = hi + 1;
    int randomPivotIndex = StdRandom.uniform(lo, hi + 1);
    exch(a, lo, randomPivotIndex);
    Comparable v = a[lo];
    while (true) {
      while (less(a[++i], v)) if (i == hi) break;
      while (less(v, a[--j])) if (j == lo) break;
      if (i >= j) break;
      exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
  }

  private static void cutoffSort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
    assert isSorted(a);
  }

  public static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    if (hi - lo <= M) {
      Insertion.sort(a);
      return;
    }

    int j = partition(a, lo, hi);
    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo, j = hi + 1;
    Comparable v = a[lo];
    while (true) {
      while (less(a[++i], v)) if (i == hi) break;
      while (less(v, a[--j])) if (j == lo) break;
      if (i >= j) break;
      exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
  }

  /** ************************** Helper sorting functions **************************** */

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    if (v == w) return false; // optimization when reference equal
    return v.compareTo(w) < 0;
  }

  // does v == w ?
  private static boolean eq(Comparable v, Comparable w) {
    if (v == w) return true; // optimization when reference equal
    return v.compareTo(w) == 0;
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

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  // print array to standard output
  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }
}
