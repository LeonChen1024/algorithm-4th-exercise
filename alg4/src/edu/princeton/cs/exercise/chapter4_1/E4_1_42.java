package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.exercise.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 4.1.42 Random Euclidean graphs. Write a EuclideanGraph client (see Exercise 4.1.37)
 * RandomEuclideanGraph that produces random graphs by generating V random points in
 * the plane, then connecting each point with all points that are within a circle
 * of radius d centered at that point. Note : The graph will almost certainly be
 * connected if d is larger than the threshold value √lgV/πV and almost certainly
 * disconnected if d is smaller than that value.
 * <p>
 * 随机欧几里得图. 编写一个 EuclideanGraph 客户端(见练习 4.1.37) RandomEuclideanGraph 通过生成 V
 * 个随机点生成随机图,然后连接每个点以该点为圆心的半径为 d 的点连起来. 注意:图几乎会被连接起来如果 d 是一个
 * 大于阈值√lgV/πV 并且如果 d 小于阈值则图几乎不连接
 *
 * @author LeonChen
 * @since 11/21/21
 */
class E4_1_42 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
//        int vertices = Integer.parseInt(args[0]);
//        double radius = Double.parseDouble(args[1]);
//        int numberOfGraphs = Integer.parseInt(args[2]);

        int vertices = 10;
        double radius = 5;
        int numberOfGraphs = 2;
        List<E4_1_37.EuclideanGraph> randomEuclideanGraphs =
                generateRandomEuclideanGraphs(numberOfGraphs, vertices, radius);

        E4_1_37.EuclideanGraph firstEuclideanGraph =
                randomEuclideanGraphs.get(0);
        firstEuclideanGraph.show(-0.1, 1.1, -0.1, 1.1, 0.03);

        UF unionFind = new UF(vertices);

        for (int vertex = 0; vertex < vertices; vertex++) {
            for (int neighbor : firstEuclideanGraph.adjacent(vertex)) {
                unionFind.union(vertex, neighbor);
            }
        }

        // Check theory that if "radius" is larger than threshold value SQRT(ln(V) /
        // (Math.PI * V)) then the graph is
        // almost certainly connected. Otherwise, it is almost certainly disconnected.
        double thresholdValue = Math.sqrt(Math.log(vertices) / (Math.PI * vertices));

        StdOut.println("Expected to be connected: " + (radius > thresholdValue));
        StdOut.println("Is connected: " + (unionFind.count() == 1));

    }

    public static List<E4_1_37.EuclideanGraph> generateRandomEuclideanGraphs(int numberOfGraphs,
                                                                             int vertices, double radius) {
        if (numberOfGraphs < 0) {
            throw new IllegalArgumentException("Number of graphs cannot be negative");
        }

        List<E4_1_37.EuclideanGraph> randomEuclideanGraphs =
                new ArrayList<>();
        for (int graph = 0; graph < numberOfGraphs; graph++) {
            E4_1_37.EuclideanGraph randomEuclideanGraph =
                    randomEuclideanGraph(vertices, radius);
            randomEuclideanGraphs.add(randomEuclideanGraph);
        }

        return randomEuclideanGraphs;
    }

    public static E4_1_37.EuclideanGraph randomEuclideanGraph(int vertices,
                                                              double radius) {
        E4_1_37.EuclideanGraph randomEuclideanGraph =
                new E4_1_37.EuclideanGraph(vertices);

        E4_1_37.EuclideanGraph.Vertex[] allVertices =
                new E4_1_37.EuclideanGraph.Vertex[vertices];

        for (int vertexId = 0; vertexId < vertices; vertexId++) {
            double randomXCoordinate = StdRandom.uniform();
            double randomYCoordinate = StdRandom.uniform();

            E4_1_37.EuclideanGraph.Vertex vertex =
                    new E4_1_37.EuclideanGraph.Vertex(vertexId, randomXCoordinate,
                            randomYCoordinate);
            allVertices[vertexId] = vertex;

            randomEuclideanGraph.addVertex(vertex);
        }

        for (int vertexId = 0; vertexId < vertices; vertexId++) {
            for (int otherVertex = vertexId + 1; otherVertex < vertices; otherVertex++) {
                double distance =
                        MathUtil.distanceBetweenPoints(allVertices[vertexId].xCoordinate,
                                allVertices[vertexId].yCoordinate,
                                allVertices[otherVertex].xCoordinate,
                                allVertices[otherVertex].yCoordinate);

                if (distance <= radius) {
                    randomEuclideanGraph.addEdge(vertexId, otherVertex);
                }
            }
        }

        return randomEuclideanGraph;
    }


}
