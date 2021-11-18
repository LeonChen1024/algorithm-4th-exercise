package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.9 Show, in the style of the figure on page 533, a detailed trace of the call dfs(0)
 * for the graph built by Graph’s input stream constructor for the file tinyGex2.txt
 * (see Exercise 4.1.2). Also, draw the tree represented by edgeTo[].
 * <p>
 * 展示,按照 533 页图的风格,绘制使用输入流构造器接收文件 tinyGex2.txt (见练习 4.1.2) 调用 dgs(0)的详细
 * 追踪图.同时,绘制 edgeTo[] 表示的树
 *
 * @author LeonChen
 * @since 10/27/21
 */
class E4_1_09 {

    /**
     * @formatter:off
     *
     * 已标记忽略,未标记将它传入 dfs(x)
     *                marked[]    adj[]
     * dfs (0)          0 T        0 5 2 6
     *                  1          1 4 8 11
     *                  2          2 5 6 0 3
     *                  3          3 10 6 2
     *                  4          4 1 8
     *                  5          5 0 10 2
     *                  6          6 2 3 0
     *                  7          7 8 11
     *                  8          8 1 11 7 4
     *                  9          9
     *                 10         10 5 3
     *                 11         11 8 7 1
     *
     *  dfs (5)         0 T        0 5 2 6
     *   check 0        1          1 4 8 11
     *                  2          2 5 6 0 3
     *                  3          3 10 6 2
     *                  4          4 1 8
     *                  5 T        5 0 10 2
     *                  6          6 2 3 0
     *                  7          7 8 11
     *                  8          8 1 11 7 4
     *                  9          9
     *                 10         10 5 3
     *                 11         11 8 7 1
     *
     *   dfs (10)       0 T        0 5 2 6
     *    check 5       1          1 4 8 11
     *                  2          2 5 6 0 3
     *                  3          3 10 6 2
     *                  4          4 1 8
     *                  5 T        5 0 10 2
     *                  6          6 2 3 0
     *                  7          7 8 11
     *                  8          8 1 11 7 4
     *                  9          9
     *                 10 T       10 5 3
     *                 11         11 8 7 1
     *
     *    dfs (3)       0 T        0 5 2 6
     *     check 10     1          1 4 8 11
     *                  2          2 5 6 0 3
     *                  3 T        3 10 6 2
     *                  4          4 1 8
     *                  5 T        5 0 10 2
     *                  6          6 2 3 0
     *                  7          7 8 11
     *                  8          8 1 11 7 4
     *                  9          9
     *                 10 T       10 5 3
     *                 11         11 8 7 1
     *
     *     dfs (6)      0 T        0 5 2 6
     *                  1          1 4 8 11
     *                  2          2 5 6 0 3
     *                  3 T        3 10 6 2
     *                  4          4 1 8
     *                  5 T        5 0 10 2
     *                  6 T        6 2 3 0
     *                  7          7 8 11
     *                  8          8 1 11 7 4
     *                  9          9
     *                 10 T       10 5 3
     *                 11         11 8 7 1
     *
     *      dfs (2)     0 T        0 5 2 6
     *       check 5    1          1 4 8 11
     *       check 6    2 T        2 5 6 0 3
     *       check 0    3 T        3 10 6 2
     *       check 3    4          4 1 8
     *      2 done      5 T        5 0 10 2
     *                  6 T        6 2 3 0
     *                  7          7 8 11
     *                  8          8 1 11 7 4
     *                  9          9
     *                 10 T       10 5 3
     *                 11         11 8 7 1
     *      check 3
     *      check 0
     *     6 done
     *     check 2
     *    3 done
     *   10 done
     *   check 2
     *  5 done
     *  check 2
     *  check 6
     * 0 done
     *
     * edgeTo[] tree
     *
     *            0
     *           5
     *         10
     *        3
     *       6
     *      2
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
