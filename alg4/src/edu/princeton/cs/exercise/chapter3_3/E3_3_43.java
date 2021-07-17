package edu.princeton.cs.exercise.chapter3_3;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.exercise.util.FileUtil;

/**
 * 3.3.43 Cost plots. Instrument RedBlackBST so that you can produce plots like the
 * ones in this section showing the cost of each put() operation during the computation
 * (see Exercise 3.1.38).
 * <p>
 * 成本图.测试 RedBlackBST使得你可以生成如正文章节中那样展示出每个 put() 的操作成本图(见练习 3.1.38)
 *
 * @author LeonChen
 * @since 7/12/21
 */
class E3_3_43 {

    /**
     * @formatter:off
     * 递归计算红色节点即可
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
            cost++;

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
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(0.005);

        RedBlackBSTCostPlots<String, Integer> redBlackBSTCostPlots = new RedBlackBSTCostPlots<>();

        int i = 0;
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
        }

        String max = "";
        int cost = redBlackBSTCostPlots.putAndComputeCost(max, 0);

        for (String word : redBlackBSTCostPlots.keys()) {
            if (redBlackBSTCostPlots.get(word) > redBlackBSTCostPlots.get(max)) {
                max = word;
            }
        }

        return max + " " + redBlackBSTCostPlots.get(max);
    }


}
