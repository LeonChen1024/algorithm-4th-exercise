package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.4 Show the contents of the sz[] and id[] arrays and the number of array accesses for each
 * input pair corresponding to the weighted quick-union examples in the text (both the reference
 * input and the worst-case input).
 *
 * <p>对每一个输入到 weighted quick-union 中的对使用文本展示出 sz[] 和 id[] 数组的内容以及数组访问次数<br>
 * (同时包括 参考输入以及最坏情况的输入)
 *
 * @author LeonChen
 * @since 4/15/20
 */
class e01_05_04 {

  public static void main(String[] args) {

    int[][] arr = new int[8][2];
    arr[0] = new int[] {9, 0};
    arr[1] = new int[] {3, 4};
    arr[2] = new int[] {5, 8};
    arr[3] = new int[] {7, 2};
    arr[4] = new int[] {2, 1};
    arr[5] = new int[] {5, 7};
    arr[6] = new int[] {0, 3};
    arr[7] = new int[] {4, 2};
    int[][] arrWorse = new int[8][2];
    arrWorse[0] = new int[] {1, 0};
    arrWorse[1] = new int[] {2, 3};
    arrWorse[2] = new int[] {4, 5};
    arrWorse[3] = new int[] {6, 7};
    arrWorse[4] = new int[] {8, 9};
    arrWorse[5] = new int[] {1, 2};
    arrWorse[6] = new int[] {4, 6};
    arrWorse[7] = new int[] {8, 1};
    uf(arr);
    uf(arrWorse);
  }

  private static void uf(int[][] arr) {
    StdOut.println("=======================================================");
    WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
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
