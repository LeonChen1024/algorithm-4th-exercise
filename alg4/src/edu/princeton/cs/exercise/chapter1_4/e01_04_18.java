package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.18 Local minimum of an array. Write a program that, given an array a[] of N distinct
 * integers, finds a local minimum: an index i such that a[i-1] < a[i] < a[i+1]. Your program should
 * use ~2lgN compares in the worst case.. <br>
 * Answer : Examine the middle value a[N/2] and its two neighbors a[N/2 - 1] and a[N/2 + 1]. If
 * a[N/2] is a local minimum, stop; otherwise search in the half with the smaller neighbor.
 *
 * <p>The original version question should be wrong. The local minimum is found the minimum , but
 * the formula is a [i-1] <a [i] <a [i + 1], which should be [i-1]> a [i ] <a [i + 1]
 *
 * <p>局部最小值.编写一个程序实现,给定一个 N 个不同整数的数组 a[],找到一个局部最小值:索引 i 满足 a[i-1] < a[i] < a[i+1]<br>
 * .你的代码在最差的情况下应该是 ~2lgN 的复杂度.<br>
 * 答案:校验中间值 a[N/2]和他的两个邻居 a[N/2-1]和a[N/2 + 1]. 如果 a[N/2] 是局部最小值,停止;否则查找比较小的邻居那一半的值.
 *
 * <p>这题英文原版题目应该有错,找的是局部最小,但是公式却是 a[i-1] < a[i] < a[i+1],应该是 a[i-1] > a[i] < a[i+1]
 *
 * @author LeonChen
 * @since 3/13/20
 */
class e01_04_18 {

  /**
   * 这个题目已经给出了答案,但是这个答案是为什么呢? 我们用白话来解释一下,首先,局部最小值的定义在边界情况是这样的,如果是头尾元素,只要判断有元素的一边即可.<br>
   * 可是即便如此,为什么又可以得出使用二分查找直接寻找呢?不会出现唯一的一个局部最小值出现在二分的另一边吗?<br>
   * 这种情况的确不会发生,我们假设数组是 a[10].当你得出 a[5]不是局部最小值的时候,那就意味着有一个邻居是更小的,假设是 a[4],那么这时候,a[3]就<br>
   * 必须比 a[4]小,否则 a[4]就是局部最小值了.可是当 a[3]比 a[4]小的时候,那么此时只要a[2]比 a[3]大的话,a[3]就是局部最小值了,<br>
   * 以此类推,a[1]也必须比 a[2]小,这时候悖论就出来了,a[0]如果小于 a[1],那么 a[1]就是局部最小值,如果 a[0]大于 a[1],那么 a[1]就是<br>
   * 局部最小值了.可以得证这个算法是没有错的.
   *
   * @param args
   */
  public static void main(String[] args) {
    double[] arr = new double[] {-0.5, -1.2, 3, 8, -1, 0, 0.7, 5, 2, -2.4, 2.4, 9, 22};

    findLocalMinimum(arr);
  }

  private static void findLocalMinimum(double[] arr) {
    int lo = 0;
    int hi = arr.length - 1;
    int mid;

    if (arr.length < 2) {
      StdOut.println("array length is too short to hava a local minimum");
      return;
    }
    if (arr[0] < arr[1]) {
      StdOut.println("local minimum is in index " + 0);
      return;
    }
    if (arr[arr.length - 1] < arr[arr.length - 2]) {
      StdOut.println("local minimum is in index " + (arr.length - 1));
      return;
    }

    while (lo < hi) {
      mid = lo + (hi - lo) / 2;
      if (arr[mid - 1] < arr[mid]) {
        hi = mid;
      } else if (arr[mid + 1] < arr[mid]) {
        lo = mid;
      } else {
        StdOut.println("local minimum is in index " + mid);
        return;
      }
    }
  }
}
