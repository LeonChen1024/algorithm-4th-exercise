package edu.princeton.cs.exercise.chapter2_2;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.2.22 3-way mergesort. Suppose instead of dividing in half at each step, you divide into thirds,
 * sort each third, and combine using a 3-way merge. What is the order of growth of the overall
 * running time of this algorithm?
 *
 * <p>3路合并排序. 假设每一步不分成一半来排序,而是将它分为3块,排序每三个,并且使用3路合并. 这个算法的运行时间<br>
 * 的增长级别会是怎样的
 *
 * @author LeonChen
 * @since 7/16/20
 */
class E02_02_22 {

  /**
   * 一样是 O(nlogn)
   *
   * @param args
   */
  public static void main(String[] args) {

    for (int i = 200; i < 1000000; i = i * 2) {
      Comparable[] a = new Comparable[i];
      Comparable[] b = new Comparable[i];
      for (int j = 0; j < a.length; j++) {
        a[j] = StdRandom.uniform(1000);
        b[j] = a[j];
      }
      Stopwatch stopwatch = new Stopwatch();
      Merge.sort(a);
      double t1 = stopwatch.elapsedTime();

      stopwatch = new Stopwatch();
      sort(a);
      //      show(a);
      double t2 = stopwatch.elapsedTime();

      StdOut.println(
          "when N = " + i + " 2-way mergesort used " + t1 + " ,3-way mergesort used " + t2);
    }

    //    Comparable[] a = new Comparable[] {3, 1, 4, 7, 9, 43, 223, 42, 56, 22, 66, 24, 564, 225,
    // 25, 2};
    //    //    Comparable[] a = new Comparable[] {3, 1, 4, 7};

  }

  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }

  public static void sort(Comparable[] a) {
    Comparable[] aux = new Comparable[a.length];
    sort(a, aux, 0, (a.length - 1) / 3, (a.length - 1) * 2 / 3, a.length - 1);
    assert isSorted(a);
  }

  private static void sort(Comparable[] a, Comparable[] aux, int lo1, int lo2, int lo3, int hi) {
    if (hi <= lo1 + 2) return;
    int lo12 = (lo2 - lo1) / 3 + lo1;
    int lo13 = (lo2 - lo1) / 3 + lo12;
    int lo22 = (lo3 - lo2) / 3 + lo2;
    int lo23 = (lo3 - lo2) / 3 + lo22;
    int lo32 = (hi - lo3) / 3 + lo3;
    int lo33 = (hi - lo3) / 3 + lo32;
    sort(a, aux, lo1, lo12, lo13, lo2);
    sort(a, aux, lo2 + 1, lo22, lo23, lo3);
    sort(a, aux, lo3 + 1, lo32, lo33, hi);
    merge(a, aux, lo1, lo2, lo3, hi);
  }

  private static void merge(Comparable[] a, Comparable[] aux, int lo1, int lo2, int lo3, int hi) {
    // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
    assert isSorted(a, lo1, lo2);
    assert isSorted(a, lo2 + 1, lo3);
    assert isSorted(a, lo3 + 1, hi);

    // copy to aux[]
    for (int k = lo1; k <= hi; k++) {
      aux[k] = a[k];
    }

    // merge back to a[]
    int i = lo1, j = lo2 + 1, l = lo3 + 1;
    for (int k = lo1; k <= hi; k++) {
      if (i > lo2) {
        if (j > lo3) {
          a[k] = aux[l++];
        } else if (l > hi) {
          a[k] = aux[j++];
        } else {
          if (less(aux[j], aux[l])) {
            a[k] = aux[j++];
          } else {
            a[k] = aux[l++];
          }
        }
      } else if (j > lo3) {
        if (i > lo2) {
          a[k] = aux[l++];
        } else if (l > hi) {
          a[k] = aux[i++];
        } else {
          if (less(aux[i], aux[l])) {
            a[k] = aux[i++];
          } else {
            a[k] = aux[l++];
          }
        }
      } else if (l > hi) {
        if (i > lo2) {
          a[k] = aux[j++];
        } else if (j > lo3) {
          a[k] = aux[i++];
        } else {
          if (less(aux[i], aux[j])) {
            a[k] = aux[i++];
          } else {
            a[k] = aux[j++];
          }
        }
      } else if (less(aux[j], aux[i])) {
        if (less(aux[j], aux[l])) {
          a[k] = aux[j++];
        } else {
          a[k] = aux[l++];
        }
      } else {
        if (less(aux[i], aux[l])) {
          a[k] = aux[i++];
        } else {
          a[k] = aux[l++];
        }
      }
    }

    // postcondition: a[lo .. hi] is sorted
    assert isSorted(a, lo1, hi);
  }

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length - 1);
  }
}
