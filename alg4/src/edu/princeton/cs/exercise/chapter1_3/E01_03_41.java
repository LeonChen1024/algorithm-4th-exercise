package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.41 Copy a queue. Create a new constructor so that <code>
 *     Queue<Item> r = new Queue<Item>(q);
 * </code> makes r a reference to a new and independent copy of the queue q.You should be able to
 * push and pop from either q or r without influencing the other. Hint : Delete all of the elements
 * from q and add these elements to both q and r.
 *
 * <p>复制一个 queue.创建一个构造器 <code>
 *     Queue<Item> r = new Queue<Item>(q);
 * </code> 使得 r 是一个引用指向一个新的并且独立的 queue 的副本.你应该可以对 q 或者 r 进行 push 或 pop 而不会影响到<br>
 * 另一个.提示:删除 q 中的所有元素并且添加这些元素到 q 和 r.
 *
 * @author LeonChen
 * @since 2/2/20
 */
class E01_03_41 {

  public static void main(String[] args) {

    Queue<Integer> q = new Queue<Integer>();
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    q.enqueue(4);

    Queue<Integer> r = new Queue<Integer>(q);
    //    Queue<Integer> r = q;

    q.enqueue(5);
    q.dequeue();
    r.enqueue(6);

    StdOut.println(q);
    StdOut.println(r);
  }

  public static class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item> {
      private Item item;
      private Node<Item> next;
    }

    public Queue() {
      first = null;
      last = null;
      n = 0;
    }

    public Queue(Queue<Item> q) {
      first = null;
      last = null;
      n = 0;
      int size = q.size();
      for (int i = 0; i < size; i++) {
        Item item = q.dequeue();
        enqueue(item);
        q.enqueue(item);
      }
    }

    public boolean isEmpty() {
      return first == null;
    }

    public int size() {
      return n;
    }

    public Item peek() {
      if (isEmpty()) throw new NoSuchElementException("Queue underflow");
      return first.item;
    }

    public void enqueue(Item item) {
      Node<Item> oldlast = last;
      last = new Node<Item>();
      last.item = item;
      last.next = null;
      if (isEmpty()) first = last;
      else oldlast.next = last;
      n++;
    }

    public Item dequeue() {
      if (isEmpty()) throw new NoSuchElementException("Queue underflow");
      Item item = first.item;
      first = first.next;
      n--;
      if (isEmpty()) last = null; // to avoid loitering
      return item;
    }

    public String toString() {
      StringBuilder s = new StringBuilder();
      for (Item item : this) {
        s.append(item);
        s.append(' ');
      }
      return s.toString();
    }

    public Iterator<Item> iterator() {
      return new ListIterator(first);
    }

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
