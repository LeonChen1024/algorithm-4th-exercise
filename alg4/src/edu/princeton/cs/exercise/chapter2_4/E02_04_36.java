package edu.princeton.cs.exercise.chapter2_4;

import java.awt.*;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.4.36 Performance driver I. Write a performance driver client program that uses insert to fill a
 * priority queue, then uses remove the maximum to remove half the keys, then uses insert to fill it
 * up again, then uses remove the maximum to remove all the keys, doing so multiple times on random
 * sequences of keys of various lengths ranging from small to large; measures the time taken for
 * each run; and prints out or plots the average running times.
 *
 * <p>性能驱动 I. 编写一个性能驱动客户端程序使用 insert 来填充一个优先队列,然后使用 remove 最大值来移除一半的键,<br>
 * 然后重新填充它,再使用remove最大值来移除所有的键,从小到大使用变长的键序列这么做多次;测量出每次运行需要的时间;<br>
 * 并且绘制出平均运行时间的图
 *
 * @author LeonChen
 * @since 11/16/20
 */
class E02_04_36 {

  /** @param args */
  public static void main(String[] args) {
    int n = 50000;
    StdDraw.setPenRadius(0.01);
    StdDraw.setPenColor(Color.red);
    StdDraw.setXscale(0, n);
    StdDraw.setYscale(0, 0.05);

    int t = 5;

    for (int i = 100; i < n; i *= 2) {
      double totalTime = 0;
      double avgTime = 0;
      for (int j = 0; j < t; j++) {
        Stopwatch stopwatch = new Stopwatch();
        testN(i);
        double time = stopwatch.elapsedTime();
        totalTime += time;
      }
      avgTime = totalTime / t;
      StdOut.println("when n = " + i + ", average time = " + avgTime);
      StdDraw.point(i, avgTime);
    }
  }

  private static void testN(int n) {
    MaxPQ<Double> maxPQ = new MaxPQ<>();
    // 填充数组
    for (int j = 0; j < n; j++) {
      maxPQ.insert(StdRandom.uniform());
    }

    // 移除一半的键
    for (int i = 0; i < n / 2; i++) {
      maxPQ.delMax();
    }

    // 重新填充
    for (int i = 0; i < n / 2; i++) {
      maxPQ.insert(StdRandom.uniform());
    }

    // 移除全部
    while (maxPQ.size() > 0) {
      maxPQ.delMax();
    }
  }
}
