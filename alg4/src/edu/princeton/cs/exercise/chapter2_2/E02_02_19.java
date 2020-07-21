package edu.princeton.cs.exercise.chapter2_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.2.19 Inversions. Develop and implement a linearithmic algorithm for computing the number of
 * inversions in a given array (the number of exchanges that would be performed by insertion sort
 * for that array—see Section 2.1). This quantity is related to the Kendall tau distance; see
 * Section 2.5.
 *
 * <p>倒序.开发并实现一个线性级别的算法用来计算给定数组的倒序数量(插入排序对这个数组会做交换的次数).<br>
 * 这个数量和 the Kendall tau distance 相关;可以见 2.5章.
 *
 * @author LeonChen
 * @since 7/14/20
 */
class E02_02_19 {

  private static int exNum = 0;
  /**
   * 也就是计算合并排序的时候需要交换位置的次数
   *
   * @param args
   */
  public static void main(String[] args) {
    Comparable[] a = new Comparable[] {3, 1, 4, 7, 9, 43, 223, 42, 56, 22, 66, 24, 564, 225, 25, 2};
    //    Comparable[] a = new Comparable[] {1, 2, 3, 4, 7, 9, 22, 24, 25, 42, 43, 56, 66, 223, 225,
    // 564};
    //    Comparable[] a = new Comparable[] {3, 1, 4, 7};
    sort(a);
    StdOut.print(exNum);
  }

  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length - 1);
  }

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }
  /**
   * Rearranges the array in ascending order, using the natural order.
   *
   * @param a the array to be sorted
   */
  public static void sort(Comparable[] a) {
    exNum = 0;
    Comparable[] aux = new Comparable[a.length];
    sort(a, aux, 0, a.length - 1);
    assert isSorted(a);
  }

  // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
  private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid + 1, hi);
    merge(a, aux, lo, mid, hi);
  }

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
      else if (less(aux[j], aux[i])) {
        // 因为两个子数组已经是有序的了,所以右边的数之前移动的数字已经是固定的了,那么此时移动的次数就是mid-i+1
        exNum += (mid - i + 1);
        a[k] = aux[j++];
      } else {
        a[k] = aux[i++];
      }
    }

    // postcondition: a[lo .. hi] is sorted
    assert isSorted(a, lo, hi);
  }
}
