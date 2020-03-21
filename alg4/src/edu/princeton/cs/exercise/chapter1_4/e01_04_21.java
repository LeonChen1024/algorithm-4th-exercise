package edu.princeton.cs.exercise.chapter1_4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.21 Binary search on distinct values. Develop an implementation of binary search for
 * StaticSETofInts (see page 98) where the running time of contains() is guaranteed to be ~ lg R,
 * where R is the number of different integers in the array given as argument to the constructor.
 *
 * <p>不同数中的二分搜索.开发一个二分搜索的实现 StaticSETofInts (参考 98 页)其中 contains() 方法的<br>
 * 时间复杂度要保证为 ~lgR, R是构造中作为参数的整数数组中不同的数的数量.
 *
 * @author LeonChen
 * @since 3/18/20
 */
class e01_04_21 {

  /**
   * 为了实现这个功能,需要将数组转为set去重,在进行排序,最后搜索.
   *
   * @param args
   */
  public static void main(String[] args) {

    int[] keys = new int[] {4, 6, 2, 6, 8, 2, 45, 73, 13, 42, 14, 3, 65, 23, 45, 26, 85};

    StaticSETofInts ints = new StaticSETofInts(keys);

    StdOut.println(ints.contains(9));
  }

  static class StaticSETofInts {
    private int[] array;

    public StaticSETofInts(int[] arr) {
      Set<Integer> intSet = new HashSet<>();

      for (int i = 0; i < arr.length; i++) {
        intSet.add(arr[i]);
      }

      array = new int[intSet.size()];
      int arrayIndex = 0;

      for (int key : intSet) {
        array[arrayIndex] = key;
        arrayIndex++;
      }

      Arrays.sort(array);
    }

    public boolean contains(int key) {
      return rank(key) != -1;
    }

    private int rank(int key) {
      int low = 0;
      int high = array.length - 1;

      while (low <= high) {
        int middle = low + (high - low) / 2;

        if (key < array[middle]) {
          high = middle - 1;
        } else if (key > array[middle]) {
          low = middle + 1;
        } else {
          return middle;
        }
      }

      return -1;
    }
  }
}
