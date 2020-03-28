package edu.princeton.cs.exercise.chapter1_4;

import java.util.Stack;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.27 Queue with two stacks. Implement a queue with two stacks so that each queue operation
 * takes a constant amortized number of stack operations. Hint : If you push elements onto a stack
 * and then pop them all, they appear in reverse order. If you repeat this process, they’re now back
 * in order.
 *
 * <p>使用两个stack实现的queue.实现一个queue使用两个stack实现,使得每个queue操作平均都是常量级的stack操作.<br>
 * 提示: 如果你将元素push到一个stack然后pop它们全部,它们会是一个反向的顺序.如果你重复这个过程,它们会会到正常的顺序.
 *
 * @author LeonChen
 * @since 3/23/20
 */
class e01_04_27 {

  /** @param args */
  public static void main(String[] args) {
    Queue<Integer> queue = new Queue<>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    StdOut.println("queue size should be 3 , queue.size =" + queue.size());
    queue.enqueue(4);
    Integer i = queue.dequeue();
    StdOut.println("queue dequeue should be 1 , dequeue =" + i);
    queue.enqueue(5);
    queue.enqueue(6);
    queue.enqueue(7);
    i = queue.dequeue();
    StdOut.println("queue dequeue should be 2 , dequeue =" + i);
    i = queue.dequeue();
    StdOut.println("queue dequeue should be 3 , dequeue =" + i);
    i = queue.dequeue();
    StdOut.println("queue dequeue should be 4 , dequeue =" + i);
    i = queue.dequeue();
    StdOut.println("queue dequeue should be 5 , dequeue =" + i);
    i = queue.dequeue();
    StdOut.println("queue dequeue should be 6, dequeue =" + i);
    i = queue.dequeue();
    StdOut.println("queue dequeue should be 7, dequeue =" + i);
  }

  public static class Queue<E> {

    private Stack<E> pushStack;
    private Stack<E> popStack;

    public Queue() {
      pushStack = new Stack<>();
      popStack = new Stack<>();
    }

    public int size() {
      return pushStack.size() + popStack.size();
    }

    public boolean isEmpty() {
      return pushStack.isEmpty() && popStack.isEmpty();
    }

    public void enqueue(E e) {
      pushStack.push(e);
    }

    public E dequeue() {

      if (popStack.isEmpty()) {

        if (pushStack.isEmpty()) {
          throw new RuntimeException("there is not element in queue");
        } else {
          while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
          }
        }
      }
      return popStack.pop();
    }
  }
}
