package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;


/**
 * 1.1.14 Write a static method lg() that takes an int value N as argument and returns the largest
 * int not larger than the base-2 logarithm of N. Do not use Math.
 *
 * 编写一个静态方法 lg() 接收一个 int 数值 N 作为参数并且返回最大的没有超过 N 以 2 为底的数.不要使用 Math
 */
class e01_01_14 {


  /**
   */
  public static void main(String[] args) {
    int N = 10;
    int cur = 1;
    // 因为当最后一次进入循环体++ 的时候,cur 已经超过了N ,所以对应的result应该减一,所以直接从 -1开始.
    int result = -1;
    while (cur <= N) {
      result++;
      cur = cur * 2;
    }
    StdOut.println(result);
  }
}
