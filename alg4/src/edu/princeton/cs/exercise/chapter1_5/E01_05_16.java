package edu.princeton.cs.exercise.chapter1_5;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.5.16 Amortized costs plots. Instrument your implementations from Exercise 1.5.7 to make
 * amortized costs plots like those in the text.
 *
 * <p>摊销成本图.如同正文中一样对你的练习 1.5.7 的摊销成本绘图展示.
 *
 * @author LeonChen
 * @since 4/23/20
 */
class E01_05_16 {

  /** */
  public static void main(String[] args) {

    int N = 100;
    QuickFindUF uf = new QuickFindUF(N);
    QuickUnionUF unionUF = new QuickUnionUF(N);

    for (int i = 0; i < 200; i++) {
      int randomSite1 = StdRandom.uniform(N);
      int randomSite2 = StdRandom.uniform(N);

      if (uf.connected(randomSite1, randomSite2)) {
        continue;
      }

      uf.union(randomSite1, randomSite2);
    }
    draw(uf);

    for (int i = 0; i < 200; i++) {
      int randomSite1 = StdRandom.uniform(N);
      int randomSite2 = StdRandom.uniform(N);

      if (unionUF.connected(randomSite1, randomSite2)) {
        continue;
      }

      unionUF.union(randomSite1, randomSite2);
    }
    draw(unionUF);
  }

  private static void draw(QuickFindUF uf) {
    StdOut.println("QuickFindUF start =================================");
    StdDraw.setPenRadius(0.01);
    StdDraw.setPenColor(Color.red);
    StdDraw.setXscale(0, uf.operationNum);
    StdDraw.setYscale(0, 200);
    for (int i = 0; i < uf.operationNum; i++) {
      StdDraw.point(i, uf.total.get(i));
      StdOut.println(" operation time is " + i + " , amortized cost is " + uf.total.get(i));
    }
  }

  private static void draw(QuickUnionUF uf) {
    StdOut.println("QuickUnionUF start =================================");
    StdDraw.setPenRadius(0.01);
    StdDraw.setPenColor(Color.blue);
    StdDraw.setXscale(0, uf.operationNum);
    StdDraw.setYscale(0, 200);
    for (int i = 0; i < uf.operationNum; i++) {
      StdDraw.point(i, uf.total.get(i));
      StdOut.println(" operation time is " + i + " , amortized cost is " + uf.total.get(i));
    }
  }

  public static class QuickUnionUF {

    public int accessNum;
    private int[] id; // save the site's parent link(site indexed)
    private int count; // number of components
    private int operationNum; // 操作次数
    private List<Integer> total;

    public QuickUnionUF(int n) {
      count = n;
      operationNum = 0;
      total = new ArrayList<>();
      id = new int[n];
      for (int i = 0; i < n; i++) id[i] = i;
    }

    public int count() {
      return count;
    }

    public boolean connected(int p, int q) {

      boolean isConnected = find(p) == find(q);
      if (isConnected) {
        updateCost();
      }
      return isConnected;
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
      updateCost();
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

    public void updateCost() {
      operationNum++;
      total.add(accessNum / operationNum);
    }
  }

  static class QuickFindUF {
    public int accessNum;
    private int[] id; // id[i] = component identifier of i
    private int count; // number of components
    private int operationNum; // 操作次数
    private List<Integer> total;

    /**
     * Initializes an empty union–find data structure with {@code n} sites {@code 0} through {@code
     * n-1}. Each site is initially in its own component.
     *
     * @param n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public QuickFindUF(int n) {
      count = n;
      id = new int[n];
      operationNum = 0;
      total = new ArrayList<>();
      for (int i = 0; i < n; i++) id[i] = i;
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int count() {
      return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
      validate(p);
      return id[p];
    }

    // validate that p is a valid index
    private void validate(int p) {
      int n = id.length;
      if (p < 0 || p >= n) {
        throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
      }
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     *     {@code false} otherwise
     * @throws IllegalArgumentException unless both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
      validate(p);
      validate(q);
      accessNum = accessNum + 2;
      boolean isConnected = id[p] == id[q];
      if (isConnected) {
        updateCost();
      }
      return isConnected;
    }

    /**
     * Merges the component containing site {@code p} with the the component containing site {@code
     * q}.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IllegalArgumentException unless both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
      validate(p);
      validate(q);
      accessNum = accessNum + 2;
      int pID = id[p]; // needed for correctness
      int qID = id[q]; // to reduce the number of array accesses

      // p and q are already in the same component
      if (pID == qID) return;

      for (int i = 0; i < id.length; i++) {
        accessNum++;
        if (id[i] == pID) {
          accessNum++;
          id[i] = qID;
        }
      }
      count--;
      updateCost();
    }

    public void updateCost() {
      operationNum++;
      total.add(accessNum / operationNum);
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
  }
}
