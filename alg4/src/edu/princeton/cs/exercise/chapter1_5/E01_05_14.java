package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.14 Weighted quick-union by height. Develop a UF implementation that uses the same basic
 * strategy as weighted quick-union but keeps track of tree height and always links the shorter tree
 * to the taller one. Prove a logarithmic upper bound on the height of the trees for N sites with
 * your algorithm.
 *
 * <p>以高度为标准的Weighted quick-union. 开发一个 UF 实现使用和 weighted quick-union 相同策略的算法除了<br>
 * 保持跟踪树的高度并且总是将矮的树连接到高的树.证明N个站点的高度的上限是多少.
 *
 * @author LeonChen
 * @since 4/20/20
 */
class E01_05_14 {

  /**
   * 树的高度只有在相同高度的树的时候才会增加,其他的时候小的树将会合并进大的树里.这保证了N个站点的树的高度上界是对树<br>
   * 级别的.树的大小最多是 2^height.因此,高度最多可以增加到 lgN
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
