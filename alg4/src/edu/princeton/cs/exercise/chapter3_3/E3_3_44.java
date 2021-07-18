package edu.princeton.cs.exercise.chapter3_3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/**
 * 3.3.44 Average search time. Run empirical studies to compute the average and standard
 * deviation of the average length of a path to a random node (internal path length
 * divided by tree size) in a red-black BST built by insertion of N random keys into an
 * initially empty tree, for N from 1 to 10,000. Do at least 1,000 trials for each tree
 * size. Plot the results in a Tufte plot, like the one at the bottom of this page, fit
 * with a curve plotting the function lgN–.5.
 * <p>
 * 平均搜索时间.运行实验来计算一个 N 个随机节点的红黑树平均到一个随机节点的路径长度的平均和标准方差(内部路径
 * 长度除以树大小),N 从 1 到 10000.至少每个 N 测试 1000 次.绘制出结果图,如页膜,看是否和方法 lgN–.5 的
 * 曲线类似
 *
 * @author LeonChen
 * @since 7/13/21
 */
class E3_3_44 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        doExperiment();
    }

    private static class RedBlackBSTInternalPathLength<Key extends Comparable<Key>, Value> extends RedBlackBST<Key, Value> {

        private class Node {
            Key key;
            Value value;
            Node left, right;

            boolean color;
            int size;
            private int depth; //used only to compute the internal path length

            Node(Key key, Value value, int size, boolean color) {
                this.key = key;
                this.value = value;

                this.size = size;
                this.color = color;
            }
        }

        private Node root;

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null) {
                return 0;
            }

            return node.size;
        }

        private boolean isRed(Node node) {
            if (node == null) {
                return false;
            }

            return node.color == RED;
        }

        private Node rotateLeft(Node node) {
            if (node == null || node.right == null) {
                return node;
            }

            Node newRoot = node.right;

            node.right = newRoot.left;
            newRoot.left = node;

            newRoot.color = node.color;
            node.color = RED;

            newRoot.size = node.size;
            node.size = size(node.left) + 1 + size(node.right);

            return newRoot;
        }

        private Node rotateRight(Node node) {
            if (node == null || node.left == null) {
                return node;
            }

            Node newRoot = node.left;

            node.left = newRoot.right;
            newRoot.right = node;

            newRoot.color = node.color;
            node.color = RED;

            newRoot.size = node.size;
            node.size = size(node.left) + 1 + size(node.right);

            return newRoot;
        }

        private void flipColors(Node node) {
            if (node == null || node.left == null || node.right == null) {
                return;
            }

            //The root must have opposite color of its two children
            if ((isRed(node) && !isRed(node.left) && !isRed(node.right))
                    || (!isRed(node) && isRed(node.left) && isRed(node.right))) {
                node.color = !node.color;
                node.left.color = !node.left.color;
                node.right.color = !node.right.color;
            }
        }

        public void put(Key key, Value value) {
            if (key == null) {
                return;
            }

            if (value == null) {
                delete(key);
                return;
            }

            root = put(root, key, value);
            root.color = BLACK;
        }

        private Node put(Node node, Key key, Value value) {
            if (node == null) {
                return new Node(key, value, 1, RED);
            }

            int compare = key.compareTo(node.key);

            if (compare < 0) {
                node.left = put(node.left, key, value);
            } else if (compare > 0) {
                node.right = put(node.right, key, value);
            } else {
                node.value = value;
            }

            if (isRed(node.right) && !isRed(node.left)) {
                node = rotateLeft(node);
            }
            if (isRed(node.left) && isRed(node.left.left)) {
                node = rotateRight(node);
            }
            if (isRed(node.left) && isRed(node.right)) {
                flipColors(node);
            }

            node.size = size(node.left) + 1 + size(node.right);
            return node;
        }

        private int internalPathLength() {
            if (root == null) {
                return 0;
            }

            int internalPathLength = 0;

            Queue<Node> queue = new Queue<>();
            root.depth = 0;
            queue.enqueue(root);

            while (!queue.isEmpty()) {
                Node current = queue.dequeue();
                internalPathLength += current.depth;

                if (current.left != null) {
                    current.left.depth = current.depth + 1;
                    queue.enqueue(current.left);
                }
                if (current.right != null) {
                    current.right.depth = current.depth + 1;
                    queue.enqueue(current.right);
                }
            }

            return internalPathLength;
        }

        private double averagePathLength() {
            if (size() == 0) {
                return 0;
            }

            return (internalPathLength() / (double) size()) + 1;
        }
    }

    private static void doExperiment() {
        double maxNumberOfOperations = 10000;
        double maxCost = 20;
        int originValue = 1;

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 10000);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(0.005);


        double lastComputedAveragePathLength = 0;
        double lastExpectedAveragePathLength = -1;

        for (int size = originValue; size <= maxNumberOfOperations; size++) {
            int numberOfTrials = 1000;

            long totalAvgPathLengths = 0;

            for (int t = 0; t < numberOfTrials; t++) {
                RedBlackBSTInternalPathLength<Integer, Integer> redBlackBSTInternalPathLength =
                        new RedBlackBSTInternalPathLength<>();

                for (int i = 0; i < size; i++) {
                    Integer randomKey = StdRandom.uniform(Integer.MAX_VALUE);
                    redBlackBSTInternalPathLength.put(randomKey, randomKey);
                }

                double averagePathLength = redBlackBSTInternalPathLength.averagePathLength();
                totalAvgPathLengths += averagePathLength;

            }

            double averageOfAveragesPathLength = totalAvgPathLengths / (double) numberOfTrials;
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(size, averageOfAveragesPathLength);

            //Draw the expected average path length -> lg N - .5
            double expectedAveragePathLength = (Math.log(size) / Math.log(2)) - 0.5;
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(size, expectedAveragePathLength);
            if (lastExpectedAveragePathLength != -1) {
                StdDraw.line(size - 1, lastExpectedAveragePathLength, size,
                        expectedAveragePathLength);
            }
            lastExpectedAveragePathLength = expectedAveragePathLength;

            lastComputedAveragePathLength = averageOfAveragesPathLength;
        }

    }

}
