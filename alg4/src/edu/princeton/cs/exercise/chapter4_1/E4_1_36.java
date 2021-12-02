package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * 4.1.36 Edge connectivity. A bridge in a graph is an edge that, if removed, would
 * separate a connected graph into two disjoint subgraphs. A graph that has no bridges is
 * said to be edge connected. Develop a DFS-based data type for determing whether a given
 * graph is edge connected.
 * <p>
 * 边连接.图中的桥指的是,如果删除它,会将一个连接图分割成两个不相交的子图.一个图没有桥被叫做边连接的.开发一个
 * 基于DFS数据类型来判断一个给定图是否是边连接的.
 *
 * @author LeonChen
 * @since 11/14/21
 */
class E4_1_36 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {

        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 0);

        List<Edge> bridges1 = findBridges(graph1);

        if (bridges1.size() == 0) {
            StdOut.println("Graph is two-edge connected");
        } else {
            StdOut.println("Bridges");

            for (Edge edge : bridges1) {
                StdOut.println(edge.vertex1 + "-" + edge.vertex2);
            }
        }
        StdOut.println("Expected: Graph is two-edge connected\n");

        Graph graph2 = new Graph(6);
        graph2.addEdge(0, 1);
        graph2.addEdge(2, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(3, 5);
        graph2.addEdge(4, 5);
        graph2.addEdge(3, 4);
        graph2.addEdge(1, 5);

        List<Edge> bridges2 = findBridges(graph2);

        if (bridges2.size() == 0) {
            StdOut.println("Graph is two-edge connected");
        } else {
            StdOut.println("Bridges");

            for (Edge edge : bridges2) {
                StdOut.println(edge.vertex1 + "-" + edge.vertex2);
            }
        }
        StdOut.println("Expected: 1-5\n");

        Graph graph3 = new Graph(7);
        graph3.addEdge(0, 1);
        graph3.addEdge(2, 1);
        graph3.addEdge(0, 2);
        graph3.addEdge(3, 6);
        graph3.addEdge(4, 6);
        graph3.addEdge(3, 4);
        graph3.addEdge(1, 5);
        graph3.addEdge(5, 6);

        List<Edge> bridges3 = findBridges(graph3);

        if (bridges3.size() == 0) {
            StdOut.println("Graph is two-edge connected");
        } else {
            StdOut.println("Bridges");

            for (Edge edge : bridges3) {
                StdOut.println(edge.vertex1 + "-" + edge.vertex2);
            }
        }
        StdOut.println("Expected: 5-6");
        StdOut.println("Expected: 1-5");
    }

    private static class Edge {
        int vertex1;
        int vertex2;

        Edge(int vertex1, int vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }
    }

    private static int count;

    private static int[] low; // low[v] = lowest preorder of any vertex connected to v
    private static int[] time; // time[v] = order in which dfs examines v

    private static List<Edge> findBridges(Graph graph) {
        low = new int[graph.V()];
        time = new int[graph.V()];

        List<Edge> bridges = new ArrayList<>();

        for (int vertex = 0; vertex < graph.V(); vertex++) {
            low[vertex] = -1;
            time[vertex] = -1;
        }

        for (int vertex = 0; vertex < graph.V(); vertex++) {
            if (time[vertex] == -1) {
                dfs(graph, vertex, vertex, bridges);
            }
        }

        return bridges;
    }

    private static void dfs(Graph graph, int currentVertex, int sourceVertex,
                            List<Edge> bridges) {
        time[currentVertex] = count;
        low[currentVertex] = count;
        count++;

        for (int neighbor : graph.adj(currentVertex)) {
            if (time[neighbor] == -1) {
                dfs(graph, neighbor, currentVertex, bridges);

                low[currentVertex] = Math.min(low[currentVertex], low[neighbor]);

                if (low[neighbor] == time[neighbor]) {
                    bridges.add(new Edge(currentVertex, neighbor));
                }

            } else if (neighbor != sourceVertex) {
                low[currentVertex] = Math.min(low[currentVertex], time[neighbor]);
            }
        }
    }


}
