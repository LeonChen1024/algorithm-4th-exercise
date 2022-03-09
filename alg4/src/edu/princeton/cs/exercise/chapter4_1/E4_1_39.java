package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 4.1.39 Random graphs. Write a program ErdosRenyiGraph that takes integer values V
 * and E from the command line and builds a graph by generating E random pairs
 * of integers between 0 and V-1. Note: This generator produces self-loops and parallel
 * edges.
 * <p>
 * 随机图.编写一个程序 ErdosRenyiGraph 从命令行接收整数 V 和 E 并且生成 E 个0 到 V-1 的随机对构建一个图.
 * 注意:这个生成器会生成自循环和平行边
 *
 * @author LeonChen
 * @since 11/18/21
 */
class E4_1_39 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        int vertices = Integer.parseInt(args[0]);
        int edges = Integer.parseInt(args[1]);

        Graph randomGraph = erdosRenyiGraph(vertices, edges);
        StdOut.println(randomGraph);

    }

    public static Graph erdosRenyiGraph(int vertices, int edges) {
        Graph randomGraph = new Graph(vertices);

        for (int edge = 0; edge < edges; edge++) {
            int vertexId1 = StdRandom.uniform(vertices);
            int vertexId2 = StdRandom.uniform(vertices);

            randomGraph.addEdge(vertexId1, vertexId2);
        }

        return randomGraph;
    }

}
