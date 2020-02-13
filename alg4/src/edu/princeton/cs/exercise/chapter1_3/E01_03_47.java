package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.47 Catenable queues, stacks, or steques. Add an extra operation catenation that
 * (destructively) concatenates two queues, stacks, or steques (see Exercise 1.3.32). Hint : Use a
 * circular linked list, maintaining a pointer to the last item.
 *
 * <p>可连接的队列,栈或者 steques . 添加一个额外的操作 链接 (破坏性的) 链接两个 queue,stacks 或者 steques (见练习 1.3.32). <br>
 * 提示: 使用一个循环链表,维护一个指向最后一个项的指针.
 *
 * @author LeonChen
 * @since 2/12/20
 */
class E01_03_47 {

  public static void main(String[] args) {
    // test catenable queue
    StdOut.println();

    StdOut.println(" test catenable queue");
    CatenableQueue<Integer> queue = new CatenableQueue<>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    StdOut.println("src queue = " + queue);
    CatenableQueue<Integer> addedQueue = new CatenableQueue<>();
    addedQueue.enqueue(4);
    addedQueue.enqueue(5);
    addedQueue.enqueue(6);
    StdOut.println("added queue = " + addedQueue);
    queue.catenate(addedQueue);
    addedQueue.enqueue(7);
    StdOut.println("after add = " + queue);
    StdOut.println("after add size = " + queue.size());

    // test catenable stack
    StdOut.println();

    StdOut.println("test catenable stack ");
    CatenableStack<Integer> stack = new CatenableStack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    StdOut.println("src stack = " + stack);
    CatenableStack<Integer> addedStack = new CatenableStack<>();
    addedStack.push(4);
    addedStack.push(5);
    addedStack.push(6);
    StdOut.println("added stack = " + addedStack);
    stack.catenate(addedStack);
    addedStack.push(7);
    StdOut.println("after add = " + stack);
    StdOut.println("after add size = " + stack.size());

    // test catenable stack
    StdOut.println();
    StdOut.println("test catenable steque ");
    CatenableSteque<Integer> steque = new CatenableSteque<>();
    steque.enqueue(1);
    steque.enqueue(2);
    steque.enqueue(3);
    StdOut.println("src steque = " + steque);

    CatenableSteque<Integer> addedSteque = new CatenableSteque<>();
    addedSteque.enqueue(4);
    addedSteque.enqueue(5);
    addedSteque.enqueue(6);
    StdOut.println("added steque = " + addedSteque);

    steque.catenate(addedSteque);
    StdOut.println("after add = " + steque);
    StdOut.println("after add size = " + steque.size);
  }

  private static class CatenableSteque<E> {
    private Node first;
    private Node last;
    private int size;

    public CatenableSteque() {
      first = null;
      last = null;
      size = 0;
    }

    public CatenableSteque<E> catenate(CatenableSteque<E> stack) {
      last.next = stack.first;
      size = size + stack.size;
      last = stack.last;

      stack.first = null;
      stack.last = null;
      stack.size = 0;

      return this;
    }

    public void push(E e) {
      Node<E> node = new Node<>(null, e);
      if (first == null) {
        first = node;
        last = node;
      } else {
        node.next = first;
        first = node;
      }
      size++;
    }

    public E pop() {
      if (first != null) {
        Node<E> oldFirst = first;
        first = first.next;
        if (first == null) {
          last = null;
        }
        size--;
        return oldFirst.value;
      }
      return null;
    }

    public void enqueue(E e) {
      Node<E> node = new Node<>(null, e);
      if (last == null) {
        first = node;
        last = node;
      } else {
        Node oldLast = last;
        last = node;
        oldLast.next = last;
      }
      size++;
    }

    @Override
    public String toString() {
      StringBuilder s = new StringBuilder();
      Node cur = first;

      while (cur != null) {
        s.append(cur.value + " ");
        cur = cur.next;
      }

      return s.toString();
    }

    private static class Node<E> {
      E value;
      Node next;

      public Node(Node next, E value) {
        this.next = next;
        this.value = value;
      }

      public Node() {}
    }
  }

  public static class CatenableStack<Item> implements Iterable<Item> {
    private Node<Item> first; // top of stack
    private int n; // size of the stack

    // helper linked list class
    private class Node<Item> {
      private Item item;
      private Node<Item> next;
    }

    /** Initializes an empty stack. */
    public CatenableStack() {
      first = null;
      n = 0;
    }

    public CatenableStack<Item> catenate(CatenableStack<Item> stack) {
      Node cur = stack.first;
      while (cur.next != null) {
        cur = cur.next;
      }
      cur.next = first;

      first = stack.first;
      n = n + stack.n;

      stack.first = null;
      stack.n = 0;
      return this;
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
    @Override
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
    @Override
    public Iterator<Item> iterator() {
      return new ListIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
      private Node<Item> current;

      public ListIterator(Node<Item> first) {
        current = first;
      }

      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }

      @Override
      public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        Item item = current.item;
        current = current.next;
        return item;
      }
    }
  }

  public static class CatenableQueue<Item> implements Iterable<Item> {
    private Node<Item> first; // beginning of queue
    private Node<Item> last; // end of queue
    private int n; // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
      private Item item;
      private Node<Item> next;
    }

    /** Initializes an empty queue. */
    public CatenableQueue() {
      first = null;
      last = null;
      n = 0;
    }

    public CatenableQueue<Item> catenate(CatenableQueue<Item> queue) {
      last.next = queue.first;
      n = n + queue.size();
      last = queue.last;
      queue.first = null;
      queue.last = null;
      queue.n = 0;
      return this;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
      return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
      return n;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
      if (isEmpty()) throw new NoSuchElementException("Queue underflow");
      return first.item;
    }

    /**
     * Adds the item to this queue.
     *
     * @param item the item to add
     */
    public void enqueue(Item item) {
      Node<Item> oldlast = last;
      last = new Node<Item>();
      last.item = item;
      last.next = null;
      if (isEmpty()) first = last;
      else oldlast.next = last;
      n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
      if (isEmpty()) throw new NoSuchElementException("Queue underflow");
      Item item = first.item;
      first = first.next;
      n--;
      if (isEmpty()) last = null; // to avoid loitering
      return item;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    @Override
    public String toString() {
      StringBuilder s = new StringBuilder();
      for (Item item : this) {
        s.append(item);
        s.append(' ');
      }
      return s.toString();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    @Override
    public Iterator<Item> iterator() {
      return new ListIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
      private Node<Item> current;

      public ListIterator(Node<Item> first) {
        current = first;
      }

      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }

      @Override
      public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        Item item = current.item;
        current = current.next;
        return item;
      }
    }
  }
}
