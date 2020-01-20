package edu.princeton.cs.exercise.chapter1_2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.2.10 Develop a class VisualCounter that allows both increment and decrement operations. Take
 * two arguments N and max in the constructor, where N specifies the maximum number of operations
 * and max specifies the maximum absolute value for the counter. As a side effect, create a plot
 * showing the value of the counter each time its tally changes.
 *
 * <p>开发一个类 VisualCounter 允许增加和减少的操作.在构造器中接收两个参数 N 和 max , N 指明操作的最大次数,max <br>
 * 指定了计数的最大绝对值.作为一个副作用,创建一个图像展示每次计数的值的改变.
 *
 * @author LeonChen
 * @since 12/5/19
 */
class e01_02_10 {

  public static void main(String[] args) {
    VisualCounter counter = new VisualCounter(20, 10);
    for (int i = 0; i < 30; i++)
      if (StdRandom.uniform() > 0.5) {
        counter.increment();
      } else {
        counter.decrement();
      }
    StdOut.println(counter.curTime + " , " + counter.total);
  }

  public static class VisualCounter {

    /** current use time. */
    private int curTime = 0;

    /** current value num */
    private int total = 0;

    /** count absolute max number. */
    private int max;
    /** operations max times. */
    private int N;

    public VisualCounter(int N, int max) {
      this.N = N;
      this.max = max;
      StdDraw.setXscale(0, N);
      StdDraw.setYscale(-max, max);
      StdDraw.line(0, 0, N, 0);
      StdDraw.setPenRadius(0.05);
    }

    public void increment() {
      if (curTime < N) {
        curTime++;
        if (total < max) {
          total++;
        }
        StdDraw.point(curTime, total);
      }
    }

    public void decrement() {
      if (curTime < N) {
        curTime++;
        if (total > -max) {
          total--;
        }
        StdDraw.point(curTime, total);
      }
    }
  }
}
