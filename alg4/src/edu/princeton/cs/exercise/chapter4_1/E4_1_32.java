package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

/**
 * 4.1.32 Parallel edge detection. Devise a linear-time algorithm to count the parallel
 * edges in a graph.
 * <p>
 * 平行边检测.研究一个线性时间算法来计算图中的平行边
 *
 * @author LeonChen
 * @since 11/10/21
 */
class E4_1_32 {

    /**
     * @formatter:off
     * a 的邻接数组中的顶点b的邻接数组中如果出现 a 那就是平行边
     * @formatter:on
     */
    public static void main(String[] args) {
        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 0);
        StdOut.println(countParallelEdges(graph1) + " Expected: 0");

        Graph graph2 = new Graph(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);
        graph2.addEdge(3, 0);
        graph2.addEdge(3, 2);
        StdOut.println(countParallelEdges(graph2) + " Expected: 1");

        Graph graph3 = new Graph(4);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 0);
        graph3.addEdge(2, 3);
        graph3.addEdge(3, 2);
        graph3.addEdge(3, 2);
        graph3.addEdge(3, 0);
        StdOut.println(countParallelEdges(graph3) + " Expected: 3");
    }

    private static int countParallelEdges(Graph graph) {
        int parallelEdges = 0;

        for (int vertex = 0; vertex < graph.V(); vertex++) {
            boolean[] neighbors = new boolean[graph.V()];

            for (int neighbor : graph.adj(vertex)) {
                if (!neighbors[neighbor]) {
                    neighbors[neighbor] = true;
                } else {
                    parallelEdges++;
                }
            }
        }
        return parallelEdges / 2;
    }


}
