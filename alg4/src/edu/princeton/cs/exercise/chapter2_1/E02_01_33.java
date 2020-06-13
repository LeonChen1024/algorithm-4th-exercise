package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.33 Distribution. Write a client that enters into an infinite loop running sort() on arrays of
 * the size given as the third command-line argument, measures the time taken for each run, and uses
 * StdDraw to plot the average running times. A picture of the distribution of the running times
 * should emerge.
 *
 * <p>分布图.编写一个客户端进入一个无限循环的对给定命令行参数的大小数组运行 sort()方法,测量每次运行的时间,<br>
 * 并且使用 StdDraw 来绘制平均运行时间. 应该会出现一个分布时间图.
 *
 * @author LeonChen
 * @since 5/28/20
 */
class E02_01_33 {

  private static final String INSERTION = "INSERTION";
  private static final String SELECTION = "SELECTION";
  private static final String SHELL = "SHELL";

  /**
   * 每次运行都移动x位置,这样可以比较直观的看到数据分布
   *
   * @param args
   */
  public static void main(String[] args) {
    double usedTime = 0;
    String type = SELECTION;
    initCanvas(300, 8);
    int N = 30000;
    int x = 0;
    while (true) {
      Comparable[] a = new Comparable[N];
      for (int j = 0; j < N; j++) {
        a[j] = StdRandom.uniform(N);
      }

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

      usedTime = stopwatch.elapsedTime();
      StdOut.println(" when x= " + x + " , used time is " + usedTime);
      StdDraw.point(x, usedTime);
      x++;
    }
  }

  private static void initCanvas(int xMax, int yMax) {
    StdDraw.setCanvasSize(500, 500);
    StdDraw.setPenRadius(0.01);
    StdDraw.setXscale(0, xMax);
    StdDraw.setYscale(0, yMax);
  }
}
