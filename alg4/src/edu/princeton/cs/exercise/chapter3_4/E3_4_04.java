package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;
import java.util.Set;

/**
 * 3.4.4 Write a program to find values of a and M, with M as small as possible, such that
 * the hash function (a * k) % M for transforming the kth letter of the alphabet into a
 * table index produces distinct values (no collisions) for the keys S E A R C H X M P L.
 * The result is known as a perfect hash function.
 * <p>
 * 编写一个程序来找到 a 和 M 的值,M 的值尽量小,使得 hash 函数(a*k)%M 转换字母表第 k 个字母到表索引时产生
 * 不同的值(没有碰撞),对于键 S E A R C H X M P L.结果是一个完美 hash 函数
 *
 * @author LeonChen
 * @since 7/24/21
 */
class E3_4_04 {

    /**
     * @formatter:off
     * 如果是只需要对这些特定输入完美 hash 则使用该程序测试.如果是针对字母表完美 hash 则 a=1 , M =26
     * @formatter:on
     */
    public static void main(String[] args) {
        int[] values = perfectHashFunction();

        if (values != null) {
            StdOut.println("a = " + values[0]);
            StdOut.println("m = " + values[1]);
        }
    }

    private static int[] perfectHashFunction() {
        int[] values = new int[2];

        int[] letterValues = {19, 5, 1, 18, 3, 8, 24, 13, 16, 12};

        for (int m = 2; m <= 100; m++) {
            for (int a = 1; a <= 1000; a++) {
                Set<Integer> hashes = new HashSet<>();

                for (int i = 0; i < letterValues.length; i++) {
                    int hash = hashCodeFunction(a, letterValues[i], m);
                    hashes.add(hash);
                }

                if (hashes.size() == 10) {
                    //Perfect hash function found
                    values[0] = a;
                    values[1] = m;
                    return values;
                }
            }
        }

        return null;
    }

    private static int hashCodeFunction(int a, int k, int m) {
        return (a * k) % m;
    }


}
