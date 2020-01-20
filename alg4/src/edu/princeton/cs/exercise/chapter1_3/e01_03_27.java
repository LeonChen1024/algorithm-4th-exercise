package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.27 Write a method max() that takes a reference to the first node in a linked list as argument
 * and returns the value of the maximum key in the list. Assume that all keys are positive integers,
 * and return 0 if the list is empty.
 *
 * <p>编写一个方法 max() 接收一个链表的第一个节点的引用作为参数并且返回列表中的最大的键.假设所有的键都是正整数,并且如果<br>
 * 列表是空的时候返回0
 *
 * @author LeonChen
 * @since 1/9/20
 */
class e01_03_27 {

  public static void main(String[] args) {

    Node<Integer> node5 = new Node<>(null, 5);
    Node<Integer> node4 = new Node<>(node5, 6);
    Node<Integer> node3 = new Node<>(node4, 3);
    Node<Integer> node2 = new Node<>(node3, 4);
    Node<Integer> node1 = new Node<>(node2, 12);

    StdOut.print(max(node1));
  }

  private static int max(Node<Integer> first) {

    Node<Integer> curNode = first;

    int maxNum = 0;

    while (curNode != null) {
      if (curNode.value > maxNum) {
        maxNum = curNode.value;
      }
      curNode = curNode.next;
    }
    return maxNum;
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
