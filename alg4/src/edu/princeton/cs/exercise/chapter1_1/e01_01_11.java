package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

/**
 * Write a code fragment that prints the contents of a two-dimensional boolean array, using * to
 * represent true and a space to represent false. Include row and column numbers.
 *
 * 编写一个代码片段打印出一个二维 boolean 数组的内容,使用*来代表 true ,空格代表 false.包含行列号
 */
class e01_01_11 {

  /**
   */
  public static void main(String[] args) {
    boolean[][] twoDArray = new boolean[][] {{true, false, true}, {false, true, false}};
    for (int i = 0; i < twoDArray.length; i++) {
      for (int j = 0; j < twoDArray[i].length; j++) {
        String result = "";
        if (twoDArray[i][j]) {
          result = "*";

        } else {
          result = " ";
        }
        StdOut.println("row= " + i + 1 + " , column= " + j + 1 + " , " + result);
      }
    }
  }
}
