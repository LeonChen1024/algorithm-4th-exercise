package edu.princeton.cs.exercise.chapter1_3;

/**
 * 1.3.22 Suppose that x is a linked list Node. What does the following code fragment do? <code>
 *     t.next = x.next; x.next = t;
 * </code> Answer : Inserts node t immediately after node x.
 *
 * <p>假设x是一个linked list 节点. 下面的代码片段是做什么的?<code>
 *       t.next = x.next; x.next = t;
 *   </code> 答案: 差入一个节点t到x的相邻后面
 *
 * @author LeonChen
 * @since 1/8/20
 */
class e01_03_22 {

  public static void main(String[] args) {
    // 先把t的下一个节点设为 x 原来的下一个节点,在将x的下一个节点换成t节点.
  }
}
