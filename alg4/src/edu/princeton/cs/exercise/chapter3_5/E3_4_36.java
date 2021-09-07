package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 3.4.36 List length range. Write a program that inserts N random int keys into a table
 * of size N / 100 using separate chaining, then finds the length of the shortest and longest
 * lists,forN=10^3, 10^4, 10^5,10^6.
 * <p>
 * 列表长度范围.编写一个程序插入 N 个随机整数键到一个大小为  N / 100 使用独立链的表中,然后找到长度最短和
 * 最长的链表,其中 N=10^3, 10^4, 10^5,10^6.
 *
 * @author LeonChen
 * @since 8/19/21
 */
class E3_4_36 {


    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        int[] keySizes = {1000, 10000, 100000, 1000000};

        for (int keySizeIndex = 0; keySizeIndex < keySizes.length; keySizeIndex++) {
            int numberOfKeys = keySizes[keySizeIndex];
            StdOut.println("N = " + numberOfKeys);

            SeparateChainingHashST<Integer, Integer> hashST =
                    new SeparateChainingHashST<>(numberOfKeys / 100);

            for (int i = 0; i < numberOfKeys; i++) {
                int randomIntegerKey = StdRandom.uniform(Integer.MAX_VALUE);
                hashST.put(randomIntegerKey, randomIntegerKey);
            }

            int shortestListLength = Integer.MAX_VALUE;
            int longestListLength = Integer.MIN_VALUE;

            for (SequentialSearchST list : hashST.st) {
                if (list != null) {
                    if (list.size() < shortestListLength) {
                        shortestListLength = list.size();
                    }
                    if (list.size() > longestListLength) {
                        longestListLength = list.size();
                    }
                }
            }

            StdOut.println("Length of the shortest list: " + shortestListLength);
            StdOut.println("Length of the longest list: " + longestListLength);
        }
    }

}
