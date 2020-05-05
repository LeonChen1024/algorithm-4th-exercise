package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.15 Binomial trees. Show that the number of nodes at each level in the worst-case trees for
 * weighted quick-union are binomial coefficients. Compute the average depth of a node in a
 * worst-case tree with N = 2n nodes.
 *
 * <p>二项式树. 证明 weighted quick-union 在最差情况下树的每一层的节点数量都是二项式系数.<br>
 * 计算最差情况下 N = 2n 个节点的树的平均节点深度
 *
 * @author LeonChen
 * @since 4/21/20
 */
class E01_05_15 {

  /**
   * 二项式系数举例 <code>
   *
   * 1
   * 1 1
   * 1 2 1
   * 1 3 3 1
   * 1 4 6 4 1
   * 1 5 10 10 5 1
   * </code><br>
   * 二项式系数的计算方法是将上一次的结果,和结果向后移动一位相加得到.比如 <code>
   *   1 1
   * +   1 1
   * = 1 2 1
   *
   *   1 2 1
   * +   1 2 1
   * = 1 3 3 1
   *   </code><br>
   * 而在 weighted quick-union 中,最差情况出现在每次 union 的时候都是将两个大小相同的树相加.可以发现<br>
   * 树的节点变化和二项式一致,第一次只有一个节点是 1, 第二次是两个节点 1 1 ,第三次是4个节点 是 1 2 1 ,<br>
   * 第四次是 8个节点 是 1 3 3 1 以此类推.<br>
   * 最差的情况下的节点数量是 N = 2^n n是最差树的union次数.此时有 n+1 层节点.<br>
   * n=3 的时候, N=8, 有4层. 总的深度是 1*0 + 3*1+ 3*2 + 1*3 = 12 . 平均节点深度是 12 / 8 = 3/2.<br>
   * n=4 的时候, N=16, 有5层. 总的深度是 1*0 + 4*1 + 6*2 + 4*3 + 1*4= 32. 平均节点深度是 32/16 = 4/2.<br>
   * n=5 的时候, N=32, 有6层. 总的深度是 1*0 + 5*1 + 10*2 + 10*3 + 5*4 + 1*5= 80. 平均节点深度是 80/32 = 5/2.<br>
   * 我们可以推断 平均节点深度是 n/2<br>
   * 证明: 首先基本的示例 k = 1 时, 平均深度是 1/2 满足条件. 接下来推广这个定义到全部情况.<br>
   * 给定两个大小为 K =2^k 的子树,假设每个子树的平均深度是 k/2,我们要证明将这两个树结合后的平均深度是<br>
   * (k+1)/2. 可知,合并时其中一个子树会合并到另一个子树中,并且每个节点的深度都+1.所以增加的总深度为 K = 2^k .<br>
   * 此时整个树的深度为 k/2 * 2^k +(k/2 *2^k + 2^k) =2^k * (k+1). 此时整个树的总节点数是 2K = 2^(k+1)<br>
   * 平均深度是 2^k * (k + 1) / (2^(k + 1)) = (k + 1) / 2 得证.
   */
  public static void main(String[] args) {
    WeightedQuickUnionByHeightUF uf = new WeightedQuickUnionByHeightUF(10);
    int[][] arr = new int[8][2];
    arr[0] = new int[] {9, 0};
    arr[1] = new int[] {3, 4};
    arr[2] = new int[] {5, 8};
    arr[3] = new int[] {7, 2};
    arr[4] = new int[] {2, 1};
    arr[5] = new int[] {5, 7};
    arr[6] = new int[] {0, 3};
    arr[7] = new int[] {4, 2};

    int index = 0;
    while (index < 8) {
      int[] b = arr[index];
      if (!uf.connected(b[0], b[1])) {
        uf.union(b[0], b[1]);
      }
      printResult(uf);
      index++;
    }
  }

  private static void printResult(WeightedQuickUnionByHeightUF uf) {

    StdOut.println("array content is " + uf);
  }

  static class WeightedQuickUnionByHeightUF {
    private int[] parent; // parent[i] = parent of i
    private int[] height; // height[i] = height of subtree rooted at i
    private int count; // number of components

    /**
     * Initializes an empty union-find data structure with {@code n} elements {@code 0} through
     * {@code n-1}. Initially, each elements is in its own set.
     *
     * @param n the number of elements
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public WeightedQuickUnionByHeightUF(int n) {
      count = n;
      parent = new int[n];
      height = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
        height[i] = 0;
      }
    }

    /**
     * Returns the number of sets.
     *
     * @return the number of sets (between {@code 1} and {@code n})
     */
    public int count() {
      return count;
    }

    /**
     * Returns the canonical element of the set containing element {@code p}.
     *
     * @param p an element
     * @return the canonical element of the set containing {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
      validate(p);
      while (p != parent[p]) p = parent[p];
      return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
      int n = parent.length;
      if (p < 0 || p >= n) {
        throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
      }
    }

    /**
     * Returns true if the two elements are in the same set.
     *
     * @param p one element
     * @param q the other element
     * @return {@code true} if {@code p} and {@code q} are in the same set; {@code false} otherwise
     * @throws IllegalArgumentException unless both {@code 0 <= p < n} and {@code 0 <= q < n}
     * @deprecated Replace with two calls to {@link #find(int)}.
     */
    @Deprecated
    public boolean connected(int p, int q) {
      return find(p) == find(q);
    }

    /**
     * Merges the set containing element {@code p} with the the set containing element {@code q}.
     *
     * @param p one element
     * @param q the other element
     * @throws IllegalArgumentException unless both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
      int i = find(p);
      int j = find(q);
      if (i == j) return;

      // make shorter root point to taller one
      if (height[i] < height[j]) parent[i] = j;
      else if (height[i] > height[j]) parent[j] = i;
      else {
        parent[j] = i;
        height[i]++;
      }
      count--;
    }

    @Override
    public String toString() {
      String s = "";

      for (int i = 0; i < parent.length; i++) {
        s += parent[i] + " ";
      }
      s += "\n ";

      return s;
    }

    //    /**
    //     * Reads an integer {@code n} and a sequence of pairs of integers (between {@code 0} and
    // {@code
    //     * n-1}) from standard input, where each integer in the pair represents some element; if
    // the
    //     * elements are in different sets, merge the two sets and print the pair to standard
    // output.
    //     *
    //     * @param args the command-line arguments
    //     */
    //    public static void main(String[] args) {
    //      int n = StdIn.readInt();
    //      WeightedQuickUnionByHeightUF uf = new WeightedQuickUnionByHeightUF(n);
    //      while (!StdIn.isEmpty()) {
    //        int p = StdIn.readInt();
    //        int q = StdIn.readInt();
    //        if (uf.find(p) == uf.find(q)) continue;
    //        uf.union(p, q);
    //        StdOut.println(p + " " + q);
    //      }
    //      StdOut.println(uf.count() + " components");
    //    }
  }
}
