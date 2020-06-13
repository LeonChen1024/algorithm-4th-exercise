package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.32 Plot running times. Write a client that uses StdDraw to plot the average running times of
 * the algorithm for random inputs and various values of the array size. You may add one or two more
 * command-line arguments. Strive to design a useful tool.
 *
 * <p>运行时间图. 编写一个客户端使用 StdDraw 来绘制在不同数组大小情况下使用随机输入的算法平均值.你可能需要增加<br>
 * 一到两个命令行参数.尽量设计一个有用的工具.
 *
 * @author LeonChen
 * @since 5/28/20
 */
class E02_01_32 {

  private static final String INSERTION = "INSERTION";
  private static final String SELECTION = "SELECTION";
  private static final String SHELL = "SHELL";

  /** @param args */
  public static void main(String[] args) {
    double usedTime = 0;
    double oldTime = 0;
    String type = SHELL;
    initCanvas(200000, 60);

    for (int N = 1000; N < 200000; N = N * 2) {
      oldTime = usedTime;
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
      StdOut.println(
          " when N= "
              + N
              + " , used time is "
              + usedTime
              + " , the ratio is "
              + usedTime / oldTime);
      StdDraw.point(N, usedTime);
    }
  }

  private static void initCanvas(int xMax, int yMax) {
    StdDraw.setCanvasSize(500, 500);
    StdDraw.setPenRadius(0.02);
    StdDraw.setXscale(0, xMax);
    StdDraw.setYscale(0, yMax);
  }
}
