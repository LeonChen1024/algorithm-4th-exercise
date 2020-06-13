package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.28 Equal keys. Formulate and validate hypotheses about the running time of insertion sort and
 * selection sort for arrays that contain just two key values, assuming that the values are equally
 * likely to occur.
 *
 * <p>相等的键. 说明和验证假说关于一个数组只有两种键的值的情况下插入排序和选择排序的运行时间,假设两种值出现的概率相同.
 *
 * @author LeonChen
 * @since 5/26/20
 */
class E02_01_28 {

  private static final String INSERTION = "INSERTION";
  private static final String SELECTION = "SELECTION";

  /**
   * 插入排序的运行时间更短,选择排序每次都需要对比所有的元素,而插入排序则不用.<br>
   * 将上一题的答案稍微修改一下即可得证.通过随机数以0.5为分界的概率将值分为0和1,此时0,1出现概率相同.
   *
   * @param args
   */
  public static void main(String[] args) {
    StdOut.println("========================== INSERTION ==============================");
    timeUsed(INSERTION);
    StdOut.println("========================== SELECTION ==============================");
    timeUsed(SELECTION);
  }

  private static void timeUsed(String sortType) {
    int size = 128;
    double time = 0;

    while (size < 50000) {
      Comparable[] a = new Comparable[size];
      for (int i = 0; i < size; i++) {
        a[i] = StdRandom.uniform() >= 0.5 ? 1 : 0;
      }
      Stopwatch timer = new Stopwatch();
      switch (sortType) {
        case INSERTION:
          Insertion.sort(a);
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
