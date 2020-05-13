package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 1.5.25 Doubling test for random grids. Develop a performance-testing client that takes an int
 * value T from the command line and performs T trials of the following experiment: Use your client
 * from Exercise 1.5.18 to generate the connections in an N-by-N square grid, randomly oriented and
 * in random order, then use UnionFind to determine connectivity as in our development client,
 * looping until all sites are connected. For each N, print the value of N, the average number of
 * connections processed, and the ratio of the running time to the previous. Use your program to
 * validate the hypotheses in the text that the running times for quick-find and quick-union are
 * quadratic and weighted quick-union is near-linear. Note : As N doubles, the number of sites in
 * the grid increases by a factor of 4, so expect a doubling factor of 16 for quadratic and 4 for
 * linear.
 *
 * <p>为随机网格进行倍率测试.开发一个性能测试客户端,从命令行接收一个整数 T 并且执行 T 次下面的试验:<br>
 * 使用你练习1.5.18的客户端来生成 N*N的网格的连接,使用随机的方向和随机的顺序,然后使用 UnionFind 在我们的<br>
 * 客户端中判断连接,循环直到所有的站点被连接.对于每个N ,打印出N的值,以及平均处理的连接数量,还有和上一次的<br>
 * 运行时间的对比.使用你的程序证明正文中的假说,quick-find 和 quick-union 是平方级别的,而 weighted quick-union<br>
 * 是接近线性的.注意: 随着 N 翻倍,往各种的站点乘以4倍,所以平方的因子应该是16而线性的因子应该是4.
 *
 * @author LeonChen
 * @since 4/28/20
 */
class E01_05_25 {

  /** */
  public static void main(String[] args) {
    StdOut.println("===============test quick-find start ======================");
    testqf(5, 1000, 1);
    StdOut.println("===============test quick-union start ======================");
    testqu(5, 1000, 1);
    StdOut.println("===============test weighted quick-union start ======================");
    testwqu(5, 1000, 1);
  }

  private static void testqf(int n, int maxN, int T) {
    int N = n;
    double oldTimeAverage = Double.MIN_VALUE;
    while (N < maxN) {
      QuickFindUF uf = new QuickFindUF(N * N);

      double usedTime = 0;
      double usedTimeTotal = 0;
      double usedTimeAverage = 0;
      int useConnectionNumTotal = 0;
      int useConnectionNumAverage = 0;

      for (int i = 0; i < T; i++) {
        Stopwatch stopwatch = new Stopwatch();
        int useConnectionNum = 0;

        for (E01_05_18.Connection connection : E01_05_18.generate(N)) {
          if (uf.count() > 1) {
            useConnectionNum++;
            if (uf.connected(connection.p, connection.q)) {
              continue;
            }

            uf.union(connection.p, connection.q);
          } else {
            break;
          }
        }
        useConnectionNumTotal += useConnectionNum;
        usedTime = stopwatch.elapsedTime();
        usedTimeTotal += usedTime;
      }
      usedTimeAverage = usedTimeTotal / T;
      useConnectionNumAverage = useConnectionNumTotal / T;
      double ratio;
      ratio = usedTimeAverage / oldTimeAverage;
      oldTimeAverage = usedTimeAverage;
      StdOut.println(
          "N is "
              + N
              + " ,  used connection num is "
              + useConnectionNumAverage
              + " , used time is "
              + usedTimeAverage
              + " , the ratio is "
              + ratio);
      N += N;
    }
  }

  private static void testqu(int n, int maxN, int T) {
    int N = n;
    double oldTimeAverage = Double.MIN_VALUE;
    while (N < maxN) {
      QuickUnionUF uf = new QuickUnionUF(N * N);

      double usedTime = 0;
      double usedTimeTotal = 0;
      double usedTimeAverage = 0;
      int useConnectionNumTotal = 0;
      int useConnectionNumAverage = 0;

      for (int i = 0; i < T; i++) {
        Stopwatch stopwatch = new Stopwatch();
        int useConnectionNum = 0;

        for (E01_05_18.Connection connection : E01_05_18.generate(N)) {
          if (uf.count() > 1) {
            useConnectionNum++;
            if (uf.connected(connection.p, connection.q)) {
              continue;
            }

            uf.union(connection.p, connection.q);
          } else {
            break;
          }
        }
        useConnectionNumTotal += useConnectionNum;
        usedTime = stopwatch.elapsedTime();
        usedTimeTotal += usedTime;
      }
      usedTimeAverage = usedTimeTotal / T;
      useConnectionNumAverage = useConnectionNumTotal / T;
      double ratio;
      ratio = usedTimeAverage / oldTimeAverage;
      oldTimeAverage = usedTimeAverage;
      StdOut.println(
          "N is "
              + N
              + " ,  used connection num is "
              + useConnectionNumAverage
              + " , used time is "
              + usedTimeAverage
              + " , the ratio is "
              + ratio);
      N += N;
    }
  }

  private static void testwqu(int n, int maxN, int T) {
    int N = n;
    double oldTimeAverage = Double.MIN_VALUE;
    while (N < maxN) {
      WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N * N);

      double usedTime = 0;
      double usedTimeTotal = 0;
      double usedTimeAverage = 0;
      int useConnectionNumTotal = 0;
      int useConnectionNumAverage = 0;

      for (int i = 0; i < T; i++) {
        Stopwatch stopwatch = new Stopwatch();
        int useConnectionNum = 0;

        for (E01_05_18.Connection connection : E01_05_18.generate(N)) {
          if (uf.count() > 1) {
            useConnectionNum++;
            if (uf.connected(connection.p, connection.q)) {
              continue;
            }

            uf.union(connection.p, connection.q);
          } else {
            break;
          }
        }
        useConnectionNumTotal += useConnectionNum;
        usedTime = stopwatch.elapsedTime();
        usedTimeTotal += usedTime;
      }
      usedTimeAverage = usedTimeTotal / T;
      useConnectionNumAverage = useConnectionNumTotal / T;
      double ratio;
      ratio = usedTimeAverage / oldTimeAverage;
      oldTimeAverage = usedTimeAverage;
      StdOut.println(
          "N is "
              + N
              + " ,  used connection num is "
              + useConnectionNumAverage
              + " , used time is "
              + usedTimeAverage
              + " , the ratio is "
              + ratio);
      N += N;
    }
  }
}
