package edu.princeton.cs.exercise.chapter2_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.2.9 Use of a static array like aux[] is inadvisable in library software because multiple
 * clients might use the class concurrently. Give an implementation of Merge that does not use a
 * static array. Do not make aux[] local to merge() (see the Q&A for this section). Hint : Pass the
 * auxiliary array as an argument to the recursive sort().
 *
 * <p>在软件库中使用静态数组比如 aux[] 是不合适的,因为客户端可能并发使用这些类.给出一个实现 合并不使用一个静态数组.<br>
 * 不要将aux[]当作merge()的场所(可以看本章节的 Q&A). 提示: 将辅助数组作为一个参数传递到递归sort()方法中
 *
 * @author LeonChen
 * @since 6/24/20
 */
class E02_02_09 {

  public static void main(String[] args) {}

  // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
  private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
    // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
    assert isSorted(a, lo, mid);
    assert isSorted(a, mid + 1, hi);

    // copy to aux[]
    for (int k = lo; k <= hi; k++) {
      aux[k] = a[k];
    }

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
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid + 1, hi);
    merge(a, aux, lo, mid, hi);
  }

  /**
   * Rearranges the array in ascending order, using the natural order.
   *
   * @param a the array to be sorted
   */
  public static void sort(Comparable[] a) {
    Comparable[] aux = new Comparable[a.length];
    sort(a, aux, 0, a.length - 1);
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

  /**
   * ************************************************************************* Index mergesort.
   * *************************************************************************
   */
  // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
  private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {

    // copy to aux[]
    for (int k = lo; k <= hi; k++) {
      aux[k] = index[k];
    }

    // merge back to a[]
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) index[k] = aux[j++];
      else if (j > hi) index[k] = aux[i++];
      else if (less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
      else index[k] = aux[i++];
    }
  }

  /**
   * Returns a permutation that gives the elements in the array in ascending order.
   *
   * @param a the array
   * @return a permutation {@code p[]} such that {@code a[p[0]]}, {@code a[p[1]]}, ..., {@code
   *     a[p[N-1]]} are in ascending order
   */
  public static int[] indexSort(Comparable[] a) {
    int n = a.length;
    int[] index = new int[n];
    for (int i = 0; i < n; i++) index[i] = i;

    int[] aux = new int[n];
    sort(a, index, aux, 0, n - 1);
    return index;
  }

  // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
  private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(a, index, aux, lo, mid);
    sort(a, index, aux, mid + 1, hi);
    merge(a, index, aux, lo, mid, hi);
  }

  // print array to standard output
  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }
}
