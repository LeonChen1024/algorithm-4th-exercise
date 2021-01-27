package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.15 Write a static method histogram() that takes an array a[] of int values and an integer M
 * as arguments and returns an array of length M whose ith entry is the number of times the
 * integer i appeared in the argument array. If the values in a[] are all between 0 and M–1, the
 * sum of the values in the returned array should be equal to a.length.
 *
 * 编写一个静态方法 histogram() 接收一个数组 a[] 包含 int 值和一个整数 M 作为参数,并且返回一个长度为 M 的数组,它的第 i 个项
 * 是i 出现在参数数组中的次数.如果 a[]中的值都是在 0~M-1,返回的数组值的和应该等于 a.length
 */
class e01_01_15 {


  private static int[] histogram(int[] a, int M) {
    int[] result = new int[M];
    for (int i : a) {
      if (i < M) {
        result[i]++;
      }
    }

    int sum = 0;
    for (int i : result) {
      StdOut.print(i + ", ");
      sum += i;
    }
    StdOut.println();
    StdOut.println(sum);
    return result;
  }




  /**
   */
  public static void main(String[] args) {
    histogram(new int[]{3,2,5,7,8,4}, 5);

  }
}
