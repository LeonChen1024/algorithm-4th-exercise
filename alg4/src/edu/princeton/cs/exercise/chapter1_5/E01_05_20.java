package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.20 Dynamic growth. Using linked lists or a resizing array, develop a weighted quick-union
 * implementation that removes the restriction on needing the number of objects ahead of time. Add a
 * method newSite() to the API, which returns an int identifier.
 *
 * <p>动态增长.使用链表或者是可变长数组开发一个 weighted quick-union 实现,它移除了在开始的时候需要提供对象<br>
 * 的数量的限制.添加了一个 newSite() 方法到API , 返回一个 int 标识.
 *
 * @author LeonChen
 * @since 4/26/20
 */
class E01_05_20 {

  /** */
  public static void main(String[] args) {
    WeightedQuickUnionUF uf = new WeightedQuickUnionUF();

    uf.newSite();
    uf.newSite();
    uf.newSite();
    uf.newSite();
    uf.newSite();
    uf.newSite();
    uf.newSite();
    uf.newSite();
    uf.newSite();
    uf.newSite();
    uf.newSite();

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
    private int siteNums;

    public WeightedQuickUnionUF() {
      siteNums = 0;
      accessNum = 0;
    }

    public int newSite() {
      if (id == null) {
        id = new int[2];
        sz = new int[2];
      } else if (siteNums == id.length) {
        resizeArray(siteNums * 2);
      }

      int newSiteId = siteNums;
      id[newSiteId] = newSiteId;
      sz[newSiteId] = 1;

      siteNums++;
      count++;

      return newSiteId;
    }

    private void resizeArray(int newSize) {
      int[] newIdArray = new int[newSize];
      int[] newSzArray = new int[newSize];

      for (int i = 0; i < siteNums; i++) {
        newIdArray[i] = id[i];
        newSzArray[i] = sz[i];
      }

      id = newIdArray;
      sz = newSzArray;
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
