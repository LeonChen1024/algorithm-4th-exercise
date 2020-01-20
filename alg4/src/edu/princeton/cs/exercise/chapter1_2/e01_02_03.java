package edu.princeton.cs.exercise.chapter1_2;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.2.3 Write a n Interval2D client that takes command-line arguments N, min, and max and generates
 * N random 2D intervals whose width and height are uniformly distributed between min and max in the
 * unit square. Draw them on StdDraw and print the number of pairs of intervals that intersect and
 * the number of intervals that are contained in one another.
 *
 * <p>编写一个 Interval2D 客户端接收一个命令行参数N,min,和max并且生成N个随机 2D intervals 宽高是在min 和 max 中<br>
 * 的一个单位正方形中随机分布的.使用StdDraw 进行绘制并打印相交的对的数量,还有包含在另一个intervals中的intervals的数量
 *
 * @author LeonChen
 * @since 12/4/19
 */
class e01_02_03 {

  public static void main(String[] args) {

    int N = 10;
    double min = 0.3;
    double max = 0.9;

    // 生成N个随机 Interval2D
    Interval2D[] interval2DS = new Interval2D[N];
    for (int i = 0; i < N; i++) {
      double xmin = StdRandom.uniform(min, max);
      double xmax = StdRandom.uniform(min, max);
      double ymin = StdRandom.uniform(min, max);
      double ymax = StdRandom.uniform(min, max);

      if (xmax < xmin) {
        double temp = xmax;
        xmax = xmin;
        xmin = temp;
      }

      if (ymax < ymin) {
        double temp = ymax;
        ymax = ymin;
        ymin = temp;
      }
      Interval1D in1 = new Interval1D(xmin, xmax);
      Interval1D in2 = new Interval1D(ymin, ymax);

      Interval2D interval2D = new Interval2D(in1, in2);
      interval2DS[i] = interval2D;
      interval2D.draw();
    }
    // 计算相交数量
    for (int i = 0; i < N; i++) {
      Interval2D interval = interval2DS[i];
      for (int j = i + 1; j < N; j++) {
        if (interval.intersects(interval2DS[j])) {
          System.out.println(interval + " " + interval2DS[j]);
          break;
        }
      }
    }
  }
}
