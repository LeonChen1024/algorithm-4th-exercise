package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.20 Show, in the style of the figures in this section, a detailed trace of Cycle for
 * finding a cycle in the graph built by Graph’s input stream constructor for the file
 * tinyGex2.txt (see Exercise 4.1 2). What is the order of growth of the running time
 * of the Cycle constructor, in the worst case?
 * <p>
 * 展示,使用本章的风格图表绘制,找出使用 Graph 输入法构造器构造的图中的环,使用文件 tinyGex2.txt(见练习
 * 4.1.2)作为输入. Cycle 构造器在最差的情况的运行时间增长幅度怎么样?
 *
 * @author LeonChen
 * @since 11/2/21
 */
class E4_1_20 {

    /**
     * @formatter:off
     *              Has Cycle?             marked[]
     *                   F        0 1 2 3 4 5 6 7 8 9 10 11
     * dfs(0)                     T
     *  dfs(5)                    T         T
     *   check 0         F
     *   dfs(10)                  T         T         T
     *    check 5        F
     *    dfs(3)                  T     T   T         T
     *     check 10      F
     *     dfs(6)                 T     T   T T       T
     *      dfs(2)                T   T T   T T       T
     *       check 5     T (cycle found here)
     *       check 6     T
     *       check 0     T
     *       check 3     T
     *      2 done
     *      check 3      T
     *      check 0      T
     *     6 done
     *     check 2       T
     *    3 done
     *   10 done
     *   check 2         T
     *  5 done
     *  check 2          T
     *  check 6          T
     * 0 done
     * dfs(1)                     T T T T   T T       T
     *  dfs(4)                    T T T T T T T       T
     *   check 1         T
     *   dfs(8)                   T T T T T T T   T   T
     *    check 1        T
     *    dfs(11)                 T T T T T T T   T   T  T
     *     check 8       T
     *     dfs(7)                 T T T T T T T T T   T  T
     *      check 8      T
     *      check 11     T
     *     7 done
     *     check 1       T
     *    11 done
     *    check 7        T
     *    check 4        T
     *   8 done
     *  4 done
     *  check 8          T
     *  check 11         T
     * 1 done
     * dfs(9)                     T T T T T T T T T T T  T
     * 9 done
     *
     * 最差情况是 O(V+E).每个邻接列表校验一次,有 2*E 个项(每边来回各一次);初始化 mark[] 数组消耗了 V
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
