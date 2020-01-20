package edu.princeton.cs.exercise.chapter1_2;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

/**
 * 1.2.15 File input. Develop a possible implementation of the static readInts() method from In
 * (which we use for various test clients, such as binary search on page 47) that is based on the
 * split() method in String. Solution: <code>
 * public static int[] readInts(String name)
 * {
 *     In in = new In(name);
 *      String input = StdIn.readAll();
 *      String[] words = input.split("\\s+");
 *      int[] ints = new int[words.length;
 *      for int i = 0; i < word.length; i++)
 *      ints[i] = Integer.parseInt(words[i]);
 *      return ints;
 * }
 *
 * </code> We will consider a different implementation in Section 1.3(seepage126).
 *
 * <p>文件输入.开发一个静态 readInts() 方法接收 In(我们使用在很多的测试客户端中,比如 47页的二分搜索) 是基于String的<br>
 * split()方法.解决方案<code>
 *  * public static int[] readInts(String name)
 *  * {
 *  *     In in = new In(name);
 *  *      String input = StdIn.readAll();
 *  *      String[] words = input.split("\\s+");
 *  *      int[] ints = new int[words.length;
 *  *      for int i = 0; i < word.length; i++)
 *  *      ints[i] = Integer.parseInt(words[i]);
 *  *      return ints;
 *  * }
 *  *
 *  * </code>
 *
 * @author LeonChen
 * @since 12/10/19
 */
class e01_02_15 {

  public static void main(String[] args) {
    // 输入可用文件路径
    int[] ints = readInts("");
    System.out.println(Arrays.toString(ints));
  }

  public static int[] readInts(String name) {
    In in = new In(name);
    String input = StdIn.readAll();
    // 使用一个或多个空格分割
    String[] words = input.split("\\s+");
    int[] ints = new int[words.length];
    for (int i = 0; i < words.length; i++) ints[i] = Integer.parseInt(words[i]);
    return ints;
  }
}
