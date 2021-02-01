package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.exercise.Matrix;

/**
 * 1.1.33 Matrix library. Write a library Matrix that implements the following API: <br>
 * <code>
 * public class Matrix
 * static double dot(double[] x, double[] y) vector dot product
 * static double[][] mult(double[][] a, double[][] b) matrix-matrix product
 * static double[][] transpose(double[][] a) transpose
 * static double[] mult(double[][] a, double[] x) matrix-vector product
 * static double[] mult(double[] y, double[][] a) vector-matrix product
 * </code> Develop a test client that reads values from standard input and tests all the methods.
 *
 * <p>编写一个Matrix 库,实现以下API <br>
 * <code>
 * public class Matrix
 * static double dot(double[] x, double[] y) 向量点乘
 * static double[][] mult(double[][] a, double[][] b) 矩阵乘法
 * static double[][] transpose(double[][] a) 转置矩阵
 * static double[] mult(double[][] a, double[] x) 矩阵和向量积
 * static double[] mult(double[] y, double[][] a) 向量和矩阵积
 * </code>
 */
class E1_1_33 {


    public static void main(String[] args) {
        StdOut.println(Matrix.dot(new double[]{1, 2}, new double[]{2, 3}));
        double[][] a = Matrix.mult(new double[][]{{1, 2}, {1, 2}}, new double[][]{{2, 2}, {2, 2}});
        double[][] b = Matrix.transpose(new double[][]{{1, 2}, {1, 2}});
        double[][] c = Matrix.mult(new double[][]{{1, 2}, {1, 2}}, new double[]{2, 2});
        double[][] d = Matrix.mult(new double[]{1, 2}, new double[][]{{2, 2}, {2, 2}});
    }
}
