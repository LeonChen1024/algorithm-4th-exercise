package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.6 What does the following code fragment do to the queue q? <code>
 *    Stack<String> stack = new Stack<String>();
 *     while (!q.isEmpty()) stack.push(q.dequeue());
 *     while (!stack.isEmpty()) q.enqueue(stack.pop());
 * </code> 下面的代码片段对队列q做了什么?
 *
 * @author LeonChen
 * @since 12/25/19
 */
class e01_03_06 {

  public static void main(String[] args) {
    Queue<String> q = new Queue<>();
    q.enqueue("1");
    q.enqueue("2");
    q.enqueue("3");
    q.enqueue("4");
    q.enqueue("5");

    Stack<String> stack = new Stack<String>();
    while (!q.isEmpty()) stack.push(q.dequeue());
    while (!stack.isEmpty()) q.enqueue(stack.pop());

    for (String s : q) {
      StdOut.print(s);
    }

    // 反转了q中元素的顺序. 利用了stack 的后入先出原则与 queue 的先入先出原则.

  }
}
