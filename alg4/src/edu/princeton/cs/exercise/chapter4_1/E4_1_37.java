package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.*;

/**
 * 4.1.37 Euclidean graphs. Design and implement an API EuclideanGraph for graphs
 * whose vertices are points in the plane that include coordinates. Include a method
 * show() that uses StdDraw to draw the graph.
 * <p>
 * 欧几里得图.设计并实现一个 API EuclideanGraph 给那些顶点包含了坐标的图.包括一个方法 show() 使用
 * StdDraw 来绘制图
 *
 * @author LeonChen
 * @since 11/14/21
 */
class E4_1_37 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        EuclideanGraph euclideanGraph = new EuclideanGraph(7);

        EuclideanGraph.Vertex vertex0 = new EuclideanGraph.Vertex(0, 6.1, 1.3);
        EuclideanGraph.Vertex vertex1 = new EuclideanGraph.Vertex(1, 7.2, 2.5);
        EuclideanGraph.Vertex vertex2 = new EuclideanGraph.Vertex(2, 8.4, 1.3);
        EuclideanGraph.Vertex vertex3 = new EuclideanGraph.Vertex(3, 8.4, 15.3);
        EuclideanGraph.Vertex vertex4 = new EuclideanGraph.Vertex(4, 6.1, 15.3);
        EuclideanGraph.Vertex vertex5 = new EuclideanGraph.Vertex(5, 7.2, 5.2);
        EuclideanGraph.Vertex vertex6 = new EuclideanGraph.Vertex(6, 7.2, 8.4);

        euclideanGraph.addVertex(vertex0);
        euclideanGraph.addVertex(vertex1);
        euclideanGraph.addVertex(vertex2);
        euclideanGraph.addVertex(vertex3);
        euclideanGraph.addVertex(vertex4);
        euclideanGraph.addVertex(vertex5);
        euclideanGraph.addVertex(vertex6);

        euclideanGraph.addEdge(0, 1);
        euclideanGraph.addEdge(2, 1);
        euclideanGraph.addEdge(0, 2);
        euclideanGraph.addEdge(3, 6);
        euclideanGraph.addEdge(4, 6);
        euclideanGraph.addEdge(3, 4);
        euclideanGraph.addEdge(1, 5);
        euclideanGraph.addEdge(5, 6);

        euclideanGraph.show(0, 15, -2, 18, 0.5);
        StdOut.println(euclideanGraph);
    }

    public static class EuclideanGraph {

        public static class Vertex {
            protected int id;
            protected String name;
            protected double xCoordinate;
            protected double yCoordinate;

            Vertex(int id, double xCoordinate, double yCoordinate) {
                this(id, String.valueOf(id), xCoordinate, yCoordinate);
            }

            Vertex(int id, String name, double xCoordinate, double yCoordinate) {
                this.id = id;
                this.name = name;
                this.xCoordinate = xCoordinate;
                this.yCoordinate = yCoordinate;
            }

            public void updateName(String name) {
                this.name = name;
            }
        }

        private final int vertices;
        private int edges;
        private Vertex[] allVertices;
        private Bag<Integer>[] adjacent;

        public EuclideanGraph(int vertices) {
            this.vertices = vertices;
            this.edges = 0;
            allVertices = new Vertex[vertices];
            adjacent = (Bag<Integer>[]) new Bag[vertices];

            for (int vertex = 0; vertex < vertices; vertex++) {
                adjacent[vertex] = new Bag<>();
            }
        }

        public int vertices() {
            return vertices;
        }

        public int edges() {
            return edges;
        }

        public void addVertex(Vertex vertex) {
            allVertices[vertex.id] = vertex;
        }

        public void addEdge(int vertexId1, int vertexId2) {
            if (allVertices[vertexId1] == null || allVertices[vertexId2] == null) {
                throw new IllegalArgumentException("Vertex id not found");
            }

            adjacent[vertexId1].add(vertexId2);
            adjacent[vertexId2].add(vertexId1);
            edges++;
        }

        public void show(double xScaleLow, double xScaleHigh, double yScaleLow,
                         double yScaleHigh, double radiusOfCircleAroundVertex) {
            StdDraw.setCanvasSize(500, 400);
            StdDraw.setXscale(xScaleLow, xScaleHigh);
            StdDraw.setYscale(yScaleLow, yScaleHigh);

            StdDraw.setPenRadius(0.002D);
            StdDraw.setPenColor(Color.BLACK);

            for (int vertexId = 0; vertexId < vertices; vertexId++) {
                for (int neighbor : adjacent(vertexId)) {
                    Vertex neighborVertex = allVertices[neighbor];

                    if (neighbor >= vertexId) {
                        StdDraw.line(allVertices[vertexId].xCoordinate,
                                allVertices[vertexId].yCoordinate,
                                neighborVertex.xCoordinate, neighborVertex.yCoordinate);
                    }
                }
            }

            for (int vertexId = 0; vertexId < vertices; vertexId++) {
                if (allVertices[vertexId] != null) {

                    StdDraw.setPenColor(Color.WHITE);
                    StdDraw.filledCircle(allVertices[vertexId].xCoordinate,
                            allVertices[vertexId].yCoordinate,
                            radiusOfCircleAroundVertex);

                    StdDraw.setPenColor(Color.BLACK);
                    StdDraw.circle(allVertices[vertexId].xCoordinate,
                            allVertices[vertexId].yCoordinate,
                            radiusOfCircleAroundVertex);

                    StdDraw.setPenColor(Color.BLUE);
                    StdDraw.text(allVertices[vertexId].xCoordinate,
                            allVertices[vertexId].yCoordinate,
                            allVertices[vertexId].name);
                }
            }
        }

        public int degree(int vertex) {
            return adjacent[vertex].size();
        }

        public Iterable<Integer> adjacent(int vertexId) {
            return adjacent[vertexId];
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

}
