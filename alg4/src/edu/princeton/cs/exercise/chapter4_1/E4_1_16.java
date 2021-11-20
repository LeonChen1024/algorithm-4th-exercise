package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.CC;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

/**
 * 4.1.16 The eccentricity of a vertex v is the the length of the shortest path from that
 * vertex to the furthest vertex from v. The diameter of a graph is the maximum eccentricity
 * of any vertex. The radius of a graph is the smallest eccentricity of any vertex. A
 * center is a vertex whose eccentricity is the radius. Implement the following API:
 * <p>
 * /                public class GraphProperties
 * GraphProperties(Graph G)                 constructor (exception if G not connected)
 * int eccentricity(int v)                  eccentricity of v
 * int diameter()                           diameter of G
 * int radius()                             radius of G
 * int center()                             a center of G
 * <p>
 * 偏心率是一个顶 v 到最远顶点的最短路径长度.图直径是任意顶点的最大偏心率.图半径是任意顶点的最小偏心率.中心
 * 是一个顶点的偏心率等于半径.实现以下 API:
 *
 * @author LeonChen
 * @since 10/31/21
 */
class E4_1_16 {

    /**
     * @formatter:off
     *
     * @formatter:on
     */
    public static void main(String[] args) {
        //Graph
        // 0 -- 1 -- 2 -- 3 -- 4 -- 5 -- 6 -- 7 -- 8 -- 9 -- 10

        Graph graph = new Graph(11);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);
        graph.addEdge(9, 10);

        GraphProperties graphProperties = new GraphProperties(graph);
        StdOut.println("Diameter: " + graphProperties.diameter() + " Expected: 10");
        StdOut.println("Radius: " + graphProperties.radius() + " Expected: 5");
        StdOut.println("Center: " + graphProperties.center() + " Expected: 5");
    }

    public static class GraphProperties {

        private int[] eccentricities;
        private int diameter;
        private int radius;
        private int center;

        GraphProperties(Graph graph) {
            eccentricities = new int[graph.V()];


            CC cc = new CC(graph);

            if (cc.count() != 1) {
                throw new RuntimeException("Graph must be connected");
            }

            getProperties(graph);
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

        public int diameter() {
            return diameter;
        }

        public int radius() {
            return radius;
        }

        public int center() {
            return center;
        }
    }


}
