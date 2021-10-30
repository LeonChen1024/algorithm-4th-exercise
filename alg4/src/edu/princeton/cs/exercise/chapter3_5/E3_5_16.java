package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.5.16 Add a method sum() to SparseVector that takes a SparseVector as argument
 * and returns a SparseVector that is the term-by-term sum of this vector and the argument
 * vector. Note: You need delete() (and special attention to precision) to handle the
 * case where an entry becomes 0.
 * <p>
 * 添加一个方法 sum() 到 SparseVector中,接收一个 SparseVector 作为参数并且返回一个该矢量和参数矢量的
 * 逐项和的 SparseVector. 注意:你需要 delete()(并且需要格外注意精度)来处理条目变成 0的情况
 *
 * @author LeonChen
 * @since 9/10/21
 */
class E3_5_16 {

    /**
     * @formatter:off
     * 将值当做 key 即可
     * @formatter:on
     */
    public static void main(String[] args) {
        //Sparse vector
        // 2.2 1.5 0 3.0 1.2
        // Sparse vector to sum
        // 0 -1.5 2.25 -3.0 -1.1999 4.2 0 9.8
        // Expected sparse vector result
        // 2.2 0 2.25 0 0.0001 4.2 0 9.8

        SparseVectorSum sparseVector = new SparseVectorSum();
        sparseVector.put(0, 2.2);
        sparseVector.put(1, 1.5);
        sparseVector.put(3, 3.0);
        sparseVector.put(4, 1.2);

        SparseVectorSum sparseVectorToSum = new SparseVectorSum();
        sparseVectorToSum.put(1, -1.5);
        sparseVectorToSum.put(2, 2.25);
        sparseVectorToSum.put(3, -3.0);
        sparseVectorToSum.put(4, -1.1999);
        sparseVectorToSum.put(5, 4.2);
        sparseVectorToSum.put(7, 9.8);

        SparseVectorSum sparseVectorSumResult = sparseVector.sum(sparseVectorToSum);
        int maxKey = 0;
        for (Integer key : sparseVectorSumResult.hashTable.keys()) {
            if (key > maxKey) {
                maxKey = key;
            }
        }

        StdOut.println("Sparse Vector Result:");
        for (int i = 0; i <= maxKey; i++) {
            StdOut.printf("%.4f", sparseVectorSumResult.get(i));

            if (i != maxKey) {
                StdOut.print(" ");
            }
        }

        StdOut.println("\nExpected:\n2.2000 0.0000 2.2500 0.0000 0.0001 4.2000 0.0000 9.8000");

    }

    private static class SparseVectorSum {

        private static final double FLOATING_POINT_EPSILON = 1E-6;
        private SeparateChainingHashST<Integer, Double> hashTable;

        public SparseVectorSum() {
            hashTable = new SeparateChainingHashST<>();
        }

        public int size() {
            return hashTable.size();
        }

        public void put(int key, double value) {
            hashTable.put(key, value);
        }

        public double get(int key) {
            if (!hashTable.contains(key)) {
                return 0;
            } else {
                return hashTable.get(key);
            }
        }

        public void delete(int key) {
            hashTable.delete(key);
        }

        public SparseVectorSum sum(SparseVectorSum sparseVectorToSum) {
            for (Integer key : sparseVectorToSum.hashTable.keys()) {
                if (!hashTable.contains(key)) {
                    put(key, sparseVectorToSum.get(key));
                } else {
                    double sum = get(key) + sparseVectorToSum.get(key);

                    if (Math.abs(sum) <= FLOATING_POINT_EPSILON) {
                        delete(key);
                    } else {
                        put(key, sum);
                    }
                }
            }
            return this;
        }

        public double dot(double[] that) {
            double sum = 0.0;

            for (int key : hashTable.keys()) {
                sum += that[key] * this.get(key);
            }

            return sum;
        }
    }


}
