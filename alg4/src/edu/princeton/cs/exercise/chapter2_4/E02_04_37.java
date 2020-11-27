package edu.princeton.cs.exercise.chapter2_4;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.4.37 Performance driver II. Write a performance driver client program that uses insert to fill
 * a priority queue, then does as many remove the maximum and insert operations as it can do in 1
 * second, doing so multiple times on random sequences of keys of various lengths ranging from small
 * to large; and prints out or plots the average number of remove the maximum operations it was able
 * to do.
 *
 * <p>性能驱动 II.编写一个性能驱动客户端程序使用 insert 来填充优先队列,然后尽可能的在1秒内删除最大值并且插入,<br>
 * 在一个随机键变长序列中执行多次并且序列长度从小到大;并且打印出或绘制出移除最大值操作的平均次数.
 *
 * @author LeonChen
 * @since 11/17/20
 */
class E02_04_37 {
  static boolean run = true;

  /** @param args */
  public static void main(String[] args) {

    int n = 50000;
    StdDraw.setPenRadius(0.01);
    StdDraw.setPenColor(Color.red);
    StdDraw.setXscale(0, n);
    StdDraw.setYscale(0, 20000000);

    int t = 5;

    for (int i = 100; i < n; i *= 2) {
      double totalCount = 0;
      double avgCount = 0;
      for (int j = 0; j < t; j++) {
        Stopwatch stopwatch = new Stopwatch();
        totalCount += testN(i);
      }
      avgCount = totalCount / t;
      StdOut.println("when n = " + i + ", average count = " + avgCount);
      StdDraw.point(i, avgCount);
    }
  }

  private static double testN(int n) {
    MaxPQ<Double> maxPQ = new MaxPQ<>();
    // 填充数组
    for (int j = 0; j < n; j++) {
      maxPQ.insert(StdRandom.uniform());
    }

    Timer timer = new Timer();
    timer.schedule(
        new TimerTask() {
          @Override
          public void run() {
            run = false;
          }
        },
        1000);

    double count = 0;

    while (run) {
      maxPQ.delMax();
      maxPQ.insert(StdRandom.uniform());
      count++;
    }
    run = true;

    return count;
  }
}
