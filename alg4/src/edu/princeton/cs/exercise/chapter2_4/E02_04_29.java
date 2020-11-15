package edu.princeton.cs.exercise.chapter2_4;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.4.29 Min/max priority queue. Design a data type that supports the following operations: insert,
 * delete the maximum, and delete the minimum (all in logarithmic time); and find the maximum and
 * find the minimum (both in constant time). Hint: Use two heaps.
 *
 * <p>最小/最大 优先队列.设计一个数据类型支持以下操作:插入,删除 最大值,还有删除最小值(全都是使用对数级时间);还有找到<br>
 * 最大值和最小值(全都是常量级时间).提示:使用两个堆.
 *
 * @author LeonChen
 * @since 10/30/20
 */
class E02_04_29 {

  /**
   * 使用一个最大堆一个最小堆来保存同样的数组,插入时同时插入两个相同元素到两个队列中.获取最大最小值时直接从对应堆获取<br>
   * 即可,删除的时候,需要索引到另一个相同元素并删除.删除另一个队列的元素做法是,先和该队列最后的元素交换,然后删除最后<br>
   * 元素,最后将交换元素进行swim和sink,使他到达他应该在的位置 . 主要方法<code>
   *      public MinMaxNode delMax() {
   *       minPQ.remove(maxPQ.max().getPair().index);
   *       MinMaxNode key = maxPQ.delMax();
   *       n--;
   *       return key;
   *     }
   *
   *     public void exch(int i, int j) {
   *
   *       pq[i].getPair().setPair(pq[j]);
   *       pq[j].getPair().setPair(pq[i]);
   *
   *       MinMaxNode tempNode = pq[i].getPair();
   *       Comparable tempValue = pq[i].getValue();
   *
   *       pq[i].setValue(pq[j].getValue());
   *       pq[i].setPair(pq[j].getPair());
   *
   *       pq[j].setValue(tempValue);
   *       pq[j].setPair(tempNode);
   *     }
   *
   *     public void remove(int index) {
   *       exch(index, n);
   *       pq[n] = null;
   *       n--;
   *       swim(index);
   *       sink(index);
   *     }
   *
   * </code>
   */
  public static void main(String[] args) {

    Integer[] a = new Integer[] {3, 45, 7, 2, 4, 679, 344, 635, 57, 32, 47, 238, 23, 46, 87};
    MinMaxPQ pq = new MinMaxPQ();
    for (int i = 0; i < 15; i++) {
      MinMaxNode[] nodes = MinMaxPQ.genPair(a[i], pq.n + 1);
      pq.insert(nodes);
    }
    StdOut.println(pq);
    pq.delMax();
    pq.delMin();
    StdOut.println(pq);
  }

  private static class MinMaxPQ {
    private MinPQ minPQ;
    private MaxPQ maxPQ;
    private int n;

    public MinMaxPQ() {
      this(1);
    }

    public MinMaxPQ(int capacity) {
      minPQ = new MinPQ(capacity);
      maxPQ = new MaxPQ(capacity);
      n = 0;
    }

    public MinMaxNode delMax() {
      minPQ.remove(maxPQ.max().getPair().index);
      MinMaxNode key = maxPQ.delMax();
      n--;
      return key;
    }

    public MinMaxNode delMin() {
      maxPQ.remove(minPQ.min().getPair().index);
      MinMaxNode key = minPQ.delMin();
      n--;
      return key;
    }

    public static MinMaxNode[] genPair(Comparable comparable, int index) {

      MinMaxNode<Comparable> min = new MinMaxNode<>();
      MinMaxNode<Comparable> max = new MinMaxNode<>();

      min.setValue(comparable);
      max.setValue(comparable);

      min.setPair(max);
      max.setPair(min);

      min.setIndex(index);
      max.setIndex(index);

      return new MinMaxNode[] {min, max};
    }

    public void insert(MinMaxNode[] nodes) {
      n++;
      minPQ.insert(nodes[0]);
      maxPQ.insert(nodes[1]);
    }

    @Override
    public String toString() {
      return "MinMaxPQ{" + "minPQ=" + minPQ + ", maxPQ=" + maxPQ + ", n=" + n + '}';
    }
  }

  public static class MinMaxNode<Key extends Comparable> implements Comparable<MinMaxNode> {

    /** 节点的值 */
    private Key value;

    /** 另一个堆中的对应节点 */
    private MinMaxNode pair;

    /** 在当前堆中的索引 */
    private int index;

    @Override
    public String toString() {
      return value.toString();
    }

    @Override
    public int compareTo(MinMaxNode o) {
      return value.compareTo(o.value);
    }

    public Key getValue() {
      return value;
    }

    public void setValue(Key value) {
      this.value = value;
    }

    public MinMaxNode<Key> getPair() {
      return pair;
    }

    public void setPair(MinMaxNode pair) {
      this.pair = pair;
    }

    public int getIndex() {
      return index;
    }

    public void setIndex(int index) {
      this.index = index;
    }
  }

  public static class MaxPQ {
    protected MinMaxNode[] pq; // store items at indices 1 to n
    protected int n; // number of items on priority queue

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity the initial capacity of this priority queue
     */
    public MaxPQ(int initCapacity) {
      pq = new MinMaxNode[initCapacity + 1];
      n = 0;
    }

    /** Initializes an empty priority queue. */
    public MaxPQ() {
      this(1);
    }

    /**
     * Initializes a priority queue from the array of keys. Takes time proportional to the number of
     * keys, using sink-based heap construction.
     *
     * @param keys the array of keys
     */
    public MaxPQ(MinMaxNode[] keys) {
      n = keys.length;
      pq = (MinMaxNode[]) new Object[keys.length + 1];
      for (int i = 0; i < n; i++) pq[i + 1] = keys[i];
      for (int k = n / 2; k >= 1; k--) sink(k);
      assert isMaxHeap();
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
      return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
      return n;
    }

    /**
     * Returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public MinMaxNode max() {
      if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
      return pq[1];
    }

    // helper function to double the size of the heap array
    private void resize(int capacity) {
      assert capacity > n;
      MinMaxNode[] temp = new MinMaxNode[capacity];
      for (int i = 1; i <= n; i++) {
        temp[i] = pq[i];
      }
      pq = temp;
    }

    /**
     * Adds a new key to this priority queue.
     *
     * @param x the new key to add to this priority queue
     */
    public void insert(MinMaxNode x) {

      // double size of array if necessary
      if (n == pq.length - 1) resize(2 * pq.length);

      // add x, and percolate it up to maintain heap invariant
      pq[++n] = x;
      swim(n);
      assert isMaxHeap();
    }

    /**
     * Removes and returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public MinMaxNode delMax() {
      if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
      MinMaxNode max = pq[1];
      exch(1, n--);
      sink(1);
      pq[n + 1] = null; // to avoid loitering and help with garbage collection

      if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
      assert isMaxHeap();
      return max;
    }

    /**
     * ************************************************************************* Helper functions to
     * restore the heap invariant.
     * *************************************************************************
     */
    protected void swim(int k) {
      while (k > 1 && less(k / 2, k)) {
        exch(k, k / 2);
        k = k / 2;
      }
    }

    protected void sink(int k) {
      while (2 * k <= n) {
        int j = 2 * k;
        if (j < n && less(j, j + 1)) j++;
        if (!less(k, j)) break;
        exch(k, j);
        k = j;
      }
    }

    /**
     * ************************************************************************* Helper functions
     * for compares and swaps.
     * *************************************************************************
     */
    private boolean less(int i, int j) {
      return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * 只交换值和对应元素,index不变
     *
     * @param i
     * @param j
     */
    public void exch(int i, int j) {

      pq[i].getPair().setPair(pq[j]);
      pq[j].getPair().setPair(pq[i]);

      MinMaxNode tempNode = pq[i].getPair();
      Comparable tempValue = pq[i].getValue();

      pq[i].setValue(pq[j].getValue());
      pq[i].setPair(pq[j].getPair());

      pq[j].setValue(tempValue);
      pq[j].setPair(tempNode);
    }

    public void remove(int index) {
      exch(index, n);
      pq[n] = null;
      n--;
      swim(index);
      sink(index);
    }

    @Override
    public String toString() {
      return "MaxPQ{" + "pq=" + Arrays.toString(pq) + ", n=" + n + '}';
    }

    // is pq[1..n] a max heap?
    private boolean isMaxHeap() {
      for (int i = 1; i <= n; i++) {
        if (pq[i] == null) return false;
      }
      for (int i = n + 1; i < pq.length; i++) {
        if (pq[i] != null) return false;
      }
      if (pq[0] != null) return false;
      return isMaxHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a max heap?
    private boolean isMaxHeapOrdered(int k) {
      if (k > n) return true;
      int left = 2 * k;
      int right = 2 * k + 1;
      if (left <= n && less(k, left)) return false;
      if (right <= n && less(k, right)) return false;
      return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }

    /**
     * ************************************************************************* Iterator.
     * *************************************************************************
     */

    /**
     * Returns an iterator that iterates over the keys on this priority queue in descending order.
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in descending order
     */
    public Iterator<MinMaxNode> iterator() {
      return new HeapIterator();
    }

    private class HeapIterator implements Iterator<MinMaxNode> {

      // create a new pq
      private MaxPQ copy;

      // add all items to copy of heap
      // takes linear time since already in heap order so no keys move
      public HeapIterator() {
        copy = new MaxPQ(size());
        for (int i = 1; i <= n; i++) copy.insert(pq[i]);
      }

      public boolean hasNext() {
        return !copy.isEmpty();
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }

      public MinMaxNode next() {
        if (!hasNext()) throw new NoSuchElementException();
        return copy.delMax();
      }
    }
  }

  public static class MinPQ {
    public MinMaxNode[] pq; // store items at indices 1 to n
    protected int n; // number of items on priority queue

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity the initial capacity of this priority queue
     */
    public MinPQ(int initCapacity) {
      pq = new MinMaxNode[initCapacity + 1];
      n = 0;
    }

    /** Initializes an empty priority queue. */
    public MinPQ() {
      this(1);
    }

    /**
     * Initializes a priority queue from the array of keys.
     *
     * <p>Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param keys the array of keys
     */
    public MinPQ(MinMaxNode[] keys) {
      n = keys.length;
      pq = (MinMaxNode[]) new Object[keys.length + 1];
      for (int i = 0; i < n; i++) pq[i + 1] = keys[i];
      for (int k = n / 2; k >= 1; k--) sink(k);
      assert isMinHeap();
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
      return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
      return n;
    }

    /**
     * Returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public MinMaxNode min() {
      if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
      return pq[1];
    }

    // helper function to double the size of the heap array
    private void resize(int capacity) {
      assert capacity > n;
      MinMaxNode[] temp = new MinMaxNode[capacity];
      for (int i = 1; i <= n; i++) {
        temp[i] = pq[i];
      }
      pq = temp;
    }

    /**
     * Adds a new key to this priority queue.
     *
     * @param x the key to add to this priority queue
     */
    public void insert(MinMaxNode x) {
      // double size of array if necessary
      if (n == pq.length - 1) resize(2 * pq.length);

      // add x, and percolate it up to maintain heap invariant
      pq[++n] = x;
      swim(n);
      assert isMinHeap();
    }

    /**
     * Removes and returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public MinMaxNode delMin() {
      if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
      MinMaxNode min = pq[1];
      exch(1, n--);
      sink(1);
      pq[n + 1] = null; // to avoid loiterig and help with garbage collection
      if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
      assert isMinHeap();
      return min;
    }

    /**
     * ************************************************************************* Helper functions to
     * restore the heap invariant.
     * *************************************************************************
     */
    protected void swim(int k) {
      while (k > 1 && greater(k / 2, k)) {
        exch(k, k / 2);
        k = k / 2;
      }
    }

    protected void sink(int k) {
      while (2 * k <= n) {
        int j = 2 * k;
        if (j < n && greater(j, j + 1)) j++;
        if (!greater(k, j)) break;
        exch(k, j);
        k = j;
      }
    }

    /**
     * ************************************************************************* Helper functions
     * for compares and swaps.
     * *************************************************************************
     */
    private boolean greater(int i, int j) {
      return ((Comparable<MinMaxNode>) pq[i]).compareTo(pq[j]) > 0;
    }
    /**
     * 只交换值和对应元素,index不变
     *
     * @param i
     * @param j
     */
    public void exch(int i, int j) {
      pq[i].getPair().setPair(pq[j]);
      pq[j].getPair().setPair(pq[i]);

      MinMaxNode tempNode = pq[i].getPair();
      Comparable tempValue = pq[i].getValue();

      pq[i].setValue(pq[j].getValue());
      pq[i].setPair(pq[j].getPair());

      pq[j].setValue(tempValue);
      pq[j].setPair(tempNode);
    }

    public void remove(int index) {
      exch(index, n);
      pq[n] = null;
      n--;
      swim(index);
      sink(index);
    }

    @Override
    public String toString() {
      return "MinPQ{" + "pq=" + Arrays.toString(pq) + ", n=" + n + '}';
    }

    // is pq[1..n] a min heap?
    private boolean isMinHeap() {
      for (int i = 1; i <= n; i++) {
        if (pq[i] == null) return false;
      }
      for (int i = n + 1; i < pq.length; i++) {
        if (pq[i] != null) return false;
      }
      if (pq[0] != null) return false;
      return isMinHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeapOrdered(int k) {
      if (k > n) return true;
      int left = 2 * k;
      int right = 2 * k + 1;
      if (left <= n && greater(k, left)) return false;
      if (right <= n && greater(k, right)) return false;
      return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

    /**
     * Returns an iterator that iterates over the keys on this priority queue in ascending order.
     *
     * <p>The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    public Iterator<MinMaxNode> iterator() {
      return new HeapIterator();
    }

    private class HeapIterator implements Iterator<MinMaxNode> {
      // create a new pq
      private edu.princeton.cs.algs4.MinPQ<MinMaxNode> copy;

      // add all items to copy of heap
      // takes linear time since already in heap order so no keys move
      public HeapIterator() {
        copy = new edu.princeton.cs.algs4.MinPQ<MinMaxNode>(size());
        for (int i = 1; i <= n; i++) copy.insert(pq[i]);
      }

      public boolean hasNext() {
        return !copy.isEmpty();
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }

      public MinMaxNode next() {
        if (!hasNext()) throw new NoSuchElementException();
        return copy.delMin();
      }
    }
  }
}
