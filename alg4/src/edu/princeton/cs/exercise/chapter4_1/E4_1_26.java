package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.exercise.util.Constants;

import java.util.Iterator;

/**
 * 4.1.26 Write a SymbolGraph client like DegreesOfSeparation that uses depth-first
 * search instead of breadth-first search to find paths connecting two performers,
 * producing output like that shown on the facing page.
 * <p>
 * 编写一个 SymbolGraph 客户端如同 DegreesOfSeparation 使用深度优先搜索取代广度优先搜索来找到连接两个
 * 演员的路径,产生如同之前那页展示的输出
 *
 * @author LeonChen
 * @since 11/4/21
 */
class E4_1_26 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        String movieFilePath = Constants.FILES_PATH + "movies.txt";
        new DegreesOfSeparationDFS().degreesOfSeparationDFS(movieFilePath, "/",
                "Bacon, Kevin");


    }

    private static class DegreesOfSeparationDFS {

        private class DepthFirstPathsIterative {

            private boolean[] visited;
            private int[] edgeTo;
            private final int source;

            public DepthFirstPathsIterative(Graph graph, int source) {
                visited = new boolean[graph.V()];
                edgeTo = new int[graph.V()];
                this.source = source;
                depthFirstSearchIterative(graph, source);
            }

            private void depthFirstSearchIterative(Graph graph, int sourceVertex) {
                Stack<Integer> stack = new Stack<>();
                stack.push(sourceVertex);
                visited[sourceVertex] = true;

                // Used to be able to iterate over each adjacency list, keeping track of which
                // vertex in each adjacency list needs to be explored next
                Iterator<Integer>[] adjacentIterators =
                        (Iterator<Integer>[]) new Iterator[graph.V()];

                for (int vertexId = 0; vertexId < adjacentIterators.length; vertexId++) {
                    if (graph.getAdj()[vertexId] != null) {
                        adjacentIterators[vertexId] = graph.getAdj()[vertexId].iterator();
                    }
                }

                while (!stack.isEmpty()) {
                    int currentVertex = stack.peek();

                    if (adjacentIterators[currentVertex].hasNext()) {
                        int neighbor = adjacentIterators[currentVertex].next();

                        if (!visited[neighbor]) {
                            stack.push(neighbor);
                            visited[neighbor] = true;
                            edgeTo[neighbor] = currentVertex;
                        }
                    } else {
                        stack.pop();
                    }
                }
            }

            public boolean hasPathTo(int vertex) {
                return visited[vertex];
            }

            public Iterable<Integer> pathTo(int vertex) {
                if (!hasPathTo(vertex)) {
                    return null;
                }

                Stack<Integer> path = new Stack<>();

                for (int currentVertex = vertex; currentVertex != source; currentVertex = edgeTo[currentVertex]) {
                    path.push(currentVertex);
                }

                path.push(source);
                return path;
            }

        }

        private void degreesOfSeparationDFS(String filePath, String separator, String sourcePerformer) {
            SymbolGraph symbolGraph = new SymbolGraph(filePath, separator);

            Graph graph = symbolGraph.graph();

            if (!symbolGraph.contains(sourcePerformer)) {
                StdOut.println(sourcePerformer + " not in database.");
                return;
            }

            int sourceVertex = symbolGraph.index(sourcePerformer);
            DepthFirstPathsIterative depthFirstPathsIterative = new DepthFirstPathsIterative(graph, sourceVertex);

            while (!StdIn.isEmpty()) {
                String sink = StdIn.readLine();

                if (symbolGraph.contains(sink)) {
                    int destinationVertex = symbolGraph.index(sink);

                    if (depthFirstPathsIterative.hasPathTo(destinationVertex)) {
                        for (int vertexInPath : depthFirstPathsIterative.pathTo(destinationVertex)) {
                            StdOut.println("    " + symbolGraph.name(vertexInPath));
                        }
                    } else {
                        StdOut.println("Not connected");
                    }
                } else {
                    StdOut.println("Not in database.");
                }
            }
        }
    }


}
