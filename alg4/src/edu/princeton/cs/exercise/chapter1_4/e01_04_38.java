package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

/**
 * 1.4.38 Naive 3-sum implementation. Run experiments to evaluate the following implementation of
 * the inner loop of ThreeSum: <code>
 * for(inti=0;i<N;i++)
 *     for(intj=0;j<N;j++)
 *         for(intk=0;k<N;k++)
 *             if(i<j&&j<k)
 *                 if(a[i]+a[j]+a[k]==0)
 *                     cnt++;
 * </code> Do so by developing a version of DoublingTest that computes the ratio of the running
 * times of this program and ThreeSum.
 *
 * <p>幼稚的 3-sum 实现.对以下使用内部循环的ThreeSum 实现进行测试:
 *
 * <p>通过开发一个 DoublingTest 来计算这种方式和 ThreeSum之间的比例.
 *
 * @author LeonChen
 * @since 3/31/20
 */
class e01_04_38 {

  /** */
  public static void main(String[] args) {
    int max = 10000;

    for (int i = 1; i < 1000000000; i = i * 2) {
      int[] arr = new int[i];
      for (int j = 0; j < i; j++) {
        arr[j] = StdRandom.uniform(-max, max);
      }

      Stopwatch stopwatch = new Stopwatch();

      naiveThreeSum(arr);
      double naiveTime = stopwatch.elapsedTime();

      stopwatch = new Stopwatch();
      ThreeSum.count(arr);
      double time = stopwatch.elapsedTime();
      StdOut.println(
          "when N = "
              + i
              + " , naive used time = "
              + naiveTime
              + " , standard used time = "
              + time
              + " ,ratio = "
              + (naiveTime + 0.00) / time);
    }
  }

  private static int naiveThreeSum(int[] a) {
    int cnt = 0;
    int N = a.length;
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        for (int k = 0; k < N; k++) if (i < j && j < k) if (a[i] + a[j] + a[k] == 0) cnt++;

    return cnt;
  }
}
