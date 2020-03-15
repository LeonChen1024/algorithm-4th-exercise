package edu.princeton.cs.exercise.chapter1_4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.11 Add an instance method howMany() to StaticSETofInts (page 99) that finds the number of
 * occurrences of a given key in time proportional to log N in the worst case.
 *
 * <p>添加一个实例方法 howMany() 到 StaticSETofInts(第99页) 中使得找到一个给定键出现的次数在最差的情况下的时间<br>
 * 复杂度是 logN 的正比
 *
 * @author LeonChen
 * @since 3/9/20
 */
class e01_04_11 {

  /** 思路与前一题类似,不断使用二分法,先找出目标值最小索引,在找出目标值最大索引. */
  public static void main(String[] args) {

    StaticSETofInts staticSETofInts =
        new StaticSETofInts(new int[] {1, 3, 5, 7, 22, 22, 22, 22, 45, 76});
    StdOut.println(staticSETofInts.howMany(22));
  }

  public static class StaticSETofInts {
    private int[] a;

    public StaticSETofInts(int[] keys) {
      a = new int[keys.length];
      for (int i = 0; i < keys.length; i++) a[i] = keys[i]; // defensive copy
      Arrays.sort(a);
    }

    public boolean contains(int key) {
      return rank(key) != -1;
    }

    private int rank(int key) { // Binary search.
      int lo = 0;
      int hi = a.length - 1;
      while (lo <= hi) { // Key is in a[lo..hi] or not present.
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) hi = mid - 1;
        else if (key > a[mid]) lo = mid + 1;
        else return mid;
      }
      return -1;
    }

    private int howMany(int key) {
      int origMid = rank(key);

      // 找到索引最小的目标值
      int smallestIndex = origMid;
      if (a[smallestIndex - 1] == key) {
        smallestIndex = indexOfSmallest(a, key, 0, smallestIndex - 1);
      }

      // 找到索引最大的目标值
      int bigestIndex = origMid;
      if (a[bigestIndex + 1] == key) {
        bigestIndex = indexOfBigest(a, key, bigestIndex + 1, a.length - 1);
      }

      return bigestIndex - smallestIndex + 1;
    }

    public int indexOfSmallest(int[] a, int key, int lo, int hi) {
      while (lo <= hi) {
        // Key is in a[lo..hi] or not present.
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) {
          hi = mid - 1;
        } else if (key > a[mid]) {
          lo = mid + 1;
        } else {
          if (a[mid - 1] == key) {
            hi = mid - 1;
          } else {
            return mid;
          }
        }
      }
      return -1;
    }

    public int indexOfBigest(int[] a, int key, int lo, int hi) {
      while (lo <= hi) {
        // Key is in a[lo..hi] or not present.
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) {
          hi = mid - 1;
        } else if (key > a[mid]) {
          lo = mid + 1;
        } else {
          if (a[mid + 1] == key) {
            lo = mid + 1;
          } else {
            return mid;
          }
        }
      }
      return -1;
    }
  }
}
