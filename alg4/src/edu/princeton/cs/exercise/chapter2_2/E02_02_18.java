package edu.princeton.cs.exercise.chapter2_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.2.18 Shuffling a linked list. Develop and implement a divide-and-conquer algorithm that
 * randomly shuffles a linked list in linearithmic time and logarithmic extra space.
 *
 * <p>打乱一个链表.开发并实现一个分而治之的算法可以在一个线性的时间内和对数的额外空间来随机打乱一个链表
 *
 * @author LeonChen
 * @since 7/13/20
 */
class E02_02_18 {

  static class Node<T> {
    T value;
    Node<T> next;

    public Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }
  }

  /**
   * 使用快慢指针对半分.
   *
   * @param args
   */
  public static void main(String[] args) {
    Node<Comparable> node1 = new Node<>(3, null);
    Node<Comparable> node2 = new Node<>(7, node1);
    Node<Comparable> node3 = new Node<>(4, node2);
    Node<Comparable> node4 = new Node<>(22, node3);
    Node<Comparable> node5 = new Node<>(45, node4);
    Node<Comparable> node6 = new Node<>(6, node5);
    Node<Comparable> node7 = new Node<>(5, node6);
    Node<Comparable> head = new Node<>(80, node7);

    Node<Comparable> cur = shuffles(head);

    while (cur != null) {
      StdOut.print(cur.value + " , ");
      cur = cur.next;
    }
  }

  private static Node<Comparable> shuffles(Node<Comparable> head) {
    Node<Comparable> mid = getMiddleNode(head);
    Node<Comparable> temp = mid;
    mid = mid.next;
    temp.next = null;
    Node<Comparable> newHead = null;

    if (StdRandom.uniform() < 0.5) {
      newHead = head;
      head = head.next;
    } else {
      newHead = mid;
      mid = mid.next;
    }

    Node<Comparable> cur = newHead;
    while (head != null && mid != null) {
      if (StdRandom.uniform() < 0.5) {
        cur.next = head;
        head = head.next;
      } else {
        cur.next = mid;
        mid = mid.next;
      }
      cur = cur.next;
    }
    while (head != null) {
      cur.next = head;
      head = head.next;
      cur = cur.next;
    }

    while (mid != null) {
      cur.next = mid;
      mid = mid.next;
      cur = cur.next;
    }

    cur.next = null;
    return newHead;
  }

  private static Node<Comparable> getMiddleNode(Node<Comparable> head) {
    if (head == null) {
      return null;
    }

    Node slow = head;
    Node fast = head;

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
}
