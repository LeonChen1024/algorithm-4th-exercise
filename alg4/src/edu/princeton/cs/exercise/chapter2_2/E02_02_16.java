package edu.princeton.cs.exercise.chapter2_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.2.16 Natural mergesort. Write a version of bottom-up mergesort that takes advantage of order in
 * the array by proceeding as follows each time it needs to find two arrays to merge: find a sorted
 * subarray (by incrementing a pointer until finding an entry that is smaller than its predecessor
 * in the array), then find the next, then merge them. Analyze the running time of this algorithm in
 * terms of the array size and the number of maximal increasing sequences in the array.
 *
 * <p>自然合并排序. 编写一个自底向上的合并排序版本在需要合并两个数组的时候通过以下处理利用数组的有序性:<br>
 * 找到一个有序的子数组(通过递增指针直到找到一个项比前一个项要小),然后找下一个有序子数组,然后合并他们.<br>
 * 根据数组的大小和数组中最大的递增序列来分析这个算法的运行时间
 *
 * @author LeonChen
 * @since 7/9/20
 */
class E02_02_16 {

  /** @param args */
  public static void main(String[] args) {

    // 数组的大小的影响
    for (int i = 200; i < 10000; i = 2 * i) {
      Comparable[] comparables = new Comparable[i];
      // 连续数组大小的影响
      for (int j = 20; j < 9 * i / 10; j = j * 2) {
        for (int k = 0; k < j; k++) {
          comparables[k] = k;
        }
        for (int k = j; k < i; k++) {
          comparables[k] = StdRandom.uniform(i);
        }
        Stopwatch timer = new Stopwatch();
        naturalMergesort(comparables);
        double time = timer.elapsedTime();

        StdOut.println(
            "array size is " + i + " , max sorted arr is " + j + ", used time is " + time);
      }
    }
  }

  private static Comparable[] naturalMergesort(Comparable[] arr) {
    int firstEnd = findSortedArrEnd(arr, 0);
    if (firstEnd == arr.length - 1) {
      return arr;
    }
    int secondEnd = findSortedArrEnd(arr, firstEnd + 1);

    Comparable[] comparables = new Comparable[firstEnd + 1];
    for (int i = 0; i < firstEnd + 1; i++) {
      comparables[i] = arr[i];
    }

    int fisrtIndex = 0;
    int secondIndex = firstEnd + 1;
    int index = 0;
    while (fisrtIndex <= firstEnd && secondIndex <= secondEnd) {
      if (comparables[fisrtIndex].compareTo(arr[secondIndex]) <= 0) {
        arr[index++] = comparables[fisrtIndex++];
      } else {
        arr[index++] = arr[secondIndex++];
      }
    }

    while (fisrtIndex <= firstEnd) {
      arr[index++] = comparables[fisrtIndex++];
    }

    return naturalMergesort(arr);
  }

  private static int findSortedArrEnd(Comparable[] arr, int start) {
    int end = arr.length - 1;
    for (int j = start; j < arr.length - 1; j++) {
      if (arr[j].compareTo(arr[j + 1]) > 0) {
        end = j;
        break;
      }
    }

    return end;
  }
}
