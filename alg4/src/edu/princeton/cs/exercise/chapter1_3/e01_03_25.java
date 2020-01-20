package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.25 Write a method insertAfter() that takes two linked-list Node arguments and inserts the
 * second after the first on its list (and does nothing if either argument is null).
 *
 * <p>编写一个方法 insertAfter() 接收两个链表节点参数并且将第二个参数差入到第一个参数的后面(如果两个参数有一个是null<br>
 * 则不做任何操作.
 *
 * @author LeonChen
 * @since 1/8/20
 */
class e01_03_25 {

  public static void main(String[] args) {

    Node<String> node5 = new Node<>(null, "5");
    Node<String> node4 = new Node<>(node5, "4");
    Node<String> node3 = new Node<>(node4, "3");
    Node<String> node2 = new Node<>(node3, "2");
    Node<String> node1 = new Node<>(node2, "1");

    Node<String> source = new Node<>(null, "src");

    insertAfter(node1, source);

    Node<String> cur = node1;
    while (cur != null) {
      StdOut.print(cur.value);
      cur = cur.next;
    }
  }

  private static void insertAfter(Node<String> target, Node<String> source) {
    if (target != null && source != null) {
      source.next = target.next;
      target.next = source;
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
