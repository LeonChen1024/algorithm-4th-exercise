package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 1.4.37 Autoboxing performance penalty. Run experiments to determine the performance penalty on
 * your machine for using autoboxing and auto-unboxing. Develop an implementation
 * FixedCapacityStackOfInts and use a client such as DoublingRatio to compare its performance with
 * the generic FixedCapacityStack<Integer>, for a large number of push() and pop() operations.
 *
 * <p>自动装箱的性能损失.在你的机器上对使用自动装箱和自动拆箱.开发一个 FixedCapacityStackOfInts 实现并且使用<br>
 * 一个客户端如 DoublingRatio 来对比它和 FixedCapacityStack<Integer> 在使用大量的push() 和 pop() 操作<br>
 * 情况下的性能差距.
 *
 * @author LeonChen
 * @since 3/31/20
 */
class e01_04_37 {

  /** */
  public static void main(String[] args) {
    int N = 10000000;

    FixedCapacityStackOfInts stackOfInts = new FixedCapacityStackOfInts(10);
    Stopwatch stopwatch = new Stopwatch();
    for (int i = 0; i < N; i++) {
      stackOfInts.push(i);
      stackOfInts.pop();
    }
    StdOut.println("pure int use time = " + stopwatch.elapsedTime());

    FixedCapacityStack<Integer> stack = new FixedCapacityStack<>(10);
    stopwatch = new Stopwatch();
    for (int i = 0; i < N; i++) {
      stack.push(i);
      stack.pop();
    }
    StdOut.println(" autoboxing and auto-unboxing use time = " + stopwatch.elapsedTime());
  }

  private static class FixedCapacityStackOfInts {

    private int[] values;
    private int size;

    public FixedCapacityStackOfInts(int capacity) {
      values = new int[capacity];
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public int size() {
      return size;
    }

    public void push(int item) {
      if (size == values.length) {
        throw new RuntimeException("Stack overflow");
      }

      values[size] = item;
      size++;
    }

    public int pop() {
      if (isEmpty()) {
        throw new RuntimeException("Stack underflow");
      }

      int item = values[size - 1];
      size--;
      return item;
    }
  }

  private static class FixedCapacityStack<Item> {

    private Item[] values;
    private int size;

    public FixedCapacityStack(int capacity) {
      values = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public int size() {
      return size;
    }

    public void push(Item item) {
      if (size == values.length) {
        throw new RuntimeException("Stack overflow");
      }

      values[size] = item;
      size++;
    }

    public Item pop() {
      if (isEmpty()) {
        throw new RuntimeException("Stack underflow");
      }

      Item item = values[size - 1];
      size--;
      return item;
    }
  }
}
