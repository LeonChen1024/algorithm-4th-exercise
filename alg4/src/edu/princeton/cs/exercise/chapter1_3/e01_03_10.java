package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Stack;

/**
 * 1.3.10 Write a filter InfixToPostfix that converts an arithmetic expression from infix to
 * postfix.
 *
 * <p>编写一个过滤器 InfixToPostfix 来将一个算术表达式从中缀转换成后缀
 *
 * @author LeonChen
 * @since 12/30/19
 */
class e01_03_10 {

  public static void main(String[] args) {
    // 中缀就是我们日常生活写的算术表达式,符号在中间,这便于人类使用,但是计算机处理起来就比较麻烦.
    // 后缀就是将符号放在后侧,便于计算机处理
    // 我们以上一个练习的结果作为输入

    String[] in =
        new String[] {
          "(", "(", "1", "+", "2", ")", "*", "(", "(", "3", "-", "4", ")", "*", "(", "5", "-", "6",
          ")", ")", ")"
        };

    Stack<String> stack = new Stack<>();
    for (int i = 0; i < in.length; i++) {
      String s = in[i];
      if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {

        stack.push(s);
      } else if (s.equals("(")) {
        continue;
      } else if (s.equals(")")) {
        System.out.print(stack.pop() + " ");
      } else {
        System.out.print(s + " ");
      }
    }
  }
}
