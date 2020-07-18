package edu.princeton.cs.exercise.chapter2_2;

/**
 * 2.2.13 Lower bound for average case. Prove that the expected number of compares used by any
 * compare-based sorting algorithm must be at least ~N lg N (assuming that all possible orderings of
 * the input are equally likely). Hint: The expected number of compares is at least the external
 * path length of the compare tree (the sum of the lengths of the paths from the root to all
 * leaves), which is minimized when it is balanced.
 *
 * <p>使得平均情况有更低的下限.证明任意的对比排序算法的对比次数都是至少 ~NlgN 的(假设所有的输入的顺序都是几乎相等的).<br>
 * 提示: 对比的最少次数是决策树的外部路径长度(从根到所有叶子的路径长度的总和),当这个树是平衡二叉树的时候最小.
 *
 * @author LeonChen
 * @since 7/3/20
 */
class E02_02_13 {

  /**
   * 首先,我们要知道决策树是怎么样的 <code>
   *     比如我们输入3个值  3,1,2 那么决策树会怎么运行呢? 左子树代表< , 右子树代表>
   *
   *                            a1 : a2
   *                          _____|__________________________
   *                          |                              |
   *                      a2 : a3                        a2 : a3
   *                   _____|_____                  ________|________
   *                  |          |                  |               |
   *              a1,a2,a3     a1 : a3           a1:a3            a3,a2,a1
   *                        ______|_____          __|___________
   *                       |           |          |            |
   *                    a1,a3,a2     a3,a1,a2    a2,a1,a3    a2,a3,a1
   *
   * 最后 3,1,2 对应的叶子节点是  a2,a3,a1
   * </code> 我们知道 N 个数会有 N! 种排序.那么就会对应决策树中的 N! 个叶子. 由二叉树的性质可以得出高度为 h 的<br>
   * 二叉树最多有 2^h 个叶子节点. 所以N个数的最小高度h是 2^h > N!, h>=log(N!).<br>
   * H(i) 代表有i个叶子节点的决策树的外部路径长度. 比如 3,1,2 就是 H(6) = 2+3+3+3+3+2=16<br>
   * 外部路径比高度h少1是因为根结点高度并不属于外部路径.当叶子节点数量翻倍的时候,其实就是当前的叶子节点向下扩展出两个<br>
   * 子节点.可得 H(i) =2H(i/2) +i . 由于向下扩展所以需要+i. H(i)=ilogi , i 对应的就是上面的 N! ,可得 <br>
   * H(N!)=N!log(N!) 求平均即 H(N!)/N!=log(N!) = NlogN <br>
   * log(N!) = NlogN 推导过程如下 https://pic4.zhimg.com/80/v2-65f5ac01c4b1bfab923e7b7d6d682a67_720w.jpg
   *
   * @param args
   */
  public static void main(String[] args) {}
}
