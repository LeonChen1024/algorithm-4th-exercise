package edu.princeton.cs.exercise.chapter2_4;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.4.28 Selection filter. Write a TopM client that reads points (x, y, z) from standard input,
 * takes a value M from the command line, and prints the M points that are closest to the origin in
 * Euclidean distance. Estimate the running time of your client for N = 10^8 andM=10^4.
 *
 * <p>选择过滤.编写一个 TopM 客户端从标准输入中读取点 (x,y,z), 从命令行中读取一个 M 的值,并且打印出M个距离原始<br>
 * 点欧几里得距离最近的点. 估算你的客户端在 N = 10^8 和 M=10^4 时的运行时间.
 *
 * @author LeonChen
 * @since 10/28/20
 */
class E02_04_28 {

  /**
   * 欧几里得距离在3维空间的情况下公式为 d = \sqrt{x^2 + y^2 + z^2} , 我们可以通过计算N的比较小的情况下<br>
   * 得出的值并且不断进行倍率增加计算出时间复杂度.
   */
  public static void main(String[] args) {

    int M = (int) Math.pow(10, 4);
    double lastTime = 1;

    for (int N = (int) Math.pow(10, 5); N < Math.pow(10, 7); N = N * 2) {
      Point[] p = new Point[N];
      for (int i = 0; i < N; i++) {
        p[i] =
            new Point(StdRandom.uniform(10000), StdRandom.uniform(10000), StdRandom.uniform(10000));
      }

      Stopwatch stopwatch = new Stopwatch();
      MaxPQ<Point> maxPQ = new MaxPQ<>();
      // 初始加入 M 个元素用来存储最后要的 M个最小值
      for (int i = 0; i < M; i++) {
        maxPQ.insert(p[i]);
      }
      for (int i = M; i < N; i++) {
        maxPQ.delMax();
        maxPQ.insert(p[i]);
      }
      // 打印距离最小的点, 为了观察时间变化,注释
      //      while (!maxPQ.isEmpty()) {
      //        StdOut.println(maxPQ.delMax());
      //      }
      double time = stopwatch.elapsedTime();
      double ratio = time / lastTime;
      lastTime = time;
      StdOut.println("when N = " + N + ", use time = " + time + ", ratio = " + ratio);
    }
  }

  public static class Point implements Comparable<Point> {
    private int x;
    private int y;
    private int z;
    private Double d;

    public Point(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
      d = Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public int compareTo(Point o) {
      return d.compareTo(o.d);
    }

    @Override
    public String toString() {
      return "Point{" + "x=" + x + ", y=" + y + ", z=" + z + ", d=" + d + '}';
    }
  }
}
