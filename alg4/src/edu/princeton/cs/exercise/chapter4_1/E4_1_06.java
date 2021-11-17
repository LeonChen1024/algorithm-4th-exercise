package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 4.1.6 Consider the four-vertex graph with edges 0-1, 1-2, 2-3, and 3-0. Draw an
 * array of adjacency-lists that could not have been built calling addEdge() for these
 * edges no matter what order.
 * <p>
 * 思考一个四顶点图有边 0-1, 1-2, 2-3, 和 3-0.找到一个邻接列表数组不能通过条用 addEdge() 来构成的情况
 *
 * @author LeonChen
 * @since 10/25/21
 */
class E4_1_06 {

    /**
     * @formatter:off
     * 最后组成了一个环形,只要改变其中一个顺序即可
     * adj[]
     *   0  -> 1 -> 3 (原始顺序是 0 -> 3 -> 1)
     *   1  -> 2 -> 0
     *   2  -> 3 -> 1
     *   3  -> 0 -> 2
     * @formatter:on
     */
    public static void main(String[] args) {

        GraphNoParallerAndSelfLoop graph = new GraphNoParallerAndSelfLoop(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(0, 0);
        graph.addEdge(3, 2);


    }

    private static class GraphNoParallerAndSelfLoop extends Graph {
        public GraphNoParallerAndSelfLoop(int V) {
            super(V);
        }

        public GraphNoParallerAndSelfLoop(In in) {
            super(in);
        }

        public GraphNoParallerAndSelfLoop(Graph G) {
            super(G);
        }

        @Override
        public void addEdge(int v, int w) {
            if (v == w) {
                StdOut.println("cannot add self loop " + v + "-" + w);
                return;
            } else if (hasEdge(w, v)) {
                StdOut.println("cannot add paraller edge" + v + "-" + w);
                return;
            }

            super.addEdge(v, w);
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
