package edu.princeton.cs.exercise.chapter3_3;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 3.3.42 Count red nodes. Write a program that computes the percentage of red nodes
 * in a given red-black BST. Test your program by running at least 100 trials of the
 * experiment of inserting N random keys into an initially empty tree, for N = 10^4, 10^5,
 * and 10^6,and formulate an hypothesis.
 * <p>
 * 计算红色节点.编写一个程序计算给定红黑树中红色节点的比例.运行至少 100 次实验程序测试插入 N 个随机键到一个
 * 初始空树中,其中N = 10^4, 10^5, 以及 10^6 ,并且推算出一个假说
 *
 * @author LeonChen
 * @since 7/11/21
 */
class E3_3_42 {

    /**
     * @formatter:off
     * 递归计算红色节点即可
     * @formatter:on
     */
    public static void main(String[] args) {
        doExperiment();

    }

    private static void doExperiment() {
        int[] treeSizes = {10000, 100000, 1000000};
        int numberOfExperiments = 100;

        for (int size = 0; size < treeSizes.length; size++) {
            int totalNodesCount = treeSizes[size] * numberOfExperiments;
            int totalRedNodesCount = 0;

            for (int trial = 0; trial < numberOfExperiments; trial++) {
                int treeSize = treeSizes[size];

                RedBlackBST<Integer, Integer> redBlackBST = new RedBlackBST<>();
                for (int i = 0; i < treeSize; i++) {
                    int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
                    redBlackBST.put(randomKey, randomKey);
                }

                totalRedNodesCount += countRedNodes(redBlackBST);
            }

            double percentageOfRedNodes = ((double) totalRedNodesCount) / ((double) totalNodesCount) * 100;
            printResults(treeSizes[size], percentageOfRedNodes);
        }
    }

    private static int countRedNodes(RedBlackBST<Integer, Integer> redBlackBST) {
        if (redBlackBST.isEmpty()) {
            return 0;
        }

        return countRedNodes(redBlackBST.root);
    }

    private static int countRedNodes(RedBlackBST.Node node) {
        if (node == null) {
            return 0;
        }

        int redNodeCount = node.color == RedBlackBST.RED ? 1 : 0;

        return redNodeCount + countRedNodes(node.left) + countRedNodes(node.right);
    }

    private static void printResults(int treeSize, double percentageOfRedNodes) {
        StdOut.printf("%9d %15.2f\n", treeSize, percentageOfRedNodes);
    }


}
