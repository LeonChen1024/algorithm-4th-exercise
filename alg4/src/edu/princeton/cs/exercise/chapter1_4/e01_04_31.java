package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.31 Deque with three stacks. Implement a deque with three stacks so that each deque operation
 * takes a constant amortized number of stack operations.
 *
 * <p>使用3个stack实现一个Deque.使用3个stacks实现一个deque使得每个deque操作平均消耗常量级的stack操作
 *
 * @author LeonChen
 * @since 3/24/20
 */
class e01_04_31 {

  /**
   * 我们可以将两个stack作为Deque的两边,它们都支持后入先出,只要将头节点朝外即可.<br>
   * 当我们碰到pop的一边没有元素的时候,我们可以将另一边的一半先弹出 并推入临时栈,<br>
   * 然后将剩下的弹出推入到空元素这边,这时候顺序刚好合适. 而临时栈内现在的顺序是反的,<br>
   * 我们需要将他全部弹出并推入此时空的这个栈,顺序恢复.
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
    private Stack<Item> right;
    private Stack<Item> temp;

    public Deque() {
      left = new Stack<>();
      right = new Stack<>();
      temp = new Stack<>();
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
        for (int i = 0; i < right.size() / 2; i++) {
          temp.push(right.pop());
        }
        while (!right.isEmpty()) {
          left.push(right.pop());
        }
        while (!temp.isEmpty()) {
          right.push(temp.pop());
        }
      }
      return left.pop();
    }

    public Item popRight() {
      if (isEmpty()) {
        throw new RuntimeException(" there is not element can be poped");
      }
      if (right.isEmpty()) {
        for (int i = 0; i < left.size() / 2; i++) {
          temp.push(left.pop());
        }
        while (!left.isEmpty()) {
          right.push(left.pop());
        }
        while (!temp.isEmpty()) {
          left.push(temp.pop());
        }
      }

      return right.pop();
    }
  }
}
