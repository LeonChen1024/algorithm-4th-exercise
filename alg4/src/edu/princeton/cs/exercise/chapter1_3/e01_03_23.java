package edu.princeton.cs.exercise.chapter1_3;

/**
 * 1.3.23 Why does the following code fragment not do the same thing as in the previous question?
 * <code>x.next = t; t.next = x.next; </code>Answer : When it comes time to update t.next, x.next is
 * no longer the original node following x, but is instead t itself!
 *
 * <p>为什么下面的代码片段没有做出和前一个问题相同的作用?<code>x.next = t; t.next = x.next; </code> <br>
 * 答案: 当它更新t.next 的时候 ,x.next 已经不是原来的跟在x之后的节点了,而是变成了t自身.
 *
 * @author LeonChen
 * @since 1/8/20
 */
class e01_03_23 {

  public static void main(String[] args) {}
}
