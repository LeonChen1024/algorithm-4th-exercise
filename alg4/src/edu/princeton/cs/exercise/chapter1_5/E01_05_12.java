package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.12 Quick-union with path compression. Modify quick-union (page 224) to include path
 * compression, by adding a loop to union() that links every site on the paths from p and q to the
 * roots of their trees to the root of the new tree. Give a sequence of input pairs that causes this
 * method to produce a path of length 4. Note : The amortized cost per operation for this algorithm
 * is known to be logarithmic.
 *
 * <p>使用路径压缩的 Quick-union.修改 Quick-union ( 224 页) 使它包含路径压缩功能,通过添加一个循环到 union() <br>
 * 链接路径上的每一个站点从 p 和 q 到他们树的根节点指向新的树的根节点.给定一个输入对序列使得这个方法产生一个路径长度<br>
 * 是 4 的树.注意:已知这个算法每个操作的摊销成本时对数级别的.
 *
 * @author LeonChen
 * @since 4/19/20
 */
class E01_05_12 {

  /** 前面实现的 Quick-union 已经自带路径压缩 */
  public static void main(String[] args) {
    PathCompressionQuickUnionUF uf = new PathCompressionQuickUnionUF(10);
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

  private static void printResult(PathCompressionQuickUnionUF uf) {

    StdOut.println("array content is " + uf + " , access num is " + uf.accessNum);
  }

  static class PathCompressionQuickUnionUF {

    public int accessNum;
    private int[] id; // save the site's parent link(site indexed)
    private int count; // number of components

    public PathCompressionQuickUnionUF(int n) {
      count = n;

      id = new int[n];
      for (int i = 0; i < n; i++) id[i] = i;
    }

    public int count() {
      return count;
    }

    public boolean connected(int p, int q) {
      return find(p) == find(q);
    }

    public int find(int p) {

      // find root
      // id[p] save the parent of p
      while (p != id[p]) {
        p = id[p];
        accessNum += 2;
      }
      accessNum++;
      return p;
    }

    public void union(int p, int q) {

      int pRoot = find(p); // find pRoot
      int qRoot = find(q); // find qRoot

      if (pRoot == qRoot) return;

      accessNum++;
      id[pRoot] = qRoot;
      count--;
    }

    @Override
    public String toString() {
      String s = "";

      for (int i = 0; i < id.length; i++) {
        s += id[i] + " ";
      }
      s += " ," + count + " components";

      return s;
    }

    //    public static void main(String[] args) {
    //
    //      // initialize N components
    //      int N = StdIn.readInt();
    //      QuickUnionUF uf = new QuickUnionUF(N);
    //      StdOut.println(uf);
    //
    //      while (!StdIn.isEmpty()) {
    //
    //        int p = StdIn.readInt();
    //        int q = StdIn.readInt();
    //
    //        if (uf.connected(p, q)) { // ignore if connected
    //          StdOut.println(p + " " + q + " is connected");
    //          continue;
    //        }
    //
    //        uf.union(p, q); // connect p and q
    //        StdOut.println(p + " " + q);
    //        StdOut.println(uf);
    //      }
    //    }
  }
}
