package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.31 Doubling test. Write a client that performs a doubling test for sort algorithms. Start at
 * N equal to 1000, and print N, the predicted number of seconds, the actual number of seconds, and
 * the ratio as N doubles. Use your program to validate that insertion sort and selection sort are
 * quadratic for random inputs, and formulate and test a hypothesis for shellsort.
 *
 * <p>双倍测试. 编写一个客户端对排序算法执行双倍测试.从 N等于1000开始,并且打印出N,预测用时秒数,实际用时秒数,<br>
 * 还有和N翻倍前后的比例.使用你的程序来验证插入排序和选择排序对于随机输入是平方级的,并且算出并验证 shellsort的假说.
 *
 * @author LeonChen
 * @since 5/27/20
 */
class E02_01_31 {

  /**
   * <code>
   *  ================= Insertion.sort ================================
   *  when N= 1000 , used time is 0.015 , the ratio is Infinity
   *  when N= 2000 , used time is 0.007 , the ratio is 0.4666666666666667
   *  when N= 4000 , used time is 0.025 , the ratio is 3.5714285714285716
   *  when N= 8000 , used time is 0.086 , the ratio is 3.4399999999999995
   *  when N= 16000 , used time is 0.531 , the ratio is 6.174418604651164
   *  when N= 32000 , used time is 1.609 , the ratio is 3.030131826741996
   *  when N= 64000 , used time is 6.357 , the ratio is 3.950901180857676
   *  when N= 128000 , used time is 33.598 , the ratio is 5.285197420166745
   *
   *  可以看到插入排序是平方级的.
   *  ================= Selection.sort ================================
   *  when N= 1000 , used time is 0.017 , the ratio is Infinity
   *  when N= 2000 , used time is 0.02 , the ratio is 1.1764705882352942
   *  when N= 4000 , used time is 0.024 , the ratio is 1.2
   *  when N= 8000 , used time is 0.075 , the ratio is 3.125
   *  when N= 16000 , used time is 0.178 , the ratio is 2.3733333333333335
   *  when N= 32000 , used time is 0.771 , the ratio is 4.331460674157304
   *  when N= 64000 , used time is 3.621 , the ratio is 4.696498054474708
   *  when N= 128000 , used time is 16.119 , the ratio is 4.451532725766363
   *
   *  可以看到选择排序是平方级的.
   *
   *  ================= Shell.sort ================================
   *  when N= 1000 , used time is 0.003 , the ratio is Infinity
   *  when N= 2000 , used time is 0.004 , the ratio is 1.3333333333333333
   *  when N= 4000 , used time is 0.004 , the ratio is 1.0
   *  when N= 8000 , used time is 0.006 , the ratio is 1.5
   *  when N= 16000 , used time is 0.006 , the ratio is 1.0
   *  when N= 32000 , used time is 0.013 , the ratio is 2.1666666666666665
   *  when N= 64000 , used time is 0.031 , the ratio is 2.3846153846153846
   *  when N= 128000 , used time is 0.083 , the ratio is 2.67741935483871
   *  when N= 256000 , used time is 0.232 , the ratio is 2.7951807228915664
   *  when N= 512000 , used time is 0.605 , the ratio is 2.607758620689655
   *
   *  可以看出 希尔排序是接近线性的,次平方级.
   * </code>
   *
   * @param args
   */
  public static void main(String[] args) {
    double usedTime = 0;
    double oldTime = 0;
    for (int i = 0; i < 3; i++) {
      switch (i) {
        case 0:
          StdOut.println("===================Insertion.sort======================");
          break;
        case 1:
          StdOut.println("===================Selection.sort======================");
          break;
        case 2:
          StdOut.println("===================Shell.sort======================");
      }
      for (int N = 1000; N < 200000; N = N * 2) {
        oldTime = usedTime;
        Comparable[] a = new Comparable[N];
        for (int j = 0; j < N; j++) {
          a[j] = StdRandom.uniform(N);
        }

        Stopwatch stopwatch = new Stopwatch();
        switch (i) {
          case 0:
            Insertion.sort(a);
            break;
          case 1:
            Selection.sort(a);
            break;
          case 2:
            Shell.sort(a);
        }

        usedTime = stopwatch.elapsedTime();
        StdOut.println(
            " when N= "
                + N
                + " , used time is "
                + usedTime
                + " , the ratio is "
                + usedTime / oldTime);
      }
    }
  }
}
