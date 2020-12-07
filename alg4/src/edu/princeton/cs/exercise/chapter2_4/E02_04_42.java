package edu.princeton.cs.exercise.chapter2_4;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.4.42 Preorder heaps. Implement a version of heapsort based on the idea of representing the
 * heap-ordered tree in preorder rather than in level order. Count the number of compares used by
 * your program and the number of compares used by the standard implementation, for randomly ordered
 * keys with N = 10^3, 10^6, and 10^9.
 *
 * <p>前序堆.实现一个堆排序使用前序遍历而不是层序遍历.计算你的程序和标准实现的对比次数,使用无序键并且 N = 10^3,<br>
 * 10^6, and 10^9.
 *
 * @author LeonChen
 * @since 11/28/20
 */
class E02_04_42 {

  private static int compareTime;

  /**
   * 分析详见 E02_04_42.md
   *
   * @param args
   */
  public static void main(String[] args) {

    int[] ns = new int[] {(int) Math.pow(10, 3), (int) Math.pow(10, 6), (int) Math.pow(10, 9)};
    for (int i = 0; i < ns.length; i++) {
      int n = ns[i];
      Integer[] a = new Integer[n];
      Integer[] b = new Integer[n];
      for (int j = 0; j < n; j++) {
        int uniform = StdRandom.uniform(n);
        a[j] = uniform;
      }
      System.arraycopy(a, 0, b, 0, a.length);
      preOrderHeapSort(a);
      MaxPQStd std = new MaxPQStd(b);
      for (int j = 0; j < b.length; j++) {
        std.delMax();
      }
      StdOut.println(
          "preorder compare time = " + compareTime + ", std compare time = " + std.compareCount);
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

  private static int preOrderHeapSort(Integer[] a) {
    compareTime = 0;
    buildHeap(a, 0, a.length);

    // 排序
    int n = a.length;
    while (n > 1) {
      int tail = getLast(a, 0, n);
      Integer t = a[tail];
      for (int i = tail + 1; i < n; i++) {
        a[i - 1] = a[i];
      }
      n--;
      exch(a, 0, n);
      a[0] = t;
      sink(a, 0, n);
    }
    return compareTime;
  }

  private static int getLast(Integer[] a, int i, int n) {
    if (n <= 1) {
      return i;
    }
    int h = (int) (Math.log(n) / Math.log(2));
    int left = (int) (Math.pow(2, h - 1) - 1);
    int right = left;
    // 通过剩余节点是否大于叶子节点所能容纳的一半的数量,判断叶子节点在左右子树的分布
    if (n - left <= Math.pow(2, h)) {
      // 叶子都在左子树
      left = n - right - 1;
      return getLast(a, i + 1, left);
    } else {
      left = (int) (Math.pow(2, h) - 1);
      right = n - left - 1;
      return getLast(a, i + 1 + left, right);
    }
  }

  private static void buildHeap(Integer[] a, int i, int n) {
    if (n <= 1) {
      return;
    }
    int h = (int) (Math.log(n) / Math.log(2));
    int left = (int) (Math.pow(2, h - 1) - 1);
    int right = left;
    // 通过剩余节点是否大于叶子节点所能容纳的一半的数量,判断叶子节点在左右子树的分布
    if (n - left <= Math.pow(2, h)) {
      // 叶子都在左子树
      left = n - right - 1;
    } else {
      left = (int) (Math.pow(2, h) - 1);
      right = n - left - 1;
    }
    // 递归建堆
    buildHeap(a, i + 1, left);
    buildHeap(a, i + 1 + left, right);
    sink(a, i, n);
  }

  private static void sink(Integer[] a, int i, int n) {
    if (n <= 1) {
      return;
    }
    int h = (int) (Math.log(n) / Math.log(2));
    int left = (int) (Math.pow(2, h - 1) - 1);
    int right = left;
    // 通过剩余节点是否大于叶子节点所能容纳的一半的数量,判断叶子节点在左右子树的分布
    if (n - left <= Math.pow(2, h)) {
      // 叶子都在左子树
      left = n - right - 1;
    } else {
      left = (int) (Math.pow(2, h) - 1);
      right = n - left - 1;
    }
    // 找处比较大的子节点和根结点对比看是否需要下沉
    int bigChild = i + 1;
    int size = left;
    if (right != 0) {
      if (less(a, bigChild, bigChild + left)) {
        bigChild = bigChild + left;
        size = right;
      }
    }
    if (less(a, i, bigChild)) {
      exch(a, i, bigChild);
      sink(a, bigChild, size);
    }
  }

  private static void exch(Integer[] a, int i, int j) {
    Integer temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  private static boolean less(Integer[] a, int b, int c) {
    compareTime++;
    return a[b].compareTo(a[c]) < 0;
  }
}
