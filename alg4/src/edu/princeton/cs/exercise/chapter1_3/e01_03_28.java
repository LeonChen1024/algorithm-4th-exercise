package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.28 Develop a recursive solution to the previous question.
 *
 * <p>开发一个上个问题的迭代解决方案
 *
 * @author LeonChen
 * @since 1/19/20
 */
class e01_03_28 {

  public static void main(String[] args) {

    Node<Integer> node5 = new Node<>(null, 5);
    Node<Integer> node4 = new Node<>(node5, 6);
    Node<Integer> node3 = new Node<>(node4, 3);
    Node<Integer> node2 = new Node<>(node3, 4);
    Node<Integer> node1 = new Node<>(node2, 12);

    StdOut.print(max(node1, 0));
  }

  private static int max(Node<Integer> first, int maxNum) {
    Node<Integer> curNode = first;

    if (curNode == null) {
      return maxNum;
    }
    if (curNode.value > maxNum) {
      maxNum = curNode.value;
    }
    curNode = curNode.next;
    return max(curNode, maxNum);
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
