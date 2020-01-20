package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.9 Write a program that takes from standard input an expression without left parentheses and
 * prints the equivalent infix expression with the parentheses inserted. For example, given the
 * input: <code>1+2)*3-4)*5-6)))</code> your program should print <code>((1+2)*((3-4)*(5-6))</code>
 *
 * <p>编写一个程序从标准输入接收一个没有左括号的表达式并且打印出修复后的表达式带有插入的左括号.比如,给定输入<br>
 * <code>1+2)*3-4)*5-6)))</code> 你的程序应该打印出 <code>((1+2)*((3-4)*(5-6))</code>
 *
 * @author LeonChen
 * @since 12/27/19
 */
class e01_03_09 {

  public static void main(String[] args) {

    // 设定一个括号包括两个数据和一个操作符的公式是成立的,所以碰到反括号的时候从两个栈中取出最近的内容组合,
    // 最后根据后入先出的原则反序相加得到结果.

    String[] input =
        new String[] {
          "1", "+", "2", ")", "*", "3", "-", "4", ")", "*", "5", "-", "6", ")", ")", ")"
        };

    Stack<String> opStack = new Stack<>();
    Stack<String> dataStack = new Stack<>();
    for (int i = 0; i < input.length; i++) {
      String s = input[i];
      if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
        opStack.push(s);
      } else if (s.equals(")")) {
        String s2 = dataStack.pop();
        String s1 = dataStack.pop();
        String op = opStack.pop();
        dataStack.push("(" + s1 + op + s2 + s);
      } else {
        dataStack.push(s);
      }
    }

    String result = "";
    for (int i = 0; i < dataStack.size(); i++) {
      result = dataStack.pop() + result;
    }
    StdOut.print(result);
  }
}
