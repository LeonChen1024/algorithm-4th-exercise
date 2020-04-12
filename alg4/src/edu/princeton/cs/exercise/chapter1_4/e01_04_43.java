package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 1.4.43 Resizing arrays versus linked lists. Run experiments to validate the hypothesis that
 * resizing arrays are faster than linked lists for stacks (see Exercise 1.4.35 and Exercise
 * 1.4.36). Do so by developing a version of DoublingRatio that computes the ratio of the running
 * times of the two programs.
 *
 * <p>可变长数组和链表. 通过试验证明可变长数组实现的栈比链表实现的栈速度更快(见 练习 1.4.35 和 1.4.36).<br>
 * 通过开发一个 DoublingRatio 来计算这两个程序的运行时间增长比例.
 *
 * @author LeonChen
 * @since 4/6/20
 */
class e01_04_43 {

  private static final String LINKED = "LINKED";
  private static final String ARRAY = "ARRAY";

  // linked list
  static Stack<Integer> linkedStack = new Stack();

  static java.util.Stack<Integer> arrayStack = new java.util.Stack();

  /**
   * <code>
   *     ======= start linked list =======
   * trail     250 numbers,use time   0.001 , ratio is Infinity
   * trail     500 numbers,use time   0.000 , ratio is   0.0
   * trail    1000 numbers,use time   0.000 , ratio is   NaN
   * trail    2000 numbers,use time   0.000 , ratio is   NaN
   * trail    4000 numbers,use time   0.000 , ratio is   NaN
   * trail    8000 numbers,use time   0.000 , ratio is   NaN
   * trail   16000 numbers,use time   0.001 , ratio is Infinity
   * trail   32000 numbers,use time   0.002 , ratio is   2.0
   * trail   64000 numbers,use time   0.002 , ratio is   1.0
   * trail  128000 numbers,use time   0.003 , ratio is   1.5
   * trail  256000 numbers,use time   0.005 , ratio is   1.7
   * trail  512000 numbers,use time   0.008 , ratio is   1.6
   * trail 1024000 numbers,use time   0.018 , ratio is   2.3
   * trail 2048000 numbers,use time   0.072 , ratio is   4.0
   * trail 4096000 numbers,use time   0.140 , ratio is   1.9
   * trail 8192000 numbers,use time   0.307 , ratio is   2.2
   * trail 16384000 numbers,use time   2.412 , ratio is   7.9
   * trail 32768000 numbers,use time   4.920 , ratio is   2.0
   * ======= start ARRAY =======
   * trail     250 numbers,use time   0.001 , ratio is Infinity
   * trail     500 numbers,use time   0.000 , ratio is   0.0
   * trail    1000 numbers,use time   0.001 , ratio is Infinity
   * trail    2000 numbers,use time   0.001 , ratio is   1.0
   * trail    4000 numbers,use time   0.000 , ratio is   0.0
   * trail    8000 numbers,use time   0.001 , ratio is Infinity
   * trail   16000 numbers,use time   0.002 , ratio is   2.0
   * trail   32000 numbers,use time   0.004 , ratio is   2.0
   * trail   64000 numbers,use time   0.004 , ratio is   1.0
   * trail  128000 numbers,use time   0.006 , ratio is   1.5
   * trail  256000 numbers,use time   0.011 , ratio is   1.8
   * trail  512000 numbers,use time   0.021 , ratio is   1.9
   * trail 1024000 numbers,use time   0.041 , ratio is   2.0
   * trail 2048000 numbers,use time   0.087 , ratio is   2.1
   * trail 4096000 numbers,use time   0.171 , ratio is   2.0
   * trail 8192000 numbers,use time   0.342 , ratio is   2.0
   * trail 16384000 numbers,use time   0.693 , ratio is   2.0
   * trail 32768000 numbers,use time   1.574 , ratio is   2.3
   * </code>从运行结果得证
   */
  public static void main(String[] args) {

    StdOut.println("======= start linked list =======");
    doublelingRatio(LINKED);
    StdOut.println("======= start ARRAY =======");
    doublelingRatio(ARRAY);
  }

  private static void doublelingRatio(String method) {
    double prev = timeTrial(125, method);
    for (int n = 250; n < 50000000; n += n) {
      double time = timeTrial(n, method);
      StdOut.printf("trail %7d numbers,use time %7.3f , ratio is %5.1f\n", n, time, time / prev);
      prev = time;
    }
  }

  public static double timeTrial(int n, String method) {

    Stopwatch timer = new Stopwatch();
    switch (method) {
      case LINKED:
        linkedTest(n);
        break;
      case ARRAY:
        arrayTest(n);
        break;
      default:
        break;
    }
    return timer.elapsedTime();
  }

  private static void arrayTest(int n) {
    for (int i = 0; i < n; i++) {
      arrayStack.push(1);
    }
    for (int i = 0; i < n; i++) {
      arrayStack.pop();
    }
  }

  private static void linkedTest(int n) {
    for (int i = 0; i < n; i++) {
      linkedStack.push(1);
    }
    for (int i = 0; i < n; i++) {
      linkedStack.pop();
    }
  }
}
