package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.exercise.chapter1_3.e01_03_32;

/**
 * 1.4.30 Deque with a stack and a steque. Implement a deque with a stack and a steque (see Exercise
 * 1.3.32) so that each deque operation takes a constant amortized number of stack and steque
 * operations.
 *
 * <p>使用一个 stack 和一个 steque 实现的 Deque.使用一个 stack 和一个 steque (见练习 1.3.32)实现一个 Deque<br>
 * 使得没个deque操作平均消耗一个常量级的stack和steque操作.
 *
 * @author LeonChen
 * @since 3/24/20
 */
class e01_04_30 {

  /**
   * 我们注意到,stack 和 steque 同时支持后入先出,所以一个做Deque的左边,一个做右边即可,头节点朝外,<br>
   * 当需要pop的一边没有元素的时候,将另一边全部弹出推入空的这边即可.
   *
   * @param args
   */
  public static void main(String[] args) {

    Deque<Integer> deque = new Deque<>();

    deque.pushLeft(1);
    deque.pushLeft(2);
    deque.pushRitht(3);
    StdOut.println("steque size should be 3 , steque.size =" + deque.size());
    deque.pushLeft(4);
    Integer i = deque.popRight();
    StdOut.println("stack pop should be 3 , pop =" + i);
    deque.pushRitht(5);
    deque.pushLeft(6);
    i = deque.popRight();
    StdOut.println("stack pop should be 5 , pop =" + i);
    i = deque.popRight();
    StdOut.println("stack pop should be 1 , pop =" + i);
    i = deque.popRight();
    StdOut.println("stack pop should be 2 , pop =" + i);
    i = deque.popLeft();
    StdOut.println("stack pop should be 6 , pop =" + i);
    i = deque.popLeft();
    StdOut.println("stack pop should be 4, pop =" + i);
  }

  /**
   * API -----------------------------------------------------------<br>
   * <code>
   *     isEmpty() is the deque empty?
   *     int size() number of items in the deque
   *     void pushLeft(Item item) add an item to the left end
   *     void pushRight(Item item) add an item to the right end
   *     Item popLeft() remove an item from the left end
   *     Item popRight() remove an item from the right end
   * </code>
   */
  private static class Deque<Item> {
    private Stack<Item> left;
    private e01_03_32.Steque<Item> right;

    public Deque() {
      left = new Stack<>();
      right = new e01_03_32.Steque<>();
    }

    public boolean isEmpty() {
      return left.isEmpty() && right.isEmpty();
    }

    public int size() {
      return left.size() + right.size();
    }

    public void pushLeft(Item item) {
      left.push(item);
    }

    public void pushRitht(Item item) {
      right.push(item);
    }

    public Item popLeft() {
      if (isEmpty()) {
        throw new RuntimeException(" there is not element can be poped");
      }
      if (left.isEmpty()) {
        while (!right.isEmpty()) {
          left.push(right.pop());
        }
      }
      return left.pop();
    }

    public Item popRight() {
      if (isEmpty()) {
        throw new RuntimeException(" there is not element can be poped");
      }
      if (right.isEmpty()) {
        while (!left.isEmpty()) {
          right.push(left.pop());
        }
      }

      return right.pop();
    }
  }
}
