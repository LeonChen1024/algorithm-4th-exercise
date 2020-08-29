package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.3.22 Fast 3-way partitioning. (J.Bentley and D.McIlroy) Implement an entropyoptimal sort based
 * on keeping item's with equal keys at both the left and right ends of the subarray. Maintain
 * indices p and q such that a[lo..p-1] and a[q+1..hi] are all equal to a[lo], an index i such that
 * a[p..i-1] are all less than a[lo], and an index j such that a[j+1..q] are all greater than a[lo].
 * Add to the inner partitioning loop code to swap a[i] with a[p] (and increment p) if it is equal
 * to v and to swap a[j] with a[q] (and decrement q) if it is equal to v before the usual
 * comparisons of a[i] and a[j] with v. After the partitioning loop has terminated, add code to swap
 * the items with equal keys into position. Note : This code complements the code given in the text,
 * in the sense that it does extra swaps for keys equal to the partitioning item’s key, while the
 * code in the text does extra swaps for keys that are not equal to the partitioning item’s key.
 *
 * <p>快速3路分割. (J.Bentley 和 D.McIlroy) 实现了一个基于将带有相同键的项放到子数组的最左端和最右端的熵最优排序.<br>
 * 保证索引 p和q使得 a[lo..p-1] 和 a[q+1..hi] 都等于 a[lo], 一个索引i添加到内部分割循环代码中,当它对应的键<br>
 * 等于v交换 a[i] 和 a[p](并自增p) 的值 并且当a[j] 等于v 的时候交换 a[j] 和 a[q](并自减q)的值. 在分割循环<br>
 * 结束后添加代码来交换拥有相同key的项到这个位置.注意: 这个代码完善了正文中的给的代码,并且它对和分割项拥有相同key 的<br>
 * 项做了额外的交换,而正文中的代码对与分割项key不同的项做了额外的交换.
 *
 * @author LeonChen
 * @since 8/20/20
 */
class E02_03_22 {

  // cutoff to insertion sort, must be >= 1
  private static final int INSERTION_SORT_CUTOFF = 8;

  // cutoff to median-of-3 partitioning
  private static final int MEDIAN_OF_3_CUTOFF = 40;

  /**
   * Rearranges the array in ascending order, using the natural order.
   *
   * @param a the array to be sorted
   */
  public static void sort(Comparable[] a) {
    sort(a, 0, a.length - 1);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    int n = hi - lo + 1;

    if (n <= INSERTION_SORT_CUTOFF) {
      // cutoff to insertion sort
      insertionSort(a, lo, hi);
      return;
    } else if (n <= MEDIAN_OF_3_CUTOFF) {
      // use median-of-3 as partitioning element
      int m = median3(a, lo, lo + n / 2, hi);
      exch(a, m, lo);
    } else {
      // 一种取出更准确的中值的办法
      // use Tukey ninther as partitioning element
      int eps = n / 8;
      int mid = lo + n / 2;
      int m1 = median3(a, lo, lo + eps, lo + eps + eps);
      int m2 = median3(a, mid - eps, mid, mid + eps);
      int m3 = median3(a, hi - eps - eps, hi - eps, hi);
      int ninther = median3(a, m1, m2, m3);
      exch(a, ninther, lo);
    }

    // Bentley-McIlroy 3-way partitioning
    int i = lo, j = hi + 1;
    int p = lo, q = hi + 1;
    Comparable v = a[lo];
    while (true) {
      while (less(a[++i], v)) if (i == hi) break;
      while (less(v, a[--j])) if (j == lo) break;

      // pointers cross
      if (i == j && eq(a[i], v)) exch(a, ++p, i);
      if (i >= j) break;

      exch(a, i, j);
      if (eq(a[i], v)) exch(a, ++p, i);
      if (eq(a[j], v)) exch(a, --q, j);
    }

    i = j + 1;
    for (int k = lo; k <= p; k++) exch(a, k, j--);
    for (int k = hi; k >= q; k--) exch(a, k, i++);

    sort(a, lo, j);
    sort(a, i, hi);
  }

  // sort from a[lo] to a[hi] using insertion sort
  private static void insertionSort(Comparable[] a, int lo, int hi) {
    for (int i = lo; i <= hi; i++)
      for (int j = i; j > lo && less(a[j], a[j - 1]); j--) exch(a, j, j - 1);
  }

  // return the index of the median element among a[i], a[j], and a[k]
  private static int median3(Comparable[] a, int i, int j, int k) {
    return (less(a[i], a[j])
        ? (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i)
        : (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
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

  // print array to standard output
  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }

  /**
   * Reads in a sequence of strings from standard input; quicksorts them (using an optimized version
   * of quicksort); and prints them to standard output in ascending order.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    String[] a =
        new String[] {
          "3", "4", "2fh", "99", "23", "2fh", "453g", "2fh", "gre", "43gg", "2fh", "4fg", "3"
        };
    sort(a);
    assert isSorted(a);
    show(a);
  }
}
