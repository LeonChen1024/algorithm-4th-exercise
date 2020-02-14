package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 1.3.48 Two stacks with a deque. Implement two stacks with a single deque so that each operation
 * takes a constant number of deque operations (see Exercise 1.3.33).
 *
 * <p>使用一个 双向队列 实现两个栈. 通过一个 deque 实现两个栈使得每个操作只需要常数次的 deque 操作(见 练习 1.3.33)
 *
 * @author LeonChen
 * @since 2/13/20
 */
class E01_03_48 {

  public static void main(String[] args) {
    TwoStack<Integer> twoStack = new TwoStack<>();
    twoStack.leftStackPush(1);
    twoStack.leftStackPush(2);
    twoStack.leftStackPush(3);

    twoStack.rightStackPush(4);
    twoStack.rightStackPush(5);
    twoStack.rightStackPush(6);
    StdOut.println(twoStack.leftStackPop());
    StdOut.println(twoStack.leftStackPop());
    StdOut.println(twoStack.leftStackPop());
    StdOut.println(twoStack.leftStackPop());
    StdOut.println(twoStack.leftStackPop());
    twoStack.leftStackPush(7);
    StdOut.println(twoStack.leftStackPop());
    StdOut.println(twoStack.rightStackPop());
  }

  /** 通过传入一个分隔符区分开两个栈.使得左栈和右栈互不干扰. */
  private static class TwoStack<Item> {

    private Deque<Item> deque;
    private Item divider;

    public TwoStack() {
      deque = new Deque<>();
      divider = (Item) new Object();
      deque.pushLeft(divider);
    }

    public void leftStackPush(Item item) {
      deque.pushLeft(item);
    }

    public void rightStackPush(Item item) {
      deque.pushRitht(item);
    }

    public Item leftStackPop() {
      Item item = deque.popLeft();
      if (item == divider) {
        deque.pushLeft(item);
        return null;
      }
      return item;
    }

    public Item rightStackPop() {
      Item item = deque.popRight();
      if (item == divider) {
        deque.pushRitht(item);
        return null;
      }
      return item;
    }
  }

  private static class Deque<Item> implements Iterable<Item> {
    private Node<Item> left;
    private Node<Item> right;
    private int size;

    public Deque() {
      left = null;
      right = null;
      size = 0;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public int size() {
      return size;
    }

    public void pushLeft(Item item) {
      Node<Item> node = new Node<Item>(null, null, item);
      if (left == null) {
        left = node;
        right = node;
      } else {
        node.next = left;
        left.prev = node;
        left = node;
      }
      size++;
    }

    public void pushRitht(Item item) {
      Node<Item> node = new Node<>(null, null, item);
      if (left == null) {
        left = node;
        right = node;
      } else {
        right.next = node;
        node.prev = right;
        right = node;
      }
      size++;
    }

    public Item popLeft() {
      if (left == null) {
        return null;
      }
      Node<Item> oldLeft = left;
      left = left.next;
      if (left == null) {
        right = null;
      } else {
        left.prev = null;
      }

      oldLeft.next = null;
      size--;
      return oldLeft.value;
    }

    public Item popRight() {
      if (right == null) {
        return null;
      }
      Node<Item> oldright = right;
      right = right.prev;

      if (right == null) {
        left = null;
      } else {
        right.next = null;
      }

      oldright.prev = null;
      size--;
      return oldright.value;
    }

    @Override
    public Iterator<Item> iterator() {
      return new Itera();
    }

    class Itera implements Iterator<Item> {

      private Node<Item> curNode;

      public Itera() {
        curNode = left;
      }

      @Override
      public boolean hasNext() {
        return curNode != null;
      }

      @Override
      public Item next() {
        Node<Item> oldNode = curNode;
        curNode = curNode.next;
        return oldNode.value;
      }
    }

    private static class Node<E> {
      E value;
      Node next;
      Node prev;

      public Node(Node prev, Node next, E value) {
        this.next = next;
        this.prev = prev;
        this.value = value;
      }

      public Node() {}
    }
  }
}
