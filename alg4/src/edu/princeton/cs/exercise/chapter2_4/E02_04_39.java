package edu.princeton.cs.exercise.chapter2_4;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.4.39 Cost of construction. Determine empirically the percentage of time heapsort spends in the
 * construction phase for N = 10^3, 10^6, and 10^9.
 *
 * <p>构造成本.根据试验判断在 N = 10^3, 10^6, 和 10^9 的情况下堆排序的时间百分比消耗在构造里
 *
 * @author LeonChen
 * @since 11/20/20
 */
class E02_04_39 {

  /** @param args */
  public static void main(String[] args) {
    int[] ns = new int[] {(int) Math.pow(10, 3), (int) Math.pow(10, 6), (int) Math.pow(10, 9)};
    int t = 5;

    for (int i = 0; i < ns.length; i++) {
      int n = ns[i];
      MaxPQ maxPQ = new MaxPQ();
      Stopwatch totalWatch = new Stopwatch();
      Stopwatch constructionWatch = new Stopwatch();
      for (int j = 0; j < n; j++) {
        maxPQ.insert(StdRandom.uniform());
      }
      double constructionTime = constructionWatch.elapsedTime();
      for (int j = 0; j < n; j++) {
        maxPQ.delMax();
      }
      double totalTime = totalWatch.elapsedTime();
      StdOut.println("construction time percentage is " + constructionTime / totalTime);
    }
  }
}
