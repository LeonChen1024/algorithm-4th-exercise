package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.45 Stack generability. Suppose that we have a sequence of intermixed push and pop operations
 * as with our test stack client, where the integers 0, 1, . . . , N-1 in that order (push
 * directives) are intermixed with N minus signs (pop directives). Devise an algorithm that
 * determines whether the intermixed sequence causes the stack to underflow. (You may use only an
 * amount of space independent of N—you cannot store the integers in a data structure.) Devise a
 * linear-time algorithm that determines whether a given permutation can be generated as output by
 * our test client (depending on where the pop directives occur).
 *
 * <p>Solution: The stack does not overflow unless there exists an integer k such that the first k
 * pop operations occur before the first k push operations. If a given permutation can be generated,
 * it is uniquely generated as follows: if the next integer in the output permutation is in the top
 * of the stack, pop it; otherwise, push it onto the stack.
 *
 * <p>栈可生成性.假设我们有一个混合了 push 和 pop 操作的序列作为我们测试的栈客户端,我们使用 0,1,...,N-1 作为顺序(入栈)<br>
 * 并且混杂了到 N 的 负号(表示弹出).设计一个算法表明一个混合序列是否会导致栈下溢出.(你必须使用与 N 无关的空间量-你不可以存储<br>
 * 所有整数到一个数据结构中.)设计一个线性时间算法表明一个给定的排列可以被我们的测试程序生成(根据 pop 操作在哪里出现).
 *
 * <p>解决方案:栈除非存在一个整数 k 使得前 k 次 pop 操作发生在前 k 次 push 操作之前的时候会出现溢出.如果一个给定序列可以生成<br>
 * ,那么下面是他的唯一生成方式: 如果输出序列中的下一个整数在栈的顶部就弹出,否则继续往栈里推入.
 *
 * @author LeonChen
 * @since 2/6/20
 */
class E01_03_45 {

  public static void main(String[] args) {
    StdOut.println(testGenerability("143", 5));
    StdOut.println(testGenerability("413", 5));
  }

  private static boolean testGenerability(String permutation, int N) {

    Stack stack = new Stack();
    StringBuilder src = new StringBuilder();
    int cur = 0;
    stack.push(cur);
    src.append(cur);
    for (int i = 0; i < permutation.length(); i++) {
      Character c = permutation.charAt(i);

      String cc = stack.peek().toString();
      while (!cc.equals(c.toString())) {
        cur = cur + 1;
        if (cur > N - 1) {
          return false;
        }
        stack.push(cur);
        src.append(cur);
        cc = stack.peek().toString();
      }
      stack.pop();
      src.append('-');
    }
    StdOut.println(src);
    return true;
  }
}
