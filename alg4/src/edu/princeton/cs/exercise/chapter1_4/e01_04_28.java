package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.28 Stack with a queue. Implement a stack with a single queue so that each stack operations
 * takes a linear number of queue operations. Hint : To delete an item, get all of the elements on
 * the queue one at a time, and put them at the end, except for the last one which you should delete
 * and return. (This solution is admittedly very inefficient.)
 *
 * <p>使用一个queue 实现Stack . 使用一个queue 实现一个 stack 使得每个stack操作消耗一个线性级queue操作时间.<br>
 * 提示:为了要删除一个项,每次都讲queue的元素全部弹出,并且重新放到末尾,除了最后一个元素,你应该要删除他并返回(这个<br>
 * 方案是非常低效的
 *
 * @author LeonChen
 * @since 3/23/20
 */
class e01_04_28 {

  /** @param args */
  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    StdOut.println("stack size should be 3 , queue.size =" + stack.size());
    stack.push(4);
    Integer i = stack.pop();
    StdOut.println("stack pop should be 4 , pop =" + i);
    stack.push(5);
    stack.push(6);
    stack.push(7);
    i = stack.pop();
    StdOut.println("stack pop should be 7 , pop =" + i);
    i = stack.pop();
    StdOut.println("stack pop should be 6 , pop =" + i);
    i = stack.pop();
    StdOut.println("stack pop should be 5 , pop =" + i);
    i = stack.pop();
    StdOut.println("stack pop should be 3 , pop =" + i);
    i = stack.pop();
    StdOut.println("stack pop should be 2, pop =" + i);
    i = stack.pop();
    StdOut.println("stack pop should be 1, pop =" + i);
  }

  public static class Stack<E> {

    private Queue<E> queue;

    public Stack() {
      queue = new Queue<>();
    }

    public int size() {
      return queue.size();
    }

    public boolean isEmpty() {
      return queue.isEmpty();
    }

    public void push(E e) {
      queue.enqueue(e);
    }

    public E pop() {

      if (queue.isEmpty()) {
        throw new RuntimeException("there is not element in Stack");
      } else {
        for (int i = 0; i < queue.size() - 1; i++) {
          queue.enqueue(queue.dequeue());
        }
      }
      return queue.dequeue();
    }
  }
}
