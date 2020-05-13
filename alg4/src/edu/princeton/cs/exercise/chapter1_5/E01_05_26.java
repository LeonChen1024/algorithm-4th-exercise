package edu.princeton.cs.exercise.chapter1_5;

import java.awt.*;

import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 1.5.26 Amortized plot for Erdös-Renyi. Develop a client that takes an int value N from the
 * command line and does an amortized plot of the cost of all operations in the style of the plots
 * in the text for the process of generating random pairs of integers between 0 and N-1, calling
 * connected() to determine if they are connected and then union() if not (as in our development
 * client), looping until all sites are connected.
 *
 * <p>Erdös-Renyi 的摊销图.开发一个客户端从命令行接收一个 N 并且绘制一个所有操作的摊销成本图.使用正文中处理<br>
 * 生成随机0到N-1之间对的样式,调用 connected() 来判断他们是否连接了,如果不是则 union() (如同我们开发的<br>
 * 客户端一样),不断循环直到所有站点被连接.
 *
 * @author LeonChen
 * @since 4/28/20
 */
class E01_05_26 {

  /** */
  public static void main(String[] args) {
    int T = 3;
    int N = 200;
    double usedTime = 0;
    double ave = 0;
    double oldAve = 0;
    int maxN = 1000000;
    while (N < maxN) {
      for (int i = 0; i < T; i++) {
        // test quick-union
        QuickUnionUF uf = new QuickUnionUF(N);
        // test quick-find
        //        QuickFindUF uf = new QuickFindUF(N);
        // test weighted quick-union
        //        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
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
      StdDraw.setPenRadius(0.01);
      StdDraw.setPenColor(Color.blue);
      StdDraw.setXscale(0, maxN);
      StdDraw.setYscale(0, 200);
      StdDraw.point(N, ave);
      N += N;
    }
  }
}
