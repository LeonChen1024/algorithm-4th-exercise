package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.20 Write a method delete() that takes an int argument k and deletes the kth element in a
 * linked list, if it exists.
 *
 * <p>编写一个 delete() 方法接收一个 int 参数k 并且删除链表中的第k个元素,如果它存在的话.
 *
 * @author LeonChen
 * @since 1/7/20
 */
class e01_03_20 {

  public static void main(String[] args) {

    Node node3 = new Node(null, 4);
    Node node2 = new Node(node3, 3);
    Node node1 = new Node(node2, 2);
    Node first = new Node(node1, 1);

    delete(3, first);
  }

  private static void delete(int k, Node first) {

    if (k == 1) {
      first = first.next;
      return;
    }

    int index = 1;
    Node curNode = first;
    Node preNode = null;
    while (curNode != null) {
      preNode = curNode;
      curNode = curNode.next;
      if (index + 1 == k) {
        preNode.next = curNode != null ? curNode.next : curNode;
        break;
      }
      index++;
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
