package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.exercise.util.Constants;

/**
 * 4.1.15 Modify the input stream constructor for Graph to also allow adjacency lists
 * from standard input (in a manner similar to SymbolGraph), as in the example
 * tinyGadj.txt shown at right. After the number of vertices and edges, each line contains
 * a vertex and its list of adjacent vertices.
 * <p>
 * 修改 Graph 的输入流构造器使他同样允许标准输入邻接列表(和 SymbolGraph 类似),如同右边的 tinyGadj.txt
 * 展示的那样.在顶点数量和边数之后,每行包含一个顶点和它的邻接列表
 *
 * @author LeonChen
 * @since 10/31/21
 */
class E4_1_15 {

    /**
     * @formatter:off
     *
     * @formatter:on
     */
    public static void main(String[] args) {

        String filePath = Constants.FILES_PATH + "tinyGadj.txt";
        Graph graph = new Graph(new In(filePath));
        StdOut.println(graph);

        StdOut.println("Expected:\n" +
                "0: 6 5 2 1\n" +
                "1: 0\n" +
                "2: 0\n" +
                "3: 5 4\n" +
                "4: 6 5 3\n" +
                "5: 4 3 0\n" +
                "6: 4 0\n" +
                "7: 8\n" +
                "8: 7\n" +
                "9: 12 11 10\n" +
                "10: 9\n" +
                "11: 12 9\n" +
                "12: 11 9");
    }

    public static class Graph {

        private final int vertices;
        private int edges;
        private Bag<Integer>[] adjacent;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.edges = 0;
            adjacent = (Bag<Integer>[]) new Bag[vertices];

            for (int i = 0; i < vertices; i++) {
                adjacent[i] = new Bag<>();
            }
        }

        public Graph(In in) {
            this(in.readInt());
            int edges = in.readInt(); //Not used
            in.readLine(); //Move to next line

            while (in.hasNextLine()) {
                String[] vertices = in.readLine().split(" ");

                int vertex = Integer.parseInt(vertices[0]);
                for (int i = 1; i < vertices.length; i++) {
                    addEdge(vertex, Integer.parseInt(vertices[i]));
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
