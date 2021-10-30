package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.5.15 Write a program that takes a string on standard input and an integer k as
 * command-line argument and puts on standard output a sorted list of the k-grams found in
 * the string, each followed by its index in the string.
 * <p>
 * 编写一个程序在标准输入接受一个字符串和一个整数 k 作为命令行参数并且输出一个有序的 k长的短语列表,每个跟上他
 * 在字符串中的索引
 *
 * @author LeonChen
 * @since 9/9/21
 */
class E3_5_15 {

    /**
     * @formatter:off
     * 将值当做 key 即可
     * @formatter:on
     */
    public static void main(String[] args) {
//        int k = 3;
//        String string = "asdasdcefasdvmneio";
//
//        RedBlackBST<String, Integer> kGrams = produceKGrams(string, k);
//        for (String kGram : kGrams.keys()) {
//            StdOut.println(kGram + " " + kGrams.get(kGram));
//        }

        test();
    }

    private static RedBlackBST<String, Integer> produceKGrams(String string, int k) {
        if (k > string.length()) {
            throw new IllegalArgumentException("k cannot be higher than string length");
        }

        RedBlackBST<String, Integer> kGrams = new RedBlackBST<>();

        for (int i = 0; i <= string.length() - k; i++) {
            String kGram = string.substring(i, i + k);
            kGrams.put(kGram, i);
        }

        return kGrams;
    }

    private static void test() {
        int k = 4;
        String string = "algorithms";

        StdOut.println();
        RedBlackBST<String, Integer> kGrams = produceKGrams(string, k);
        for (String kGram : kGrams.keys()) {
            StdOut.println(kGram + " " + kGrams.get(kGram));
        }

        StdOut.println("\nExpected:\nalgo 0\ngori 2\nithm 5\nlgor 1\norit 3\nrith 4\nthms 6");
    }


}
