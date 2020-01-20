package edu.princeton.cs.exercise;

import edu.princeton.cs.algs4.StdOut;

public class Fibonacci {

  private static Long[] arr;

  public static long F(int N) {
    if (N == 0) return 0;
    if (N == 1) return 1;
    return F(N - 1) + F(N - 2);
  }

  public static void main(String[] args) {
    arr = new Long[100];
    for (int N = 0; N < 100; N++) {
      //      StdOut.println(N + " " + F(N));
      //      StdOut.println(N + " " + F1(N));
      StdOut.println(N + " " + F2(N));
    }
  }

  /**
   * 通过从小到大进行计算,避免多次重复计算.
   *
   * @param N
   * @return
   */
  public static long F1(int N) {
    if (N == 0) return 0;
    if (N == 1) return 1;
    long f = 0;
    long g = 1;
    for (int i = 0; i < N; i++) {
      f = f + g;
      g = f - g;
    }
    return f;
  }

  /**
   * 通过保存每次计算值来节省时间
   *
   * @param N
   * @return
   */
  public static long F2(int N) {
    if (N == 0) return 0;
    if (N == 1) return 1;
    if (arr[N] != null) {
      return arr[N];
    }
    arr[N] = F2(N - 1) + F2(N - 2);
    return arr[N];
  }
}
