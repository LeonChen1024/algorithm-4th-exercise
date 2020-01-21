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

    node2.prev = node1;
    node2.next = node3;
    node2.value = "2";

    node3.prev = node2;
    node3.next = node4;
    node3.value = "3";

    node4.prev = node3;
    node4.next = node5;
    node4.value = "4";

    node5.prev = node4;
    node5.next = null;
    node5.value = "5";

    DoubleNode node = new DoubleNode();
    node.value = "n";

    //    DoubleNode curNode = insertFirst(node3, node);
    //    DoubleNode curNode = insertLast(node3, node);
    //    DoubleNode curNode = deleteLast(node3);
    //    DoubleNode curNode = deleteFirst(node3);
    //    DoubleNode curNode = insertCurNodePrev(node3, node);
    //    DoubleNode curNode = insertCurNodeNext(node3, node);
    DoubleNode curNode = removeCurNode(node3);
    if (curNode == null) {
      return;
    }
    DoubleNode first = findFirst(curNode);
    curNode = first;
    while (curNode != null) {
      StdOut.print(curNode.value);
      curNode = curNode.next;
    }
  }

  private static DoubleNode removeCurNode(DoubleNode<String> removeNode) {
    if (removeNode.prev != null) {
      removeNode.prev.next = removeNode.next;
    }
    if (removeNode.next != null) {
      removeNode.next.prev = removeNode.prev;
    }

    DoubleNode returnNode = null;
    if (removeNode.prev != null) {
      returnNode = removeNode.prev;
    } else if (removeNode.next != null) {
      returnNode = removeNode.next;
    }

    removeNode.prev = null;
    removeNode.next = null;
    return returnNode;
  }

  private static DoubleNode insertCurNodeNext(DoubleNode<String> listNode, DoubleNode insertNode) {
    if (listNode == null) {
      return insertNode;
    }

    if (listNode.next != null) {
      listNode.next.prev = insertNode;
      insertNode.next = listNode.next;
    }

    listNode.next = insertNode;
    insertNode.prev = listNode;
    return insertNode;
  }

  private static DoubleNode insertCurNodePrev(DoubleNode<String> listNode, DoubleNode insertNode) {
    if (listNode == null) {
      return insertNode;
    }

    if (listNode.prev != null) {
      listNode.prev.next = insertNode;
      insertNode.prev = listNode.prev;
    }
    listNode.prev = insertNode;
    insertNode.next = listNode;
    return insertNode;
  }

  private static DoubleNode deleteFirst(DoubleNode<String> listNode) {
    if (listNode == null) {
      return null;
    }
    DoubleNode first = findFirst(listNode);
    DoubleNode newFisrt;
    if (first.next == null) {
      return null;
    } else {
      first.next.prev = null;
      newFisrt = first.next;
      first.next = null;
    }

    return newFisrt;
  }

  private static DoubleNode deleteLast(DoubleNode<String> listNode) {
    if (listNode == null) {
      return null;
    }

    DoubleNode last = findLast(listNode);
    DoubleNode newLast;
    if (last.prev == null) {
      return null;
    } else {
      last.prev.next = null;
      newLast = last.prev;
      last.prev = null;
    }

    return newLast;
  }

  private static DoubleNode findFirst(DoubleNode curNode) {
    while (curNode.prev != null) {
      curNode = curNode.prev;
    }
    return curNode;
  }

  private static DoubleNode findLast(DoubleNode curNode) {
    while (curNode.next != null) {
      curNode = curNode.next;
    }
    return curNode;
  }

  private static DoubleNode insertLast(DoubleNode<String> listNode, DoubleNode insertNode) {
    if (listNode == null) {
      return insertNode;
    }
    DoubleNode last = findLast(listNode);
    last.next = insertNode;
    insertNode.prev = last;

    return insertNode;
  }

  private static DoubleNode insertFirst(DoubleNode listNode, DoubleNode insertNode) {
    if (listNode == null) {
      return insertNode;
    }

    DoubleNode first = findFirst(listNode);

    first.prev = insertNode;
    insertNode.next = first;

    return insertNode;
  }

  private static class DoubleNode<E> {
    E value;
    DoubleNode next;
    DoubleNode prev;

    public DoubleNode(DoubleNode prev, DoubleNode next, E value) {
      this.next = next;
      this.value = value;
      this.prev = prev;
    }

    public DoubleNode() {}
  }
}
