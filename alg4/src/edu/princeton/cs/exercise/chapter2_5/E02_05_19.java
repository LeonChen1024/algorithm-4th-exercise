package edu.princeton.cs.exercise.chapter2_5;

import edu.princeton.cs.algs4.Inversions;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.5.19 Kendall tau distance. Write a program KendallTau.java that computes the Kendall tau
 * distance between two permutations in linearithmic time.
 *
 * <p> Kendall tau 距离.编写一个程序 KendallTau.java 在线性对数的复杂度下计算两个序列的 Kendall tau 距离
 *
 * @author LeonChen
 * @since 12/30/20
 */
class E02_05_19 {

    /**
     * 官方解答.总的做法就是将ainv 数组的键为 a 数组中 i 的值,ainv 的值为 i.然后 bnew 数组的键为 b 中值得索引
     * 而 bnew 得值则是同样的值在 a 数组中的索引. 最后在这个数组中就得到了索引是 b 得索引初始值,值是 a 得索引
     * 初始值得这样一个数组.此时我们在针对这个数组做一个合并排序,计算出值错位的情况,就可以得出最后的结果了.
     */
    public static void main(String[] args) {
    }


    public static class KendallTau {

        // return Kendall tau distance between two permutations
        public static long distance(int[] a, int[] b) {
            if (a.length != b.length) {
                throw new IllegalArgumentException("Array dimensions disagree");
            }
            int n = a.length;

            int[] ainv = new int[n];
            for (int i = 0; i < n; i++) {
                ainv[a[i]] = i;
            }

            Integer[] bnew = new Integer[n];
            for (int i = 0; i < n; i++) {
                bnew[i] = ainv[b[i]];
            }

            return Inversions.count(bnew);
        }


        // return a random permutation of size n
        public static int[] permutation(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = i;
            }
            StdRandom.shuffle(a);
            return a;
        }


        public static void main(String[] args) {

            // two random permutation of size n
//            int n = Integer.parseInt(args[0]);
            int n = 10;
            int[] a = KendallTau.permutation(n);
            int[] b = KendallTau.permutation(n);


            // print initial permutation
            for (int i = 0; i < n; i++) {
                StdOut.println(a[i] + " " + b[i]);
            }
            StdOut.println();
            StdOut.println("inversions = " + KendallTau.distance(a, b));
        }
    }


}
