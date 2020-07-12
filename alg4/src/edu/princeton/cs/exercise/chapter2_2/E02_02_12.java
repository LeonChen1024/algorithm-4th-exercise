package edu.princeton.cs.exercise.chapter2_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.2.12 Sublinear extra space. Develop a merge implementation that reduces the extra space
 * requirement to max(M, N/M), based on the following idea: Divide the array into N/M blocks of size
 * M (for simplicity in this description, assume that N is a multiple of M). Then, (i) considering
 * the blocks as items with their first key as the sort key, sort them using selection sort; and
 * (ii) run through the array merging the first block with the second, then the second block with
 * the third, and so forth.
 *
 * <p>亚线性的额外空间.开发一个合并实现减少所需的额外空间为最多(M,N/M),使用以下几个主意:<br>
 * 将数组分为 N/M 个大小为 M 的大小(可以简化这个描述,假设 N 是 M 的倍数). 然后, (i) 考虑将这些块的第一个键<br>
 * 作为排序键,使用选择排序来将他们排序;并且 (ii) 将第一个块和第二个块进行合并,然后将第二个块和第三个块合并,<br>
 * 以此类推
 *
 * @author LeonChen
 * @since 6/30/20
 */
class E02_02_12 {

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

  public static void sort(Comparable[] a) {

    int blockSize = 10;
    int blocks = a.length / blockSize;
    Comparable[] aux = new Comparable[blockSize];

    //    (i)
    for (int i = 0; i < blocks; i++) {
      selectionSort(a, i * blockSize, i * blockSize + blockSize);
    }
    selectionSortBlock(a, blockSize);
    StdOut.println("===============mid==================");
    show(a);
    for (int l = 1; l < blocks; l++) {
      for (int i = 0; i < blocks - l; i++) {
        int lo = i * blockSize;
        int mid = lo + blockSize;
        int hi = mid + blockSize;

        int k = 0;
        for (int j = lo; j < mid; j++) {
          aux[k] = a[j];
          k++;
        }
        k = 0;
        int left = lo;
        int right = mid;

        while (k < blockSize && right < hi) {
          if (aux[k].compareTo(a[right]) <= 0) {
            a[left] = aux[k];
            left++;
            k++;
          } else {
            a[left] = a[right];
            right++;
            left++;
          }
        }
        if (right == hi) {
          while (k < blockSize) {
            a[left] = aux[k];
            left++;
            k++;
          }
        }
      }
    }

    StdOut.println("===============end==================");
    show(a);
    assert isSorted(a);
  }

  /**
   * 将block 块排序
   *
   * @param a
   * @param blockSize
   */
  private static void selectionSortBlock(Comparable[] a, int blockSize) {
    for (int i = 0; i < a.length; i = i + blockSize) {
      int min = i;
      for (int j = i; j < a.length; j = j + blockSize) {
        if (a[min].compareTo(a[j]) > 0) {
          min = j;
        }
      }
      if (min != i) {
        for (int j = 0; j < blockSize; j++) {
          exch(a, i + j, min + j);
        }
      }
    }
  }

  private static void selectionSort(Comparable[] a, int lo, int hi) {
    int n = hi - lo;
    for (int i = lo; i < hi; i++) {
      int min = i;
      for (int j = i + 1; j < hi; j++) {
        if (less(a[j], a[min])) min = j;
      }
      exch(a, i, min);
      assert isSorted(a, 0, i);
    }
  }

  // exchange a[i] and a[j]
  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
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
