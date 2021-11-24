package edu.princeton.cs.exercise.chapter4_1;

import edu.princeton.cs.algs4.CC;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolGraph;
import edu.princeton.cs.exercise.util.Constants;

import java.util.HashSet;
import java.util.Set;

/**
 * 4.1.24 Compute the number of connected components in movies.txt, the size of the
 * largest component, and the number of components of size less than 10. Find the
 * eccentricity, diameter, radius, a center, and the girth of the largest component in
 * the graph. Does it contain Kevin Bacon?
 * <p>
 * 计算 movies.txt 中的连接组件数量,最大的组件,以及大小少于 10 的组件数量.算出最大组件的 eccentricity, diameter,
 * radius,  center, girth.它是否包含了 Kevin Bacon?
 *
 * @author LeonChen
 * @since 11/3/21
 */
class E4_1_24 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        String filePath = Constants.FILES_PATH + Constants.MOVIES_FILE;
        String separator = "/";
        SymbolGraph movieSymbolGraph = new SymbolGraph(filePath, separator);
        Graph graph = movieSymbolGraph.graph();
        CC cc = new CC(graph);
        StdOut.println("cc num = " + cc.count());
        int largestCC = 0;
        int largestSize = 0;
        Set<Integer> less10Set = new HashSet<>();
        for (int i = 0; i < graph.V(); i++) {
            int size = cc.size(i);
            if (size > largestSize) {
                largestCC = i;
            }
            if (size < 10) {
                less10Set.add(cc.id(i));
            }


        }
        StdOut.println("largest CC is " + largestCC + " , less than 10 CC num is " + less10Set.size());
        E4_1_18.GraphProperties graphProperties = new E4_1_18.GraphProperties(graph,
                false);

        StdOut.println("eccentricity= " + graphProperties.eccentricity(largestCC));
        StdOut.println("diameter= " + graphProperties.diameter());
        StdOut.println("radius= " + graphProperties.radius());
        StdOut.println("center= " + graphProperties.center());
        StdOut.println("girth= " + graphProperties.girth());
        String kevinBaconName = "Bacon, Kevin";
        StdOut.println("contains  Kevin Bacon? " + (cc.id(movieSymbolGraph.indexOf(
                "Kevin Bacon")) == cc.id(largestCC)));

    }


}
