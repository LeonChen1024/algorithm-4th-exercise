package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.19 Show, in the style of the figure on page 545, a detailed trace of CC for finding
 * the connected components in the graph built by Graph’s input stream constructor for the
 * file tinyGex2.txt (see Exercise 4.1.2).
 * <p>
 * 展示,按照 545 页的样式绘制图表,一个关于 CC 在使用 Graph 的输入流构造器构造的 图 中找到连接组件的细节追踪
 * 图,使用 tinyGex2.txt 作为输入(见 练习 4.1.2)
 *
 * @author LeonChen
 * @since 11/1/21
 */
class E4_1_19 {

    /**
     * @formatter:off
     *                    count            marked[]                       id[]
     *                            0 1 2 3 4 5 6 7 8 9 10 11    0 1 2 3 4 5 6 7 8 9 10 11
     * dfs(0)               0     T                            0
     *  dfs(5)              0     T         T                  0         0
     *   check 0
     *   dfs(10)            0     T         T         T        0         0         0
     *    check 5
     *    dfs(3)            0     T     T   T         T        0     0   0         0
     *     check 10
     *     dfs(6)           0     T     T   T T       T        0     0   0 0       0
     *      dfs(2)          0     T   T T   T T       T        0   0 0   0 0       0
     *       check 5
     *       check 6
     *       check 0
     *       check 3
     *      2 done
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
     * dfs(1)               1     T T T T   T T       T        0 1 0 0   0 0       0
     *  dfs(4)              1     T T T T T T T       T        0 1 0 0 1 0 0       0
     *   check 1
     *   dfs(8)             1     T T T T T T T   T   T        0 1 0 0 1 0 0   1   0
     *    check 1
     *    dfs(11)           1     T T T T T T T   T   T  T     0 1 0 0 1 0 0   1   0  1
     *     check 8
     *     dfs(7)           1     T T T T T T T T T   T  T     0 1 0 0 1 0 0 1 1   0  1
     *      check 8
     *      check 11
     *     7 done
     *     check 1
     *    11 done
     *    check 7
     *    check 4
     *   8 done
     *  4 done
     *  check 8
     *  check 11
     * 1 done
     * dfs(9)               2     T T T T T T T T T T T  T     0 1 0 0 1 0 0 1 1 2 0  1
     * 9 done
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
