package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.2 Give the output printed by java Stack for the input
 *
 * <p>it was - the best - of times - - - it was - the - -
 *
 * <p>给出以下Stack 接收以下输入的输出.
 *
 * @author LeonChen
 * @since 12/24/19
 */
class e01_03_02 {

  public static void main(String[] args) {

    String[] input =
        new String[] {
          "it", "was", "-", "the", "best", "-", "of", "times", "-", "-", "-", "it", "was", "-",
          "the", "-", "-"
        };

    Stack<String> stack = new Stack<String>();
    for (int i = 0; i < input.length; i++) {
      String item = input[i];
      if (!item.equals("-")) stack.push(item);
      else if (!stack.isEmpty()) stack.pop();
    }

    for (String s : stack) {
      StdOut.println(s + " ");
    }
  }
}
