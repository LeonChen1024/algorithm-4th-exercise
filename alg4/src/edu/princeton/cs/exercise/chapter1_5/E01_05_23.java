package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 1.5.23 Compare quick-find with quick-union for Erdös-Renyi model. Develop a performance-testing
 * client that takes an int value T from the command line and performs T trials of the following
 * experiment: Use your client from Exercise 1.5.17 to generate random connections. Save the
 * connections, so that you can use both quick-union and quick-find to determine connectivity as in
 * our development client, looping until all sites are connected. For each N, print the value of N
 * and the ratio of the two running times.
 *
 * <p>对于 Erdös-Renyi 模型对比 quick-find 和 quick-union.开发一个性能测试客户端从命令行接收一个整数 T 并且<br>
 * 执行T次下面的试验: 使用你练习 1.5.17 来生成随机连接.保存连接,使得你可以使用 quick-union 和 quick-find <br>
 * 在我们的程序中判断连接,不断循环直到所有站点被连接.对于每个N,打印出 N 的值和两次运行时间的比例.
 *
 * @author LeonChen
 * @since 4/27/20
 */
class E01_05_23 {

  /** */
  @SuppressWarnings(value = "all")
  public static void main(String[] args) {
    int T = 3;
    int N = 200;
    double quUsedTime = 0;
    double qfUsedTime = 0;
    double quAve = 0;
    double qfAve = 0;
    while (N < 100000) {
      for (int i = 0; i < T; i++) {
        // test quick-union
        QuickUnionUF quickUnionUF = new QuickUnionUF(N);
        // test quick-find
        QuickFindUF quickFindUF = new QuickFindUF(N);

        //  quickUnionUF
        Stopwatch qutime = new Stopwatch();
        while (quickUnionUF.count() > 1) {

          int[] connection = E01_05_17.getRadomConnection(N);
          if (quickUnionUF.connected(connection[0], connection[1])) {
            continue;
          }

          quickUnionUF.union(connection[0], connection[1]);
        }
        quUsedTime = qutime.elapsedTime();
        quAve += quUsedTime;

        //  quickFindUF
        Stopwatch qftime = new Stopwatch();
        while (quickFindUF.count() > 1) {
          int[] connection;
          connection = E01_05_17.getRadomConnection(N);
          if (quickFindUF.connected(connection[0], connection[1])) {
            continue;
          }

          quickFindUF.union(connection[0], connection[1]);
        }
        qfUsedTime = qftime.elapsedTime();
        qfAve += qfUsedTime;
      }
      quAve = quAve / T;
      qfAve = qfAve / T;
      double ratio;
      ratio = quAve / qfAve;
      StdOut.println(
          "N is "
              + N
              + " ,  quick-union average used time is "
              + quAve
              + " ,quick-find average used time is "
              + qfAve
              + " , the ratio is "
              + ratio);
      N += N;
    }
  }
}
