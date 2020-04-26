package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.6 Repeat Exercise 1.5.5 for weighted quick-union.
 *
 * <p>使用 weighted quick-union 来重复练习 1.5.5
 *
 * @author LeonChen
 * @since 4/16/20
 */
class e01_05_06 {

  /**
   * 忽略一些常数的操作次数.<br>
   * <code>for (int i = 0; i < N; i++) id[i] = i;</code> 10 * 10^9 = 10^10<br>
   * <code>for (int i = 0; i < N; i++) sz[i] = 1;</code> 10 * 10^9 = 10^10<br>
   * <code>uf.connected(b[0], b[1])</code> 最差的情况下消耗 2lg(10^9) 个指令.<br>
   * <code>public void union(int p, int q) {</code> 最差的情况下消耗 5+2lg(10^9) 个指令<br>
   * 由于上两句被循环包裹,所以他们分别是 10*10^6*2lg(10^9) 和 10*10^6*(5+2lg(10^9))<br>
   * 总和是 ~ 10^10 + 10^10+ 10^7*60 + 10^7*65 <br>
   * ~ 2 * 10^10 + 6*10^8 + 6*10^8 ~ 2 * 10^10 +10^9 条指令<br>
   * 用时是 (2 * 10^10 +10^9 )/10^9 ~ 21s
   */
  public static void main(String[] args) {

    int[][] arr = new int[10 ^ 6][2];
    arr[0] = new int[] {9, 0};
    arr[1] = new int[] {3, 4};
    arr[2] = new int[] {5, 8};
    arr[3] = new int[] {7, 2};
    arr[4] = new int[] {2, 1};
    arr[5] = new int[] {5, 7};
    arr[6] = new int[] {0, 3};
    arr[7] = new int[] {4, 2};
    uf(arr);
  }

  private static void uf(int[][] arr) {
    StdOut.println("=======================================================");
    WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10 ^ 9);
    int index = 0;
    while (index < arr.length) {
      int[] b = arr[index];
      uf.accessNum = 0;
      if (!uf.connected(b[0], b[1])) {
        uf.union(b[0], b[1]);
      }
      printResult(uf);
      index++;
    }
  }

  private static void printResult(WeightedQuickUnionUF uf) {

    StdOut.println("array content is " + uf + " , access num is " + uf.accessNum);
  }

  public static class WeightedQuickUnionUF {

    public int accessNum;
    private int[] id; // parent link(site indexed)
    private int[] sz; // size of component for roots(site indexed)
    private int count; // number of components

    public WeightedQuickUnionUF(int N) {

      count = N;

      id = new int[N];
      for (int i = 0; i < N; i++) id[i] = i;

      sz = new int[N];
      for (int i = 0; i < N; i++) sz[i] = 1;
    }

    public int count() {
      return count;
    }

    public boolean connected(int p, int q) {
      return find(p) == find(q);
    }

    public int find(int p) {

      while (p != id[p]) {
        p = id[p];
        accessNum += 2;
      }

      return p;
    }

    public void union(int p, int q) {

      int pRoot = find(p);
      int qRoot = find(q);

      if (pRoot == qRoot) return;

      // make smaller root point to larger one
      if (sz[pRoot] < sz[qRoot]) {
        id[pRoot] = qRoot;
        sz[qRoot] += sz[pRoot];
      } else {
        id[qRoot] = pRoot;
        sz[pRoot] += sz[qRoot];
      }
      accessNum += 5;

      count--;
    }

    @Override
    public String toString() {
      String s = "";

      for (int i = 0; i < id.length; i++) {
        s += id[i] + " ";
      }
      s += "\n    tree size is ";

      for (int i = 0; i < sz.length; i++) {
        s += sz[i] + " ";
      }
      s += ", components size is " + count;

      return s;
    }
  }
}
