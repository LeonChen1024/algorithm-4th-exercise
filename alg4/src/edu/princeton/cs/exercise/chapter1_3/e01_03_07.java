package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.7 Add a method peek() to Stack that returns the most recently inserted item on the stack
 * (without popping it).
 *
 * <p>添加一个 peek() 方法到 Stack 中返回最近插入到stack的项.
 *
 * @author LeonChen
 * @since 12/26/19
 */
class e01_03_07 {

  public static void main(String[] args) {

    //    Stack 中已经实现.
    Stack<String> stack = new Stack<String>();
    stack.push("1");
    // 先peek 再 pop 证明 peek 没有弹出最近的项.
    StdOut.print(stack.peek());
    StdOut.print(stack.pop());
  }
}
