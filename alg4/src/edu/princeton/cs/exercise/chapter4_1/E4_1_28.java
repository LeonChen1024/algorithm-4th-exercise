package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.28 Two graphs are isomorphic if there is a way to rename the vertices of one to
 * make it identical to the other. Draw all the nonisomorphic graphs with two, three,
 * four, and five vertices.
 * <p>
 * 如果存在重命名一个顶点使得这两个图完全一致,那么这两个图是同构的.绘制所有非同构的图,分别有 2,3,4,5 个顶点
 *
 * @author LeonChen
 * @since 11/6/21
 */
class E4_1_28 {

    /**
     * @formatter:off
     * 2 个顶点时有两种异构图:
     * o o
     *
     * o-o
     *
     * 3 个顶点时有 4 种异构图:
     *  o
     * o o
     *
     *  o
     * o-o
     *
     * o-o-o
     *
     *  o
     * / \
     * o-o
     *
     * 4 个顶点时有 11 种异构图:
     * o o o o
     *
     * o-o o o
     *
     * o-o-o o
     *
     * o-o-o-o
     *
     * o-o o-o
     *
     *   o
     *   |
     *   o
     *  / \
     * o   o
     *
     *   o
     *  / \
     * o---o o
     *
     * o-o
     * | |
     * o-o
     *
     *   o
     *   |
     *   o
     *  / \
     * o---o
     *
     *   o
     *  / \
     * o---o
     *  \ /
     *   o
     *
     *    o
     *   /|\
     *  / o \
     * / / \ \
     * o------o
     *
     * 5 个顶点时,有 34 种异构图:
     *   o
     * o   o
     *  o o
     *
     *   o
     * o   o
     *  o-o
     *
     *   o
     * o    o
     *     /
     *  o-o
     *
     *    o
     * o     o
     *  \   /
     *   o o
     *
     *    o
     *  / | \
     * o  o  o
     *      o
     *
     *   o
     *  / \
     * o   o
     *  o-o
     *
     *    o
     *  /  \
     * o    o
     *  \
     *   o o
     *
     *    o
     *  /  \
     * o----o
     *   o o
     *
     *    o
     *  /|\\
     * o o oo
     *
     * o-o
     * | |
     * o-o o
     *
     * o
     * |
     * o-o
     * | |
     * o o
     *
     *   o
     *   |
     *   o
     *  / \
     * o---o o
     *
     * o-o-o-o-o
     *
     *   o  o
     *  /|  |
     * o |  |
     *  \|  |
     *   o  o
     *
     * o--o
     * |  |
     * o--o
     * |
     * o
     *
     * o   o
     * |   |
     * o---o
     *  \ /
     *   o
     *
     *  o--o
     *   \/
     * o--o--o
     *
     *    o
     *  /  \
     * o    o
     *  \   /
     *   o-o
     *
     *     o
     *  / / \\
     * o------o
     * \ /\ /\/
     *  \|/\ |/
     *   o---o
     *
     *     o
     *  / / \\
     * o------o
     * \ /\ /\/
     *  \|/\ |/
     *   o   o
     *
     * o---o
     * |\ /|\
     * | X | o
     * |/ \|/
     * o---o
     *
     * o---o
     * |\ /|
     * | o |
     * |/ \|
     * o---o
     *
     * o---o
     * |\ /|\
     * | X | o
     * |/ \|
     * o---o
     *
     *    o
     *  // \
     * o-o  o
     *    \/
     *     o
     *
     * o-o-o-o
     *  \| | /
     *    o
     *
     *     o
     *    /\
     *   /  \
     *  /    \
     * o------o
     * \  \ / /
     *  \ /\ /
     *   o  o
     *
     * o---o
     * | X | o
     * o---o
     *
     * o   o
     * |\ /|
     * | o |
     * |/ \|
     * o   o
     *
     *      o
     *     /|\
     * o--o | o
     *     \|/
     *      o
     *
     *   o
     *   |
     *   o
     *  /|\
     * o | o
     *  \|/
     *   o
     *
     *   o
     *  / \
     * o---o
     * |   |
     * o---o
     *
     * o---o
     * |  /|
     * | o |
     * |/  |
     * o---o
     *
     *   o
     *   |
     *   o
     *   |
     *   o
     *  / \
     * o---o
     *
     * o---o
     * | \ |
     * o---o o
     *
     * 可以参考 https://www.graphclasses.org/smallgraphs.html
     *
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
