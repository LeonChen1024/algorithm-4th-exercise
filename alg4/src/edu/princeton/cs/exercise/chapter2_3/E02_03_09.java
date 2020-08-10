package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.9 Explain what happens when Quick.sort() is run on an array having items with just two
 * distinct keys, and then explain what happens when it is run on an array having just three
 * distinct keys.
 *
 * <p>解释当对一个包含两种不同的key的数组进行 Quick.sort() 的时候发生了什么,然后解释如果是对只有 3 种不同的key<br>
 * 进行排序的时候发生了什么.
 *
 * @author LeonChen
 * @since 8/4/20
 */
class E02_03_09 {

  private static int compareTime;

  /**
   * 如果使用基础的 Quick.sort() , 只有两种元素的时候 <code>
   *
   * when N = 200 , compare 1343 times
   * when N = 400 , compare 4489 times
   * when N = 800 , compare 11502 times
   * when N = 1600 , compare 27226 times
   * when N = 3200 , compare 61669 times
   * when N = 6400 , compare 136603 times
   * when N = 12800 , compare 298910 times
   * when N = 25600 , compare 649267 times
   * when N = 51200 , compare 1407731 times
   * </code> <br>
   * 和其他的情况差不多,因为虽然有很多相同的元素,但是却依然需要对比. 三种元素的情况也是类似的.
   *
   * <p>这个时候应该使用3路分割的快速排序效率比较高
   *
   * @param args
   */
  public static void main(String[] args) {
    for (int i = 200; i < 100000; i = i * 2) {

      Comparable[] a = new Comparable[i];
      for (int j = 0; j < i; j++) {
        a[j] = StdRandom.uniform() > 0.5 ? 1 : 2;
      }
      quickSort(a);
      StdOut.println("when N = " + i + " , compare " + compareTime + " times");
    }
  }

  private static void quickSort(Comparable[] a) {
    StdRandom.shuffle(a);

    sort(a);
  }

  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
    assert isSorted(a);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    int j = partition(a, lo, hi);
    int leftSubArraySize = j - lo;
    int rightSubArraySize = hi - j;

    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
    assert isSorted(a, lo, hi);
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    Comparable v = a[lo];
    while (true) {

      // find item on lo to swap
      while (less(a[++i], v)) {
        if (i == hi) break;
      }

      // find item on hi to swap
      while (less(v, a[--j])) {
        if (j == lo) break; // redundant since a[lo] acts as sentinel
      }

      // check if pointers cross
      if (i >= j) break;

      exch(a, i, j);
    }

    // put partitioning item v at a[j]
    exch(a, lo, j);

    // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    return j;
  }

  private static boolean less(Comparable v, Comparable w) {
    compareTime++;
    if (v == w) return false; // optimization when reference equals
    return v.compareTo(w) < 0;
  }

  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length - 1);
  }

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }
}
