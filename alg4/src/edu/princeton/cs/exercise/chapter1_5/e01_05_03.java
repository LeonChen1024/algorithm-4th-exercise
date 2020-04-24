package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.3 Do Exercise 1.5.1, but use weighted quick-union (page 228).
 *
 * <p>使用 weighted quick-union (见 228页)来做1.5.1
 *
 * @author LeonChen
 * @since 4/15/20
 */
class e01_05_03 {

  public static void main(String[] args) {
    WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
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
