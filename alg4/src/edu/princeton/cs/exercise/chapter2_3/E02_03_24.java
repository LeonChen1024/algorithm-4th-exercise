package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.3.24 Samplesort. (W.Frazer and A.McKellar) Implement a quicksort based on using a sample of
 * size 2^k - 1. First, sort the sample, then arrange to have the recursive routine partition on the
 * median of the sample and to move the two halves of the rest of the sample to each subarray, such
 * that they can be used in the subarrays, without having to be sorted again. This algorithm is
 * called samplesort.
 *
 * <p>采样排序. (W.Frazer and A.McKellar)实现了一个基于对于大小 2^k - 1 进行取样的快排.首先,对样本排序,然后<br>
 * 然后使用样本的中位数进行递归分割，并将样本其余的两半移到每个子数组,这样他们可以在子数组中使用,而不需要再<br>
 * 排序一遍.这个算法叫做 采样排序.
 *
 * @author LeonChen
 * @since 8/21/20
 */
class E02_03_24 {

  /**
   * 通过将采样数组排序后和未排序数组一起分割,使用采样数组中的中位数作为分割点,使得分割点选取更加的合理. <code>
   * n is 1000 , original 0.003 seconds , Samplesort 0.001 seconds
   * n is 2000 , original 0.0 seconds , Samplesort 0.002 seconds
   * n is 4000 , original 0.002 seconds , Samplesort 0.003 seconds
   * n is 8000 , original 0.005 seconds , Samplesort 0.005 seconds
   * n is 16000 , original 0.006 seconds , Samplesort 0.01 seconds
   * n is 32000 , original 0.01 seconds , Samplesort 0.013 seconds
   * n is 64000 , original 0.032 seconds , Samplesort 0.033 seconds
   * n is 128000 , original 0.053 seconds , Samplesort 0.038 seconds
   * n is 256000 , original 0.131 seconds , Samplesort 0.156 seconds
   * n is 512000 , original 0.426 seconds , Samplesort 0.315 seconds
   * n is 1024000 , original 0.586 seconds , Samplesort 0.474 seconds
   * </code>
   */
  public static void main(String[] args) {
    int k = 5;
    for (int n = 1000; n < 2000000; n = n * 2) {
      Comparable[] a = new Comparable[n];

      for (int i = 0; i < n; i++) {
        a[i] = StdRandom.uniform(1000);
      }
      Comparable[] b = new Comparable[n];
      System.arraycopy(a, 0, b, 0, a.length);

      Stopwatch timer = new Stopwatch();
      Quick.sort(a);
      double originTime = timer.elapsedTime();

      timer = new Stopwatch();

      sampleSort(b, k);

      double threeTime = timer.elapsedTime();

      assert isSorted(a);
      assert isSorted(b);
      StdOut.println(
          "n is "
              + n
              + " , original "
              + originTime
              + " seconds , Samplesort "
              + threeTime
              + " seconds ");
    }
  }

  private static void sampleSort(Comparable[] b, int k) {
    if (b.length < Math.pow(2, k + 1)) {
      // 小于 2^(k+1) 的数组直接进行快排,避免剩余未排序数量比采样数组还小
      Quick.sort(b);
      return;
    }
    StdRandom.shuffle(b);
    int samplehi = (int) Math.pow(2, k) - 2;
    // 利用快速排序对取样数组进行排序
    Quick.sort(b, 0, samplehi);
    // 找到取样数组的中位数
    int sampleMid = samplehi / 2;
    // 将取样数组后半部分放到数组末尾
    int i = samplehi, j = b.length - 1;
    while (i != sampleMid) exch(b, i--, j--);
    sort(b, 0, sampleMid, j, b.length - 1);
  }

  private static void sort(Comparable[] b, int samplelo, int lo, int hi, int samplehi) {
    if (hi <= lo) return;

    // 将采样数组中点作为分割点
    int j = partition(b, lo, hi);
    // 采样数组后半部分已经是比较过的,直接放到最后
    if (lo - samplelo > 1) {
      // p 应该始终指向有序部分的最后一项
      // v 应该始终指向有序部分的前面一项
      int p = lo - 1, v = j - 1;
      for (int i = 0; i < (lo - samplelo) / 2; i++) {
        exch(b, p--, v--);
      }
      sort(b, samplelo, p, v, j - 1);
    } else {
      // 取样数组已经用完，退化为普通 Quicksort
      Quick.sort(b, samplelo, j - 1);
    }

    // 采样数组前半部分已经是比较过的,直接放到前面
    if (samplehi - hi > 1) {
      // p 应该始终指向有序部分的前面一项
      // v 应该始终指向有序部分的最后一项
      int p = hi, v = j;
      for (int i = 0; i < (samplehi - hi) / 2; i++) {
        exch(b, ++p, ++v);
      }
      sort(b, j + 1, v, p, samplehi);
    } else {
      // 取样数组已用完，退化为普通 Quicksort
      Quick.sort(b, j + 1, samplehi);
    }
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

  // print array to standard output
  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }
}
