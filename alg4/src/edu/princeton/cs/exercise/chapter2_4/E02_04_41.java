package edu.princeton.cs.exercise.chapter2_4;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.4.41 Multiway heaps. Implement a version of heapsort based on complete heap-ordered 3-ary and
 * 4-ary trees, as described in the text. Count the number of compares used by each and the number
 * of compares used by the standard implementation, for randomly ordered distinct keys with N =
 * 10^3, 10^6, and 10^9.
 *
 * <p>多路堆.实现一个基于完全堆排序的3路堆和4路堆的堆排序版本,如同正文描述的一样.计算他们的对比次数以及标准实现的<br>
 * 次数,使用无序不重复的键,其中 N =10^3, 10^6, 和 10^9.
 *
 * @author LeonChen
 * @since 11/24/20
 */
class E02_04_41 {

  /**
   * 其实就是子节点和父节点的计算方式需要修改.基本上和标准的2叉堆没有太大的区别<br>
   * k叉堆每个节点有k个子节点.所以首个子节点和最后一个子节点之间想差k-1.可以得出第一层一个节点1.1的子节点是从<br>
   * 2~2+k-1, 2的子节点是 2+k~2+2k-1,3的子节点是 2+2k~2+3K-1. 也就是说一个节点的子节点的位置是,本层节点的最后<br>
   * 一个加上这个节点同层之前的节点的子节点.所以可以得出节点i的子节点是 k(i-1)+2~ki+1,根据这个公式可以反推出<br>
   * 父节点的索引是 (i-2)/k +1 取下限 或者是 (i-2)/k 取上限
   *
   * @param args
   */
  public static void main(String[] args) {

    int[] ns = new int[] {(int) Math.pow(10, 3), (int) Math.pow(10, 6), (int) Math.pow(10, 9)};
    int t = 3;

    for (int i = 0; i < ns.length; i++) {
      int n = ns[i];

      Integer[] a = new Integer[n];
      Integer[] a1 = new Integer[n];
      Integer[] a2 = new Integer[n];
      for (int j = 0; j < n; j++) {
        int uniform = StdRandom.uniform(1000000000);
        a[j] = uniform;
      }

      System.arraycopy(a, 0, a1, 0, n);
      System.arraycopy(a, 0, a2, 0, n);
      MaxPQStd maxPQ = new MaxPQStd(a);
      MaxPQThreeW maxPQThree = new MaxPQThreeW(a1);
      MaxPQFourW maxPQFourW = new MaxPQFourW(a2);

      for (int j = 0; j < n; j++) {
        maxPQ.delMax();
        maxPQThree.delMax();
        maxPQFourW.delMax();
      }
      StdOut.println(
          " std compare count = "
              + maxPQ.compareCount
              + " , Three way count = "
              + maxPQThree.compareCount
              + " , four way count = "
              + maxPQFourW.compareCount);
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

  private static class MaxPQThreeW<Key> extends MaxPQ<Key> {
    private int compareCount = 0;

    public MaxPQThreeW(Key[] a) {
      super(a);
    }

    @Override
    protected void sink(int k) {
      while (3 * (k - 1) + 2 <= n) {
        int j = 3 * (k - 1) + 2;
        int cur = j;
        while (cur <= (3 * k + 1) && cur < n) {
          if (less(j, cur)) {
            j = cur;
          }
          cur++;
        }

        if (!less(k, j)) break;
        exch(k, j);
        k = j;
      }
    }

    @Override
    protected void swim(int k) {
      while (k > 1 && less((k - 2) / 3 + 1, k)) {
        exch(k, (k - 2) / 3 + 1);
        k = (k - 2) / 3 + 1;
      }
    }

    @Override
    protected boolean less(int i, int j) {
      compareCount++;
      return super.less(i, j);
    }
  }

  private static class MaxPQFourW<Key> extends MaxPQ<Key> {
    private int compareCount = 0;

    public MaxPQFourW(Key[] a) {
      super(a);
    }

    @Override
    protected void sink(int k) {
      while (4 * (k - 1) + 2 <= n) {
        int j = 4 * (k - 1) + 2;
        int cur = j;
        while (cur <= (4 * k + 1) && cur < n) {
          if (less(j, cur)) {
            j = cur;
          }
          cur++;
        }

        if (!less(k, j)) break;
        exch(k, j);
        k = j;
      }
    }

    @Override
    protected void swim(int k) {
      while (k > 1 && less((k - 2) / 4 + 1, k)) {
        exch(k, (k - 2) / 4 + 1);
        k = (k - 2) / 4 + 1;
      }
    }

    @Override
    protected boolean less(int i, int j) {
      compareCount++;
      return super.less(i, j);
    }
  }
}
