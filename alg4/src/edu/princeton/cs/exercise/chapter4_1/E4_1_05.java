package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 4.1.5 Modify Graph to disallow parallel edges and self-loops.
 * <p>
 * 修改 Graph 来禁用平行边和自循环
 *
 * @author LeonChen
 * @since 10/24/21
 */
class E4_1_05 {

    /**
     * @formatter:off
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
