package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.4 Write a stack client Parentheses that reads in a text stream from standard input and uses a
 * stack to determine whether its parentheses are properly balanced. For example, your program
 * should print true for [()]{}{[()()]()} and false for [(]).
 *
 * <p>编写一个stack 括号处理客户端,他可以读取一个标准输入的文本流并且使用一个stack来判断这些括号是否平衡.比如,你的代码<br>
 * 对 [()]{}{[()()]()} 应该输出true,对 [(]) 应该输出false.
 *
 * @author LeonChen
 * @since 12/25/19
 */
class e01_03_04 {

  public static void main(String[] args) {

    // 由于合理的括号分布,最内层的括号之间肯定是不会夹杂别的括号的,所以在接收到反扩号的时候,上一个应该是对应的正括号,
    // 两两抵消后后面的反括号也以此类推.

    //    String[] pas =
    //        new String[] {
    //          "[", "(", ")", "]", "{", "}", "{", "[", "(", ")", "(", ")", "]", "(", ")", "}"
    //        };
    String[] pas = new String[] {"[", "(", "]", ")"};

    StdOut.println(validPas(pas));
  }

  private static boolean validPas(String[] pas) {
    Stack<String> stack = new Stack<>();
    for (int i = 0; i < pas.length; i++) {
      String p = pas[i];
      if (p.equals(")")) {
        if ("(".equals(stack.peek())) {
          stack.pop();
        } else {
          return false;
        }
      } else if (p.equals("}")) {
        if ("{".equals(stack.peek())) {
          stack.pop();
        } else {
          return false;
        }
      } else if (p.equals("]")) {
        if ("[".equals(stack.peek())) {
          stack.pop();
        } else {
          return false;
        }
      } else {
        stack.push(p);
      }
    }
    if (stack.size() == 0) {
      return true;
    }

    return false;
  }
}
