package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.exercise.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 4.1.44 Real-world graphs. Find a large weighted graph on the web—perhaps a map
 * with distances, telephone connections with costs, or an airline rate schedule. Write a
 * program RandomRealGraph that builds a graph by choosing V vertices at random and
 * E edges at random from the subgraph induced by those vertices.
 * <p>
 * 真实世界的图.在网络上找到一个巨大的加权图可能是距离图,电话连接成本图,或者是飞机相关行程图.编写一个程序
 * RandomRealGraph 通过随机选择 V 个顶点和E 条边的子图来构建一个图.
 *
 * @author LeonChen
 * @since 11/23/21
 */
class E4_1_44 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
//        int randomVerticesToChoose = Integer.parseInt(args[0]);
//        int randomEdgesToChoose = Integer.parseInt(args[1]);
        int randomVerticesToChoose = 60;
        int randomEdgesToChoose = 20;

        Graph randomRealGraph =
                randomRealGraph(randomVerticesToChoose, randomEdgesToChoose);

        StdOut.println(randomRealGraph);
    }

    private static class Graph {
        private int vertices;
        private int edges;
        private SeparateChainingHashST<Integer, Bag<Integer>> adjacent;

        public Graph() {
            vertices = 0;
            edges = 0;
            adjacent = new SeparateChainingHashST<>();
        }

        public int vertices() {
            return vertices;
        }

        public int edges() {
            return edges;
        }

        public void addVertex(int vertex) {
            if (!adjacent.contains(vertex)) {
                adjacent.put(vertex, new Bag<>());
                vertices++;
            }
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

        public Iterable<Integer> adjacent(int vertex) {
            return adjacent.get(vertex);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            for (int vertex = 0; vertex < vertices(); vertex++) {
                stringBuilder.append(vertex).append(": ");

                if (adjacent(vertex) != null) {
                    for (Integer neighbor : adjacent(vertex)) {
                        stringBuilder.append(neighbor).append(" ");
                    }
                }

                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
    }

    private static class Edge {
        int vertex1;
        int vertex2;

        Edge(int vertex1, int vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }
    }

    private static Graph randomRealGraph(int randomVerticesToChoose,
                                         int randomEdgesToChoose) {
        String filePath = Constants.FILES_PATH + Constants.US_AIR_FILE;
        String separator = " ";

        In in = new In(filePath);
        String[] firstLine = in.readLine().split(separator);
        int vertices = Integer.parseInt(firstLine[0]);
        int edges = Integer.parseInt(firstLine[2]);

        Graph fullGraph = new Graph();

        for (int edge = 0; edge < edges; edge++) {
            String[] connection = in.readLine().split(separator);

            int city1 = Integer.parseInt(connection[0]);
            int city2 = Integer.parseInt(connection[1]);
            double distance = Double.parseDouble(connection[2]); // Not used in this
            // exercise

            fullGraph.addEdge(city1, city2);
        }

        Graph randomSubGraph = new Graph();
        SeparateChainingHashST<Integer, Integer> graphToSubGraphMap =
                new SeparateChainingHashST<>();

        List<Edge> allSubGraphEdges = new ArrayList<>();

        while (graphToSubGraphMap.size() < randomVerticesToChoose) {
            // Randomly choose a vertex between 1 and vertices
            int randomVertexId = 1 + StdRandom.uniform(vertices);

            if (graphToSubGraphMap.contains(randomVertexId)) {
                continue;
            }

            int subGraphVertexId1 = graphToSubGraphMap.size();
            graphToSubGraphMap.put(randomVertexId, subGraphVertexId1);

            randomSubGraph.addVertex(subGraphVertexId1);

            for (Integer neighbor : fullGraph.adjacent(randomVertexId)) {
                if (graphToSubGraphMap.contains(neighbor)) {
                    int subGraphVertexId2 = graphToSubGraphMap.get(neighbor);
                    allSubGraphEdges.add(new Edge(subGraphVertexId1, subGraphVertexId2));
                }
            }
        }

        // Randomly choose E edges from the subgraph induced by the random vertices
        if (randomEdgesToChoose > allSubGraphEdges.size()) {
            throw new IllegalArgumentException("Not enough edges to choose from the " +
                    "induced subgraph");
        }

        // Randomly choose edges
        for (int edgeIndex = 0; edgeIndex < randomEdgesToChoose; edgeIndex++) {
            int randomEdgeId = StdRandom.uniform(edgeIndex, allSubGraphEdges.size());

            Edge randomEdge = allSubGraphEdges.get(randomEdgeId);
            allSubGraphEdges.set(randomEdgeId, allSubGraphEdges.get(edgeIndex));
            allSubGraphEdges.set(edgeIndex, randomEdge);

            randomSubGraph.addEdge(randomEdge.vertex1, randomEdge.vertex2);
        }
        return randomSubGraph;
    }


}
