package edu.princeton.cs.exercise.chapter1_3;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.1 Add a method isFull() to FixedCapacityStackOfStrings.
 *
 * <p>添加一个 isFull() 方法到 FixedCapacityStackOfStrings
 *
 * @author LeonChen
 * @since 12/24/19
 */
public class e01_03_01 {

  public static class FixedCapacityStackOfStrings implements Iterable<String> {
    private String[] a; // holds the items
    private int N; // number of items in stack

    // create an empty stack with given capacity
    public FixedCapacityStackOfStrings(int capacity) {
      a = new String[capacity];
      N = 0;
    }

    public boolean isEmpty() {
      return N == 0;
    }

    /**
     * 是否填充满了.
     *
     * @return
     */
    public boolean isFull() {
      // 通过判断当前数量和最大数量的比来实现
      return N == a.length;
    }

    public void push(String item) {
      a[N++] = item;
    }

    public String pop() {
      return a[--N];
    }

    public String peek() {
      return a[N - 1];
    }

    public Iterator<String> iterator() {
      return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<String> {
      private int i = N - 1;

      public boolean hasNext() {
        return i >= 0;
      }

      public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        return a[i--];
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }
    }

    public static void main(String[] args) {
      int max = Integer.parseInt(args[0]);
      FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(max);
      while (!StdIn.isEmpty()) {
        String item = StdIn.readString();
        if (!item.equals("-")) stack.push(item);
        else if (stack.isEmpty()) StdOut.println("BAD INPUT");
        else StdOut.print(stack.pop() + " ");
      }
      StdOut.println();

      // print what's left on the stack
      StdOut.print("Left on stack: ");
      for (String s : stack) {
        StdOut.print(s + " ");
      }
      StdOut.println();
    }
  }
}
