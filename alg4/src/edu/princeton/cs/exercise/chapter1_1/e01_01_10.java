package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.10 What is wrong with the following code fragment? <code>
 *     int[] a;
 *     for (int i = 0; i < 10; i++) a[i] = i * i;
 * </code>int[] a; for(inti=0;i<10;i++) a[i]=i*i; Solution: It does not allocate memory for a[]
 * with new. This code results in a variable a might not have been initialized compile-time error.
 *
 * 下面的代码片段哪里错了?
 *
 * 方案: 它没有为 a[] 使用 new 来分配内存.这个代码导致变量 a 可能没有初始化导致编译时异常
 *
 */
class e01_01_10 {

  /**
   */
  public static void main(String[] args) {
    //    int[] a;
    //    for (int i = 0; i < 10; i++) a[i] = i * i;
  }
}
