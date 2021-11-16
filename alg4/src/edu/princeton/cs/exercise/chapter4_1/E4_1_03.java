package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.*;

/**
 * 4.1.3 Create a copy constructor for Graph that takes as input a graph G and creates
 * and initializes a new copy of the graph. Any changes a client makes to G should not
 * affect the newly created graph.
 * <p>
 * 创建一个复制构造器给 Graph ,它接收一个图 G 作为输入并初始化一个新的图复制品.所有对 G 的修改不能影响到
 * 新创建的图
 *
 * @author LeonChen
 * @since 10/22/21
 */
class E4_1_03 {

    /**
     * @formatter:off
     * 不要使用相同的对象引用即可,要注意由于添加顺序的不同,要将原来的图的 bag 倒序添加进来
     * @formatter:on
     */
    public static void main(String[] args) {

        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);

        CopyGraph copyGraph = new CopyGraph(graph);
        StdOut.println(copyGraph);

        StdOut.println("Expected:\n" +
                "0: 3 2 1\n" +
                "1: 4 2 0\n" +
                "2: 3 1 0\n" +
                "3: 2 0\n" +
                "4: 1\n");

        copyGraph.addEdge(0, 4);
        StdOut.println("Edges in original graph: " + graph.E() + " Expected: 6");
        StdOut.println("Edges in copy graph: " + copyGraph.edges() + " Expected: 7");
    }

    private static class CopyGraph {
        private final int vertices;
        private int edges;
        private Bag<Integer>[] adjacent;

        public CopyGraph(int vertices) {
            this.vertices = vertices;
            this.edges = 0;
            adjacent = (Bag<Integer>[]) new Bag[vertices];

            for (int i = 0; i < vertices; i++) {
                adjacent[i] = new Bag<>();
            }
        }

        public CopyGraph(In in) {
            this(in.readInt());
            int edges = in.readInt();

            for (int i = 0; i < edges; i++) {
                int vertex1 = in.readInt();
                int vertex2 = in.readInt();
                addEdge(vertex1, vertex2);
            }
        }

        public CopyGraph(Graph graph) {
            if (graph == null) {
                vertices = 0;
            } else {
                this.vertices = graph.V();
                this.edges = graph.E();
                adjacent = (Bag<Integer>[]) new Bag[vertices];

                for (int i = 0; i < vertices; i++) {
                    adjacent[i] = new Bag<>();
                }

                for (int vertex = 0; vertex < graph.V(); vertex++) {
                    // Reverse so that adjacency list is in the same order as original
                    Stack<Integer> stack = new Stack<>();
                    for (int neighbor : graph.getAdj()[vertex]) {
                        stack.push(neighbor);
                    }
                    for (int neighbor : stack) {
                        adjacent[vertex].add(neighbor);
                    }
                }
            }
        }

        public int vertices() {
            return vertices;
        }

        public int edges() {
            return edges;
        }

        public void addEdge(int vertex1, int vertex2) {
            adjacent[vertex1].add(vertex2);
            adjacent[vertex2].add(vertex1);
            edges++;
        }

        public Iterable<Integer> adjacent(int vertex) {
            return adjacent[vertex];
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
