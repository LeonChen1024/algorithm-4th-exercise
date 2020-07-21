package edu.princeton.cs.exercise.chapter2_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.2.20 Indirect sort. Develop and implement a version of mergesort that does not rearrange the
 * array, but returns an int[] array perm such that perm[i] is the index of the i th smallest entry
 * in the array.
 *
 * <p>间接排序.开发并实现一个版本的合并排序它不会重排数组,而是返回一个 int[] 数组perm使得 perm[i] 是第i小的项<br>
 * 的索引
 *
 * @author LeonChen
 * @since 7/15/20
 */
class E02_02_20 {

  /**
   * 只需要将排序的传入数组换成 index 数组,并且对比的时候使用这个 index 再索引值进行对比
   *
   * @param args
   */
  public static void main(String[] args) {
    //    Comparable[] a = new Comparable[] {3, 1, 4, 7, 9, 43, 223, 42, 56, 22, 66, 24, 564, 225,
    // 25, 2};
    Comparable[] a = new Comparable[] {3, 1, 4, 7};

    show(indexSort(a));
  }

  private static void show(int[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

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

  public static int[] indexSort(Comparable[] a) {
    int n = a.length;
    int[] index = new int[n];
    for (int i = 0; i < n; i++) index[i] = i;

    int[] aux = new int[n];
    sort(a, index, aux, 0, n - 1);
    return index;
  }

  private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(a, index, aux, lo, mid);
    sort(a, index, aux, mid + 1, hi);
    merge(a, index, aux, lo, mid, hi);
  }
}
