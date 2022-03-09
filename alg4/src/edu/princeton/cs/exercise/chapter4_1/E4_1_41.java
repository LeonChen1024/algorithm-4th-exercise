package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

import static edu.princeton.cs.exercise.chapter4_1.E4_1_39.erdosRenyiGraph;

/**
 * 4.1.41 Random sparse graphs. Write a program RandomSparseGraph to generate random
 * sparse graphs for a well-chosen set of values of V and E such that you can use it to
 * run meaningful empirical tests on graphs drawn from the Erdös-Renyi model.
 * <p>
 * 随机稀疏图.编写一个程序 RandomSparseGraph 来生成随机稀疏图,使用选好的 V 和 E 的值,你可以使用它来运行
 * 实验来测试 Erdös-Renyi 模型
 *
 * @author LeonChen
 * @since 11/20/21
 */
class E4_1_41 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        int numberOfGraphs = 100;
//        int numberOfGraphs = Integer.parseInt(args[0]);

        List<Graph> randomSparseGraphs = randomSparseGraph(numberOfGraphs);
        StdOut.println("Random sparse graphs generated: " + (randomSparseGraphs.size() ==
                numberOfGraphs));

    }

    //A graph is considered sparse if its number of different edges is within a small
    // constant factor of V
    public static List<Graph> randomSparseGraph(int numberOfGraphs) {

        if (numberOfGraphs < 0) {
            throw new IllegalArgumentException("Number of graphs cannot be negative");
        }

        List<Graph> randomSparseGraphs = new ArrayList<>();
        int[] graphVerticesCount = {10, 100, 1000, 10000};

        for (int graph = 0; graph < numberOfGraphs; graph++) {
            int vertices = graphVerticesCount[graph % 4];
            int edges = vertices * 3;

            Graph randomSparseGraph = erdosRenyiGraph(vertices, edges);
            randomSparseGraphs.add(randomSparseGraph);
        }

        return randomSparseGraphs;
    }


}
