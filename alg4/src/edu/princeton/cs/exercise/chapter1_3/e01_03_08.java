package edu.princeton.cs.exercise.chapter1_3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.8 Give the contents and size of the array for DoublingStackOfStrings with the input <code>
 *     it was - the best - of times - - - it was - the - -
 * </code> 给出输入到 DoublingStackOfStrings 的数组的内容和大小.
 *
 * @author LeonChen
 * @since 12/26/19
 */
class e01_03_08 {

  public static void main(String[] args) {
    DoublingStackOfStrings<String> stack = new DoublingStackOfStrings<String>();

    String[] in =
        new String[] {
          "it", "was", "-", "the", "best", "-", "of", "times", "-", "-", "-", "it", "was", "-",
          "the", "-", "-"
        };

    for (int i = 0; i < in.length; i++) {
      String s = in[i];
      if (s.equals("-")) {
        stack.pop();
      } else {
        stack.push(s);
      }
    }
    StdOut.println(stack.arraySize());

    for (Iterator<String> iter = stack.inputArrayContent().iterator(); iter.hasNext(); ) {

      StdOut.print(iter.next() + " ");
    }
  }

  // 使用一个额外的数组来保存输入的内容即可
  public static class DoublingStackOfStrings<Item> implements Iterable<Item> {
    private Node<Item> first; // top of stack
    private int n; // size of the stack
    private LinkedList<Item> doubleling;

    // helper linked list class
    private static class Node<Item> {
      private Item item;
      private Node<Item> next;

      public Item getItem() {
        return item;
      }

      public void setItem(Item item) {
        this.item = item;
      }

      public Node<Item> getNext() {
        return next;
      }

      public void setNext(Node<Item> next) {
        this.next = next;
      }
    }

    /** Initializes an empty stack. */
    public DoublingStackOfStrings() {
      first = null;
      n = 0;
      doubleling = new LinkedList<>();
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
      doubleling.add(item);
    }

    /** @return input array's size. */
    public int arraySize() {
      return doubleling.size();
    }

    public LinkedList<Item> inputArrayContent() {
      return doubleling;
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

      public ListIterator(Node<Item> first) {
        current = first;
      }

      public boolean hasNext() {
        return current != null;
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }

      public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        Item item = current.item;
        current = current.next;
        return item;
      }
    }
  }
}
