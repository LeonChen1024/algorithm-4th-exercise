package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;
/**
 * 1.1.17 Criticize the following recursive function: <code>
 *     public static String exR2(int n)
 *   {
 *     String s = exR2(n-3) + n + exR2(n-2) + n;
 *     if (n <= 0) return "";
 *     return s;
 *   }
 * </code>Answer : The base case will never be reached. A call to exR2(3) will result in calls to
 * exR2(0), exR2(-3), exR3(-6), and so forth until a StackOverflowError occurs.
 *
 * 分析下面递归函数:
 *
 * 答案: 永远不会到达基础条件.调用 exR2(3) 将会导致调用exR2(0), exR2(-3), exR3(-6)等等直到出现 StackOverflowError
 *
 *
 */
class E1_1_17 {



  public static String exR2(int n) {
    String s = exR2(n - 3) + n + exR2(n - 2) + n;
    if (n <= 0) return "";
    return s;
  }


  /**
   */
  public static void main(String[] args) {
    StdOut.println(exR2(6));
  }
}
