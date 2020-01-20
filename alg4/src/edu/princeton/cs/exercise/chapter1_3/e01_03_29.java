package edu.princeton.cs.exercise.chapter1_3;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.29 Write a Queue implementation that uses a circular linked list, which is the same as a
 * linked list except that no links are null and the value of last.next is first whenever the list
 * is not empty. Keep only one Node instance variable (last).
 *
 * <p>编写一个 Queue 通过使用一个循环链表实现,和链表是一样的除了没有连接到null的节点,并且当列表不为空的时候<br>
 * last.next 的值是 first .仅保留一个 Node实例(last)
 *
 * @author LeonChen
 * @since 1/19/20
 */
class e01_03_29 {

  public static void main(String[] args) {
    CircularQueue<String> circularQueue = new CircularQueue<>();
    circularQueue.enqueue("a");
    circularQueue.enqueue("b");
    circularQueue.enqueue("c");
    circularQueue.enqueue("d");
    circularQueue.enqueue("e");

    circularQueue.dequeue();
    circularQueue.dequeue();
    circularQueue.dequeue();

    StdOut.print(circularQueue.dequeue());
    StdOut.print(circularQueue.dequeue());
  }

  public static class CircularQueue<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public CircularQueue() {
      first = null;
      last = null;
      size = 0;
    }

    public boolean isEmpty() {
      return first == null;
    }

    public int size() {
      return size;
    }

    public E peek() {
      if (isEmpty()) throw new NoSuchElementException("Queue underflow");
      return first.value;
    }

    public void enqueue(E item) {
      Node<E> oldlast = last;
      last = new Node<E>();
      last.value = item;
      last.next = null;
      if (isEmpty()) first = last;
      else oldlast.next = last;
      size++;
    }

    public E dequeue() {
      if (isEmpty()) throw new NoSuchElementException("Queue underflow");
      E item = first.value;
      first = first.next;
      size--;
      if (isEmpty()) last = null; // to avoid loitering
      return item;
    }

    public static class Node<E> {
      E value;
      Node next;

      public Node(Node next, E value) {
        this.next = next;
        this.value = value;
      }

      public Node() {}
    }
  }
}
