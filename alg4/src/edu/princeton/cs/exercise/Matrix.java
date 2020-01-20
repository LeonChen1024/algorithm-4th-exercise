package edu.princeton.cs.exercise;

public class Matrix {

  /**
   * https://en.wikipedia.org/wiki/Dot_product
   *
   * <p>https://baike.baidu.com/item/%E7%82%B9%E7%A7%AF
   *
   * @param x
   * @param y
   * @return
   */
  public static double dot(double[] x, double[] y) {

    if (x.length != y.length) {
      System.out.println("x and y should have same size.");
      return 0;
    }
    double result = 0;
    for (int i = 0; i < x.length; i++) {
      result += x[i] * y[i];
    }

    return result;
  }

  /**
   * https://en.wikipedia.org/wiki/Matrix_multiplication
   *
   * <p>https://baike.baidu.com/item/%E7%9F%A9%E9%98%B5%E4%B9%98%E6%B3%95
   *
   * @param a
   * @param b
   * @return
   */
  public static double[][] mult(double[][] a, double[][] b) {
    if (a[0].length != b.length) {
      System.out.println("the column's size of a should be the same as the row's size of b");
      return null;
    }
    double[][] c = new double[a.length][b[0].length];

    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < b[0].length; j++) {
        for (int k = 0; k < b.length; k++) {

          c[i][j] += a[i][k] * b[k][j];
        }
      }
    }
    return c;
  }

  /**
   * https://en.wikipedia.org/wiki/Transpose
   *
   * <p>https://baike.baidu.com/item/%E8%BD%AC%E7%BD%AE%E7%9F%A9%E9%98%B5
   *
   * @param a
   * @return
   */
  public static double[][] transpose(double[][] a) {
    double[][] c = new double[a[0].length][a.length];
    for (int i = 0; i < a[0].length; i++) {
      for (int j = 0; j < a.length; j++) {
        c[i][j] = a[j][i];
      }
    }
    return c;
  }

  public static double[][] mult(double[][] a, double[] x) {
    if (a[0].length != x.length) {
      System.out.println("the column's size of a should be the same as the row's size of x");
      return null;
    }
    double[][] c = new double[a.length][1];
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[0].length; j++) {
        c[i][0] += a[i][j] * x[j];
      }
    }
    return c;
  }

  public static double[][] mult(double[] y, double[][] a) {
    if (y.length != a.length) {
      System.out.println("the column's size of y should be the same as the row's size of a");
      return null;
    }
    double[][] c = new double[1][a[0].length];

    for (int j = 0; j < a[0].length; j++) {
      for (int k = 0; k < a.length; k++) {
        c[0][j] += y[k] * a[k][j];
      }
    }

    return c;
  }
}
