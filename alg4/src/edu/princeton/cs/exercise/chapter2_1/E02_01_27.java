package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.27 Shellsort is subquadratic. Use SortCompare to compare shellsort with insertion sort and
 * selection sort on your computer. Use array sizes that are increasing powers of 2, starting at
 * 128.
 *
 * <p>希尔排序的用时是次平方级的. 使用 SortCompare 在你的电脑上对比希尔排序和插入排序还有选择排序.使用数组<br>
 * 大小按2的幂次增加,从128开始.
 *
 * @author LeonChen
 * @since 5/26/20
 */
class E02_01_27 {

  private static final String INSERTION = "INSERTION";
  private static final String SHELLSORT = "SHELLSORT";
  private static final String SELECTION = "SELECTION";

  /**
   * <code>
   *     ========================== INSERTION ==============================
   * size = 128, the sort used about 0.002seconds
   * size = 256, the sort used about 0.001seconds
   * size = 512, the sort used about 0.007seconds
   * size = 1024, the sort used about 0.012seconds
   * size = 2048, the sort used about 0.012seconds
   * size = 4096, the sort used about 0.027seconds
   * size = 8192, the sort used about 0.089seconds
   * size = 16384, the sort used about 0.352seconds
   * size = 32768, the sort used about 1.408seconds
   * ========================== SHELLSORT ==============================
   * size = 128, the sort used about 0.001seconds
   * size = 256, the sort used about 0.001seconds
   * size = 512, the sort used about 0.001seconds
   * size = 1024, the sort used about 0.002seconds
   * size = 2048, the sort used about 0.003seconds
   * size = 4096, the sort used about 0.003seconds
   * size = 8192, the sort used about 0.003seconds
   * size = 16384, the sort used about 0.003seconds
   * size = 32768, the sort used about 0.008seconds
   * ========================== SELECTION ==============================
   * size = 128, the sort used about 0.002seconds
   * size = 256, the sort used about 0.001seconds
   * size = 512, the sort used about 0.002seconds
   * size = 1024, the sort used about 0.004seconds
   * size = 2048, the sort used about 0.008seconds
   * size = 4096, the sort used about 0.016seconds
   * size = 8192, the sort used about 0.039seconds
   * size = 16384, the sort used about 0.157seconds
   * size = 32768, the sort used about 0.67seconds
   * </code> 可以得证
   *
   * @param args
   */
  public static void main(String[] args) {
    StdOut.println("========================== INSERTION ==============================");
    timeUsed(INSERTION);
    StdOut.println("========================== SHELLSORT ==============================");
    timeUsed(SHELLSORT);
    StdOut.println("========================== SELECTION ==============================");
    timeUsed(SELECTION);
  }

  private static void timeUsed(String sortType) {
    int size = 128;
    double time = 0;

    while (size < 50000) {
      Comparable[] a = new Comparable[size];
      for (int i = 0; i < size; i++) {
        a[i] = StdRandom.uniform(size);
      }
      Stopwatch timer = new Stopwatch();
      switch (sortType) {
        case INSERTION:
          Insertion.sort(a);
          break;
        case SHELLSORT:
          Shell.sort(a);
          break;
        case SELECTION:
          Selection.sort(a);
          break;
      }

      time = timer.elapsedTime();
      StdOut.println("size = " + size + ", the sort used about " + time + "seconds");
      size = size * 2;
    }
  }
}
