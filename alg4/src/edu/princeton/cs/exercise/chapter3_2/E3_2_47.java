package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/**
 * 3.2.47 Average search time. Run empirical studies to compute the average and standard
 * deviation of the average length of a path to a random node (internal path length
 * divided by tree size, plus 1) in a BST built by insertion of N random keys into an
 * initially empty tree, for N from 100 to 10,000. Do 1,000 trials for each tree size.
 * Plot the results in a Tufte plot, like the one at the bottom of this page, fit with
 * a curve plotting the function 1.39 lg N – 1.85 (see Exercise 3.2.35 and Exercise 3.2.39).
 * <p>
 * 平均搜索时间. 运行实研究并计算插入 N 个随机键的空 BST 中一个随机节点的平均路径长度和标准方差(内部路径长度
 * 除以树的大小,+1),其中 N 从100 到 10,000.每个树的大小做 1000 次实验.绘制出 Tufte 的结果图,就像这页
 * 下面的那个图,符合绘制函数 1.39 lg N – 1.85(见练习 3.2.35 和练习 3.2.39)
 *
 * @author LeonChen
 * @since 5/18/21
 */
class E3_2_47 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        StdDraw.setXscale(100, 10000);
        StdDraw.setYscale(0, 30);
        for (int n = 100; n <= 10000; n++) {
            double formula = (1.39 * (Math.log(n) / Math.log(2)) - 1.85);
            StdDraw.setPenColor(Color.RED);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(n, formula);


            for (int t = 0; t < 1000; t++) {
                long totalPath = 0;
                BST<Integer, Integer> bst = new BST<>();
                for (int i = 0; i < n; i++) {
                    bst.put(StdRandom.uniform(Integer.MAX_VALUE), i);
                }
                int avgPath = bst.root.depthSum / bst.size() + 1;
                StdDraw.setPenColor(Color.blue);
                StdDraw.setPenRadius(0.007);
                StdDraw.point(n, avgPath);
                for (int j = 0; j < n; j++) {
                    BST.Node node = bst.select(bst.root, j);

                    long squareDeviation =
                            (long) Math.pow((node.depthSum / node.size + 1 - avgPath), 2);
                    totalPath = squareDeviation;
                }
                double standardDeviation = Math.sqrt(totalPath / n);
                StdDraw.setPenColor(Color.green);
                StdDraw.setPenRadius(0.007);
                StdDraw.point(n, standardDeviation);
            }
        }
    }

}
