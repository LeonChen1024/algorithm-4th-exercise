package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.30 Write a function that takes the first Node in a linked list as argument and
 * (destructively) reverses the list, returning the first Node in the result. Iterative solution :
 * To accomplish this task, we maintain references to three consecutive nodes in the linked list,
 * reverse, first, and second. At each iteration, we extract the node first from the original linked
 * list and insert it at the beginning of the reversed list. We maintain the invariant that first is
 * the first node of what’s left of the original list, second is the second node of what’s left of
 * the original list, and reverse is the first node of the resulting reversed list. <code>
 * public Node reverse(Node x) {
 *    Node first = x;
 *    Node reverse = null;
 *    while (first != null) {
 *        Node second = first.next;
 *        first.next = reverse;
 *        reverse = first;
 *        first = second;
 *    }
 *    return reverse;
 * }
 * </code> When writing code involving linked lists, we must always be careful to properly handle
 * the exceptional cases (when the linked list is empty, when the list has only one or two nodes)
 * and the boundary cases (dealing with the first or last items). This is usually much trickier than
 * handling the normal cases. Recursive solution : Assuming the linked list has N nodes, we
 * recursively reverse the last N – 1 nodes, and then carefully append the first node to the end.
 *
 * <p>编写一个函数接收一个链表的第一个节点作为参数并且(破坏性的)反转这个链表,结果返回它的第一个节点.迭代的解决方案:<br>
 * 为了完成这个任务,我们维护3个该列表中的连续节点,反转,第一个,还有第二个.在每个迭代中,我们首先从原始链表中提取出节点<br>
 * 并且插入到反转列表的开始.我们保持first节点是原始链表剩下的第一个节点,second是原始列表剩下的第二个节点,并且reverse<br>
 * 是反转列表的第一个节点. <code>
 *  * public Node reverse(Node x) {
 *  *    Node first = x;
 *  *    Node reverse = null;
 *  *    while (first != null) {
 *  *        Node second = first.next;
 *  *        first.next = reverse;
 *  *        reverse = first;
 *  *        first = second;
 *  *    }
 *  *    return reverse;
 *  * }
 *  * </code> 当编写涉及链表的代码时,我们必须总是注意合理的处理异常情况(当这个链表是空的,当这个列表只有一个或者两个<br>
 * 节点)还有边界情况(处理第一个和最后一个项目)这通常比处理正常情况更加棘手.递归方案:假设链表有N个节点,我们递归的反转<br>
 * 最后N-1个节点,然后小心的添加第一个节点到最后.<code>
 * public Node reverse(Node first) {
 *     if (first == null) return null;
 *     if (first.next == null) return first;
 *     Node second = first.next;
 *     Node rest = reverse(second);
 *     second.next = first;
 *     first.next = null;
 *     return rest;
 *   }
 *     </code>
 *
 * @author LeonChen
 * @since 1/20/20
 */
class e01_03_30 {

  public static void main(String[] args) {
    Node<String> node5 = new Node<>(null, "5");
    Node<String> node4 = new Node<>(node5, "4");
    Node<String> node3 = new Node<>(node4, "3");
    Node<String> node2 = new Node<>(node3, "2");
    Node<String> node1 = new Node<>(node2, "1");

    Node curNode = reverse(node1);
    while (curNode != null) {
      StdOut.print(curNode.value);
      curNode = curNode.next;
    }
  }

  //  public static Node reverse(Node first) {
  //    if (first == null) return null;
  //    if (first.next == null) return first;
  //    Node second = first.next;
  //    Node rest = reverse(second);
  //    second.next = first;
  //    first.next = null;
  //    return rest;
  //  }

  public static Node reverse(Node x) {
    Node first = x;
    Node reverse = null;
    while (first != null) {
      Node second = first.next;
      first.next = reverse;
      reverse = first;
      first = second;
    }
    return reverse;
  }

  public static class Node<E> {
    E value;
    Node next;

    public Node(Node next, E value) {
      this.next = next;
      this.value = value;
    }

    public Node() {}
  }
}
