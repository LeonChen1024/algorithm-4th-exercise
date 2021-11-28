package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

/**
 * 4.1.29 Modify Cycle so that it works even if the graph contains self-loops and parallel
 * edges.
 * <p>
 * 修改 Cycle 使得它即使在图包含自循环和平行边的时候也能使用
 *
 * @author LeonChen
 * @since 11/7/21
 */
class E4_1_29 {

    /**
     * @formatter:off
     *
     * @formatter:on
     */
    public static void main(String[] args) {

        Graph graphWithSimpleCycle = new Graph(4);
        graphWithSimpleCycle.addEdge(0, 1);
        graphWithSimpleCycle.addEdge(1, 2);
        graphWithSimpleCycle.addEdge(2, 3);
        graphWithSimpleCycle.addEdge(3, 0);

        CycleThatDoesNotCountParallelEdgesOrSelfLoops cycle1 =
                new CycleThatDoesNotCountParallelEdgesOrSelfLoops(graphWithSimpleCycle);
        StdOut.println("Has cycle (simple cycle): " + cycle1.hasCycle() + " Expected: " +
                "true");

        Graph graphWithParallelEdges = new Graph(4);
        graphWithParallelEdges.addEdge(0, 1);
        graphWithParallelEdges.addEdge(1, 2);
        graphWithParallelEdges.addEdge(2, 1);
        graphWithParallelEdges.addEdge(2, 3);

        CycleThatDoesNotCountParallelEdgesOrSelfLoops cycle2 =
                new CycleThatDoesNotCountParallelEdgesOrSelfLoops(graphWithParallelEdges);
        StdOut.println("Has cycle (graph with parallel edges): " + cycle2.hasCycle() +
                " Expected: false");

        Graph graphWithSelfLoop = new Graph(4);
        graphWithSelfLoop.addEdge(0, 1);
        graphWithSelfLoop.addEdge(1, 2);
        graphWithSelfLoop.addEdge(2, 3);
        graphWithSelfLoop.addEdge(3, 3);

        CycleThatDoesNotCountParallelEdgesOrSelfLoops cycle3 =
                new CycleThatDoesNotCountParallelEdgesOrSelfLoops(graphWithSelfLoop);
        StdOut.println("Has cycle (graph with self-loop): " + cycle3.hasCycle() + " " +
                "Expected: false");

    }

    public static class CycleThatDoesNotCountParallelEdgesOrSelfLoops {
        private boolean[] visited;
        private int[] edgeTo;
        private boolean hasCycle;

        public CycleThatDoesNotCountParallelEdgesOrSelfLoops(Graph graph) {
            visited = new boolean[graph.V()];
            edgeTo = new int[graph.V()];

            for (int source = 0; source < graph.V(); source++) {
                if (!visited[source]) {
                    dfs(graph, source, source);
                }
            }
        }

        private void dfs(Graph graph, int vertex, int origin) {
            visited[vertex] = true;
            edgeTo[vertex] = origin;

            for (int neighbor : graph.adj(vertex)) {
                if (!visited[neighbor]) {
                    dfs(graph, neighbor, vertex);
                } else if (neighbor != origin
                        && edgeTo[neighbor] != vertex
                        && neighbor != vertex) {
                    hasCycle = true;
                }
            }
        }

        public boolean hasCycle() {
            return hasCycle;
        }
    }


}
