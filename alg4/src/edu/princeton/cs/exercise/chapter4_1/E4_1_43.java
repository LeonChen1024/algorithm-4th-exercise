package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.exercise.util.MathUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 4.1.43 Random grid graphs. Write a EuclideanGraph client RandomGridGraph that
 * generates random graphs by connecting vertices arranged in a √V-by-√V grid to their
 * neighbors (see Exercise 1.5.15). Augment your program to add R extra random edges.
 * For large R, shrink the grid so that the total number of edges remains about V. Add
 * an option such that an extra edge goes from a vertex s to a vertex t with probability
 * inversely proportional to the Euclidean distance between s and t.
 * <p>
 * 随机网格图.编写一个 EuclideanGraph 客户端 RandomGridGraph 通过连接一个 √V-by-√V 的网格顶点的邻居
 * 生成随机图(见练习 1.5.15).使你的程序添加额外 R 个随机边.对于大的 R,收缩网格使得总的边的数量保持 V.
 * 添加一个选项使得顶点 s 到顶点t 的额外边和s 到 t 欧几里得距离成反比
 *
 * @author LeonChen
 * @since 11/22/21
 */
class E4_1_43 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        //Parameters example: 9 5 100
        //                    16 40 100
//        int vertices = Integer.parseInt(args[0]);
//        int extraEdges = Integer.parseInt(args[1]);
//        int numberOfGraphs = Integer.parseInt(args[2]);
        int vertices = 9;
        int extraEdges = 5;
        int numberOfGraphs = 100;

        List<E4_1_37.EuclideanGraph> randomGridGraphs =
                generateRandomGridGraphs(numberOfGraphs, vertices, extraEdges);
        E4_1_37.EuclideanGraph firstRandomGridGraph =
                randomGridGraphs.get(0);
        firstRandomGridGraph.show(-0.1, 1.1, -0.1, 1.1, 0.03);

        StdOut.println(firstRandomGridGraph);
    }

    private static class Edge {
        int vertex1;
        int vertex2;

        Edge(int vertex1, int vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }
    }

    public static List<E4_1_37.EuclideanGraph> generateRandomGridGraphs(int numberOfGraphs,
                                                                        int vertices,
                                                                        int extraEdges) {
        if (numberOfGraphs < 0) {
            throw new IllegalArgumentException("Number of graphs cannot be negative");
        }

        List<E4_1_37.EuclideanGraph> randomGridGraphs =
                new ArrayList<>();

        for (int graph = 0; graph < numberOfGraphs; graph++) {
            E4_1_37.EuclideanGraph randomGridGraph =
                    randomGridGraph(vertices, extraEdges);
            randomGridGraphs.add(randomGridGraph);
        }

        return randomGridGraphs;
    }

    public static E4_1_37.EuclideanGraph randomGridGraph(int vertices,
                                                         int extraEdges) {

        if (Math.sqrt(vertices) != (int) Math.sqrt(vertices)) {
            throw new IllegalArgumentException("Vertex number must have an integer " +
                    "square root");
        }

        // Instance used only to create the vertices
        E4_1_37.EuclideanGraph euclideanGraph =
                new E4_1_37.EuclideanGraph(0);

        int vertexNumberSqrt = (int) Math.sqrt(vertices);

        // Create the grid
        E4_1_37.EuclideanGraph.Vertex[][] verticesGrid =
                new E4_1_37.EuclideanGraph.Vertex[vertexNumberSqrt][vertexNumberSqrt];

        E4_1_37.EuclideanGraph.Vertex[] allVertices =
                new E4_1_37.EuclideanGraph.Vertex[vertices];

        for (int vertexId = 0; vertexId < vertices; vertexId++) {
            double randomXCoordinate = StdRandom.uniform();
            double randomYCoordinate = StdRandom.uniform();

            E4_1_37.EuclideanGraph.Vertex vertex =
                    new E4_1_37.EuclideanGraph.Vertex(vertexId, randomXCoordinate,
                            randomYCoordinate);
            allVertices[vertexId] = vertex;

            int[] cellRowAndColumn = getCellRowAndColumn(vertexId, vertexNumberSqrt);
            verticesGrid[cellRowAndColumn[0]][cellRowAndColumn[1]] = vertex;
        }

        // Generate extra random edges
        List<Edge> extraEdgesList = getRandomExtraEdges(extraEdges, vertices,
                allVertices);

        int[] shrinkResult = shrinkGraphIfNecessary(vertexNumberSqrt, vertices,
                extraEdgesList);
        int shrinkTimes = shrinkResult[0];
        vertexNumberSqrt = shrinkResult[1];
        vertices = shrinkResult[2];

        // Update vertex IDs in the grid if any shrink occurred
        if (shrinkTimes > 0) {
            updateVerticesIds(vertexNumberSqrt, allVertices, verticesGrid);
        }

        // Create the graph
        E4_1_37.EuclideanGraph randomEuclideanGridGraph =
                new E4_1_37.EuclideanGraph(vertices);

        for (int vertexId = 0; vertexId < vertices; vertexId++) {
            int[] cellRowAndColumn = getCellRowAndColumn(vertexId, vertexNumberSqrt);
            E4_1_37.EuclideanGraph.Vertex vertex =
                    verticesGrid[cellRowAndColumn[0]][cellRowAndColumn[1]];

            randomEuclideanGridGraph.addVertex(vertex);
        }

        // Connect vertices to their neighbors
        connectVerticesToNeighbors(randomEuclideanGridGraph, vertexNumberSqrt);

        // Add extra edges to the graph
        addExtraEdges(randomEuclideanGridGraph, extraEdgesList, allVertices);

        return randomEuclideanGridGraph;
    }

    private static List<Edge> getRandomExtraEdges(int extraEdges, int vertices,
                                                  E4_1_37.EuclideanGraph.Vertex[] allVertices) {
        List<Edge> extraEdgesList = new ArrayList<>();

        while (extraEdges > 0) {
            int randomVertexId1 = StdRandom.uniform(vertices);
            int randomVertexId2 = StdRandom.uniform(vertices);

            // The probability of connecting a vertex s to a vertex t is inversely
            // proportional
            // to the Euclidean distance between s and t
            double distance =
                    MathUtil.distanceBetweenPoints(allVertices[randomVertexId1].xCoordinate,
                            allVertices[randomVertexId1].yCoordinate,
                            allVertices[randomVertexId2].xCoordinate,
                            allVertices[randomVertexId2].yCoordinate);

            boolean shouldConnect = 1 - StdRandom.uniform() >= distance;
            if (shouldConnect) {
                extraEdgesList.add(new Edge(randomVertexId1, randomVertexId2));
                extraEdges--;
            }
        }

        return extraEdgesList;
    }

    private static int[] shrinkGraphIfNecessary(int vertexNumberSqrt, int vertices,
                                                List<Edge> extraEdgesList) {
        int shrinkTimes = 0;
        boolean shouldCheckIfNeedsToShrink = true;

        int originalVertexNumberSqrt = vertexNumberSqrt;

        // Check if the grid will have to be shrunk
        while (shouldCheckIfNeedsToShrink) {
            // Each cell in the bottom row and right column has one edge
            // Bottom row cells have edges on their right (except the cell n - 1, n - 1)
            int bottomRowEdges = vertexNumberSqrt - 1;
            // Right column cells have edges bellow them (except the cell n - 1, n - 1)
            int rightColumnEdges = vertexNumberSqrt - 1;

            // All other cells have 2 edges (below them and to the right), except cell
            // (n - 1, n - 1)
            int otherCellsEdges = (vertices - bottomRowEdges - rightColumnEdges - 1) * 2;

            int totalNonExtraEdges = bottomRowEdges + rightColumnEdges + otherCellsEdges;
            int totalEdges = totalNonExtraEdges + extraEdgesList.size();

            // We are assuming that the "about 2V" in the exercise description is
            // within 20% of 2V.
            // If R is too large, we shrink the grid to decrease the number of vertices
            // and edges.
            if (totalEdges > 1.2 * (2 * vertices)) {
                vertexNumberSqrt--;
                vertices = vertexNumberSqrt * vertexNumberSqrt;
                shrinkTimes++;

                HashSet<Integer> removedVertices = new HashSet<>();
                int row = 0;

                // Remove right column
                for (int i = 0; i < vertexNumberSqrt; i++) {
                    row++;
                    int removedVertexId = (row * originalVertexNumberSqrt) - shrinkTimes;
                    removedVertices.add(removedVertexId);
                }

                int nextVertexRemoved = row * originalVertexNumberSqrt;

                // Remove last row
                for (int i = 0; i < vertexNumberSqrt + 1; i++) {
                    removedVertices.add(nextVertexRemoved);
                    nextVertexRemoved++;
                }

                List<Edge> extraEdgesToRemoveAfterShrinking = new ArrayList<>();
                for (Edge extraEdge : extraEdgesList) {
                    if (removedVertices.contains(extraEdge.vertex1) || removedVertices.contains(extraEdge.vertex2)) {
                        extraEdgesToRemoveAfterShrinking.add(extraEdge);
                    }
                }

                extraEdgesList.removeAll(extraEdgesToRemoveAfterShrinking);
            } else {
                shouldCheckIfNeedsToShrink = false;
            }

            // The smallest grid we can have is a 2x2 grid, so if we reach this grid
            // dimensions we break
            if (vertexNumberSqrt == 2) {
                shouldCheckIfNeedsToShrink = false;
            }
        }

        return new int[]{shrinkTimes, vertexNumberSqrt, vertices};
    }

    private static void updateVerticesIds(int vertexNumberSqrt,
                                          E4_1_37.EuclideanGraph.Vertex[] allVertices,
                                          E4_1_37.EuclideanGraph.Vertex[][] verticesGrid) {
        int currentVertexId = 0;

        for (int row = 0; row < vertexNumberSqrt; row++) {
            for (int column = 0; column < vertexNumberSqrt; column++) {
                allVertices[verticesGrid[row][column].id].id = currentVertexId;
                verticesGrid[row][column].id = currentVertexId;
                verticesGrid[row][column].updateName(String.valueOf(currentVertexId));

                currentVertexId++;
            }
        }
    }

    private static void connectVerticesToNeighbors(E4_1_37.EuclideanGraph randomEuclideanGridGraph,
                                                   int vertexNumberSqrt) {
        int[] neighborRows = {-1, 1, 0, 0};
        int[] neighborColumns = {0, 0, -1, 1};

        for (int row = 0; row < vertexNumberSqrt; row++) {
            for (int column = 0; column < vertexNumberSqrt; column++) {
                for (int i = 0; i < 4; i++) {
                    int neighborRow = row + neighborRows[i];
                    int neighborColumn = column + neighborColumns[i];

                    if (isValidCell(vertexNumberSqrt, neighborRow, neighborColumn)) {
                        int vertexId1 = getVertexId(row, column, vertexNumberSqrt);
                        int vertexId2 = getVertexId(neighborRow, neighborColumn,
                                vertexNumberSqrt);

                        //Used to avoid connecting vertices more than once
                        if (vertexId1 < vertexId2) {
                            randomEuclideanGridGraph.addEdge(vertexId1, vertexId2);
                        }
                    }
                }
            }
        }
    }

    private static void addExtraEdges(E4_1_37.EuclideanGraph randomEuclideanGridGraph,
                                      List<Edge> extraEdgesList,
                                      E4_1_37.EuclideanGraph.Vertex[] allVertices) {
        for (Edge extraEdge : extraEdgesList) {
            // We have to access allVertices[] here because it has the updated vertex
            // ids (in the cases where graph
            // shrinking occurred).
            randomEuclideanGridGraph.addEdge(allVertices[extraEdge.vertex1].id,
                    allVertices[extraEdge.vertex2].id);
        }
    }

    private static boolean isValidCell(int dimensionSize, int row, int column) {
        return row >= 0 && row < dimensionSize && column >= 0 && column < dimensionSize;
    }

    private static int getVertexId(int row, int column, int columns) {
        return row * columns + column;
    }

    private static int[] getCellRowAndColumn(int vertexId, int columns) {
        int[] cellRowAndColumn = new int[2];
        cellRowAndColumn[0] = vertexId / columns; // Row
        cellRowAndColumn[1] = vertexId % columns; // Column

        return cellRowAndColumn;
    }


}
