package edu.princeton.cs.exercise.chapter1_2;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.2.1 Write a Point2D client that takes an integer value N from the command line, generates N
 * random points in the unit square, and computes the distance separating the closest pair of
 * points.
 *
 * <p>编写一个 Point2D 客户端从命令行接收一个整数 N ,生成 N 个在单位正方形上的随机点,并且计算最近的一对点的距离.
 *
 * @author LeonChen
 * @since 12/3/19
 */
class e01_02_01 {

  public static void main(String[] args) {
    int N = 100;
    Point2D[] point2DS = new Point2D[N];

    // 计算出 N 个随机点
    StdDraw.setPenColor(StdDraw.RED);
    for (int i = 0; i < N; i++) {
      point2DS[i] = new Point2D(StdRandom.uniform(), StdRandom.uniform());
      point2DS[i].draw();
    }

    double minDistance = Double.MAX_VALUE;
    Point2D minPs = null;
    Point2D minPe = null;
    // 对比每一对点的距离
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        double distance = point2DS[i].distanceTo(point2DS[j]);
        if (minDistance > distance) {
          minDistance = distance;
          minPs = point2DS[i];
          minPe = point2DS[j];
        }
      }
    }

    StdDraw.line(minPs.x(), minPs.y(), minPe.x(), minPe.y());

    StdOut.println(minDistance);
  }
}
