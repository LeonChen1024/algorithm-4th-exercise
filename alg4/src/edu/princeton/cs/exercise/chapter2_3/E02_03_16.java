package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.3.16 Best case. Write a program that produces a best-case array (with no duplicates) for sort()
 * in Algorithm 2.5: an array of N items with distinct keys having the property that every partition
 * will produce subarrays that differ in size by at most 1 (the same subarray sizes that would
 * happen for an array of N equal keys). (For the purposes of this exercise, ignore the initial
 * shuffle.)
 *
 * <p>The following exercises describe variants of quicksort. Each of them calls for an
 * implementation, but naturally you will also want to use SortCompare for experiments to evaluate
 * the effectiveness of each suggested modification.
 *
 * <p>最佳情况. 编写一个程序产生一个对于算法 2.5中sort()的最佳数组(不带重复的值):一个拥有不重复的 N 个项的数组<br>
 * 每次分割都会产生两个大小差异最多为1的子数组(相同大小的子数组的情况会发生在数组中带有 N 个相同键的情况).<br>
 * (在这个练习的情况下,忽略初始的打乱操作)
 *
 * <p>后面的练习描述了几个快排的变体.他们每种都调用了一个实现,很自然的你也会想使用 SortCompare 来评估这几种<br>
 * 推荐的修改造成的性能效率是如何的.
 *
 * @author LeonChen
 * @since 8/14/20
 */
class E02_03_16 {
  private static void best(int[] a, int lo, int hi) {

    // precondition:  a[lo..hi] contains keys lo to hi, in order
    /**
     * 将第一个元素与中间元素交换位置,使得拿第一个元素做分割点的时候,最后他会换到他原来应该在的位置,即中间.<br>
     * 递归这个操作.
     */
    for (int i = lo; i <= hi; i++) assert a[i] == i;

    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    best(a, lo, mid - 1);
    best(a, mid + 1, hi);
    exch(a, lo, mid);
  }

  public static int[] best(int n) {
    int[] a = new int[n];
    for (int i = 0; i < n; i++) a[i] = i;
    best(a, 0, n - 1);
    return a;
  }

  // exchange a[i] and a[j]
  private static void exch(int[] a, int i, int j) {
    int swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  public static void main(String[] args) {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    int n = 52;
    int[] a = best(n);
    for (int i = 0; i < n; i++)
      // StdOut.println(a[i]);
      StdOut.print(alphabet.charAt(a[i]));
    StdOut.println();
  }
}
