package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 1.5.24 Fast algorithms for Erdös-Renyi model. Add weighted quick-union and weighted quick-union
 * with path compression to your tests from Exercise 1.5.23 . Can you discern a difference between
 * these two algorithms?
 *
 * <p>Erdös-Renyi 模型的快速算法.添加 weighted quick-union and weighted quick-union with path <br>
 * compression 到你的练习 1.5.23 中.你能分辨出这两个算法之间的不同吗?
 *
 * @author LeonChen
 * @since 4/29/20
 */
class E01_05_24 {

  /** */
  public static void main(String[] args) {
    int T = 3;
    int N = 200;
    double usedTime = 0;
    double compressionUsedTime = 0;
    double ave = 0;
    double compressionAve = 0;
    while (N < 100000) {
      for (int i = 0; i < T; i++) {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(N);
        E01_05_13.WeightedCompressionQuickUnionUF compressionQuickUnionUF =
            new E01_05_13.WeightedCompressionQuickUnionUF(N);

        //  weighted quick-union
        Stopwatch timer = new Stopwatch();
        while (weightedQuickUnionUF.count() > 1) {

          int[] connection = E01_05_17.getRadomConnection(N);
          if (weightedQuickUnionUF.connected(connection[0], connection[1])) {
            continue;
          }

          weightedQuickUnionUF.union(connection[0], connection[1]);
        }
        usedTime = timer.elapsedTime();
        ave += usedTime;

        //   quick-union with path compression
        Stopwatch compressionTimer = new Stopwatch();
        while (compressionQuickUnionUF.count() > 1) {
          int[] connection;
          connection = E01_05_17.getRadomConnection(N);

          if (compressionQuickUnionUF.connected(connection[0], connection[1])) {
            continue;
          }

          compressionQuickUnionUF.union(connection[0], connection[1]);
        }
        compressionUsedTime = compressionTimer.elapsedTime();
        compressionAve += compressionUsedTime;
      }
      ave = ave / T;
      compressionAve = compressionUsedTime / T;
      double ratio;
      ratio = ave / compressionAve;
      StdOut.println(
          "N is "
              + N
              + " , weighted quick-union average used time is "
              + ave
              + " ,   quick-union with path compression average used time is "
              + compressionAve
              + " , the ratio is "
              + ratio);
      N += N;
    }
  }
}
