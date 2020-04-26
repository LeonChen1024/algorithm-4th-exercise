package edu.princeton.cs.exercise.chapter1_5;

/**
 * 1.5.8 Give a counterexample that shows why this intuitive implementation of union() for
 * quick-find is not correct: <code>
 * public void union(int p, int q)
 * {
 *    if (connected(p, q))
 *      return;
 *    // Rename p’s component to q’s name.
 *    for (int i = 0; i < id.length; i++)
 *      if (id[i] == id[p])
 *        id[i] = id[q];
 *    count--;
 * }
 * </code>
 *
 * <p>给出一个反例来证明为什么这个 quick-find 的 union() 方法的实现是不对的.
 *
 * @author LeonChen
 * @since 4/18/20
 */
class e01_05_08 {

  /** 当 i= p 的时候,id[p] 的值被改变,导致 p 后面需要更新的节点对比的时候总是 false ,值不会改变. */
  public static void main(String[] args) {}
}
