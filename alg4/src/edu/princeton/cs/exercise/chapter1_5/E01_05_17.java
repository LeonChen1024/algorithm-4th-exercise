package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.5.17 Random connections. Develop a UF client ErdosRenyi that takes an integer value N from the
 * command line, generates random pairs of integers between 0 and N-1, calling connected() to
 * determine if they are connected and then union() if not (as in our development client), looping
 * until all sites are connected, and printing the number of connections generated. Package your
 * program as a static method count() that takes N as argument and returns the number of connections
 * and a main() that takes N from the command line, calls count(), and prints the returned value.
 *
 * <p>随机连接.开发一个 UF 客户端 ErdosRenyi , 从命令行接收一个整数 N,生成 0 到 N-1 之间的随机对,调用 connected()<br>
 * 来判断他们是否相连如果没有就调用 union() (如同我们开发过的客户端一样),不断循环直到所有的网址被连接,<br>
 * 并且打印出生成的连接数量.将你的程序打包成一个静态方法 count() 接收一个 N 作为参数并且返回连接的数量,<br>
 * 还有一个main() 从命令行接收一个N ,调用count(),并且打印出返回值.
 *
 * @author LeonChen
 * @since 4/24/20
 */
public class E01_05_17 {

  /** */
  public static void main(String[] args) {

    int N = 100;
    StdOut.println(count(N));
  }

  public static int count(int n) {
    QuickUnionUF unionUF = new QuickUnionUF(n);
    int nums = 0;
    while (unionUF.count() > 1) {
      int[] connection = getRadomConnection(n);

      nums++;

      if (unionUF.connected(connection[0], connection[1])) {
        continue;
      }

      unionUF.union(connection[0], connection[1]);
    }
    return nums;
  }

  public static int[] getRadomConnection(int n) {
    int randomSite1 = StdRandom.uniform(n);
    int randomSite2 = StdRandom.uniform(n);
    return new int[] {randomSite1, randomSite2};
  }
}
