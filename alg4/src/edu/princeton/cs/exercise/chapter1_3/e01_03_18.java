package edu.princeton.cs.exercise.chapter1_3;

/**
 * 1.3.18 Suppose x is a linked-list node and not the last node on the list. What is the effect of
 * the following code fragment? <code>x.next = x.next.next;</code> Answer : Deletes from the list
 * the node immediately following x.
 *
 * <p>假设x是一个链表节点并且不是最后的一个节点.下面这段代码的作用是什么? <code>x.next = x.next.next;</code> <br>
 * 答案: 删除紧跟着x的下一个节点.
 *
 * @author LeonChen
 * @since 1/7/20
 */
class e01_03_18 {

  public static void main(String[] args) {
    // 因为它将下一个节点的引用指向了后一个节点,导致原来的下一个节点失去了引用,所以被删除.
  }
}
