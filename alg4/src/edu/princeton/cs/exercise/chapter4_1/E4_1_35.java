package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.35 Biconnectedness. A graph is biconnected if every pair of vertices is connected
 * by two disjoint paths. An articulation point in a connected graph is a vertex that
 * would disconnect the graph if it (and its adjacent edges) were removed. Prove that
 * any graph with no articulation points is biconnected. Hint : Given a pair of
 * vertices s and t and a path connecting them, use the fact that none of the vertices
 * on the path are articulation points to construct two disjoint paths connecting s and t.
 * <p>
 * 双连通性.如果图中每对顶点是通过两个不相交路径连接的,那么它就是双联通的.连接图中的一个关节点是它被删除后(还有
 * 它的邻接边)图变为非连接的.证明任意没有关节点的图是双链接的.提示:给定一对顶点 s 和 t 路径 a 连接他们,
 * 使用事实路径上没有顶点是关节点可以构造出两个不相交路径连接 s 和 t.
 *
 * @author LeonChen
 * @since 11/13/21
 */
class E4_1_35 {

    /**
     * @formatter:off
     * 任何没有关节点的图都是双连接的.
     * 证明:
     * 考虑两个顶点,s,t,还有一条路径 P1 连接 s 到 t.
     * 我们知道 P1 中没有顶点是关节点,所以对于路径中每一个顶点 v,总是有另一个路径 P2 连接 s 到 t 并且没有
     * 包含它.同样的,P2 没有包含任意的 P1 中的顶点,否则包含在两个路径中的顶点会是关节点(连接 s 到 t 必须
     * 经过他).
     * 这意味着每对顶点都是被两个不相交顶点路径(比如 P1 和 P2),使得这个图是双链接的.
     * 图解(P1 是路径 s-v1 v1-v2 v2-t 并且 P2 是路径 s-v3 v3-v4 v4-t):
     *
     * s
     * |\
     * | \
     * v1 v3
     * |   \
     * |   v4
     * v2 /
     * | /
     * |/
     * t
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
