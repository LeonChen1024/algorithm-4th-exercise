package edu.princeton.cs.exercise.chapter3_3;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.exercise.util.FileUtil;

import java.awt.*;

/**
 * 3.3.45 Count rotations. Instrument your program for Exercise 3.3.43 to plot the
 * number of rotations and node splits that are used to build the trees. Discuss the results.
 * <p>
 * 计算旋转. 对练习 3.3.43 进行测试来绘制出构建树时旋转的次数和节点分裂的次数.讨论下结果
 *
 * @author LeonChen
 * @since 7/14/21
 */
class E3_3_45 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        String[] wordsInTale = FileUtil.getAllStrFromFile(
                "src/edu/princeton/cs/algs4-data/tale.txt");
        int minLength = 8; //Same as the book analysis
        frequencyCounter(wordsInTale, minLength);
    }

    private static class RedBlackBSTCostPlots<Key extends Comparable<Key>, Value> extends RedBlackBST<Key, Value> {

        private int cost;

        private int putAndComputeCost(Key key, Value value) {
            if (key == null) {
                return 0;
            }

            if (value == null) {
                delete(key);
                return 0;
            }

            cost = 0;

            root = put(root, key, value);
            root.color = BLACK;

            return cost;
        }

        public Node put(Node node, Key key, Value value) {
            if (node == null) {
                return new Node(key, value, RED, 1);
            }

            int compare = key.compareTo(node.key);

            if (compare < 0) {
                node.left = put(node.left, key, value);
            } else if (compare > 0) {
                node.right = put(node.right, key, value);
            } else {
                node.val = value;
            }

            if (isRed(node.right) && !isRed(node.left)) {
                cost++;
                node = rotateLeft(node);
            }
            if (isRed(node.left) && isRed(node.left.left)) {
                cost++;
                node = rotateRight(node);
            }
            if (isRed(node.left) && isRed(node.right)) {
                cost++;
                flipColors(node);
            }

            node.size = size(node.left) + 1 + size(node.right);
            return node;
        }
    }


    private static String frequencyCounter(String[] words, int minLength) {

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 40000);
        StdDraw.setYscale(0, 30);
        StdDraw.setPenRadius(0.005);

        RedBlackBSTCostPlots<String, Integer> redBlackBSTCostPlots = new RedBlackBSTCostPlots<>();
        int i = 0;
        int total = 0;
        for (String word : words) {

            if (word.length() < minLength) {
                continue;
            }

            int cost;
            if (!redBlackBSTCostPlots.contains(word)) {
                cost = redBlackBSTCostPlots.putAndComputeCost(word, 1);
            } else {
                cost = redBlackBSTCostPlots.putAndComputeCost(word, redBlackBSTCostPlots.get(word) + 1);
            }
            StdDraw.point(++i, cost);
            total += cost;
        }

        String max = "";
        int cost = redBlackBSTCostPlots.putAndComputeCost(max, 0);

        for (String word : redBlackBSTCostPlots.keys()) {
            if (redBlackBSTCostPlots.get(word) > redBlackBSTCostPlots.get(max)) {
                max = word;
            }
        }
        StdDraw.setPenColor(Color.RED);
        StdDraw.line(0, total / words.length, 40000, total / words.length);

        return max + " " + redBlackBSTCostPlots.get(max);
    }


}
