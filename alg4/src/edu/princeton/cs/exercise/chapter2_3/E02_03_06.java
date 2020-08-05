package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.6 Write a program to compute the exact value of C_N, and compare the exact value with the
 * approximation 2NlnN, for N = 100, 1,000, and 10,000.
 *
 * <p>编写一个程序来计算C_N 对比的精确值,并且将它和近似值 2NlnN进行对比,N = 100,1000,10000
 *
 * @author LeonChen
 * @since 8/3/20
 */
class E02_03_06 {

  private static int compareTime;

  /** @param args */
  public static void main(String[] args) {
    int[] arrayLenths = {100, 1000, 10000};
    for (int i = 0; i < arrayLenths.length; i++) {
      int arrayLen = arrayLenths[i];

      Comparable[] a = new Comparable[arrayLen];
      for (int j = 0; j < arrayLen; j++) {
        a[j] = StdRandom.uniform(arrayLen);
      }
      compareTime = 0;
      quickSort(a);
      StdOut.println(
          "when N = "
              + arrayLen
              + " , compare "
              + compareTime
              + " times , 2NlnN = "
              + 2 * arrayLen * Math.log(arrayLen));

      //      show(a);
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
