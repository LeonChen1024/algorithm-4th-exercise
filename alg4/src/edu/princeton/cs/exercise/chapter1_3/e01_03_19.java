package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.19 Give a code fragment that removes the last node in a linked list whose first node is
 * first.
 *
 * <p>给出一个代码片段删除链表的最后一个节点,第一个节点是 first
 *
 * @author LeonChen
 * @since 1/7/20
 */
class e01_03_19 {

  public static void main(String[] args) {

    Node node3 = new Node(null, 4);
    Node node2 = new Node(node3, 3);
    Node node1 = new Node(node2, 2);
    Node first = new Node(node1, 1);
    //    Node first = null;

    deleteLast(first);
  }

  private static void deleteLast(Node first) {
    Node curNode = first;
    Node preNode = null;
    while (curNode != null && curNode.next != null) {
      preNode = curNode;
      curNode = curNode.next;
    }

    if (preNode != null) {

      preNode.next = null;
    } else {
      first = null;
    }

    curNode = first;
    while (curNode != null) {
      StdOut.print(curNode.id);
      curNode = curNode.next;
    }
  }

  private static class Node {
    private final int id;
    Node next;

    public Node(Node next, int id) {
      this.next = next;
      this.id = id;
    }
  }
}
