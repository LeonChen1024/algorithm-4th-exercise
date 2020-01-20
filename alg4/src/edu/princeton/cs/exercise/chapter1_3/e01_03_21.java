package edu.princeton.cs.exercise.chapter1_3;

import java.util.LinkedList;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.21 Write a method find() that takes a linked list and a string key as arguments and returns
 * true if some node in the list has key as its item field, false otherwise.
 *
 * <p>编写一个方法 find() 接收一个linked list 和一个 字符串 key 作为参数 , 如果列表中的一些节点<br>
 * 的值和key是一样的则返回true,否则返回false.
 *
 * @author LeonChen
 * @since 1/8/20
 */
class e01_03_21 {

  public static void main(String[] args) {
    LinkedList<String> linkedList = new LinkedList<>();
    linkedList.push("1");
    linkedList.push("2");
    linkedList.push("3");
    linkedList.push("4");

    StdOut.print(find(linkedList, "5"));
  }

  private static boolean find(LinkedList<String> list, String key) {

    for (String s : list) {

      if (s.equals(key)) {
        return true;
      }
    }
    return false;
  }
}
