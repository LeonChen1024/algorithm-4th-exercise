package edu.princeton.cs.exercise.chapter2_4;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.4.33 Index priority-queue implementation. Implement the basic operations in the index
 * priority-queue API on page 320 by modifying Algorithm 2.6 as follows: Change pq[] to hold
 * indices, add an array keys[] to hold the key values, and add an array qp[] that is the inverse of
 * pq[] — qp[i] gives the position of i in pq[] (the index j such that pq[j] is i). Then modify the
 * code in Algorithm 2.6 to maintain these data structures. Use the convention that qp[i] = -1 if i
 * is not on the queue, and include a method contains() that tests this condition. You need to
 * modify the helper methods exch() and less() but not sink() or swim().
 *
 * <p>Partial solution : <code>
 *       public class IndexMinPQ<Key extends Comparable<Key>>
 *   {
 *     private int N;
 *     // number of elements on PQ
 *     private int[] pq;
 *     // binary heap using 1-based indexing
 *     private int[] qp;
 *     // inverse: qp[pq[i]] = pq[qp[i]] = i
 *     private Key[] keys;
 *     // items with priorities
 *     public IndexMinPQ(int maxN)
 *     {
 *       keys = (Key[]) new Comparable[maxN + 1];
 *       pq = new int[maxN + 1];
 *       qp = new int[maxN + 1];
 *       for(int i=0;i<=maxN;i++)qp[i]= -1;
 *     }
 *     public boolean isEmpty()
 *     {return N==0;}
 *     public boolean contains(int k)
 *     { return qp[k] != -1; }
 *     public void insert(int k, Key key)
 *     {
 *       N++;
 *       qp[k] = N;
 *       pq[N] = k;
 *       keys[k] = key;
 *       swim(N);
 *     }
 *     public Item min()
 *     { return keys[pq[1]]; }
 *     public int delMin()
 *     {
 *       int indexOfMin = pq[1];
 *       exch(1, N--);
 *       sink(1);
 *       keys[pq[N+1]] = null;
 *       qp[pq[N+1]] = -1;
 *       return indexOfMin;
 *     }
 * </code>
 *
 * <p>索引优先队列实现.实现一个索引优先队列的基础操作API,参考P320算法 2.6做出以下修改:改变pq[]来保存索引,<br>
 * 添加一个数组keys[] 来保存key值,并且添加一个数组qp[]是pq[]的逆数组 - qp[i] 的值是 i在pq[]中的位置(返回j<br>
 * 使得 pq[j] 是i).然后修改算法2.6中的代码来保存这些数据结构.使用 qp[i] = -1 来表示 i 不在队列中,并且包含<br>
 * 一个方法包含了这个条件的测试.你需要修改帮助方法 exch() 和 less() 而不是 sink() 或者 swim()
 *
 * @author LeonChen
 * @since 11/9/20
 */
class E02_04_33 {

  /**
   * 官网给出的是最大堆解法 https://algs4.cs.princeton.edu/24pq/IndexMaxPQ.java.html<br>
   * 其实和普通优先队列没有什么不同,只要注意 key 的索引基本不变,变得是索引在 pq[]中位置.对比的时候也是使用 <br>
   * keys[pq[i]]来进行对比排序
   *
   * @param args
   */
  public static void main(String[] args) {
    Integer[] integers = {6, 34, 33, 32, 123, 11, 7, 45, 8, 4, 13};

    IndexMinPQ<Integer> priorityQueue = new IndexMinPQ<>(integers.length);

    for (int i = 0; i < integers.length; i++) {
      priorityQueue.insert(i, integers[i]);
    }

    StdOut.println("Key of index 4: " + priorityQueue.keyOf(4) + " Expected: 123");

    // Delete and print each key
    while (!priorityQueue.isEmpty()) {
      Integer key = priorityQueue.minKey();
      int index = priorityQueue.deleteMin();
      StdOut.println(index + " " + key);
    }
  }

  private static class IndexMinPQ<Key extends Comparable<Key>> {

    private Key[] keys;
    private int[] pq; // Holds the indices of the keys
    private int[] qp; // Inverse of pq -> qp[i] gives the position of i in pq[] (the index j such
    // that pq[j] is i). qp[pq[i]] = pq[qp[i]] = i
    private int size = 0;

    @SuppressWarnings("unchecked")
    public IndexMinPQ(int size) {
      keys = (Key[]) new Comparable[size + 1];
      pq = new int[size + 1];
      qp = new int[size + 1];

      for (int i = 0; i < qp.length; i++) {
        qp[i] = -1;
      }
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public int size() {
      return size;
    }

    public boolean contains(int index) {
      return qp[index] != -1;
    }

    // Return key associated with index
    public Key keyOf(int index) {
      if (!contains(index)) {
        throw new NoSuchElementException("Index is not in the priority queue");
      }

      return keys[index];
    }

    public void insert(int index, Key key) {
      if (contains(index)) {
        throw new IllegalArgumentException("Index is already in the priority queue");
      }

      if (size != keys.length - 1) {
        size++;

        keys[index] = key;
        pq[size] = index;
        qp[index] = size;

        swim(size);
      }
    }

    // Remove a minimal key and return its index
    public int deleteMin() {
      if (size == 0) {
        throw new NoSuchElementException("Priority queue underflow");
      }

      int minElementIndex = pq[1];
      exchange(1, size);

      size--;
      sink(1);

      keys[pq[size + 1]] = null;
      qp[pq[size + 1]] = -1;

      return minElementIndex;
    }

    public Key minKey() {
      if (size == 0) {
        throw new NoSuchElementException("Priority queue underflow");
      }

      return keys[pq[1]];
    }

    private void swim(int index) {
      while (index / 2 >= 1 && more(index / 2, index)) {
        exchange(index / 2, index);
        index = index / 2;
      }
    }

    private void sink(int index) {
      while (index * 2 <= size) {
        int selectedChildIndex = index * 2;

        if (index * 2 + 1 <= size && more(index * 2, index * 2 + 1)) {
          selectedChildIndex = index * 2 + 1;
        }

        if (less(selectedChildIndex, index)) {
          exchange(index, selectedChildIndex);
        } else {
          break;
        }

        index = selectedChildIndex;
      }
    }

    private boolean less(int keyIndex1, int keyIndex2) {
      return keys[pq[keyIndex1]].compareTo(keys[pq[keyIndex2]]) < 0;
    }

    private boolean more(int keyIndex1, int keyIndex2) {
      return keys[pq[keyIndex1]].compareTo(keys[pq[keyIndex2]]) > 0;
    }

    private void exchange(int keyIndex1, int keyIndex2) {
      int temp = pq[keyIndex1];
      pq[keyIndex1] = pq[keyIndex2];
      pq[keyIndex2] = temp;

      qp[pq[keyIndex1]] = keyIndex1;
      qp[pq[keyIndex2]] = keyIndex2;
    }
  }
}
