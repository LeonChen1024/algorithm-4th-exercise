package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 3.5.34 Sparse vector. Run experiments to compare the performance of matrix-vector
 * multiplication using SparseVector to the standard implementation using arrays.
 * <p>
 * 稀疏向量.运行实验来对比使用数组来实现 SparseVector 的矩阵向量 乘积的性能
 *
 * @author LeonChen
 * @since 9/17/21
 */
class E3_5_34 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {

        int size = 10000;

        double[][] arrayMatrixManyNonZeroes = new double[size][size];
        double[][] arrayMatrixManyZeroes = new double[size][size];

        double[] arrayVectorManyNonZeroes = new double[size];
        double[] arrayVectorManyZeroes = new double[size];

        E3_5_23.SparseMatrix sparseMatrixManyNonZeroes = new E3_5_23.SparseMatrix(size, size);
        E3_5_23.SparseMatrix sparseMatrixManyZeroes = new E3_5_23.SparseMatrix(size, size);

        E3_5_23.SparseVector sparseVectorManyNonZeroes = new E3_5_23.SparseVector(size);
        E3_5_23.SparseVector sparseVectorManyZeroes = new E3_5_23.SparseVector(size);

        //**************** Populate matrices ****************
        //Most values are nonzero
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                int isZero = StdRandom.uniform(100);

                //95% chance of being nonzero
                boolean isNonzeroValue = isZero <= 94;

                double value = 0;

                if (isNonzeroValue) {
                    value = StdRandom.uniform();
                }

                arrayMatrixManyNonZeroes[row][column] = value;
                sparseMatrixManyNonZeroes.put(row, column, value);
            }
        }

        //Most values are zero
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                int isZero = StdRandom.uniform(100);

                //5% chance of being nonzero
                boolean isNonzeroValue = isZero >= 95;

                double value = 0;

                if (isNonzeroValue) {
                    value = StdRandom.uniform();
                }

                arrayMatrixManyZeroes[row][column] = value;
                sparseMatrixManyZeroes.put(row, column, value);
            }
        }

        //**************** Populate vectors ****************
        //Most values are nonzero
        for (int column = 0; column < size; column++) {
            int isZero = StdRandom.uniform(100);

            //95% chance of being nonzero
            boolean isNonzeroValue = isZero <= 94;

            double value = 0;

            if (isNonzeroValue) {
                value = StdRandom.uniform();
            }

            arrayVectorManyNonZeroes[column] = value;
            sparseVectorManyNonZeroes.put(column, value);
        }

        //Most values are zero
        for (int column = 0; column < size; column++) {
            int isZero = StdRandom.uniform(100);

            //5% chance of being nonzero
            boolean isNonzeroValue = isZero >= 95;

            double value = 0;

            if (isNonzeroValue) {
                value = StdRandom.uniform();
            }

            arrayVectorManyZeroes[column] = value;
            sparseVectorManyZeroes.put(column, value);
        }

        StdOut.printf("%17s %18s %20s %10s\n", "Method | ", "Matrix type | ", "Vector type | ", "Time spent");
        String[] methods = {"Arrays", "Sparse Vectors"};
        String[] types = {"Many non-zeroes", "Many zeroes"};

        //Array matrix many non-zeroes X array vector many non-zeroes
        Stopwatch stopwatch = new Stopwatch();
        dot(arrayMatrixManyNonZeroes, arrayVectorManyNonZeroes);
        double timeSpent = stopwatch.elapsedTime();
        printResults(methods[0], types[0], types[0], timeSpent);

        //Array matrix many non-zeroes X array vector many zeroes
        stopwatch = new Stopwatch();
        dot(arrayMatrixManyNonZeroes, arrayVectorManyZeroes);
        timeSpent = stopwatch.elapsedTime();
        printResults(methods[0], types[0], types[1], timeSpent);

        //Array matrix many zeroes X array vector many non-zeroes
        stopwatch = new Stopwatch();
        dot(arrayMatrixManyZeroes, arrayVectorManyNonZeroes);
        timeSpent = stopwatch.elapsedTime();
        printResults(methods[0], types[1], types[0], timeSpent);

        //Array matrix many zeroes X array vector many zeroes
        stopwatch = new Stopwatch();
        dot(arrayMatrixManyZeroes, arrayVectorManyZeroes);
        timeSpent = stopwatch.elapsedTime();
        printResults(methods[0], types[1], types[1], timeSpent);

        //Sparse matrix many non-zeroes X sparse vector many non-zeroes
        stopwatch = new Stopwatch();
        sparseMatrixManyNonZeroes.dot(sparseVectorManyNonZeroes);
        timeSpent = stopwatch.elapsedTime();
        printResults(methods[1], types[0], types[0], timeSpent);

        //Sparse matrix many non-zeroes X sparse vector many zeroes
        stopwatch = new Stopwatch();
        sparseMatrixManyNonZeroes.dot(sparseVectorManyZeroes);
        timeSpent = stopwatch.elapsedTime();
        printResults(methods[1], types[0], types[1], timeSpent);

        //Sparse matrix many zeroes X sparse vector many non-zeroes
        stopwatch = new Stopwatch();
        sparseMatrixManyZeroes.dot(sparseVectorManyNonZeroes);
        timeSpent = stopwatch.elapsedTime();
        printResults(methods[1], types[1], types[0], timeSpent);

        //Sparse matrix many zeroes X sparse vector many zeroes
        stopwatch = new Stopwatch();
        sparseMatrixManyZeroes.dot(sparseVectorManyZeroes);
        timeSpent = stopwatch.elapsedTime();
        printResults(methods[1], types[1], types[1], timeSpent);
    }


    private static double[] dot(double[][] matrix, double[] array) {
        if (matrix[0].length != array.length) {
            throw new IllegalArgumentException("Matrix columns number and vector dimension must match");
        }

        double[] result = new double[matrix.length];

        for (int row = 0; row < matrix.length; row++) {
            double dot = 0;

            for (int column = 0; column < matrix[0].length; column++) {
                dot += matrix[row][column] * array[column];
            }

            result[row] = dot;
        }

        return result;
    }

    private static void printResults(String method, String matrixType, String vectorType, double timeSpent) {
        StdOut.printf("%14s %18s %20s %13.2f\n", method, matrixType, vectorType, timeSpent);
    }


}
