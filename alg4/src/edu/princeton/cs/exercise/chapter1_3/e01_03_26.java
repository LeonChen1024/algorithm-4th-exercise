package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.26 Write a method remove() that takes a linked list and a string key as arguments and removes
 * all of the nodes in the list that have key as its item field.
 *
 * <p>编写一个方法 remove() 接收一个链表并且使用一个字符串键值作为参数并且移除链表中所有项目值呵键相同的节点.
 *
 * @author LeonChen
 * @since 1/9/20
 */
class e01_03_26 {

  public static void main(String[] args) {

    Node<String> node5 = new Node<>(null, "5");
    Node<String> node4 = new Node<>(node5, "4");
    Node<String> node3 = new Node<>(node4, "2");
    Node<String> node2 = new Node<>(node3, "2");
    Node<String> node1 = new Node<>(node2, "1");

    remove(node1, "1");
  }

  private static void remove(Node<String> first, String key) {

    while (first != null && first.value.equals(key)) {
      first = first.next;
    }

    Node<String> curNode = first;

    while (curNode != null) {
      if (curNode.next != null && curNode.next.value.equals(key)) {
        curNode.next = curNode.next.next;
      }
      curNode = curNode.next;
    }

    curNode = first;
    while (curNode != null) {
      StdOut.print(curNode.value);
      curNode = curNode.next;
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
