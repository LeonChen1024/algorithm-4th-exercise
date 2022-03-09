package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.exercise.util.Constants;

/**
 * 4.1.46 Random transportation graphs. One way to define a transportation system is
 * with a set of sequences of vertices, each sequence defining a path connecting the
 * vertices. For example, the sequence 0-9-3-2 defines the edges 0-9, 9-3, and 3-2.
 * Write a EuclideanGraph client RandomTransportation that builds a graph from an input
 * file consisting of one sequence per line, using symbolic names. Develop input
 * suitable to allow you to use your program to build a graph corresponding to the
 * Paris Métro system.
 * Testing all algorithms and studying all parameters against all
 * graph models is unrealistic. For each problem listed below, write a client that
 * addresses the problem for any given input graph, then choose among the generators
 * above to run experiments for that graph model. Use your judgment in selecting
 * experiments, perhaps in response to results of previous experiments. Write a
 * narrative explaining your results and any conclusions that might be drawn.
 * <p>
 * 随机运输图.一种定义运输系统的方式是使用一个序列的顶点集合,每个序列定义了一个路径连接顶点,比如,序列
 * 0-9-3-2 定了边 0-9, 9-3, 和 3-2.编写一个 EuclideanGraph 客户端 RandomTransportation 从
 * 文件输入使用符号名每行一个序列构建一个图.开发一个合适的输入使得你的程序可以构建相应的巴黎地铁系统.
 * 测试所有的算法并且研究图模型所有的参数是不现实的.对于下面列出的每个问题,编写一个客户端处理任意给定的输入图,
 * 然后在上面的生成器中进行选择运行该图模型的实验,可能会和前面的实验对应.编写一个描述解释你的结果和任何可能
 * 绘制的结论
 *
 * @author LeonChen
 * @since 11/26/21
 */
class E4_1_46 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        E4_1_37.EuclideanGraph euclideanGraph = randomTransportation();
        euclideanGraph.show(-20, 120, 0, 120, 0.5);
    }

    public static class TransportationGraph {

        private SeparateChainingHashST<String, Integer> vertexNameToIdMap;
        private String[] keys;
        private E4_1_37.EuclideanGraph graph;

        public TransportationGraph(String stream,
                                   SeparateChainingHashST<String,
                                           E4_1_37.EuclideanGraph.Vertex>
                                           metroStationToCoordinateMap) {
            String separator = "-";
            vertexNameToIdMap = new SeparateChainingHashST<>();

            //First pass
            In in = new In(stream);

            while (in.hasNextLine()) {
                String[] vertices = in.readLine().split(separator);

                for (int i = 0; i < vertices.length; i++) {
                    if (!vertexNameToIdMap.contains(vertices[i])) {
                        vertexNameToIdMap.put(vertices[i], vertexNameToIdMap.size());
                    }
                }
            }

            keys = new String[vertexNameToIdMap.size()];

            for (String vertexName : vertexNameToIdMap.keys()) {
                keys[vertexNameToIdMap.get(vertexName)] = vertexName;
            }

            graph = new E4_1_37.EuclideanGraph(vertexNameToIdMap.size());
            //Seconds pass
            in = new In(stream);

            while (in.hasNextLine()) {
                String[] vertices = in.readLine().split(separator);

                for (int vertex = 0; vertex < vertices.length - 1; vertex++) {
                    //Since we need an EuclideanGraph, we get the coordinates first
//                    int vertex1 = vertexNameToIdMap.get(vertices[vertex]);
//                    int vertex2 = vertexNameToIdMap.get(vertices[vertex + 1]);

                    E4_1_37.EuclideanGraph.Vertex coordinateVertex1 =
                            metroStationToCoordinateMap.get(vertices[vertex]);
                    E4_1_37.EuclideanGraph.Vertex coordinateVertex2 =
                            metroStationToCoordinateMap.get(vertices[vertex + 1]);

                    graph.addVertex(coordinateVertex1);
                    graph.addVertex(coordinateVertex2);

                    graph.addEdge(coordinateVertex1.id, coordinateVertex2.id);
                }
            }
        }

        public boolean contains(String vertexName) {
            return vertexNameToIdMap.contains(vertexName);
        }

        public int index(String vertexName) {
            return vertexNameToIdMap.get(vertexName);
        }

        public String name(int vertexId) {
            return keys[vertexId];
        }

        public E4_1_37.EuclideanGraph graph() {
            return graph;
        }
    }

    public static E4_1_37.EuclideanGraph randomTransportation() {
        // Based on https://parisbytrain.com/wp-content/uploads/2014/01/paris-metro-mini-map-2014.pdf

        /**
         * File contents
         *
         Jasmin-Ranelagh-Boulainvilliers
         Lourmel-Boucicaut-Félix Faure-Commerce
         Pte de Vanves-Plaisance-Pernety-Gaité
         Corvisart-Place d’Italie-Nationale-Chevaleret-Quai de la Gare-Bercy
         Tolbiac-Place d’Italie-Campo Formio-Saint Marcel
         */

        String filePath = Constants.FILES_PATH + Constants.PARIS_METRO_FILE;

        // Used only to generate the vertices
        E4_1_37.EuclideanGraph euclideanGraph =
                new E4_1_37.EuclideanGraph(0);

        SeparateChainingHashST<String,
                E4_1_37.EuclideanGraph.Vertex> metroStationToCoordinateMap =
                new SeparateChainingHashST<>();
        metroStationToCoordinateMap.put("Jasmin", new E4_1_37.EuclideanGraph.Vertex(0,
                "Jasmin"
                , 10.4, 20.4));
        metroStationToCoordinateMap.put("Ranelagh", new E4_1_37.EuclideanGraph.Vertex(1,
                "Ranelagh", 10.4, 25.7));
        metroStationToCoordinateMap.put("Boulainvilliers",
                new E4_1_37.EuclideanGraph.Vertex(2,
                        "Boulainvilliers", 10.4, 32.11));

        metroStationToCoordinateMap.put("Lourmel", new E4_1_37.EuclideanGraph.Vertex(3,
                "Lourmel", 68.9, 60.4));
        metroStationToCoordinateMap.put("Boucicaut", new E4_1_37.EuclideanGraph.Vertex(4,
                "Boucicaut", 78.1, 70.2));
        metroStationToCoordinateMap.put("Félix Faure",
                new E4_1_37.EuclideanGraph.Vertex(5,
                "F" +
                        "élix Faure", 85.9, 82.4));
        metroStationToCoordinateMap.put("Commerce", new E4_1_37.EuclideanGraph.Vertex(6,
                "Commerce", 85.9, 91.4));

        metroStationToCoordinateMap.put("Pte de Vanves",
                new E4_1_37.EuclideanGraph.Vertex(7,
                "Pte de Vanves", 44.9, 23.4));
        metroStationToCoordinateMap.put("Plaisance", new E4_1_37.EuclideanGraph.Vertex(8,
                "Plaisance", 70.1, 19.1));
        metroStationToCoordinateMap.put("Pernety", new E4_1_37.EuclideanGraph.Vertex(9,
                "Pernety", 89.2, 15.4));
        metroStationToCoordinateMap.put("Gaité", new E4_1_37.EuclideanGraph.Vertex(10,
                "Gaité",
                105.9, 13.9));

        metroStationToCoordinateMap.put("Corvisart", new E4_1_37.EuclideanGraph.Vertex(11,
                "Corvisart", -10.9, 66.9));
        metroStationToCoordinateMap.put("Place d’Italie",
                new E4_1_37.EuclideanGraph.Vertex(12,
                        "Place d’Italie", 10.3, 78.9));
        metroStationToCoordinateMap.put("Nationale", new E4_1_37.EuclideanGraph.Vertex(13,
                "Nationale", 40.9, 80.2));
        metroStationToCoordinateMap.put("Chevaleret",
                new E4_1_37.EuclideanGraph.Vertex(14,
                "Chevaleret", 46.2, 90.9));
        metroStationToCoordinateMap.put("Quai de la Gare",
                new E4_1_37.EuclideanGraph.Vertex(15
                        , "Quai de la Gare", 52.9, 101.0));
        metroStationToCoordinateMap.put("Bercy", new E4_1_37.EuclideanGraph.Vertex(16,
                "Bercy",
                62.9, 111.2));

        metroStationToCoordinateMap.put("Tolbiac", new E4_1_37.EuclideanGraph.Vertex(17,
                "Tolbiac", 10.3, 60.9));
        metroStationToCoordinateMap.put("Campo Formio",
                new E4_1_37.EuclideanGraph.Vertex(18,
                "Campo Formio", 10.9, 93.2));
        metroStationToCoordinateMap.put("Saint Marcel",
                new E4_1_37.EuclideanGraph.Vertex(19,
                "Saint Marcel", 18.9, 103.2));

        TransportationGraph transportationGraph = new TransportationGraph(filePath,
                metroStationToCoordinateMap);
        return transportationGraph.graph;
    }


}
