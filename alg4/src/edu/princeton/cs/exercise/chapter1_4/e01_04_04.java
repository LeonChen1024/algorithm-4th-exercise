package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.4 Develop a table like the one on page 181 for TwoSum.
 *
 * <p>给 TwoSum 开发一个如同181页一样的表格.
 *
 * @author LeonChen
 * @since 3/4/20
 */
class e01_04_04 {
  /**
   * A : <code>
   *     int N = a.length;
   *       int cnt = 0;
   *       for (int i = 0; i < N; i++) {
   *         for (int j = i + 1; j < N; j++) {
   *           if (a[i] + a[j] == 0) {
   *             cnt++;
   *           }
   *         }
   *       }
   *       return cnt;
   * </code> <br>
   * B:<code>
   *     for (; i < N; i++) {
   *         for (int j = i + 1; j < N; j++) {
   *           if (a[i] + a[j] == 0) {
   *             cnt++;
   *           }
   *         }
   *       }
   * </code><br>
   * C:<code>
   * for (; j < N; j++) {
   *           if (a[i] + a[j] == 0) {
   *             cnt++;
   *           }
   *         }
   *     </code><br>
   * D:<code>
   *  cnt++;
   * </code>
   *
   * @param args
   */

  // Statement in Block    Time in seconds         Frequency           Total time
  //        D                   t_0           x(depends on input)           t_0x
  //        C                   t_1                N^2/2 - N/2          t_1(N^2/2 - N/2)
  //        B                   t_2                    N                    t_2N
  //        A                   t_3                    1                     t_3

  public static void main(String[] args) {}

  public static class TwoSum {
    public static int count(int[] a) {
      int N = a.length;
      int cnt = 0;
      for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
          if (a[i] + a[j] == 0) {
            cnt++;
          }
        }
      }
      return cnt;
    }

    public static void main(String[] args) {
      int[] a = In.readInts(args[0]);
      StdOut.println(count(a));
    }
  }

  //  public static class TwoSumFast {
  //    public static int count(int[] a) { // Count pairs that sum to 0.
  //      Arrays.sort(a);
  //      int N = a.length;
  //      int cnt = 0;
  //      for (int i = 0; i < N; i++) {
  //        if (BinarySearch.rank(-a[i], a) > i) {
  //          cnt++;
  //        }
  //      }
  //      return cnt;
  //    }
  //
  //    public static void main(String[] args) {
  //      int[] a = In.readInts(args[0]);
  //      StdOut.println(count(a));
  //    }
  //  }
}
