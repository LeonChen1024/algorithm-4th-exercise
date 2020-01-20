package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.11 Write a program EvaluatePostfix that takes a postfix expression from standard input,
 * evaluates it, and prints the value. (Piping the output of your program from the previous exercise
 * to this program gives equivalent behavior to Evaluate.
 *
 * <p>编写一个程序 EvaluatePostfix 从标准输入里接收一个后缀表达式进行计算,并打印出结果.(从前面的练习的输出用到这个程序<br>
 * 给出相同的计算行为.
 *
 * @author LeonChen
 * @since 12/31/19
 */
class e01_03_11 {

  public static void main(String[] args) {

    String[] in = new String[] {"1", "2", "+", "3", "4", "-", "5", "6", "-", "*", "*"};

    Stack<Double> stack = new Stack<>();
    for (int i = 0; i < in.length; i++) {
      String s = in[i];
      if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
        Double s2 = stack.pop();
        Double s1 = stack.pop();
        Double result = null;
        switch (s) {
          case "+":
            result = s1 + s2;
            break;
          case "-":
            result = s1 - s2;
            break;
          case "*":
            result = s1 * s2;
            break;
          case "/":
            result = s1 / s2;
            break;
        }
        stack.push(result);
      } else {
        stack.push(Double.parseDouble(s));
      }
    }
    StdOut.print(stack.pop());
  }
}
