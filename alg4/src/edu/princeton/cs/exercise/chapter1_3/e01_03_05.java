package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.5 What does the following code fragment print when N is 50? Give a high-level description of
 * what it does when presented with a positive integer N. <code>
 *     Stack<Integer> stack = new Stack<Integer>();
 *     while (N > 0)
 *     {
 *       stack.push(N % 2);
 *       N=N/2;
 *     }
 *     for (int d : stack) StdOut.print(d);
 *     StdOut.println();
 * </code> Answer : Prints the binary representation of N (110010 when N is 50).
 *
 * <p>下面的代码片段当N是50的时候会输出什么?给出一个高等级的层次的功能说明当他使用的是一个正数的N.
 *
 * @author LeonChen
 * @since 12/25/19
 */
class e01_03_05 {

  public static void main(String[] args) {

    int N = 50;

    Stack<Integer> stack = new Stack<Integer>();
    while (N > 0) {
      stack.push(N % 2);
      N = N / 2;
    }
    for (int d : stack) StdOut.print(d);
    StdOut.println();

    // N = 50 输出的是 110010 , 他是一个求出正数二进制表达式的代码. 不断的取余入栈,
    // 最后根据后入先出的原则出栈得出二进制数.

  }
}
