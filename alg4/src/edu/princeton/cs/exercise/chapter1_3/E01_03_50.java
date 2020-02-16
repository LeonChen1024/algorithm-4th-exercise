package edu.princeton.cs.exercise.chapter1_3;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.50 Fail-fast iterator. Modify the iterator code in Stack to immediately throw a
 * java.util.ConcurrentModificationException if the client modifies the collection (via push() or
 * pop()) during iteration? b).
 *
 * <p>Solution: Maintain a counter that counts the number of push() and pop() operations. When
 * creating an iterator, store this value as an Iterator instance variable. Before each call to
 * hasNext() and next(), check that this value has not changed since construction of the iterator;
 * if it has, throw the exception.
 *
 * <p>快速失败的迭代器.修改Stack 中的迭代器代码使得客户端在迭代的时候修改(通过 push 或者 pop 在 迭代的时候)集合会立即抛出一个 <br>
 * java.util.ConcurrentModificationException 异常.
 *
 * <p>解决方案: 维护一个计数器来计算 push() 和 pop() 的操作次数.当创建一个迭代器的时候,存储这个值作为一个迭代器实例变量.<br>
 * 在每次调用 hasNext() 和 next()之前,检查这个值与这个迭代器构造的时候没有变化;如果发生了变化抛出异常.
 *
 * @author LeonChen
 * @since 2/15/20
 */
class E01_03_50 {

  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    stack.push(2);
    for (Integer i : stack) {
      stack.push(4);
    }
  }

  public static class Stack<Item> implements Iterable<Item> {
    private Node<Item> first; // top of stack
    private int n; // size of the stack

    private int operationNum;

    // helper linked list class
    private static class Node<Item> {
      private Item item;
      private Node<Item> next;
    }

    /** Initializes an empty stack. */
    public Stack() {
      first = null;
      n = 0;
      operationNum = 0;
    }

    /**
     * Returns true if this stack is empty.
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
      return first == null;
    }

    /**
     * Returns the number of items in this stack.
     *
     * @return the number of items in this stack
     */
    public int size() {
      return n;
    }

    /**
     * Adds the item to this stack.
     *
     * @param item the item to add
     */
    public void push(Item item) {
      Node<Item> oldfirst = first;
      first = new Node<Item>();
      first.item = item;
      first.next = oldfirst;
      n++;
      operationNum++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public Item pop() {
      if (isEmpty()) throw new NoSuchElementException("Stack underflow");
      Item item = first.item; // save item to return
      first = first.next; // delete first node
      n--;
      operationNum++;
      return item; // return the saved item
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public Item peek() {
      if (isEmpty()) throw new NoSuchElementException("Stack underflow");
      return first.item;
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in this stack in LIFO order, separated by spaces
     */
    public String toString() {
      StringBuilder s = new StringBuilder();
      for (Item item : this) {
        s.append(item);
        s.append(' ');
      }
      return s.toString();
    }

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order
     */
    public Iterator<Item> iterator() {
      return new ListIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
      private Node<Item> current;
      private int curOperNum;

      public ListIterator(Node<Item> first) {
        current = first;
        curOperNum = operationNum;
      }

      @Override
      public boolean hasNext() {
        if (curOperNum != operationNum) {
          throw new ConcurrentModificationException();
        }
        return current != null;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }

      @Override
      public Item next() {
        if (curOperNum != operationNum) {
          throw new ConcurrentModificationException();
        }
        if (!hasNext()) throw new NoSuchElementException();
        Item item = current.item;
        current = current.next;
        return item;
      }
    }
  }
}
