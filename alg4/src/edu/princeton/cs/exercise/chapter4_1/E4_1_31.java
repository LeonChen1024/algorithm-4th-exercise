package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.31 Graph enumeration. How many different undirected graphs are there with V
 * vertices and E edges (and no parallel edges)?
 * <p>
 * 图枚举.V 个顶点和 E 条边的情况下有多少个不同的无向图(没有平行边)
 *
 * @author LeonChen
 * @since 11/9/21
 */
class E4_1_31 {

    /**
     * @formatter:off
     * 4.1.31 - Graph enumeration
     *
     * If we were considering graphs with no self-loops:
     *
     * There are (V) ways to choose a set {u, v} of two vertices. From this set there are E ways to choose the vertices to connect.
     *           (2)
     * So there are ((V)) = (V! / 2! * (V - 2)!)! / E! * ((V! / 2! * (V - 2)!) - E)!
     *              ((2))
     *              ( E )
     * different undirected graphs with V vertices and E edges (and no parallel edges and no self-loops).
     *
     * Since we are also considering graphs with self-loops, there are (V) * 2^V ways to choose a set {u, v} of two vertices in which
     *                                                                 (2)
     * vertices may or may not have a self-loop.
     *
     * So there are ((V) * 2^V) = ((V! / 2! * (V - 2)!) * 2^V)! / E! * (((V! / 2! * (V - 2)!) * 2^V) - E)!
     *              ((2)      )
     *              (    E    )
     * different undirected graphs with V vertices and E edges (and no parallel edges).
     *
     * Reference:
     * Handbook of Discrete and Combinatorial Mathematics by Kenneth H. Rosen, page 580
     * https://math.stackexchange.com/questions/1072726/counting-simple-connected-labeled-graphs-with-n-vertices-and-k-edges
     * https://math.stackexchange.com/questions/128439/how-to-determine-the-number-of-directed-undirected-graphs
     *
     * 有机会还是好好学习一下数学吧
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
