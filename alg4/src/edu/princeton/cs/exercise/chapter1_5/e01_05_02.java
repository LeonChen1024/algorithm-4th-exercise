package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.2 Do Exercise 1.5.1, but use quick-union (page 224). In addition, draw the forest of trees
 * represented by the id[] array after each input pair is processed.
 *
 * <p>做练习1.5.1, 但是使用 quick-union(见224页).此外,在每个输入对处理之后绘制出森林图代表id数组
 *
 * @author LeonChen
 * @since 4/14/20
 */
class e01_05_02 {

  /**
   * 森林图如下 <code>
   * 初始    0    1    2    3    4    5    6    7    8    9
   *
   * ========================================================
   *
   * 9 0    0    1    2    3    4    5    6    7    8
   *        |
   *        9
   *
   * ========================================================
   * 3 4    0    1    2         4    5    6    7    8
   *        |                   |
   *        9                   3
   *
   * ==========================================================
   * 5 8    0    1    2         4         6    7    8
   *        |                   |                   |
   *        9                   3                   5
   *
   * ========================================================
   * 7 2    0    1    2         4         6         8
   *        |         |         |                   |
   *        9         7         3                   5
   *
   * =========================================================
   * 2 1    0    1              4         6         8
   *        |    |              |                   |
   *        9    2              3                   5
   *             |
   *             7
   *
   * ==========================================================
   * 5 7    0      1            4         6
   *        |    |   |          |
   *        9    2   8          3
   *             |   |
   *             7   5
   *
   * ==========================================================
   * 0 3          _1_            _4_       6
   *             |   |          |   |
   *             2   8          3   0
   *             |   |              |
   *             7   5              9
   *
   * ==========================================================
   * 4 2          ___1___               6
   *             |   |   |
   *             2   8  _4_
   *             |   |  | |
   *             7   5  3 0
   *                      |
   *                      9
   *
   * </code>
   *
   * @param args
   */
  public static void main(String[] args) {
    QuickUnionUF uf = new QuickUnionUF(10);
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

  private static void printResult(QuickUnionUF uf) {

    StdOut.println("array content is " + uf + " , access num is " + uf.accessNum);
  }

  public static class QuickUnionUF {

    public int accessNum;
    private int[] id; // save the site's parent link(site indexed)
    private int count; // number of components

    public QuickUnionUF(int n) {
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

      accessNum += 2;
      // find root
      // id[p] save the parent of p
      while (p != id[p]) p = id[p];

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
