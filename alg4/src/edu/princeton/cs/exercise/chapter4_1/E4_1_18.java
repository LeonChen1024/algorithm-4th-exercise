package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.*;

/**
 * 4.1.18 The girth of a graph is the length of its shortest cycle. If a graph is acyclic,
 * then its girth is infinite. Add a method girth() to GraphProperties that returns the
 * girth of the graph. Hint : Run BFS from each vertex. The shortest cycle containing s
 * is a shortest path from s to some vertex v, plus the edge from v back to s.
 * <p>
 * 图的周长是循环的最短长度.如果图是无环的,那么它的长度是无限的.添加一个方法 girth() 到GraphProperties
 * 返回图的周长.提示: 从每个顶点运行 BFS .最短循环包含 s 是从 s 到顶点 v,加上 v 返回 s.
 *
 * @author LeonChen
 * @since 11/1/21
 */
class E4_1_18 {

    /**
     * @formatter:off
     * 记录访问点和边的连接点,当碰到一个已访问但是连接点不是当前点的时候,就是找到循环了
     * @formatter:on
     */
    public static void main(String[] args) {
        // Graph with girth = 3
        Graph graph1 = new Graph(6);
        graph1.addEdge(2, 3);
        graph1.addEdge(0, 1);
        graph1.addEdge(3, 1);
        graph1.addEdge(5, 3);
        graph1.addEdge(2, 0);
        graph1.addEdge(1, 2);
        graph1.addEdge(4, 2);
        graph1.addEdge(4, 5);
        graph1.addEdge(4, 0);

        // Graph with girth = 2
        Graph graph2 = new Graph(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 0); //Parallel edge
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);

        // Graph with girth = 4
        Graph graph3 = new Graph(4);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 2);
        graph3.addEdge(2, 3);
        graph3.addEdge(3, 0);

        // Graph with girth = Integer.MAX_VALUE
        Graph graph4 = new Graph(4);
        graph4.addEdge(0, 1);
        graph4.addEdge(1, 3);
        graph4.addEdge(2, 3);

        GraphProperties graphProperties1 = new GraphProperties(graph1, false);
        StdOut.println("Girth 1: " + graphProperties1.girth() + " Expected: 3");

        GraphProperties graphProperties2 = new GraphProperties(graph2, false);
        StdOut.println("Girth 2: " + graphProperties2.girth() + " Expected: 2");

        GraphProperties graphProperties3 = new GraphProperties(graph3, false);
        StdOut.println("Girth 3: " + graphProperties3.girth() + " Expected: 4");

        GraphProperties graphProperties4 = new GraphProperties(graph4, false);
        StdOut.println("Girth 4: " + graphProperties4.girth() + " Expected: 2147483647");

    }

    public static class GraphProperties {

        private int[] eccentricities;
        private int diameter;
        private int radius;
        private int center;

        private int girth = Integer.MAX_VALUE;

        GraphProperties(Graph graph, boolean useIterativeDFS) {
            eccentricities = new int[graph.V()];

            CC connectedComponents;

            if (useIterativeDFS) {
                connectedComponents = new CC(graph);
            } else {
                connectedComponents = new CC(graph);
            }

            if (connectedComponents.count() != 1) {
                throw new RuntimeException("Graph must be connected");
            }

            getProperties(graph);
            computeGirth(graph);
        }

        //O(V * (V + E))
        private void getProperties(Graph graph) {
            diameter = 0;
            radius = Integer.MAX_VALUE;
            center = 0;

            for (int vertex = 0; vertex < graph.V(); vertex++) {
                BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, vertex);

                for (int otherVertex = 0; otherVertex < graph.V(); otherVertex++) {
                    if (otherVertex == vertex) {
                        continue;
                    }

                    eccentricities[vertex] = Math.max(eccentricities[vertex], breadthFirstPaths.distTo(otherVertex));
                }

                if (eccentricities[vertex] > diameter) {
                    diameter = eccentricities[vertex];
                }
                if (eccentricities[vertex] < radius) {
                    radius = eccentricities[vertex];
                    center = vertex;
                }
            }
        }

        //O(V * (V + E))
        private void computeGirth(Graph graph) {
            for (int vertex = 0; vertex < graph.V(); vertex++) {
                int shortestCycle = bfsToGetShortestCycle(graph, vertex);
                girth = Math.min(girth, shortestCycle);
            }
        }

        private int bfsToGetShortestCycle(Graph graph, int sourceVertex) {
            int shortestCycle = Integer.MAX_VALUE;
            int[] distTo = new int[graph.V()];
            int[] edgeTo = new int[graph.V()];

            Queue<Integer> queue = new Queue<>();
            boolean[] visited = new boolean[graph.V()];

            visited[sourceVertex] = true;
            edgeTo[sourceVertex] = Integer.MAX_VALUE;
            queue.enqueue(sourceVertex);

            while (!queue.isEmpty()) {
                int currentVertex = queue.dequeue();

                for (int neighbor : graph.adj(currentVertex)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        distTo[neighbor] = distTo[currentVertex] + 1;
                        edgeTo[neighbor] = currentVertex;
                        queue.enqueue(neighbor);
                    } else if (neighbor != edgeTo[currentVertex]) {
                        // Cycle found
                        int cycleLength = distTo[currentVertex] + distTo[neighbor] + 1;
                        shortestCycle = Math.min(shortestCycle, cycleLength);
                    }
                }
            }

            return shortestCycle;
        }

        public int diameter() {
            return diameter;
        }

        public int radius() {
            return radius;
        }

        public int center() {
            return center;
        }

        public int girth() {
            return girth;
        }

        public int eccentricity(int vertexId) {
            return eccentricities[vertexId];
        }
    }


}
