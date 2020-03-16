package edu.princeton.cs.exercise.chapter1_4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.14 4-sum . Develop an algorithm for the 4-sum problem.
 *
 * <p>4-sum. 为 4-sum 开发一个算法.
 *
 * @author LeonChen
 * @since 3/11/20
 */
class e01_04_14 {

  /**
   * 首先最容易想到的方法是暴力循环,但是时间复杂度会很大.<br>
   * 另一个方法我们可以利用排序来简化,一个有序数组的算法有许多不必要的计算可以省去.
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] arr = new int[] {3, 2, 5, -1, 0, -2};

    fourSum1(arr);
    fourSum2(arr);
  }

  /**
   * 也是遍历的方式,但是通过有序的特点,避免了大量的无效计算.减少了一次二分查找.时间复杂度为 N^3
   *
   * @param arr
   */
  private static void fourSum2(int[] arr) {
    Arrays.sort(arr);

    for (int i = 0; i < arr.length - 3; i++) {
      for (int j = i + 1; j < arr.length - 2; j++) {
        int last = arr.length - 1;
        for (int k = j + 1; k < arr.length - 1; ) {
          int curTotal = arr[i] + arr[j] + arr[k] + arr[last];

          if (curTotal > 0) {
            // 由于目前的和过大,应该要减少,所以last向前移动.
            last--;
          } else if (curTotal < 0) {
            // 由于目前的和过小,应该要增加,所以k向后移动.
            k++;
          } else {
            StdOut.println(
                "找到一组符合的数 [" + arr[i] + "," + arr[j] + "," + arr[k] + "," + arr[last] + "]");
            last--;
          }

          if (last <= k) {
            break;
          }
        }
      }

      //
    }
  }

  /**
   * 暴力循环,先找出3个元素,然后再寻找是否有符合条件的最后一个元素.时间复杂度约为 N^(3)lgN
   *
   * @param arr
   */
  private static void fourSum1(int[] arr) {

    for (int i = 0; i < arr.length - 3; i++) {
      for (int j = i + 1; j < arr.length - 2; j++) {
        for (int k = j + 1; k < arr.length - 1; k++) {
          int target = -(arr[i] + arr[j] + arr[k]);
          int found = indexOf(arr, target, k + 1, arr.length - 1);
          if (found != -1) {
            StdOut.println(
                "找到一组符合的数 [" + arr[i] + "," + arr[j] + "," + arr[k] + "," + arr[found] + "]");
          }
        }
      }
    }
  }

  /**
   * 二分查找
   *
   * @param a
   * @param key
   * @return
   */
  public static int indexOf(int[] a, int key, int startIndex, int endIndex) {
    int lo = startIndex;
    int hi = endIndex;
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
}
