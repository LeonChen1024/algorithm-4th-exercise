package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.19 Local minimum of a matrix. Given an N-by-N array a[] of N^2 distinct integers, design an
 * algorithm that runs in time proportional to N to find a local minimum: a pair of indices i and j
 * such that a[i][j] < a[i+1][j], a[i][j] < a[i][j+1], a[i][j] < a[i-1][j], and a[i][j] < a[i][j-1].
 * The running time of your program should be proportional to N in the worst case.
 *
 * <p>矩阵中的局部最小值.给定一个 N*N 的数组 a[] 包含了 N^2 个不同的整数,设计一个算法使得寻找本地最小值的时间复杂度是<br>
 * N 的比例:一个索引i 和 j 符合 a[i][j] < a[i+1][j], a[i][j] < a[i][j+1], a[i][j] < a[i-1][j],以及<br>
 * a[i][j] < a[i][j-1].你的程序在最差的情况下的时间复杂度应该是 N的比例.
 *
 * @author LeonChen
 * @since 3/14/20
 */
class e01_04_19 {

  /**
   * 将上一题的思路带进来,假设先找到中间列中最小的值 a[x][y],这时候它的上下元素满足条件,如果左右有比它更小的则往那边移动,<br>
   * 假设是 a[x+1][y] 因为 a[x][y]是 a[x][*] 中最小的,所以 a[x+1][*]中的最小值一定小于 a[x][*],此时这列的最小值上下左都符合<br>
   * 要求,只要判断最右侧即可,以此类推,这一侧一定会有一个局部最小值. 这个方法可以优化一下,使用二分思想在每次选取列的时候选中一个中间列.<br>
   * 这样的时间复杂度是,二分方式选取中间列 O(logN) 加上每列的最小值选取 O(N) 等于 O(NlogN).
   *
   * <p>这个算法还可以再优化,考虑使用分而治之的想法.比如 我们可以按照二分思路切割横向和纵向将矩阵分为4个部分,取<br>
   * 出边界和分割线当中的最小值,(一开始要改造数据假设外层有圈int最大值包裹,因为最外层的判断条件比较特殊,只需要有<br>
   * 数字的方向最小即可)此时判断这个数是不是局部最小,不是的话向那个更小方向的部分再次进行切割.因为这个子矩阵<br>
   * 中有数比边界 最小值还要小,所以不可能重新突破该边界.所以这个子矩阵中一定会有局部最小值.这个方式的时间复杂度为<br>
   * 2n+2n/2+2n/4+2n/8 ~ 4n 符合题目要
   *
   * <p>可以参考 http://courses.csail.mit.edu/6.006/spring11/lectures/lec02.pdf
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] arr =
        new int[][] {
          {5, -1, 3, 8, -4, 0, 7},
          {51, -11, 23, 18, -14, 10, 17},
          {25, -13, 13, 81, -42, 20, 27},
          {45, -21, 34, 38, -24, 30, 37},
          {52, -12, 36, 84, -43, 40, 47},
          {35, -41, 33, 58, -34, 50, 57},
          {56, -51, 43, 68, -19, 60, 67}
        };

    //    int[][] arr =
    //        new int[][] {
    //          {5, -1, 3},
    //          {51, -11, 23},
    //          {25, -13, 13}
    //        };

    //    int[][] arr =
    //        new int[][] {
    //          {5, -1},
    //          {51, -11},
    //        };

    //    int[][] arr =
    //        new int[][] {
    //          {5},
    //        };

    arr = addBounder(arr);

    findLocalMinimumInMatrix(arr, -1, arr.length, -1, arr[0].length, 0, 0);
  }

  private static int[][] addBounder(int[][] arr) {

    int oldLen = arr.length;
    int newlen = oldLen + 2;
    int[][] narr = new int[newlen][newlen];

    for (int i = 0; i < narr.length; i++) {
      if (i == 0 || i == narr.length - 1) {
        for (int j = 0; j < newlen; j++) {
          narr[i][j] = Integer.MAX_VALUE;
        }
      } else {
        narr[i][0] = Integer.MAX_VALUE;
        narr[i][newlen - 1] = Integer.MAX_VALUE;
        for (int j = 0; j < arr[0].length; j++) {
          narr[i][j + 1] = arr[i - 1][j];
        }
      }
    }
    return narr;
  }

  private static void findLocalMinimumInMatrix(
      int[][] arr,
      int rowStart,
      int rowEnd,
      int columnStart,
      int columnEnd,
      int curMinR,
      int curMinC) {

    int curMin = arr[curMinR][curMinC];
    int rowMid = (rowStart + rowEnd) / 2;
    int columnMid = (columnStart + columnEnd) / 2;

    // 遍历十字骨架找到当前最小值.
    for (int i = 1; i < arr.length - 1; i++) {
      if (arr[rowMid][i] < curMin) {
        curMinR = rowMid;
        curMinC = i;
        curMin = arr[rowMid][i];
      }
      if (arr[i][columnMid] < curMin) {
        curMinR = i;
        curMinC = columnMid;
        curMin = arr[i][columnMid];
      }
    }

    // 判断是否是局部最小
    if (curMin < arr[curMinR - 1][curMinC]
        && curMin < arr[curMinR + 1][curMinC]
        && curMin < arr[curMinR][curMinC - 1]
        && curMin < arr[curMinR][curMinC + 1]) {
      // 找到,返回
      StdOut.println("local minimum is at " + (curMinR - 1) + "," + (curMinC - 1));
      return;
    } else {
      // 没找到,判断往哪个象限前进
      int nextR;
      int nextC;

      if (curMinR == rowMid) {
        // 往上下两行的地方移动
        if (arr[curMinR - 1][curMinC] > arr[curMinR + 1][curMinC]) {
          rowStart = rowMid;
        } else {
          rowEnd = rowMid;
        }

        if (curMinC > columnMid) {
          columnStart = columnMid;
        } else {
          columnEnd = columnMid;
        }
      } else {
        // 往左右两列移动
        if (arr[curMinR][curMinC - 1] > arr[curMinR][curMinC + 1]) {
          columnStart = columnMid;
        } else {
          columnEnd = columnMid;
        }

        if (curMinR > rowMid) {
          rowStart = rowMid;
        } else {
          rowEnd = rowMid;
        }
      }

      findLocalMinimumInMatrix(arr, rowStart, rowEnd, columnStart, columnEnd, curMinR, curMinC);
    }
  }
}
