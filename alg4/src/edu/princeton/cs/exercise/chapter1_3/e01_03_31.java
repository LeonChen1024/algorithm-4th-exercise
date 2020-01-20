package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.31 Implement a nested class DoubleNode for building doubly-linked lists, where each node
 * contains a reference to the item preceding it and the item following it in the list (null if
 * there is no such item). Then implement static methods for the following tasks: insert at the
 * beginning, insert at the end, remove from the beginning, remove from the end, insert before a
 * given node, insert after a given node, and remove a given node.
 *
 * <p>实现一个嵌套类 DoubleNode 用来创建双向链表,每个节点包含一个指向前一个项目的引用和后一个项目的引用(如果没有<br>
 * 这个项的话就返回null).然后实现静态方法用来处理下面的任务: 插入到开始位置,插入到末尾,从起始处移除,从<br>
 * 末尾移除,插入到一个给定节点之前,插入到一个给定节点之后,还有移除给定节点.
 *
 * @author LeonChen
 * @since 1/20/20
 */
class e01_03_31 {

  public static void main(String[] args) {
    DoubleNode<String> node5 = new DoubleNode<>();
    DoubleNode<String> node4 = new DoubleNode<>();
    DoubleNode<String> node3 = new DoubleNode<>();
    DoubleNode<String> node2 = new DoubleNode<>();
    DoubleNode<String> node1 = new DoubleNode<>();

    node1.prev = null;
    node1.next = node2;
    node1.value = "1";

    DoubleNode curNode = node1;
    while (curNode != null) {
      StdOut.print(curNode.value);
      curNode = curNode.next;
    }
  }




  public static class DoubleNode<E> {
    E value;
    DoubleNode next;
    DoubleNode prev;

    public DoubleNode(DoubleNode prev,DoubleNode next, E value,) {
      this.next = next;
      this.value = value;
      this.prev = prev;
    }

    public DoubleNode() {}
  }
}
