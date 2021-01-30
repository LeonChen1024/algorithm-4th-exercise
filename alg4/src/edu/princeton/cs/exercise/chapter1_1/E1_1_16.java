package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.16 Give the value of exR1(6): <code>
 *     public static String exR1(int n) {
 *     if (n <= 0) return "";
 *     return exR1(n - 3) + n + exR1(n - 2) + n;
 *      }
 * </code>
 *
 * 给出方法 exR1(6) 的值
 */
class E1_1_16 {


  public static String exR1(int n) {
    if (n <= 0) return "";
    return exR1(n - 3) + n + exR1(n - 2) + n;
  }


  /**
   */
  public static void main(String[] args) {
// f(6) = f(3) + 6 + f(4) + 6 ;

    // f(3) = f(0) + 3 + f(1) + 3 ;
    // f(1) = f(-2) + 1 + f(-1) + 1;
    // f(4) = f(1) + 4 + f(2) + 4 ;
    // f(2) = (-1) + 2 + f(0) + 2 ;
    StdOut.println(exR1(6));
  }
}
