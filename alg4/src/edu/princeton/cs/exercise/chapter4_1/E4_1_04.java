package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 4.1.4 Add a method hasEdge() to Graph which takes two int arguments v and w and
 * returns true if the graph has an edge v-w, false otherwise.
 * <p>
 * 添加一个 hasEdge() 到Graph 中,它接收两个 int 参数 v 和 w 并且在图有边 v-w 的时候返回 true,否则
 * 返回 false
 *
 * @author LeonChen
 * @since 10/23/21
 */
class E4_1_04 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {

        EdgeCheckGraph graph = new EdgeCheckGraph(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);

        StdOut.println("0-1 expect true , result " + graph.hasEdge(0, 1));
        StdOut.println("0-4 expect false , result " + graph.hasEdge(0, 4));


    }

    private static class EdgeCheckGraph extends Graph {
        public EdgeCheckGraph(int V) {
            super(V);
        }

        public EdgeCheckGraph(In in) {
            super(in);
        }

        public EdgeCheckGraph(Graph G) {
            super(G);
        }

        public boolean hasEdge(int v, int w) {
            for (Integer integer : adj(v)) {
                if (integer == w) {
                    return true;
                }
            }
            return false;
        }
    }

}
