package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.24 Write a method removeAfter() that takes a linked-list Node as argument and removes the
 * node following the given one (and does nothing if the argument or the next field in the argument
 * node is null).
 *
 * <p>编写一个 removeAfter() 方法接收一个 linked-list 节点作为参数并且移除该结点的下一个节点.(如果参数呵下一个节点是null<br>
 * 的话则不操作.
 *
 * @author LeonChen
 * @since 1/8/20
 */
class e01_03_24 {

  public static void main(String[] args) {

    Node<String> node5 = new Node<>(null, "5");
    Node<String> node4 = new Node<>(node5, "4");
    Node<String> node3 = new Node<>(node4, "3");
    Node<String> node2 = new Node<>(node3, "2");
    Node<String> node1 = new Node<>(node2, "1");

    removeAfter(node1);

    Node<String> cur = node1;
    while (cur != null) {
      StdOut.print(cur.value);
      cur = cur.next;
    }
  }

  private static void removeAfter(Node<String> node) {

    if (node != null && node.next != null) {
      node.next = node.next.next;
    }
  }

  private static class Node<E> {
    E value;
    Node next;

    public Node(Node next, E value) {
      this.next = next;
      this.value = value;
    }
  }
}
