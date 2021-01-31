package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.23 Add to the BinarySearch test client the ability to respond to a second argument: + to
 * print numbers from standard input that are not in the whitelist, - to print numbers that are in
 * the whitelist.
 * <p>
 * 添加给 BinarySearch 测试端一个响应第二个参数的能力: + 来打印标准输入中不在白名单的数,- 来打印出在白名单中的数值
 */
class E1_1_23 {


    private static void test1_1_23(int[] whiteList, int key, String symbol) {
        int result = BinarySearch.indexOf(whiteList, key);
        if ("+".equals(symbol) && result == -1) {
            StdOut.println(key);
        } else if ("-".equals(symbol) && result != -1) {
            StdOut.println(key);
        }
    }

    /**
     *
     */
    public static void main(String[] args) {
        test1_1_23(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 23, 45, 67, 89}, 3, "-");

    }
}
