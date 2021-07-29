package edu.princeton.cs.exercise.chapter3_3;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.exercise.util.FileUtil;

/**
 * 3.3.46 Height. Instrument your program for Exercise 3.3.43 to plot the height of
 * red-black BSTs. Discuss the results.
 * <p>
 * 高度.对练习 3.3.43 测试你的程序来绘制出红黑树的高度.讨论结果
 *
 * @author LeonChen
 * @since 7/15/21
 */
class E3_3_46 {

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

    private static int bstHeight(RedBlackBST<String, Integer> redBlackBST) {
        //Minus 1 because the root has height 0
        return bstHeight(redBlackBST.root) - 1;
    }

    private static int bstHeight(RedBlackBST.Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = bstHeight(node.left);
        int rightHeight = bstHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    private static String frequencyCounter(String[] words, int minLength) {

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 40000);
        StdDraw.setYscale(0, 30);
        StdDraw.setPenRadius(0.005);
        RedBlackBST<String, Integer> redBlackBST = new RedBlackBST<>();

        int i = 0;
        for (String word : words) {

            if (word.length() < minLength) {
                continue;
            }

            if (!redBlackBST.contains(word)) {
                redBlackBST.put(word, 1);
            } else {
                redBlackBST.put(word, redBlackBST.get(word) + 1);
            }
            int height = bstHeight(redBlackBST);
            StdDraw.point(++i, height);
        }

        String max = "";
        redBlackBST.put(max, 0);
        int height = bstHeight(redBlackBST);

        for (String word : redBlackBST.keys()) {
            if (redBlackBST.get(word) > redBlackBST.get(max)) {
                max = word;
            }
        }


        return max + " " + redBlackBST.get(max);
    }

}
