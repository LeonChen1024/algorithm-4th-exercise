package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.40 Move-to-front. Read in a sequence of characters from standard input and maintain the
 * characters in a linked list with no duplicates. When you read in a previously unseen character,
 * insert it at the front of the list. When you read in a duplicate character, delete it from the
 * list and reinsert it at the beginning. Name your program MoveToFront: it implements the
 * well-known move-to-front strategy, which is useful for caching, data compression, and many other
 * applications where items that have been recently accessed are more likely to be reaccessed.
 *
 * <p>前移编码. 从标准输入中读取一个序列的字符并且使用链表来维护保证它不重复.当你读到一个之前没有见过的字符,把它插入到列表的<br>
 * 开始.当你读到一个重复的字符,删除链表里的该字符并重新将它插入到起始位置.给你的程序命名为 MoveToFront : 它实现了知名的<br>
 * move-to-front 策略,它对于缓存,数据压缩,还有很多其他符合最近访问的项很有可能被再次访问的应用是非常有用的.
 *
 * @author LeonChen
 * @since 2/2/20
 */
class E01_03_40 {

  public static void main(String[] args) {
    MoveToFront moveToFront = new MoveToFront();

    moveToFront.insert('1');
    moveToFront.insert('2');
    moveToFront.insert('3');
    moveToFront.insert('4');
    moveToFront.insert('5');
    moveToFront.insert('3');
    moveToFront.insert('1');
    moveToFront.insert('1');
    StdOut.println(moveToFront);
  }

  static class MoveToFront {
    private Node first;

    public MoveToFront() {
      first = null;
    }

    public void insert(char c) {
      if (first == null) {
        first = new Node();
        first.value = c;
        return;
      }

      if (first.value == c) {
        return;
      }

      Node cur = first;
      Node prev = null;

      while (cur != null) {
        if (cur.value == c) {
          prev.next = cur.next;
          cur.next = null;
          break;
        }
        prev = cur;
        cur = cur.next;
      }

      Node node = new Node();
      node.value = c;
      Node oldFirst = first;
      first = node;
      first.next = oldFirst;
    }

    @Override
    public String toString() {
      String result = "";
      Node cur = first;
      while (cur != null) {
        result = result + cur.value + ", ";
        cur = cur.next;
      }

      return result;
    }

    public class Node {
      char value;
      Node next;
    }
  }
}
