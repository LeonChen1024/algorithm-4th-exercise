package edu.princeton.cs.exercise.chapter1_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.5 What does the following code fragment print? <code>
 *     String s = "Hello World";
 *     s.toUpperCase();
 *     s.substring(6, 11);
 *     StdOut.println(s);
 * </code> Answer : "Hello World". String objects are immutable—string methods return a new String
 * object with the appropriate value (but they do not change the value of the object that was used
 * to invoke them). This code ignores the objects returned and just prints the original string. To
 * print "WORLD", use s = s.toUpperCase() and s = s.substring(6, 11).
 *
 * <p>以下代码会打印出什么?
 *
 * <p>回答"Hello World",String 对象是不可变的,这些方法返回了一个新的String 对象包含了合适的值(但是他们并没有改变<br>
 * 调用他们的对象的值),这段代码忽略了返回的对象并且只是打印了原始的字符串.想要打印"WORLD",使用 s = s.toUpperCase() and s = s.substring(6,
 * 11).
 *
 * @author LeonChen
 * @since 12/5/19
 */
class e01_02_05 {

  public static void main(String[] args) {
    String s = "Hello World";
    s.toUpperCase();
    s.substring(6, 11);
    StdOut.println(s);
  }
}
