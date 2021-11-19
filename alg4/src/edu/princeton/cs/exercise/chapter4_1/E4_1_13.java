package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

/**
 * 4.1.13 Add a distTo() method to the BreadthFirstPaths API and implementation, which
 * returns the number of edges on the shortest path from the source to a given vertex.
 * A distTo() query should run in constant time.
 * <p>
 * 添加一个 distTo() 方法到 BreadthFirstPaths API 中并实现,它会返回源点到给定顶点的最短路径长度.
 * distTo()方法应该是常量级时间
 *
 * @author LeonChen
 * @since 10/29/21
 */
class E4_1_13 {

    /**
     * @formatter:off
     * 源码中已经实现
     * @formatter:on
     */
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        BreadthFirstPaths paths = new BreadthFirstPaths(graph, 0);
        StdOut.println("to 1,dist " + paths.distTo(1) + ", expect 1");
        StdOut.println("to 2,dist " + paths.distTo(2) + ", expect 2");

    }


}
