package edu.princeton.cs.exercise.chapter2_2;

import java.awt.*;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.2.6 Write a program to compute the exact value of the number of array accesses used by top-down
 * mergesort and by bottom-up mergesort. Use your program to plot the values for N from 1 to 512,
 * and to compare the exact values with the upper bound 6NlgN.
 *
 * <p>编写一个程序来计算 top-down 合并排序以及 bottom-up 合并排序准确的数组访问次数.使用你程序绘制出 N 从1到<br>
 * 512 对应的值,并且和上界 6NlgN对比
 *
 * @author LeonChen
 * @since 6/22/20
 */
class E02_02_06 {

  /** */
  public static void main(String[] args) {
    StdDraw.setCanvasSize(1000, 1000);
    StdDraw.setXscale(0, 512);
    StdDraw.setYscale(0, 28000);

    for (int N = 1; N < 512; N++) {
      Comparable[] arr = new Comparable[N];
      // 生成随机数组
      for (int i = 0; i < N; i++) {
        arr[i] = StdRandom.uniform();
      }
      // 复制一份避免另一个排序使用的输入已经是排过序的
      Comparable[] arr2 = new Comparable[N];
      for (int i = 0; i < N; i++) {
        arr2[i] = arr[i];
      }

      Merge topdownMerge = new Merge();
      topdownMerge.sort(arr);
      StdOut.println(
          "N = " + N + " top-down mergesort used " + topdownMerge.accessCount + " times access ");
      StdDraw.setPenColor(Color.blue);
      StdDraw.setPenRadius(0.007);
      StdDraw.point(N, topdownMerge.accessCount);

      MergeBU bottomupMerge = new MergeBU();
      bottomupMerge.sort(arr2);
      StdOut.println(
          "N = " + N + " bottom-up mergesort used " + bottomupMerge.accessCount + " times access ");
      StdDraw.setPenColor(Color.red);
      StdDraw.setPenRadius(0.007);
      StdDraw.point(N, bottomupMerge.accessCount);

      //      6NlgN
      StdDraw.setPenColor(Color.GREEN);
      double pow = 1;
      while (Math.pow(2, pow) - N < 0.01) {
        pow += 0.01;
      }

      StdDraw.point(N, 6 * N * pow);
    }
  }

  public static class MergeBU {

    private int accessCount;

    // This class should not be instantiated.
    private MergeBU() {}

    // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
    private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

      // copy to aux[]
      for (int k = lo; k <= hi; k++) {
        accessCount += 2;
        aux[k] = a[k];
      }

      // merge back to a[]
      int i = lo, j = mid + 1;
      for (int k = lo; k <= hi; k++) {
        if (i > mid) {
        } else if (j > hi) {
          accessCount += 2;
          a[k] = aux[i++];
        } else if (less(aux[j], aux[i])) {
          accessCount += 2;
          a[k] = aux[j++];
        } else {
          accessCount += 2;
          a[k] = aux[i++];
        }
      }
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public void sort(Comparable[] a) {
      int n = a.length;
      Comparable[] aux = new Comparable[n];
      for (int len = 1; len < n; len *= 2) {
        for (int lo = 0; lo < n - len; lo += len + len) {
          int mid = lo + len - 1;
          int hi = Math.min(lo + len + len - 1, n - 1);
          merge(a, aux, lo, mid, hi);
        }
      }
      assert isSorted(a);
    }

    /**
     * ********************************************************************* Helper sorting
     * functions. *************************************************************************
     */

    // is v < w ?
    private boolean less(Comparable v, Comparable w) {
      accessCount += 2;
      return v.compareTo(w) < 0;
    }

    /**
     * ************************************************************************* Check if array is
     * sorted - useful for debugging.
     * *************************************************************************
     */
    private boolean isSorted(Comparable[] a) {
      for (int i = 1; i < a.length; i++) if (less(a[i], a[i - 1])) return false;
      return true;
    }

    // print array to standard output
    private void show(Comparable[] a) {
      for (int i = 0; i < a.length; i++) {
        StdOut.println(a[i]);
      }
    }

    /**
     * Reads in a sequence of strings from standard input; bottom-up mergesorts them; and prints
     * them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public void main(String[] args) {
      String[] a = StdIn.readAllStrings();
      edu.princeton.cs.algs4.MergeBU.sort(a);
      show(a);
    }
  }

  public static class Merge {

    private int accessCount = 0;

    // This class should not be instantiated.
    private Merge() {}

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
      // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
      assert isSorted(a, lo, mid);
      assert isSorted(a, mid + 1, hi);

      // copy to aux[]
      for (int k = lo; k <= hi; k++) {
        accessCount += 2;
        aux[k] = a[k];
      }

      // merge back to a[]
      int i = lo, j = mid + 1;
      for (int k = lo; k <= hi; k++) {
        if (i > mid) {
          accessCount += 2;
          a[k] = aux[j++];
        } else if (j > hi) {
          accessCount += 2;
          a[k] = aux[i++];
        } else if (less(aux[j], aux[i])) {
          accessCount += 2;
          a[k] = aux[j++];
        } else {
          accessCount += 2;
          a[k] = aux[i++];
        }
      }

      // postcondition: a[lo .. hi] is sorted
      assert isSorted(a, lo, hi);
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
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
    public void sort(Comparable[] a) {
      Comparable[] aux = new Comparable[a.length];
      sort(a, aux, 0, a.length - 1);
      assert isSorted(a);
    }

    /**
     * ************************************************************************* Helper sorting
     * function. *************************************************************************
     */

    // is v < w ?
    private boolean less(Comparable v, Comparable w) {
      accessCount += 2;
      return v.compareTo(w) < 0;
    }

    /**
     * ************************************************************************* Check if array is
     * sorted - useful for debugging.
     * *************************************************************************
     */
    private boolean isSorted(Comparable[] a) {
      return isSorted(a, 0, a.length - 1);
    }

    private boolean isSorted(Comparable[] a, int lo, int hi) {
      for (int i = lo + 1; i <= hi; i++) {
        accessCount += 2;
        if (less(a[i], a[i - 1])) {
          return false;
        }
      }

      return true;
    }

    /**
     * ************************************************************************* Index mergesort.
     * *************************************************************************
     */
    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    private void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {

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
    public int[] indexSort(Comparable[] a) {
      int n = a.length;
      int[] index = new int[n];
      for (int i = 0; i < n; i++) index[i] = i;

      int[] aux = new int[n];
      sort(a, index, aux, 0, n - 1);
      return index;
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
      if (hi <= lo) return;
      int mid = lo + (hi - lo) / 2;
      sort(a, index, aux, lo, mid);
      sort(a, index, aux, mid + 1, hi);
      merge(a, index, aux, lo, mid, hi);
    }

    // print array to standard output
    private void show(Comparable[] a) {
      for (int i = 0; i < a.length; i++) {
        StdOut.println(a[i]);
      }
    }

    /**
     * Reads in a sequence of strings from standard input; mergesorts them; and prints them to
     * standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public void main(String[] args) {
      String[] a = StdIn.readAllStrings();
      edu.princeton.cs.algs4.Merge.sort(a);
      show(a);
    }
  }
}
