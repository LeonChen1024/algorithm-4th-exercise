package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/**
 * 3.2.44 Cost plots. Instrument BST so that you can produce plots like the ones in this
 * section showing the cost of each put() operation during the computation (see Exercise
 * 3.1.38).
 * <p>
 * 开销图. 测量 BST 你可以生成如本章中展示的计算每次 put()操作的开销图(见 练习 3.1.38)
 *
 * @author LeonChen
 * @since 5/11/21
 */
class E3_2_44 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        int N = 10000;
        StdDraw.setCanvasSize(1000, 700);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.red);
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, 50);

        CostBST<Integer, Integer> costBST = new CostBST();

        for (int i = 1; i <= N; i++) {
            costBST.put(StdRandom.uniform(N), 1);
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(i, costBST.getCompare());
            StdDraw.setPenColor(Color.BLUE);
            int avgCompare = costBST.totalCompare / i;

            StdDraw.point(i, avgCompare);

            StdOut.println(" operation time is " + i + " , amortized cost is " + avgCompare);
        }
    }

    private static class CostBST<Key extends Comparable<Key>, Value> extends BST<Key,
            Value> {
        private int compare = 0;
        private int totalCompare = 0;

        @Override
        public void put(Key key, Value val) {
            compare = 0;
            super.put(key, val);
        }

        protected Node put(Node x, Key key, Value val, int depth) {
            if (x == null) return new Node(key, val, 1);
            int cmp = key.compareTo(x.key);
            compare++;
            totalCompare++;
            if (cmp < 0) x.left = put(x.left, key, val, depth + 1);
            else if (cmp > 0) x.right = put(x.right, key, val, depth + 1);
            else x.val = val;
            x.size = 1 + size(x.left) + size(x.right);
            x.depthSum = depth;
            if (x.left != null) {
                x.depthSum += x.left.depthSum;
            }
            if (x.right != null) {
                x.depthSum += x.right.depthSum;
            }
            return x;
        }


        public int getCompare() {
            return compare;
        }
    }

}
