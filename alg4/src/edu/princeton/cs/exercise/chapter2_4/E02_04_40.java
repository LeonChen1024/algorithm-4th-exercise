package edu.princeton.cs.exercise.chapter2_4;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.4.40 Floyd’s method. Implement a version of heapsort based on Floyd’s
 * sink-to-the-bottom-and-then-swim idea, as described in the text. Count the number of compares
 * used by your program and the number of compares used by the standard implementation, for randomly
 * ordered distinct keys with N = 10^3, 10^6, and 10^9.
 *
 * <p>弗洛伊德方法.实现一个基于弗洛伊德的下沉到底部然后在上浮想法的堆排序版本,如正文中描述的那样.计算你的程序和标准<br>
 * 实现的对比次数,使用无序不同的键满足 N = 10^3, 10^6, 和 10^9.
 *
 * @author LeonChen
 * @since 11/23/20
 */
class E02_04_40 {

  /** @param args */
  public static void main(String[] args) {

    int[] ns = new int[] {(int) Math.pow(10, 3), (int) Math.pow(10, 6), (int) Math.pow(10, 9)};
    int t = 3;

    for (int i = 0; i < ns.length; i++) {
      int n = ns[i];

      Integer[] a = new Integer[n];
      Integer[] a1 = new Integer[n];
      for (int j = 0; j < n; j++) {
        int uniform = StdRandom.uniform(1000000000);
        a[j] = uniform;
      }

      System.arraycopy(a, 0, a1, 0, n);
      MaxPQStd maxPQ = new MaxPQStd(a);
      MaxPQFloyd maxPQF = new MaxPQFloyd(a1);

      for (int j = 0; j < n; j++) {
        maxPQ.delMax();
        maxPQF.delMax();
      }
      StdOut.println(
          " std compare count = " + maxPQ.compareCount + " , Floyd count = " + maxPQF.compareCount);
    }
  }

  private static class MaxPQStd extends MaxPQ<Integer> {
    private int compareCount = 0;

    public MaxPQStd(Integer[] a) {
      super(a);
    }

    @Override
    protected boolean less(int i, int j) {
      compareCount++;
      return super.less(i, j);
    }
  }

  private static class MaxPQFloyd<Key> extends MaxPQ<Key> {
    private int compareCount = 0;

    public MaxPQFloyd(Key[] a) {
      super(a);
    }

    public Key delMax() {
      if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
      Key max = pq[1];
      super.exch(1, n--);
      sinkThenSwim(1);
      pq[n + 1] = null; // to avoid loitering and help with garbage collection
      if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
      return max;
    }

    private void sinkThenSwim(int k) {
      while (2 * k <= n) {
        int j = 2 * k;
        if (j < n && less(j, j + 1)) j++;
        exch(k, j);
        k = j;
      }
      swim(k);
    }

    @Override
    protected boolean less(int i, int j) {
      compareCount++;
      return super.less(i, j);
    }
  }
}
