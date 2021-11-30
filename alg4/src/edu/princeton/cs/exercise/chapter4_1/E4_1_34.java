package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.exercise.util.Constants;

/**
 * 4.1.34 Symbol graph. Implement a one-pass SymbolGraph (it need not be a Graph
 * client). Your implementation may pay an extra log V factor for graph operations, for
 * symbol-table lookups.
 * <p>
 * 符号图.实现一个单次循环的 SymbolGraph (它不需要是一个图客户端). 你的实现可能在图操作查找中需要额外的
 * logV 系数.
 *
 * @author LeonChen
 * @since 11/12/21
 */
class E4_1_34 {

    /**
     * @formatter:off
     * 将两个映射表在一次循环中生成
     * @formatter:on
     */
    public static void main(String[] args) {
        String filePath = Constants.FILES_PATH + Constants.MOVIES_FILE;
        String separator = "/";

        SymbolGraphOnePass symbolGraph =
                new SymbolGraphOnePass(filePath, separator);
        Graph graph = symbolGraph.graph();

        StdOut.println("Vertices: " + graph.vertices() + " Expected: 119429");
        StdOut.println("Edges: " + graph.edges() + " Expected: 202927");
    }

    private static class Graph {

        private int vertices;
        private int edges;
        private SeparateChainingHashST<Integer, Bag<Integer>> adjacent;

        public Graph() {
            this.vertices = 0;
            this.edges = 0;
            adjacent = new SeparateChainingHashST<>();
        }

        public Graph(In in) {
            this();
            int edges = in.readInt();

            for (int i = 0; i < edges; i++) {
                int vertex1 = in.readInt();
                int vertex2 = in.readInt();
                addEdge(vertex1, vertex2);
            }
        }

        public int vertices() {
            return vertices;
        }

        public int edges() {
            return edges;
        }

        public void addEdge(int vertex1, int vertex2) {
            if (!adjacent.contains(vertex1)) {
                adjacent.put(vertex1, new Bag<>());
                vertices++;
            }
            if (!adjacent.contains(vertex2)) {
                adjacent.put(vertex2, new Bag<>());
                vertices++;
            }

            adjacent.get(vertex1).add(vertex2);
            adjacent.get(vertex2).add(vertex1);
            edges++;
        }

        public int degree(int vertex) {
            return adjacent.get(vertex).size();
        }

        public Iterable<Integer> adjacent(int vertex) {
            return adjacent.get(vertex);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            for (int vertex : adjacent.keys()) {
                stringBuilder.append(vertex).append(": ");

                for (int neighbor : adjacent(vertex)) {
                    stringBuilder.append(neighbor).append(" ");
                }
                stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        }
    }

    public static class SymbolGraphOnePass {

        private SeparateChainingHashST<String, Integer> vertexNameToIdMap;
        private String[] keys;
        private Graph graph;

        public SymbolGraphOnePass(String stream, String separator) {
            vertexNameToIdMap = new SeparateChainingHashST<>();
            graph = new Graph();

            In in = new In(stream);

            while (in.hasNextLine()) {
                String[] vertices = in.readLine().split(separator);

                // Create vertices
                for (int i = 0; i < vertices.length; i++) {
                    if (!vertexNameToIdMap.contains(vertices[i])) {
                        vertexNameToIdMap.put(vertices[i], vertexNameToIdMap.size());
                    }
                }

                // Add edges
                int vertex = vertexNameToIdMap.get(vertices[0]);
                for (int i = 1; i < vertices.length; i++) {
                    graph.addEdge(vertex, vertexNameToIdMap.get(vertices[i]));
                }
            }

            keys = new String[vertexNameToIdMap.size()];

            for (String vertexName : vertexNameToIdMap.keys()) {
                keys[vertexNameToIdMap.get(vertexName)] = vertexName;
            }
        }

        public boolean contains(String vertexName) {
            return vertexNameToIdMap.contains(vertexName);
        }

        public int index(String vertexName) {
            return vertexNameToIdMap.get(vertexName);
        }

        public String name(int vertexId) {
            return keys[vertexId];
        }

        public Graph graph() {
            return graph;
        }

    }

}
