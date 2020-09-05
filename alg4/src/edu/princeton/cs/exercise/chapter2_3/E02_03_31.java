package edu.princeton.cs.exercise.chapter2_3;

import java.awt.*;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.3.31 Histogram of running times. Write a program that takes command-line arguments N and T,
 * does T trials of the experiment of running quicksort on an array of N random Double values, and
 * plots a histogram of the observed running times. Run your program for N = 10^3, 10^4, 10^5, and
 * 10^6, with T as large as you can afford to make the curves smooth. Your main challenge for this
 * exercise is to appropriately scale the experimental results.
 *
 * <p>运行时间直方图.编写一个程序接受命令行参数 N 和 T, 对一个包含 N 个随机 Double 值的数组运行快排进行 T 次试验,<br>
 * 并且绘制一个观察运行时间的直方图. 针对 N = 10^3, 10^4, 10^5, 和 10^6,在你能接受的范围将 T 的值尽量设的大一些<br>
 * 使得曲线变得平滑.你在这个练习中主要的挑战是适当的扩大这个试验的结果
 *
 * @author LeonChen
 * @since 8/31/20
 */
class E02_03_31 {

  /**
   * <code>
   * </code>
   */
  public static void main(String[] args) {

    int[] ns =
        new int[] {
          (int) Math.pow(10, 3), (int) Math.pow(10, 4), (int) Math.pow(10, 5), (int) Math.pow(10, 6)
        };
    //    int N = (int) Math.pow(10, 3);
    //    int N = (int) Math.pow(10, 4);
    //    int N = (int) Math.pow(10, 5);
    int N = (int) Math.pow(10, 6);
    int T = 120;
    StdDraw.setCanvasSize(1300, 900);
    StdDraw.setXscale(-1, T);
    StdDraw.setPenRadius(0.01);
    StdDraw.setPenColor(Color.BLACK);

    double[] times = new double[T];
    double minTime = Double.MAX_VALUE;
    double maxTime = Double.MIN_VALUE;
    for (int i = 0; i < T; i++) {
      Comparable[] a = new Comparable[N];

      for (int j = 0; j < N; j++) {
        a[j] = StdRandom.uniform(N);
      }

      Stopwatch timer = new Stopwatch();
      Quick.sort(a);
      double time = timer.elapsedTime();
      if (time > maxTime) {
        maxTime = time;
      }
      if (time < minTime) {
        minTime = time;
      }
      times[i] = time;
      StdOut.println(" n is " + N + ",T is" + T + " , use time is " + time);
    }
    StdDraw.setYscale(0, maxTime * 1.05);
    for (int i = 0; i < T; i++) {
      StdDraw.filledRectangle(i, times[i] / 2, 0.3, times[i] / 2);
    }
  }
}
