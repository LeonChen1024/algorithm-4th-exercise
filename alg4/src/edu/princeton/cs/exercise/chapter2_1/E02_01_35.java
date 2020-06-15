package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.35 Nonuniform distributions. Write a client that generates test data by randomly ordering
 * objects using other distributions than uniform, including the following: <br>
 * ■ Gaussian <br>
 * ■ Poisson <br>
 * ■ Geometric <br>
 * ■ Discrete (see Exercise 2.1.28 for a special case) <br>
 * Develop and test hypotheses about the effect of such input on the performance of the algorithms
 * in this section.
 *
 * <p>不均匀的概率分布.编写一个客户端生成使用一个非均匀分布的随机算法生成测试数据,包括:<br>
 * - 高斯分布<br>
 * - 泊松分布<br>
 * - 几何分布<br>
 * - 离散分布<br>
 * 开发并测试本章中关于输入对于算法性能的影响假说.
 *
 * @author LeonChen
 * @since 5/29/20
 */
class E02_01_35 {

  private static final String INSERTION = "INSERTION";
  private static final String SELECTION = "SELECTION";
  private static final String SHELL = "SHELL";

  /**
   * 使用 StdRandom 中给的API就可以生成那些数据了. <code>
   *  ======================= Gaussian ===============================
   *   when sort is INSERTION , used time is 3.7491999999999996
   *   when sort is SELECTION , used time is 3.0284
   *   when sort is SHELL , used time is 0.0206
   *
   *  ======================= Poisson ===============================
   *  when sort is INSERTION , used time is 2.819
   * when sort is SELECTION , used time is 1.6498000000000002
   * when sort is SHELL , used time is 0.0058000000000000005
   *
   *  ======================= Geometric ===============================
   * when sort is INSERTION , used time is 1.491
   * when sort is SELECTION , used time is 1.5243999999999998
   * when sort is SHELL , used time is 0.004399999999999999
   *
   *  ======================= Discrete ===============================
   *  when sort is INSERTION , used time is 2.0644
   *  when sort is SELECTION , used time is 1.5404
   *  when sort is SHELL , used time is 0.004200000000000001
   * </code>
   *
   * @param args
   */
  public static void main(String[] args) {
    double usedTime = 0;
    String type = SHELL;
    int N = 50000;
    int x = 0;
    while (x < 5) {
      Comparable[] a = getDiscreteArr(N);

      Stopwatch stopwatch = new Stopwatch();
      switch (type) {
        case INSERTION:
          Insertion.sort(a);
          break;
        case SELECTION:
          Selection.sort(a);
          break;
        case SHELL:
          Shell.sort(a);
      }
      usedTime += stopwatch.elapsedTime();
      x++;
    }
    StdOut.println(" when sort is " + type + " , used time is " + usedTime / x);
  }

  private static Comparable[] getDiscreteArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = StdRandom.discrete(new int[] {3, 5, 2});
    }
    return a;
  }

  private static Comparable[] getGeometricArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = StdRandom.geometric(0.6);
    }
    return a;
  }

  private static Comparable[] getPoissonArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = StdRandom.poisson(10);
    }
    return a;
  }

  private static Comparable[] getGaussianArr(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = StdRandom.gaussian();
    }
    return a;
  }
}
