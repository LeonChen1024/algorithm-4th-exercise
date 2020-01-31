package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.38 Delete kth element. Implement a class that supports the following API: <code>
 * public class GeneralizedQueue<Item>
 *     GeneralizedQueue() create an empty queue
 *     boolean isEmpty() is the queue empty?
 *     void insert(Item x) add an item
 *     Item delete(int k) delete and return the kth least recently inserted item
 *     API for a generic generalized queue
 * </code> First, develop an implementation that uses an array implementation, and then develop one
 * that uses a linked-list implementation. Note : the algorithms and data structures that we
 * introduce in Chapter 3 make it possible to develop an implementation that can guarantee that both
 * insert() and delete() take time prortional to the logarithm of the number of items in the
 * queue—see Exercise 3.5.27.
 *
 * <p>删除第 k 个元素.实现一个类支持以下 API : <code>
 * public class GeneralizedQueue<Item>
 *     GeneralizedQueue() create an empty queue
 *     boolean isEmpty() is the queue empty?
 *     void insert(Item x) add an item
 *     Item delete(int k) delete and return the kth least recently inserted item
 *     API for a generic generalized queue
 * </code> 首先,开发一个实现使用数组来完成,然后开发一个使用链表来实现的.注意: 我们在第 3 章介绍的算法和数据结构可以开发一个实现能够保证<br>
 * insert() 和 delete()使用queue 中项总和的对数级的时间-可见 3.5.27
 *
 * @author LeonChen
 * @since 1/30/20
 */
class E01_03_38 {

  public static void main(String[] args) {
    //    GeneralizedQueue1 queue1 = new GeneralizedQueue1();
    GeneralizedQueue2 queue = new GeneralizedQueue2();
    StdOut.println(queue.isEmpty());
    queue.insert(1);
    queue.insert(2);
    queue.insert(3);
    queue.insert(4);
    queue.insert(5);
    queue.insert(6);
    queue.insert(7);
    queue.insert(8);

    StdOut.println(queue.delete(4));
    StdOut.println(queue.delete(7));
  }

  public static class GeneralizedQueue2<Item> {

    public class Node {
      Node next;
      Item value;
    }

    private Node first;
    private Node last;
    private int size;

    public GeneralizedQueue2() {
      size = 0;
      first = null;
      last = null;
    }

    boolean isEmpty() {
      return size == 0;
    }

    void insert(Item x) {
      if (size == 0) {
        first = new Node();
        first.value = x;
        last = first;
      } else {
        Node newLast = new Node();
        newLast.value = x;

        last.next = newLast;
        last = newLast;
      }
      size++;
    }

    Item delete(int k) {
      Node result = first;
      Node prev = null;
      if (k == 1) {
        if (size == 1) {
          last = null;
          first = null;
        } else {
          first = first.next;
        }
      } else {
        for (int i = 0; i < k - 1; i++) {
          prev = result;
          result = result.next;
        }
        prev.next = result.next;
        result.next = null;
      }
      size--;

      return result.value;
    }
  }

  public static class GeneralizedQueue1<Item> {

    private Item[] items;
    private int arraySize;
    private int size;

    public GeneralizedQueue1() {
      arraySize = 3;
      items = (Item[]) new Object[arraySize];
      size = 0;
    }

    boolean isEmpty() {
      return size == 0;
    }

    void insert(Item x) {
      if (size == arraySize) {
        arraySize = arraySize * 2;
        resize(arraySize);
      }
      items[size++] = x;
    }

    private void resize(int s) {
      Item[] newArr = (Item[]) new Object[s];
      System.arraycopy(items, 0, newArr, 0, items.length);
      items = newArr;
    }

    Item delete(int k) {
      Item result = items[k - 1];
      System.arraycopy(items, k, items, k - 1, size - k);
      items[--size] = null;
      return result;
    }
  }
}
