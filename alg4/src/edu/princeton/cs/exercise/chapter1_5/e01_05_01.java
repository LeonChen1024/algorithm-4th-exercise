package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.1 Show the contents of the id[] array and the number of times the array is accessed for each
 * input pair when you use quick-find for the sequence 9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2.
 *
 * <p>使用quick-find 针对以下序列 9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2 中的每一个输入对展示出 id[] 数组的<br>
 * 内容和数组访问的次数.
 *
 * @author LeonChen
 * @since 4/14/20
 */
class e01_05_01 {

  public static void main(String[] args) {
    QuickFindUF uf = new QuickFindUF(10);
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
