package edu.princeton.cs.exercise.chapter1_2;

import java.util.Arrays;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.9 Instrument BinarySearch (page 47) to use a Counter to count the total number of keys
 * examined during all searches and then print the total after all searches are complete. Hint :
 * Create a Counter in main() and pass it as an argument to rank().
 *
 * <p>改造 BinarySearch (47页) 使用一个 Counter 来计算在总的循环里检验key的次数并且在完成所有的搜索的时候打印出来.<br>
 * 提示: 在 main() 方法中创建一个 Counter 并且将它作为参数传入到 rank() 方法中.
 *
 * @author LeonChen
 * @since 12/5/19
 */
class e01_02_09 {

  public static int rank(int[] a, int key, Counter counter) {
    int lo = 0;
    int hi = a.length - 1;
    while (lo <= hi) {
      counter.increment();
      // Key is in a[lo..hi] or not present.
      int mid = lo + (hi - lo) / 2;
      if (key < a[mid]) hi = mid - 1;
      else if (key > a[mid]) lo = mid + 1;
      else return mid;
    }
    return -1;
  }

  public static void main(String[] args) {

    int[] whitelist = new int[] {1, 3, 5, 7, 2, 43, 76, 235, 6, 23};

    Counter counter = new Counter("rank");
    // sort the array
    Arrays.sort(whitelist);
    // read integer key from standard input; print if not in whitelist
    int key = 5;
    if (rank(whitelist, key, counter) != -1) {
      StdOut.println(key);
      StdOut.println(counter.toString());
    }
  }
}
