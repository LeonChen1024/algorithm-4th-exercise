package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

/**
 * 4.1.10 Prove that every connected graph has a vertex whose removal (including all
 * adjacent edges) will not disconnect the graph, and write a DFS method that finds such
 * a vertex. Hint : Consider a vertex whose adjacent vertices are all marked.
 * <p>
 * 证明每个连接图都有一个顶点的删除(包含所有邻接边)不会断开图的连接,并且编写一个 DFS 方法来找到这个顶点.
 * 提示:考虑一个所有邻接顶点都被标记的顶点
 *
 * @author LeonChen
 * @since 10/27/21
 */
class E4_1_10 {

    /**
     * @formatter:off
     * 当你访问到这个顶点的时候,如果他的所有邻接顶点都被标记了,那么这个顶点的边删除也不会影响到其他顶点
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

        DfsConnected depthFirstSearchConnected = new DfsConnected(graph, 0);
        StdOut.println("Vertex that can be removed: " + depthFirstSearchConnected.findVertexThatCanBeRemoved() + " Expected: 1");
    }

    public static class DfsConnected {

        private boolean[] visited;
        private int count;
        private int vertexThatCanBeRemoved;

        private Graph graph;
        private int sourceVertex;

        public DfsConnected(Graph graph, int sourceVertex) {
            visited = new boolean[graph.V()];

            this.graph = graph;
            this.sourceVertex = sourceVertex;
        }

        public int findVertexThatCanBeRemoved() {
            dfs(graph, sourceVertex);
            return vertexThatCanBeRemoved;
        }

        private void dfs(Graph graph, int vertex) {
            visited[vertex] = true;
            count++;

            boolean areAllNeighborsMarked = true;

            for (int neighbor : graph.adj(vertex)) {
                if (!visited[neighbor]) {
                    areAllNeighborsMarked = false;
                    dfs(graph, neighbor);
                }
            }

            if (areAllNeighborsMarked) {
                vertexThatCanBeRemoved = vertex;
            }
        }

        public boolean marked(int vertex) {
            return visited[vertex];
        }

        public int count() {
            return count;
        }

    }


}
