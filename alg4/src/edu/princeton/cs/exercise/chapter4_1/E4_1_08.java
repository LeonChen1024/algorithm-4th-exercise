package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

/**
 * 4.1.8 Develop an implementation for the Search API on page 528 that uses UF, as
 * described in the text.
 * <p>
 * 为 528 页的 Search API 使用 UF 开发一个实现 ,如同正文描述的那样
 *
 * @author LeonChen
 * @since 10/26/21
 */
class E4_1_08 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {

        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        SearchUnionFind searchUnionFind = new SearchUnionFind(graph, 1);
        StdOut.println("Count: " + searchUnionFind.count() + " Expected: 4");
        StdOut.println("Connected to 0: " + searchUnionFind.marked(0) + " Expected: true");
        StdOut.println("Connected to 1: " + searchUnionFind.marked(1) + " Expected: true");
        StdOut.println("Connected to 2: " + searchUnionFind.marked(2) + " Expected: true");
        StdOut.println("Connected to 3: " + searchUnionFind.marked(3) + " Expected: true");
        StdOut.println("Connected to 4: " + searchUnionFind.marked(4) + " Expected: false");


    }

    public static class SearchUnionFind {

        private QuickUnionUF unionUF;
        private int sourceVertex;

        SearchUnionFind(Graph graph, int sourceVertex) {
            unionUF = new QuickUnionUF(graph.V());
            this.sourceVertex = sourceVertex;

            for (int vertex = 0; vertex < graph.V(); vertex++) {
                for (int neighbor : graph.adj(vertex)) {
                    unionUF.union(vertex, neighbor);
                }
            }
        }

        public boolean marked(int vertex) {
            return unionUF.connected(sourceVertex, vertex);
        }

        public int count() {
            int sourceVertexLeader = unionUF.find(sourceVertex);
            return unionUF.getParent()[sourceVertexLeader];
        }

    }


}
