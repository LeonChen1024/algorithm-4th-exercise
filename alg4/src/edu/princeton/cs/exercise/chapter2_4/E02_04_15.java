package edu.princeton.cs.exercise.chapter2_4;

import java.util.Comparator;

/**
 * 2.4.15 Design a linear-time certification algorithm to check whether an array pq[] is a
 * min-oriented heap.
 *
 * <p>设计一个线性时间算法来验证一个数组 pq[] 是否一个面向最小堆.
 *
 * @author LeonChen
 * @since 9/22/20
 */
class E02_04_15<Key> {

  /** 将最大堆校验的比较反过来就可以了 */
  public static void main(String[] args) {}

  private int n;
  private Key[] pq; // store items at indices 1 to n
  private Comparator<Key> comparator; // optional comparator

  private boolean isMinHeapOrdered(int k) {
    if (k > n) return true;
    int left = 2 * k;
    int right = 2 * k + 1;
    if (left <= n && less(left, k)) return false;
    if (right <= n && less(right, k)) return false;
    return isMinHeapOrdered(left) && isMinHeapOrdered(right);
  }

  private boolean less(int i, int j) {
    if (comparator == null) {
      return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
    } else {
      return comparator.compare(pq[i], pq[j]) < 0;
    }
  }
}
