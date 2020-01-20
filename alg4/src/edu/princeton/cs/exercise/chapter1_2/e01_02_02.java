package edu.princeton.cs.exercise.chapter1_2;

import java.util.Arrays;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdIn;

/**
 * 1.2.2 Write a n Interval1D client that takes an int value N as command-line argument, reads N
 * intervals (each defined by a pair of double values) from standard input, and prints all pairs
 * that intersect.
 *
 * <p>编写一个 Interval1D 客户端从命令行接收一个 int 值 N 参数. 读取 N个间隔(每一个间隔都是由一对 double 值定义<br>
 * 而来) 并打印出所有相交的数值对.
 *
 * @author LeonChen
 * @since 12/3/19
 */
class e01_02_02 {

  public static void main(String[] args) {

    int N = 10;
    Interval1D[] intervals = new Interval1D[N];
    for (int i = 0; i < N; i++) {
      double d1 = StdIn.readDouble();
      double d2 = StdIn.readDouble();
      intervals[i] = new Interval1D(d1, d2);
    }
    Arrays.sort(intervals, Interval1D.MIN_ENDPOINT_ORDER);
    for (int i = 0; i < N; i++) {
      Interval1D interval = intervals[i];
      for (int j = i + 1; j < N; j++) {
        if (interval.intersects(intervals[j])) {
          System.out.println(interval + " " + intervals[j]);
          break;
        }
      }
    }
  }
}
