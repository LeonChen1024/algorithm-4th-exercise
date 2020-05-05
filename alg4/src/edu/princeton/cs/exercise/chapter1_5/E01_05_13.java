package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.13 Weighted quick-union with path compression. Modify weighted quick-union (Algorithm 1.5) to
 * implement path compression, as described in Exercise 1.5.12 . Give a sequence of input pairs that
 * causes this method to produce a tree of height 4. Note : The amortized cost per operation for
 * this algorithm is known to be bounded by a function known as the inverse Ackermann function and
 * is less than 5 for any conceivable practical value of N.
 *
 * <p>带有路径压缩的 Weighted quick-union. 修改 Weighted quick-union (算法 1.5) 实现路径压缩,如果练习<br>
 * 1.5.12 中描述的. 给定一个输入对序列导致这个方法产生一个高度为4的树. 注意: 这个算法的每个操作的摊销成本是<br>
 * 被阿克曼逆函数约束的对于任何可能的实际值 N 均小于5.
 *
 * @author LeonChen
 * @since 4/20/20
 */
public class E01_05_13 {

  /** */
  public static void main(String[] args) {
    WeightedCompressionQuickUnionUF uf = new WeightedCompressionQuickUnionUF(10);
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

  private static void printResult(WeightedCompressionQuickUnionUF uf) {

    StdOut.println("array content is " + uf + " , access num is " + uf.accessNum);
  }

  public static class WeightedCompressionQuickUnionUF {

    public int accessNum;
    private int[] id; // parent link(site indexed)
    private int[] sz; // size of component for roots(site indexed)
    private int count; // number of components

    public WeightedCompressionQuickUnionUF(int N) {

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

      while (p == id[p]) {

        accessNum++;
        return p;
      }

      accessNum++;
      return id[p] = find(id[p]);
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
