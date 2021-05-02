package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 3.2.25 Perfect balance. Write a program that inserts a set of keys into an initially
 * empty BST such that the tree produced is equivalent to binary search, in the sense
 * that the sequence of compares done in the search for any key in the BST is the same
 * as the sequence of compares used by binary search for the same set of keys.
 * <p>
 * 完美平衡.编写一个程序插入一些 key 到一个空 BST 中,使得树生成的和二分搜索一致,也就是 BST 中查找一个 key
 * 的搜索路径会和二分搜索中的路径一样
 *
 * @author LeonChen
 * @since 4/21/21
 */
class E3_2_25 {

    /**
     * @formatter:off
     * 官方解答如下
     *
     * 其实就是利用了二分搜索每次都会往下一个中点遍历的特性,将每个子树的根节点设置为当前子树的中点值
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


    /******************************************************************************
     *  Compilation:  javac PerfectBalance.java
     *  Execution:    java PerfectBalance < input.txt
     *  Dependencies: StdOut.java
     *
     *  Read sequence of strings from standard input (no duplicates),
     *  and insert into a BST so that BST is perfectly balanced.
     *
     *  % java PerfectBalance
     *  P E R F C T B I N A R Y S R H
     *  N E B A C H F I R R P R T S Y
     *
     ******************************************************************************/
    public static class PerfectBalance {

        // precondition: a[] has no duplicates
        private static void perfect(BST bst, String[] a) {
            Arrays.sort(a);
            perfect(bst, a, 0, a.length - 1);
            StdOut.println();
        }

        // precondition: a[lo..hi] is sorted
        private static void perfect(BST bst, String[] a, int lo, int hi) {
            if (hi < lo) return;
            int mid = lo + (hi - lo) / 2;
            bst.put(a[mid], mid);
            StdOut.print(a[mid] + " ");
            perfect(bst, a, lo, mid - 1);
            perfect(bst, a, mid + 1, hi);
        }

        public static void main(String[] args) {
            String[] words = StdIn.readAllStrings();
            BST<String, Integer> bst = new BST<String, Integer>();
            perfect(bst, words);
        }
    }

}
