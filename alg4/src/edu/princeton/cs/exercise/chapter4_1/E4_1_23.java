package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.exercise.util.Constants;

import java.awt.*;

/**
 * 4.1.23 Write a program BaconHistogram that prints a histogram of Kevin Bacon
 * numbers, indicating how many performers from movies.txt have a Bacon number of
 * 0, 1, 2, 3, ... . Include a category for those who have an infinite number (not
 * connected to Kevin Bacon).
 * <p>
 * 编写一个程序 BaconHistogram 来打印 Kevin Bacon 数字,表明movies.txt 中的多少个演员的 Bacon number
 * 是 0, 1, 2, 3, ... .  包含一个类别给那些拥有无限(没有连接到 Kevin Bacon )数字的人.
 *
 * @author LeonChen
 * @since 11/3/21
 */
class E4_1_23 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        baconHistogram();

    }

    private static class MovieSymbolGraph {

        private class Vertex {
            String name;
            boolean isMovie;

            Vertex(String name, boolean isMovie) {
                this.name = name;
                this.isMovie = isMovie;
            }

            @Override
            public int hashCode() {
                return name.hashCode();
            }

            @Override
            public boolean equals(Object other) {
                return other instanceof Vertex && name.equals(((Vertex) other).name);
            }
        }

        private SeparateChainingHashST<Vertex, Integer> vertexToIdMap;
        private Vertex[] keys;
        private Graph graph;

        public MovieSymbolGraph(String stream, String separator) {
            vertexToIdMap = new SeparateChainingHashST<>();

            //First pass
            In in = new In(stream);

            while (in.hasNextLine()) {
                String[] vertices = in.readLine().split(separator);

                // Movie vertex
                Vertex movieVertex = new Vertex(vertices[0], true);

                if (!vertexToIdMap.contains(movieVertex)) {
                    vertexToIdMap.put(movieVertex, vertexToIdMap.size());
                }

                for (int i = 1; i < vertices.length; i++) {
                    Vertex actorVertex = new Vertex(vertices[i], false);

                    if (!vertexToIdMap.contains(actorVertex)) {
                        vertexToIdMap.put(actorVertex, vertexToIdMap.size());
                    }
                }
            }

            keys = new Vertex[vertexToIdMap.size()];

            for (Vertex vertex : vertexToIdMap.keys()) {
                keys[vertexToIdMap.get(vertex)] = vertex;
            }

            graph = new Graph(vertexToIdMap.size());
            //Seconds pass
            in = new In(stream);

            while (in.hasNextLine()) {
                String[] vertices = in.readLine().split(separator);

                int movieVertex = vertexToIdMap.get(new Vertex(vertices[0], true));
                for (int i = 1; i < vertices.length; i++) {
                    graph.addEdge(movieVertex, vertexToIdMap.get(new Vertex(vertices[i], false)));
                }
            }
        }

        public boolean contains(String vertexName) {
            return vertexToIdMap.contains(new Vertex(vertexName, false)); //isMovie is not used here
        }

        public int index(String vertexName) {
            return vertexToIdMap.get(new Vertex(vertexName, false)); //isMovie is not used here
        }

        public Vertex vertexInformation(int vertexId) {
            return keys[vertexId];
        }

        public Graph graph() {
            return graph;
        }
    }

    private static void baconHistogram() {
        String filePath = Constants.FILES_PATH + Constants.MOVIES_FILE;
        String separator = "/";
        String kevinBaconName = "Bacon, Kevin";

        final int MAX_BACON = 30;

        MovieSymbolGraph movieSymbolGraph = new MovieSymbolGraph(filePath, separator);
        Graph graph = movieSymbolGraph.graph();
        int kevinBaconId = movieSymbolGraph.index(kevinBaconName);

        // Get Kevin Bacon numbers
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, kevinBaconId);

        double[] histogram = new double[MAX_BACON + 1];
        double maxFrequency = 0;

        for (int vertex = 0; vertex < graph.V(); vertex++) {
            if (movieSymbolGraph.vertexInformation(vertex).isMovie) {
                continue;
            }

            int kevinBaconNumber = breadthFirstPaths.distTo(vertex);

            if (kevinBaconNumber == Integer.MAX_VALUE) {
                kevinBaconNumber = MAX_BACON;
            } else {
                //Divide by 2 because the relation in the graph is
                // Actor ---- Movie ---- Actor
                // So a distance of 2 in the graph is actually a Kevin Bacon number of 1
                kevinBaconNumber /= 2;
            }

            histogram[kevinBaconNumber]++;

            if (histogram[kevinBaconNumber] > maxFrequency) {
                maxFrequency = histogram[kevinBaconNumber];
            }
        }

        drawBaconHistogram(histogram, MAX_BACON, maxFrequency);
        printHistogramValues(histogram, MAX_BACON);
    }

    private static void drawBaconHistogram(double[] histogram, int INFINITY_ID, double maxFrequency) {
        StdDraw.setCanvasSize(1024, 512);

        double minX = -3;
        double maxX = histogram.length + 2;
        double middleX = minX + (maxX - minX) / 2;

        double minY = -7000;
        double maxY = maxFrequency + 6000;
        double middleY = minY + (maxY - minY) / 2;

        StdDraw.setXscale(minX, maxX);
        StdDraw.setYscale(minY, maxY);

        // Labels
        String fontName = "Verdana";
        Font titlesFont = new Font(fontName, Font.PLAIN, 14);
        StdDraw.setFont(titlesFont);

        StdDraw.text(middleX, maxFrequency + 3000, "Bacon Histogram");
        StdDraw.text(-2, middleY, "Frequency", 90);
        StdDraw.text(middleX, -5000, "Kevin Bacon Number");

        Font graphLabelsFont = new Font(fontName, Font.PLAIN, 10);
        StdDraw.setFont(graphLabelsFont);

        // Y labels
        for (int y = 0; y <= maxFrequency; y += 2000) {
            StdDraw.text(-0.8, y, String.valueOf(y));
        }

        // X labels
        for (int x = 0; x < histogram.length; x++) {
            String label;

            if (x != INFINITY_ID) {
                label = String.valueOf(x);
            } else {
                label = "Infinite";
            }

            StdDraw.text(x, -2000, label);
        }

        for (int i = 0; i < histogram.length; i++) {
            StdDraw.filledRectangle(i, histogram[i] / 2, 0.25, histogram[i] / 2);
        }
    }

    private static void printHistogramValues(double[] histogram, int INFINITY_ID) {

        for (int i = 0; i < histogram.length; i++) {
            if (histogram[i] == 0) {
                break;
            }
            StdOut.printf("Kevin Bacon number %3d: %8.0f\n", i, histogram[i]);
        }
        StdOut.printf("Infinite: %8.0f\n", histogram[INFINITY_ID]);
    }


}
