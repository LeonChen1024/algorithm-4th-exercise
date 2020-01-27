package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * 1.3.33 Deque. A double-ended queue or deque (pronounced “deck”) is like a stack or a queue but
 * supports adding and removing items at both ends. A deque stores a collection of items and
 * supports the following API: <code>
 * public class Deque<Item> implements Iterable<Item>
 *     Deque() create an empty deque boolean
 *     isEmpty() is the deque empty?
 *     int size() number of items in the deque
 *     void pushLeft(Item item) add an item to the left end
 *     void pushRight(Item item) add an item to the right end
 *     Item popLeft() remove an item from the left end
 *     Item popRight() remove an item from the right end
 *     API for a generic double-ended queue
 * </code> Write a class Deque that uses a doubly-linked list to implement this API and a class
 * ResizingArrayDeque that uses a resizing array.
 *
 * <p>Deque. 一个双端queue 或者 deque (发音为 "deck") 是像一个栈或者一个 queue 但是支持在两端添加和删除项.一个deque 存储了一个项的集合<br>
 * 并且支持以下 API: <code>
 *  * public class Deque<Item> implements Iterable<Item>
 *  *     Deque() 创建一个空的 Deque
 *  *     isEmpty() 这个 deque 是否是空的?
 *  *     int size()  deque 中项的数量
 *  *     void pushLeft(Item item) 添加一个项到左端
 *  *     void pushRight(Item item) 添加一个项到右端
 *  *     Item popLeft() 移除一个左端的项
 *  *     Item popRight() 移除一个右端的项
 *  *     API for a generic double-ended queue
 *  * </code>
 *
 * @author LeonChen
 * @since 1/26/20
 */
class e01_03_33 {

  public static void main(String[] args) {
    Deque<String> deque = new Deque<String>();
    deque.pushLeft("1");
    deque.pushLeft("2");
    deque.pushLeft("3");
    deque.pushRitht("4");

    StdOut.print(deque.popLeft());
    StdOut.print(deque.popRight());
    StdOut.print(deque.size());

    StdOut.println();

    for (String s : deque) {
      StdOut.print(s);
    }
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
  private static class Deque<Item> implements Iterable<Item>{
    private Node<Item> left;
    private int size;

    public Deque() {
      left = null;
      size = 0;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public int size() {
      return size;
    }

    public void pushLeft(Item item) {
      Node<Item> node = new Node<>(null, item);
      if (left == null) {
        left = node;
      } else {
        node.next = left;
        left = node;
      }
      size++;
    }

    public void pushRitht(Item item) {
      Node<Item> node = new Node<>(null, item);
      if (left == null){
        left = node;
      }else {
        Node<Item> curNode = left;
        while (curNode.next !=null){
          curNode = curNode.next;
        }
        curNode.next = node;
      }
      size++;
    }

    public Item popLeft() {
      if (left == null){
        return null;
      }
      Node<Item> oldLeft = left;
      left = left.next;
      oldLeft.next = null;
      size--;
      return oldLeft.value;
    }

    public Item popRight() {
      if (left == null){
        return null;
      }
      Node<Item> curNode = left;
      Node<Item> preNode = null;
      while (curNode.next !=null){
        preNode = curNode;
        curNode = curNode.next;

      }
      preNode.next = null;
      size--;
      return curNode.value;
    }

    @Override
    public Iterator<Item> iterator() {
      return new Itera();
    }

    class Itera implements Iterator<Item>{

      private Node<Item> curNode;

      public Itera() {
        curNode = left;
      }

      @Override
      public boolean hasNext() {
        return curNode !=null;
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

      public Node(Node next, E value) {
        this.next = next;
        this.value = value;
      }

      public Node() {}
    }
  }
}
