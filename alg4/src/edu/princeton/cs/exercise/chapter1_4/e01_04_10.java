package edu.princeton.cs.exercise.chapter1_4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.10 Modify binary search so that it always returns the element with the smallest index that
 * matches the search element (and still guarantees logarithmic running time).
 *
 * <p>修改二分搜索使得它总是返回带有最小索引的目标值(并且仍然要保证 logarithmic 的运行时间)
 *
 * @author LeonChen
 * @since 3/9/20
 */
class e01_04_10 {

  /**
   * 思路,只需要在找到目标值的时候,对比目标值索引前一位是否也等于目标值,如果等于,就将高位减一继续做二分.以此类推.<br>
   * 也可以直接一位一位往前算,但是这样在有很多的目标值的时候效率较低.
   *
   * @param args
   */
  public static void main(String[] args) {}

  public static class BinarySearch {

    /** This class should not be instantiated. */
    private BinarySearch() {}

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param a the array of integers, must be sorted in ascending order
     * @param key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(int[] a, int key) {
      int lo = 0;
      int hi = a.length - 1;
      while (lo <= hi) {
        // Key is in a[lo..hi] or not present.
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) {
          hi = mid - 1;
        } else if (key > a[mid]) {
          lo = mid + 1;
        } else {
          if ((mid - 1) >= 0 && (a[mid - 1] == key)) {
            hi = mid - 1;
          } else {
            return mid;
          }
        }
      }
      return -1;
    }

    public static int bruteForceRank(int key, int[] a) {
      for (int i = 0; i < a.length; i++) if (a[i] == key) return i;
      return -1;
    }

    /**
     * Returns the index of the specified key in the specified array. This function is poorly named
     * because it does not give the <em>rank</em> if the array has duplicate keys or if the key is
     * not in the array.
     *
     * @param key the search key
     * @param a the array of integers, must be sorted in ascending order
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     * @deprecated Replaced by {@link #indexOf(int[], int)}.
     */
    @Deprecated
    public static int rank(int key, int[] a) {
      return indexOf(a, key);
    }

    /**
     * Reads in a sequence of integers from the whitelist file, specified as a command-line
     * argument; reads in integers from standard input; prints to standard output those integers
     * that do <em>not</em> appear in the file.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

      int[] ints = new int[] {3, 5, 6, 25, 25, 46, 49, 66};
      // TODO: 测试二分法用时

      // sort the array
      Arrays.sort(ints);
      // read integer key from standard input; print if not in whitelist
      while (!StdIn.isEmpty()) {
        int key = StdIn.readInt();
        StdOut.println(BinarySearch.indexOf(ints, key));
      }
    }
  }
}
