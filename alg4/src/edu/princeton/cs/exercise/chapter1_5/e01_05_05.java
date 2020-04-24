package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.5 Estimate the minimum amount of time (in days) that would be required for quick-find to
 * solve a dynamic connectivity problem with 10^9 sites and 10^6 input pairs, on a computer capable
 * of executing 10^9 instructions per second. Assume that each iteration of the inner for loop
 * requires 10 machine instructions.
 *
 * <p>估算使用 quick-find 来解决一个10^9个网址和10^6个输入对的动态连接问题的最小用时(以天为单位),使用一台每秒<br>
 * 可以处理10^9条指令的电脑.假设每个内部的for循环迭代需要10个机器指令.
 *
 * @author LeonChen
 * @since 4/16/20
 */
class e01_05_05 {

  /**
   * <code> QuickFindUF uf = new QuickFindUF(10 ^ 9);</code><br>
   * count = n; 1 instruction . id = new int[n]; 1 instruction . <br>
   * for (int i = 0; i < n; i++) id[i] = i; 10 * 10^9 + 10^9instruction <br>
   * <code>public boolean connected(int p, int q) {</code> 8 instruction <br>
   * <code>public void union(int p, int q) {</code> 10 + 10^10
   *
   * <p>总的数量是 10^10 + 10^9 + 10^6(8+10+10^10) = 10^10 + 10^9 + 18*10^6 + 10^16
   *
   * <p>量级差别太大的忽略, 可得 10^16/10^9 = 10^7秒 10^7 /60/60/24 ~115.7天
   *
   * @param args
   */
  public static void main(String[] args) {
    QuickFindUF uf = new QuickFindUF(10 ^ 9);
    int[][] arr = new int[10 ^ 6][2];

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

  private static void printResult(QuickFindUF uf) {
    StdOut.println("array content is " + uf + " , access num is " + uf.accessNum);
  }

  static class QuickFindUF {
    public int accessNum;
    private int[] id; // id[i] = component identifier of i
    private int count; // number of components

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
      return id[p] == id[q];
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

    //    /**
    //     * Reads in a sequence of pairs of integers (between 0 and n-1) from standard input,
    //     * where each integer represents some site;
    //     * if the sites are in different components, merge the two components
    //     * and print the pair to standard output.
    //     *
    //     * @param args the command-line arguments
    //     */
    //    public static void main(String[] args) {
    //      int n = StdIn.readInt();
    //      QuickFindUF uf = new QuickFindUF(n);
    //      while (!StdIn.isEmpty()) {
    //        int p = StdIn.readInt();
    //        int q = StdIn.readInt();
    //        if (uf.connected(p, q)) continue;
    //        uf.union(p, q);
    //        StdOut.println(p + " " + q);
    //      }
    //      StdOut.println(uf.count() + " components");
    //    }

  }
}
