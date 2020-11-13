package edu.princeton.cs.exercise.chapter2_4;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.4.27 Find the minimum. Add a min() method to MaxPQ. Your implementation should use constant
 * time and constant extra space.
 *
 * <p>找到最小值.添加一个 min() 方法到 MaxPQ 中.你的实现应该是用常量时间和常量的额外空间.
 *
 * @author LeonChen
 * @since 10/24/20
 */
class E02_04_27 {
  /**
   * 因为只要找到最小值就可以了,所以对最小值做一个跟踪就可以了.
   *
   * @param <Key>
   */
  public static class MaxPQ<Key> implements Iterable<Key> {
    private Key[] pq; // store items at indices 1 to n
    private int n; // number of items on priority queue
    private Comparator<Key> comparator; // optional comparator
    private Key min = null;

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity the initial capacity of this priority queue
     */
    public MaxPQ(int initCapacity) {
      pq = (Key[]) new Object[initCapacity + 1];
      n = 0;
    }

    /** Initializes an empty priority queue. */
    public MaxPQ() {
      this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity, using the given
     * comparator.
     *
     * @param initCapacity the initial capacity of this priority queue
     * @param comparator the order in which to compare the keys
     */
    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
      this.comparator = comparator;
      pq = (Key[]) new Object[initCapacity + 1];
      n = 0;
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param comparator the order in which to compare the keys
     */
    public MaxPQ(Comparator<Key> comparator) {
      this(1, comparator);
    }

    /**
     * Initializes a priority queue from the array of keys. Takes time proportional to the number of
     * keys, using sink-based heap construction.
     *
     * @param keys the array of keys
     */
    public MaxPQ(Key[] keys) {
      n = keys.length;
      pq = (Key[]) new Object[keys.length + 1];
      min = keys[0];
      for (int i = 0; i < n; i++) {
        pq[i + 1] = keys[i];
        if (less(pq[i + 1], min)) {
          min = pq[i + 1];
        }
      }
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
    public Key max() {
      if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
      return pq[1];
    }

    // helper function to double the size of the heap array
    private void resize(int capacity) {
      assert capacity > n;
      Key[] temp = (Key[]) new Object[capacity];
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
    public void insert(Key x) {

      // double size of array if necessary
      if (n == pq.length - 1) resize(2 * pq.length);

      if (min == null) {
        min = x;
      } else {
        if (less(x, min)) {
          min = x;
        }
      }
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
    public Key delMax() {
      if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
      Key max = pq[1];
      exch(1, n--);
      sink(1);
      pq[n + 1] = null; // to avoid loitering and help with garbage collection
      if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
      if (isEmpty()) {
        min = null;
      }
      assert isMaxHeap();
      return max;
    }

    public Key min() {
      return min;
    }

    /**
     * ************************************************************************* Helper functions to
     * restore the heap invariant.
     * *************************************************************************
     */
    private void swim(int k) {
      Key key = pq[k];
      while (k > 1 && less(pq[k / 2], key)) {
        pq[k] = pq[k / 2];
        k = k / 2;
      }
      pq[k] = key;
    }

    private boolean less(Key key, Key key1) {
      if (comparator == null) {
        return ((Comparable<Key>) key).compareTo(key1) < 0;
      } else {
        return comparator.compare(key, key1) < 0;
      }
    }

    private void sink(int k) {
      Key key = pq[k];
      while (2 * k <= n) {
        int j = 2 * k;
        if (j < n && less(j, j + 1)) j++;
        if (!less(key, pq[j])) break;
        pq[k] = pq[j];
        k = j;
      }
      pq[k] = key;
    }

    /**
     * ************************************************************************* Helper functions
     * for compares and swaps.
     * *************************************************************************
     */
    private boolean less(int i, int j) {
      if (comparator == null) {
        return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
      } else {
        return comparator.compare(pq[i], pq[j]) < 0;
      }
    }

    private void exch(int i, int j) {
      Key swap = pq[i];
      pq[i] = pq[j];
      pq[j] = swap;
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
    public Iterator<Key> iterator() {
      return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

      // create a new pq
      private edu.princeton.cs.algs4.MaxPQ<Key> copy;

      // add all items to copy of heap
      // takes linear time since already in heap order so no keys move
      public HeapIterator() {
        if (comparator == null) copy = new edu.princeton.cs.algs4.MaxPQ<Key>(size());
        else copy = new edu.princeton.cs.algs4.MaxPQ<Key>(size(), comparator);
        for (int i = 1; i <= n; i++) copy.insert(pq[i]);
      }

      public boolean hasNext() {
        return !copy.isEmpty();
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }

      public Key next() {
        if (!hasNext()) throw new NoSuchElementException();
        return copy.delMax();
      }
    }

    /**
     * Unit tests the {@code MaxPQ} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
      MaxPQ<String> pq = new MaxPQ<String>();
      while (!StdIn.isEmpty()) {
        String item = StdIn.readString();

        if (!item.equals("-")) {
          pq.insert(item);
          StdOut.println("current min = " + pq.min());
        } else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
      }
      StdOut.println("(" + pq.size() + " left on pq)");
    }
  }
}
