package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.29 Steque with two stacks. Implement a steque with two stacks so that each steque operation
 * (see Exercise 1.3.32) takes a constant amortized number of stack operations.
 *
 * <p>使用两个stack实现 Steque . 使用两个stack实现一个steque使得每个steque操作 (见 Exercise 1.3.32) 消耗<br>
 * 一个平均是常量级的stack 操作.
 *
 * @author LeonChen
 * @since 3/23/20
 */
class e01_04_29 {

  /** @param args */
  public static void main(String[] args) {
    Steque<Integer> steque = new Steque<>();

    steque.push(1);
    steque.push(2);
    steque.push(3);
    StdOut.println("steque size should be 3 , steque.size =" + steque.size());
    steque.push(4);
    Integer i = steque.pop();
    StdOut.println("stack pop should be 4 , pop =" + i);
    steque.push(5);
    steque.push(6);
    steque.enqueue(7);
    i = steque.pop();
    StdOut.println("stack pop should be 6 , pop =" + i);
    i = steque.pop();
    StdOut.println("stack pop should be 5 , pop =" + i);
    i = steque.pop();
    StdOut.println("stack pop should be 3 , pop =" + i);
    i = steque.pop();
    StdOut.println("stack pop should be 2 , pop =" + i);
    i = steque.pop();
    StdOut.println("stack pop should be 1, pop =" + i);
    i = steque.pop();
    StdOut.println("stack pop should be 7, pop =" + i);
  }

  /**
   * API -----------------------------------------------------------<br>
   * void push(E e) 添加一个元素到头部 <br>
   * E pop() 从头部删除一个元素 <br>
   * void enqueue(E e) 添加一个元素到尾部
   *
   * @param <E>
   */
  private static class Steque<E> {
    private Stack<E> enqueueStack;
    private Stack<E> popStack;

    public Steque() {
      enqueueStack = new Stack<>();
      popStack = new Stack<>();
    }

    public void push(E e) {
      popStack.push(e);
    }

    public int size() {
      return enqueueStack.size() + popStack.size();
    }

    public E pop() {
      if (popStack.isEmpty()) {
        if (enqueueStack.isEmpty()) {
          throw new RuntimeException("there is not element in steque");
        } else {
          while (!enqueueStack.isEmpty()) {
            popStack.push(enqueueStack.pop());
          }
        }
      }
      return popStack.pop();
    }

    public void enqueue(E e) {
      enqueueStack.push(e);
    }
  }
}
