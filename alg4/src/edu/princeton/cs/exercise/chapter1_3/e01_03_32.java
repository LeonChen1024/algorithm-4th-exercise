package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.32 Steque. A stack-ended queue or steque is a data type that supports push, pop, and enqueue.
 * Articulate an API for this ADT. Develop a linked-list-based implementation.
 *
 * <p>Steque. 一个栈型的队列或者 steque 是一个支持 push, pop,和 enqueue的数据类型.表明这个ADT的API.开发<br>
 * 一个基于链表的实现.
 *
 * @author LeonChen
 * @since 1/21/20
 */
class e01_03_32 {

  public static void main(String[] args) {
    Steque<String> steque = new Steque();
    steque.push("1");
    steque.push("2");
    steque.push("3");
    steque.push("4");
    steque.pop();
    steque.enqueue("5");

    StdOut.print(steque.pop());
    StdOut.print(steque.pop());
    StdOut.print(steque.pop());
    StdOut.print(steque.pop());
  }

  /**
   * API -----------------------------------------------------------<br>
   * void push(E e) 添加一个元素到头部 <br>
   * E pop() 从头部删除一个元素 <br>
   * void enqueue(E e) 添加一个元素到尾部
   *
   * @param <E>
   */
  private static class Steque<E> {
    private Node first;
    private Node last;
    private int size;

    public Steque() {
      first = null;
      last = null;
      size = 0;
    }

    public void push(E e) {
      Node<E> node = new Node<>(null, e);
      if (first == null) {
        first = node;
        last = node;
      } else {
        node.next = first;
        first = node;
      }
      size++;
    }

    public E pop() {
      if (first != null) {
        Node<E> oldFirst = first;
        first = first.next;
        if (first == null) {
          last = null;
        }
        size--;
        return oldFirst.value;
      }
      return null;
    }

    public void enqueue(E e) {
      Node<E> node = new Node<>(null, e);
      if (last == null) {
        first = node;
        last = node;
      } else {
        Node oldLast = last;
        last = node;
        oldLast.next = last;
      }
      size++;
    }

    @Override
    public String toString() {
      StringBuilder s = new StringBuilder();
      Node cur = first;

      while (cur != null) {
        s.append(cur.value + " ");
        cur = cur.next;
      }

      return s.toString();
    }

    private static class Node<E> {
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
