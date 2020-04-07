package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

/**
 * 1.4.39 Improved accuracy for doubling test. Modify DoublingRatio to take a second command-line
 * argument that specifies the number of calls to make to timeTrial() for each value of N. Run your
 * program for 10, 100, and 1,000 trials and comment on the precision of the results.
 *
 * <p>提高翻倍测试的精度.修改 DoublingRatio 来接收一个命令行参数作为第二个参数,这个参数指定了每个N调用 <br>
 * timeTrial() 的次数.分别运行 10,100,和1,000 次 trails并且在结果上评估准确度.
 *
 * @author LeonChen
 * @since 4/1/20
 */
class e01_04_39 {

  /** 如果按照题目要求的数量进行的话,由于机器效率过高,无法比较好的展示,所以做了修改 */
  public static void main(String[] args) {}

  public static class DoublingRatio {
    private static final int MAXIMUM_INTEGER = 1000000;

    // This class should not be instantiated.
    private DoublingRatio() {}

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em> random 6-digit
     * integers.
     *
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()} with <em>n</em> random
     *     6-digit integers
     */
    public static double timeTrial(int n) {
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
      }
      Stopwatch timer = new Stopwatch();
      ThreeSum.count(a);
      return timer.elapsedTime();
    }

    /**
     * Prints table of running times to call {@code ThreeSum.count()} for arrays of size 250, 500,
     * 1000, 2000, and so forth, along with ratios of running times between successive array sizes.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
      int repeatTime = 3;
      double prev = timeTrial(125);
      for (int i = 0; i < repeatTime; i++) {
        for (int n = 100; n <= 10000; n = n * 5) {
          double time = timeTrial(n);
          StdOut.printf("%7d %7.1f %5.1f\n", n, time, time / prev);
          prev = time;
        }
      }
    }
  }
}
