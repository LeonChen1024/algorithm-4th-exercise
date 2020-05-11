package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 1.5.22 Doubling test for Erdös-Renyi model. Develop a performance-testing client that takes an
 * int value T from the command line and performs T trials of the following experiment: Use your
 * client from Exercise 1.5.17 to generate random connections, using UnionFind to determine
 * connectivity as in our development client, looping until all sites are connected. For each N,
 * print the value of N, the average number of connections processed, and the ratio of the running
 * time to the previous. Use your program to validate the hypotheses in the text that the running
 * times for quick-find and quick-union are quadratic and weighted quick-union is near-linear.
 *
 * <p>对 Erdös-Renyi 模型进行倍率测试.开发一个性能测试客户端从命令行接收一个整数 T 并且执行 T 次下面的试验:<br>
 * 使用你的练习 1.5.17 来生成随机连接,再我们开发的客户端中使用 UnionFind 来判断,不断循环直到所有站点被连接.<br>
 * 对于每一个N,打印出N的值,还有平均处理连接的次数,还有和上一次运行的比例.使用你的程序来验证正文中的假说<br>
 * quick-find 和 quick-union 的运行时间是平方级的以及 weighted quick-union 是接近线性的.
 *
 * @author LeonChen
 * @since 4/27/20
 */
class E01_05_22 {

  /** */
  public static void main(String[] args) {
    int T = 3;
    int N = 200;
    double oldUsedTime = 0;
    double usedTime = 0;
    double ave = 0;
    double oldAve = 0;
    while (N < 1000000) {
      for (int i = 0; i < T; i++) {
        // test quick-union
        //        QuickUnionUF uf = new QuickUnionUF(N);
        // test quick-find
        //        QuickFindUF uf = new QuickFindUF(N);
        // test weighted quick-union
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        Stopwatch timer = new Stopwatch();
        while (uf.count() > 1) {
          int randomSite1 = StdRandom.uniform(N);
          int randomSite2 = StdRandom.uniform(N);

          if (uf.connected(randomSite1, randomSite2)) {
            continue;
          }

          uf.union(randomSite1, randomSite2);
        }
        usedTime = timer.elapsedTime();
        ave += usedTime;
      }
      ave = ave / T;
      double ratio = 0;
      if (oldAve != 0) {
        ratio = ave / oldAve;
      }
      oldAve = ave;
      StdOut.println("N is " + N + " , average used time is " + ave + " , the ratio is " + ratio);
      N += N;
    }
  }
}
