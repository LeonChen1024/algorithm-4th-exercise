package edu.princeton.cs.exercise.chapter2_4;

import java.util.LinkedList;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.4.3 Provide priority-queue implementations that support insert and remove the maximum, one for
 * each of the following underlying data structures: unordered array, ordered array, unordered
 * linked list, and linked list. Give a table of the worst-case bounds for each operation for each
 * of your four implementations.
 *
 * <p>提供一个优先队列实现支持插入和移除最大值,使用以下每一个数据结构来编写: 无序数组,有序数组,无序链表,和链表.给定<br>
 * 一个最差情况下你的每个实现的每个操作边界表.
 *
 * @author LeonChen
 * @since 9/15/20
 */
class E02_04_03 {

  /**
   * <code>
   *    实现                                插入(最差)           移除(最差)
   *   OrderedArrayMaxPQ (有序数组)             n                  1
   *   UnorderedArrayMaxPQ (无序数组)           1                  n
   *   OrderedLinkedListMaxPQ (有序链表)        n                  1
   *   UnorderedLinkedListMaxPQ (无序链表)      1                  n
   * </code>
   */
  public static void main(String[] args) {}

  /**
   * 直接使用java内置linkedList , 由于Node类非公开,所以会多损失一些性能
   *
   * @param <Key>
   */
  public static class UnorderedLinkedListMaxPQ<Key extends Comparable<Key>> {
    private LinkedList<Key> pq; // elements
    private int n; // number of elements

    public UnorderedLinkedListMaxPQ() {
      pq = new LinkedList<Key>();
      n = 0;
    }

    public boolean isEmpty() {
      return n == 0;
    }

    public int size() {
      return n;
    }

    public Key delMax() {
      Key max = pq.peek();
      for (Key k : pq) {
        if (less(max, k)) {
          max = k;
        }
      }
      pq.remove(max);
      n--;
      return max;
    }

    public void insert(Key key) {
      pq.add(key);
      n++;
    }

    /**
     * ************************************************************************* Helper functions.
     * *************************************************************************
     */
    private boolean less(Key v, Key w) {
      return v.compareTo(w) < 0;
    }

    /**
     * ************************************************************************* Test routine.
     * *************************************************************************
     */
    public static void main(String[] args) {
      OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<String>(10);
      pq.insert("this");
      pq.insert("is");
      pq.insert("a");
      pq.insert("test");
      while (!pq.isEmpty()) StdOut.println(pq.delMax());
    }
  }

  public static class OrderedLinkedListMaxPQ<Key extends Comparable<Key>> {
    private LinkedList<Key> pq; // elements
    private int n; // number of elements

    public OrderedLinkedListMaxPQ() {
      pq = new LinkedList<Key>();
      n = 0;
    }

    public boolean isEmpty() {
      return n == 0;
    }

    public int size() {
      return n;
    }

    public Key delMax() {
      n--;
      return pq.pop();
    }

    public void insert(Key key) {
      int i = 0;
      for (Key k : pq) {
        if (less(k, key)) {
          pq.add(i, key);
          n++;
        }
        i++;
      }
    }

    /**
     * ************************************************************************* Helper functions.
     * *************************************************************************
     */
    private boolean less(Key v, Key w) {
      return v.compareTo(w) < 0;
    }

    /**
     * ************************************************************************* Test routine.
     * *************************************************************************
     */
    public static void main(String[] args) {
      OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<String>(10);
      pq.insert("this");
      pq.insert("is");
      pq.insert("a");
      pq.insert("test");
      while (!pq.isEmpty()) StdOut.println(pq.delMax());
    }
  }

  /**
   * **************************************************************************** Compilation: javac
   * OrderedArrayMaxPQ.java Execution: java OrderedArrayMaxPQ Dependencies: StdOut.java
   *
   * <p>Priority queue implementation with an ordered array.
   *
   * <p>Limitations ----------- - no array resizing - does not check for overflow or underflow.
   *
   * <p>****************************************************************************
   */
  public static class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq; // elements
    private int n; // number of elements

    // set inititial size of heap to hold size elements
    public OrderedArrayMaxPQ(int capacity) {
      pq = (Key[]) (new Comparable[capacity]);
      n = 0;
    }

    public boolean isEmpty() {
      return n == 0;
    }

    public int size() {
      return n;
    }

    public Key delMax() {
      return pq[--n];
    }

    public void insert(Key key) {
      int i = n - 1;
      while (i >= 0 && less(key, pq[i])) {
        pq[i + 1] = pq[i];
        i--;
      }
      pq[i + 1] = key;
      n++;
    }

    /**
     * ************************************************************************* Helper functions.
     * *************************************************************************
     */
    private boolean less(Key v, Key w) {
      return v.compareTo(w) < 0;
    }

    /**
     * ************************************************************************* Test routine.
     * *************************************************************************
     */
    public static void main(String[] args) {
      OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<String>(10);
      pq.insert("this");
      pq.insert("is");
      pq.insert("a");
      pq.insert("test");
      while (!pq.isEmpty()) StdOut.println(pq.delMax());
    }
  }

  /**
   * **************************************************************************** Compilation: javac
   * UnorderedArrayMaxPQ.java Execution: java UnorderedArrayMaxPQ Dependencies: StdOut.java
   *
   * <p>Priority queue implementation with an unsorted array.
   *
   * <p>Limitations ----------- - no array resizing - does not check for overflow or underflow.
   *
   * <p>****************************************************************************
   */
  public static class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq; // elements
    private int n; // number of elements

    // set inititial size of heap to hold size elements
    public UnorderedArrayMaxPQ(int capacity) {
      pq = (Key[]) new Comparable[capacity];
      n = 0;
    }

    public boolean isEmpty() {
      return n == 0;
    }

    public int size() {
      return n;
    }

    public void insert(Key x) {
      pq[n++] = x;
    }

    public Key delMax() {
      int max = 0;
      for (int i = 1; i < n; i++) if (less(max, i)) max = i;
      exch(max, n - 1);

      return pq[--n];
    }

    /**
     * ************************************************************************* Helper functions.
     * *************************************************************************
     */
    private boolean less(int i, int j) {
      return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
      Key swap = pq[i];
      pq[i] = pq[j];
      pq[j] = swap;
    }

    /**
     * ************************************************************************* Test routine.
     * *************************************************************************
     */
    public static void main(String[] args) {
      UnorderedArrayMaxPQ<String> pq = new UnorderedArrayMaxPQ<String>(10);
      pq.insert("this");
      pq.insert("is");
      pq.insert("a");
      pq.insert("test");
      while (!pq.isEmpty()) StdOut.println(pq.delMax());
    }
  }
}
