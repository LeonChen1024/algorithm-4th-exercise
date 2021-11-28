package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 4.1.30 Eulerian and Hamiltonian cycles. Consider the graphs defined by the following
 * four sets of edges:
 * 0-1 0-2 0-3 1-3 1-4 2-5 2-9 3-6 4-7 4-8 5-8 5-9 6-7 6-9 7-8
 * 0-1 0-2 0-3 1-3 0-3 2-5 5-6 3-6 4-7 4-8 5-8 5-9 6-7 6-9 8-8
 * 0-1 1-2 1-3 0-3 0-4 2-5 2-9 3-6 4-7 4-8 5-8 5-9 6-7 6-9 7-8
 * 4-1 7-9 6-2 7-3 5-0 0-2 0-8 1-6 3-9 6-3 2-8 1-5 9-8 4-5 4-7
 * Which of these graphs have Euler cycles (cycles that visit each edge exactly once)?
 * Which of them have Hamilton cycles (cycles that visit each vertex exactly once)?
 * <p>
 * Eulerian 和 Hamiltonian 环.思考下面 4 中边集合构成的图
 * <p>
 * 哪些图有 Euler cycles (每个边只访问一次)?哪些有 Hamilton环(每个顶点只访问一次)?
 *
 * @author LeonChen
 * @since 11/8/21
 */
class E4_1_30 {

    /**
     * @formatter:off
     * Euler cycles 所有顶点必须有偶数度数,否则会重复访问
     * @formatter:on
     */
    public static void main(String[] args) {
        EulerCycle eulerCycle = new EulerCycle();

        Graph graphWithEulerPath1 = new Graph(4);
        graphWithEulerPath1.addEdge(0, 1);
        graphWithEulerPath1.addEdge(1, 2);
        graphWithEulerPath1.addEdge(2, 3);
        graphWithEulerPath1.addEdge(3, 0);
        graphWithEulerPath1.addEdge(3, 2);

        Stack<Integer> eulerCycle1 = eulerCycle.getEulerCycle(graphWithEulerPath1);

        if (eulerCycle1 != null) {
            printCycle(eulerCycle1);
        } else {
            StdOut.println("There is no Eulerian cycle");
        }
        StdOut.println("Expected: There is no Eulerian cycle\n");

        Graph graphWithEulerCycle1 = new Graph(4);
        graphWithEulerCycle1.addEdge(0, 1);
        graphWithEulerCycle1.addEdge(1, 2);
        graphWithEulerCycle1.addEdge(2, 3);
        graphWithEulerCycle1.addEdge(3, 0);

        Stack<Integer> eulerCycle2 = eulerCycle.getEulerCycle(graphWithEulerCycle1);

        if (eulerCycle2 != null) {
            printCycle(eulerCycle2);
        } else {
            StdOut.println("There is no Eulerian cycle");
        }
        StdOut.println("Expected: 0-3 3-2 2-1 1-0\n");

        //Note that vertex 12 is an isolated vertex
        Graph graphWithEulerCycle2 = new Graph(13);
        graphWithEulerCycle2.addEdge(0, 9);
        graphWithEulerCycle2.addEdge(0, 3);
        graphWithEulerCycle2.addEdge(10, 9);
        graphWithEulerCycle2.addEdge(10, 3);
        graphWithEulerCycle2.addEdge(3, 4);
        graphWithEulerCycle2.addEdge(3, 6);
        graphWithEulerCycle2.addEdge(3, 2);
        graphWithEulerCycle2.addEdge(3, 9);
        graphWithEulerCycle2.addEdge(9, 6);
        graphWithEulerCycle2.addEdge(9, 8);
        graphWithEulerCycle2.addEdge(9, 11);
        graphWithEulerCycle2.addEdge(4, 2);
        graphWithEulerCycle2.addEdge(6, 2);
        graphWithEulerCycle2.addEdge(6, 8);
        graphWithEulerCycle2.addEdge(11, 8);
        graphWithEulerCycle2.addEdge(2, 5);
        graphWithEulerCycle2.addEdge(2, 1);
        graphWithEulerCycle2.addEdge(2, 8);
        graphWithEulerCycle2.addEdge(8, 5);
        graphWithEulerCycle2.addEdge(8, 7);
        graphWithEulerCycle2.addEdge(1, 7);

        Stack<Integer> eulerCycle3 = eulerCycle.getEulerCycle(graphWithEulerCycle2);

        if (eulerCycle3 != null) {
            printCycle(eulerCycle3);
        } else {
            StdOut.println("There is no Eulerian cycle");
        }
        StdOut.println("Expected: 0-3 3-2 2-1 1-7 7-8 8-2 2-5 5-8 8-6 6-2 2-4 4-3 3-9 " +
                "9-6 6-3 3-10 10-9 9-8 8-11 11-9 9-0\n");

        Graph graphWithEulerPath2 = new Graph(4);
        graphWithEulerPath2.addEdge(0, 1);
        graphWithEulerPath2.addEdge(1, 2);
        graphWithEulerPath2.addEdge(2, 3);
        graphWithEulerPath2.addEdge(3, 0);
        graphWithEulerPath2.addEdge(3, 1);

        Stack<Integer> eulerCycle4 = eulerCycle.getEulerCycle(graphWithEulerPath2);

        if (eulerCycle4 != null) {
            printCycle(eulerCycle4);
        } else {
            StdOut.println("There is no Eulerian cycle");
        }
        StdOut.println("Expected: There is no Eulerian cycle");
    }

    private static class Edge {
        int vertex1;
        int vertex2;
        boolean isUsed;

        Edge(int vertex1, int vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            isUsed = false;
        }

        public int otherVertex(int vertex) {
            if (vertex == vertex1) {
                return vertex2;
            } else {
                return vertex1;
            }
        }
    }

    public static class EulerCycle {

        public Stack<Integer> getEulerCycle(Graph graph) {

            // A graph with no edges is considered to have an Eulerian cycle
            if (graph.E() == 0) {
                return new Stack<>();
            }

            // Necessary condition: all vertices have even degree
            // (this test is needed or it might find an Eulerian path instead of an
            // Eulerian cycle)
            // An Eulerian path have exactly 2 vertices with even degrees
            for (int vertex = 0; vertex < graph.V(); vertex++) {
                if (graph.degree(vertex) % 2 != 0) {
                    return null;
                }
            }

            // Create local view of adjacency lists, to iterate one vertex at a time
            Queue<Edge>[] adjacent = (Queue<Edge>[]) new Queue[graph.V()];
            for (int vertex = 0; vertex < graph.V(); vertex++) {
                adjacent[vertex] = new Queue<>();
            }

            for (int vertex = 0; vertex < graph.V(); vertex++) {
                int selfLoops = 0;

                for (int neighbor : graph.adj(vertex)) {
                    //Careful with self-loops
                    if (vertex == neighbor) {
                        if (selfLoops % 2 == 0) {
                            Edge edge = new Edge(vertex, neighbor);
                            adjacent[vertex].enqueue(edge);
                            adjacent[neighbor].enqueue(edge);
                        }

                        selfLoops++;
                    } else {
                        if (vertex < neighbor) {
                            Edge edge = new Edge(vertex, neighbor);
                            adjacent[vertex].enqueue(edge);
                            adjacent[neighbor].enqueue(edge);
                        }
                    }
                }
            }

            //Start the cycle with a non-isolated vertex
            int nonIsolatedVertex = nonIsolatedVertex(graph);
            Stack<Integer> dfsStack = new Stack<>();
            dfsStack.push(nonIsolatedVertex);

            Stack<Integer> eulerCycle = new Stack<>();

            while (!dfsStack.isEmpty()) {
                int vertex = dfsStack.pop();

                while (!adjacent[vertex].isEmpty()) {
                    Edge edge = adjacent[vertex].dequeue();
                    if (edge.isUsed) {
                        continue;
                    }
                    edge.isUsed = true;

                    dfsStack.push(vertex);
                    vertex = edge.otherVertex(vertex);
                }

                // Push vertex with no more leaving edges to the Euler cycle
                eulerCycle.push(vertex);
            }

            // For each edge visited, we visited a vertex. Add 1 because the first and
            // last vertices are the same.
            if (eulerCycle.size() == graph.E() + 1) {
                return eulerCycle;
            } else {
                return null;
            }
        }

        private int nonIsolatedVertex(Graph graph) {
            int nonIsolatedVertex = -1;

            for (int vertex = 0; vertex < graph.V(); vertex++) {
                if (graph.degree(vertex) > 0) {
                    nonIsolatedVertex = vertex;
                    break;
                }
            }

            return nonIsolatedVertex;
        }
    }

    private static void printCycle(Stack<Integer> eulerCycle) {
        StdOut.println("Euler cycle:");

        while (!eulerCycle.isEmpty()) {
            int vertex = eulerCycle.pop();

            if (!eulerCycle.isEmpty()) {
                StdOut.print(vertex + "-" + eulerCycle.peek());

                if (eulerCycle.size() > 1) {
                    StdOut.print(" ");
                }
            }
        }
        StdOut.println();
    }


}
