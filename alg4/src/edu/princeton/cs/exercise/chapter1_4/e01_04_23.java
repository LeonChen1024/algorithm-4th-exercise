package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.23 Binary search for a fraction. Devise a method that uses a logarithmic number of queries of
 * the form Is the number less than x? to find a rational number p/q such that 0 < p < q < N. Hint :
 * Two fractions with denominators less than N cannot differ by more than 1/N^2.
 *
 * <p>使用分数的二分查找.设计一个方法使用对数级别的时间找出目标值.通过得到 p/q 如 0 < p < q < N <br>
 * 判断两数差小于x? 即可.提示:这个对比的数的分母要小于N不能比 1/N^2更大.
 *
 * @author LeonChen
 * @since 3/19/20
 */
class e01_04_23 {

  /**
   * 因为计算机的浮点型经常是一个无限的数,所以无法比较是否是完全一样的,这个时候我们认为当两个数的差足够小的时候,<br>
   * 我们就认为他们是同样的.
   *
   * @param args
   */
  public static void main(String[] args) {

    double[] keys =
        new double[] {1.2, 4.4, 6.6, 9.78, 12.45, 15.1, 17.6, 23.87, 24.2, 34, 37, 38, 45, 47, 58};
    binarySearchFraction(4.399, keys);
  }

  public static void binarySearchFraction(double target, double[] arr) { // 数组必须是有序的
    int lo = 0;
    int hi = arr.length - 1;
    double threshold = 1.0 / (arr.length * arr.length);
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      // 这里的判断条件做个改动
      if (Math.abs(arr[mid] - target) <= threshold) {
        StdOut.println("target value is in the index of " + mid);
        return;
      } else if (target > arr[mid]) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    StdOut.println("target value is not in the array");
  }
}
