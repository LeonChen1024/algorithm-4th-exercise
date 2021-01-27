package edu.princeton.cs.exercise.chapter1_1;


/**
 * 1.1.13 Write a code fragment to print the transposition (rows and columns changed) of a
 * two-dimensional array with M rows and N columns.
 *
 * 编写一个代码片段打印出一个M 行 N 列的二维数组的转换(行列交换)
 */
class e01_01_13 {


  /**
   */
  public static void main(String[] args) {
    int[][] source = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int[][] target = new int[source[0].length][source.length];
    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[i].length; j++) {
        target[j][i] = source[i][j];
      }
    }
  }
}
