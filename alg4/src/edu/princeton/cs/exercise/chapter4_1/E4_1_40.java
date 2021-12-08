package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;

/**
 * 4.1.40 Random simple graphs. Write a program RandomSimpleGraph that takes
 * integer values V and E from the command line and produces, with equal likelihood,
 * each of the possible simple graphs with V vertices and E edges.
 * <p>
 * 随机简单图.编写一个程序 RandomSimpleGraph 从命令行接收两个整数 V 和 E 并以相同的可能性产生,每种 V 个
 * 顶点和 E 条边的简单图.
 *
 * @author LeonChen
 * @since 11/19/21
 */
class E4_1_40 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
//        int vertices = Integer.parseInt(args[0]);
//        int edges = Integer.parseInt(args[1]);

        int vertices = 10;
        int edges = 14;

        Graph randomSimpleGraph = randomSimpleGraph(vertices, edges);
        StdOut.println(randomSimpleGraph);

    }

    public static class Graph {

        private final int vertices;
        private int edges;
        private HashSet<Integer>[] adjacent;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.edges = 0;
            adjacent = (HashSet<Integer>[]) new HashSet[vertices];

            for (int vertex = 0; vertex < vertices; vertex++) {
                adjacent[vertex] = new HashSet<>();
            }
        }

        public int vertices() {
            return vertices;
        }

        public int edges() {
            return edges;
        }

        public void addEdge(int vertex1, int vertex2) {
            adjacent[vertex1].add(vertex2);
            adjacent[vertex2].add(vertex1);
            edges++;
        }

        public int degree(int vertex) {
            return adjacent[vertex].size();
        }

        public Iterable<Integer> adjacent(int vertex) {
            return adjacent[vertex];
        }

        public HashSet<Integer> adjacentSetOfValues(int vertex) {
            return adjacent[vertex];
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            for (int vertex = 0; vertex < vertices(); vertex++) {
                stringBuilder.append(vertex).append(": ");

                for (int neighbor : adjacent(vertex)) {
                    stringBuilder.append(neighbor).append(" ");
                }
                stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        }

    }

    public static Graph randomSimpleGraph(int vertices, int edges) {
        // A complete graph has n * (n - 1) / 2 edges
        int maxNumberOfEdges = (vertices * (vertices - 1)) / 2;

        if (edges > maxNumberOfEdges) {
            throw new IllegalArgumentException("The maximum number of edges a simple " +
                    "graph with " + vertices
                    + " vertices may have is " + maxNumberOfEdges);
        }

        Graph randomSimpleGraph = new Graph(vertices);

        while (randomSimpleGraph.edges() < edges) {
            int vertexId1 = StdRandom.uniform(vertices);
            int vertexId2 = StdRandom.uniform(vertices);

            if (vertexId1 != vertexId2
                    && !randomSimpleGraph.adjacentSetOfValues(vertexId1).contains(vertexId2)) {
                randomSimpleGraph.addEdge(vertexId1, vertexId2);
            }
        }
        return randomSimpleGraph;
    }

}
