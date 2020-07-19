package edu.princeton.cs.exercise.chapter2_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.2.17 Linked-list sort. Implement a natural mergesort for linked lists. (This is the method of
 * choice for sorting linked lists because it uses no extra space and is guaranteed to be
 * linearithmic.)
 *
 * <p>链表排序. 实现一个针对链表的自然合并排序.(这是一个给链表排序的选择因为他没有使用额外的空间并且复杂度为线性对数级别)
 *
 * @author LeonChen
 * @since 7/10/20
 */
class E02_02_17 {

  static class Node<T> {
    T value;
    Node<T> next;

    public Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }
  }

  /** @param args */
  public static void main(String[] args) {
    Node<Comparable> node1 = new Node<>(3, null);
    Node<Comparable> node2 = new Node<>(7, node1);
    Node<Comparable> node3 = new Node<>(4, node2);
    Node<Comparable> node4 = new Node<>(22, node3);
    Node<Comparable> node5 = new Node<>(45, node4);
    Node<Comparable> node6 = new Node<>(6, node5);
    Node<Comparable> node7 = new Node<>(5, node6);
    Node<Comparable> head = new Node<>(80, node7);

    Node<Comparable> cur = naturalMergesort(head);

    while (cur != null) {
      StdOut.print(cur.value + " , ");
      cur = cur.next;
    }
  }

  private static Node<Comparable> naturalMergesort(Node<Comparable> linkedList) {
    Node<Comparable> firstStart = linkedList;
    Node<Comparable> firstEnd = findSortedArrEnd(firstStart);
    if (firstEnd.next == null) {
      return linkedList;
    }
    Node<Comparable> secondStart = firstEnd.next;
    Node<Comparable> secondEnd = findSortedArrEnd(secondStart);
    Node<Comparable> end = secondEnd.next;
    firstEnd.next = null;
    secondEnd.next = null;
    // 单独保留头节点,避免后面的操作导致头节点丢失
    Node<Comparable> head = null;
    if (firstStart.value.compareTo(secondStart.value) <= 0) {
      head = firstStart;
      firstStart = firstStart.next;
    } else {
      head = secondStart;
      secondStart = secondStart.next;
    }

    Node<Comparable> cur = head;
    while (firstStart != null && secondStart != null) {
      if (firstStart.value.compareTo(secondStart.value) <= 0) {
        cur.next = firstStart;
        firstStart = firstStart.next;
      } else {
        cur.next = secondStart;
        secondStart = secondStart.next;
      }
      cur = cur.next;
    }

    while (firstStart != null) {
      cur.next = firstStart;
      firstStart = firstStart.next;
      cur = cur.next;
    }

    while (secondStart != null) {
      cur.next = secondStart;
      secondStart = secondStart.next;
      cur = cur.next;
    }

    // 将两个子数组合并后的最后一个节点和原来的数组的链接起来
    cur.next = end;

    return naturalMergesort(head);
  }

  private static Node<Comparable> findSortedArrEnd(Node<Comparable> start) {

    while (start.next != null) {
      if (start.value.compareTo(start.next.value) > 0) {
        return start;
      }
      start = start.next;
    }

    return start;
  }
}
