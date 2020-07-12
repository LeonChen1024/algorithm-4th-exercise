package edu.princeton.cs.exercise.chapter2_2;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.2.11 Improvements. Implement the three improvements to mergesort that are described in the text
 * on page 275: Add a cutoff for small subarrays, test whether the array is already in order, and
 * avoid the copy by switching arguments in the recursive code.
 *
 * <p>优化.实现3个优化到正文 275页的合并排序中:添加一个对小的子数组的隔断将小的数组使用插入排序排序,<br>
 * 测试数组是否已经是有序的,并且 通过更换参数的位置避免在递归中复制操作
 *
 * @author LeonChen
 * @since 6/28/20
 */
class E02_02_11 {

  private static final int CUTOFF = 10;

  public static void main(String[] args) {
    int N = 30;
    Comparable[] a = new Comparable[N];
    for (int i = 0; i < N; i++) {
      a[i] = StdRandom.uniform(100);
    }

    StdOut.println("===============start==================");
    show(a);
    sort(a);
  }

  // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
  private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
    // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
    assert isSorted(a, lo, mid);
    assert isSorted(a, mid + 1, hi);

    // #2 第二个优化,判断数组是否已经有序
    if (aux[mid].compareTo(aux[mid + 1]) <= 0) {
      return;
    }
    //    // copy to aux[]
    //    for (int k = lo; k <= hi; k++) {
    //      aux[k] = a[k];
    //    }

    // merge back to a[]
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) a[k] = aux[j++];
      else if (j > hi) a[k] = aux[i++];
      else if (less(aux[j], aux[i])) a[k] = aux[j++];
      else a[k] = aux[i++];
    }

    // postcondition: a[lo .. hi] is sorted
    assert isSorted(a, lo, hi);
  }

  // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
  private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    // #1 第一个优化,小数组使用插入排序
    if (hi - lo <= CUTOFF) {
      Insertion.sort(aux, lo, hi + 1);
      return;
    }
    int mid = lo + (hi - lo) / 2;
    sort(aux, a, lo, mid);
    sort(aux, a, mid + 1, hi);
    merge(aux, a, lo, mid, hi);
  }

  /**
   * Rearranges the array in ascending order, using the natural order.
   *
   * @param a the array to be sorted
   */
  public static void sort(Comparable[] a) {
    Comparable[] aux = new Comparable[a.length];
    for (int i = 0; i < a.length; i++) {
      aux[i] = a[i];
    }
    sort(a, aux, 0, a.length - 1);
    StdOut.println("===============end==================");
    show(aux);
    assert isSorted(a);
  }

  /**
   * ************************************************************************* Helper sorting
   * function. *************************************************************************
   */

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  /**
   * ************************************************************************* Check if array is
   * sorted - useful for debugging.
   * *************************************************************************
   */
  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length - 1);
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
