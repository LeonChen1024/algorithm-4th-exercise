package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.21 Show, in the style of the figures in this section, a detailed trace of TwoColor
 * for finding a two-coloring of the graph built by Graph’s input stream constructor
 * for the file tinyGex2.txt (see Exercise 4.1.2). What is the order of growth of the
 * running time of the TwoColor constructor, in the worst case?
 * <p>
 * 展示,使用本章的风格绘制图表,使用图输入流构造器构造的 TwoColor 找到双色图的细节跟踪,使用文件 tinyGex2.txt
 * 作为输入(见练习 4.1.2).时间增长级数最差情况下是多少?
 *
 * @author LeonChen
 * @since 11/2/21
 */
class E4_1_21 {

    /**
     * @formatter:off
     *                Is 2-colorable?                marked[]                     color[]
     *                      T               0 1 2 3 4 5 6 7 8 9 10 11    0 1 2 3 4 5 6 7 8 9 10 11
     * dfs(0)                               T                            F F F F F F F F F F F  F
     *  dfs(5)                              T         T                  F F F F F T F F F F F  F
     *   check 0            T
     *   dfs(10)                            T         T         T        F F F F F T F F F F F  F
     *    check 5           T
     *    dfs(3)                            T     T   T         T        F F F T F T F F F F F  F
     *     check 10         T
     *     dfs(6)                           T     T   T T       T        F F F T F T F F F F F  F
     *      dfs(2)                          T   T T   T T       T        F F T T F T F F F F F  F
     *       check 5        F
     *       check 6        F
     *       check 0        F
     *       check 3        F
     *      2 done
     *      check 3         F
     *      check 0         F
     *     6 done
     *     check 2          F
     *    3 done
     *   10 done
     *   check 2            F
     *  5 done
     *  check 2             F
     *  check 6             F
     * 0 done
     * dfs(1)                               T T T T   T T       T        F F T T F T F F F F F  F
     *  dfs(4)                              T T T T T T T       T        F F T T T T F F F F F  F
     *   check 1            F
     *   dfs(8)                             T T T T T T T   T   T        F F T T F T F F F F F  F
     *    check 1           F
     *    dfs(11)                           T T T T T T T   T   T  T     F F T T F T F F F F F  T
     *     check 8          F
     *     dfs(7)                           T T T T T T T T T   T  T     F F T T F T F F F F F  F
     *      check 8         F
     *      check 11        F
     *     7 done
     *     check 1          F
     *    11 done
     *    check 7           F
     *    check 4           F
     *   8 done
     *  4 done
     *  check 8             F
     *  check 11            F
     * 1 done
     * dfs(9)                               T T T T T T T T T T T  T     F F T T F T F F F F F  F
     * 9 done
     *
     * 最差情况下是 O(V + E).
     * 每个邻接列表检验一次,这里有 2*E 个项(每个边来回各一次).初始化 marked[] 和 color[] 使用了 2*V
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
