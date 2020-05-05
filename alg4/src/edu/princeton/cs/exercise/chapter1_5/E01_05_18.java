package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.exercise.chapter1_3.E01_03_34;

/**
 * 1.5.18 Random grid generator. Write a program RandomGrid that takes an int value N from the
 * command line, generates all the connections in an N-by-N grid, puts them in random order,
 * randomly orients them (so that p q and q p are equally likely to occur), and prints the result to
 * standard output. To randomly order the connections, use a RandomBag (see Exercise 1.3.34 on page
 * 167). To encapsulate p and q in a single object,use the Connection nested class shown below.
 * Package your program as two static methods: generate(), which takes N as argument and returns an
 * array of connections, and main(), which takes N from the command line, calls generate(), and
 * iterates through the returned array to print the connections.
 *
 * <p>随机网格生成器.编写一个 RandomGrid 程序从命令行接收一个整数值 N,生成一个 N*N 的网格中的所有连接,<br>
 * 将他们放进一个随机顺序,并且方向也是随机的,(使得 pq 和 qp 出现的可能性是相等的),并且打印结果到标准输出中.<br>
 * 为了将连接随机放置,使用一个 RandomBag (见 167 页的练习 1.3.34).为了封装 p和q到一个对象中,使用下面的嵌套类<br>
 * Connection .将你的程序打包成两个静态方法: generate() 接收 N 作为参数并且返回一个连接的数组,还有 main(),<br>
 * 从命令行接收一个 N 作为参数,调用 generate() ,并且使用迭代器遍历返回的数组来打印出连接.
 *
 * @author LeonChen
 * @since 4/24/20
 */
public class E01_05_18 {

  /** */
  public static void main(String[] args) {

    int N = 5;

    for (Connection connection : generate(N)) {
      StdOut.println(connection.p + " - " + connection.q);
    }
  }

  public static class Connection {
    int p;
    int q;

    public Connection(int p, int q) {
      this.p = p;
      this.q = q;
    }
  }

  public static E01_03_34.RandomBag<Connection> generate(int n) {
    E01_03_34.RandomBag<Connection> result = new E01_03_34.RandomBag<Connection>();

    // 建立横向连接
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n - 1; ++j) {
        if (StdRandom.uniform(10) > 4) {
          result.add(new Connection(i * n + j, (i * n) + j + 1));
        } else {
          result.add(new Connection((i * n) + j + 1, i * n + j));
        }
      }
    }

    // 建立纵向连接
    for (int j = 0; j < n; ++j) {
      for (int i = 0; i < n - 1; ++i) {
        if (StdRandom.uniform(10) > 4) {
          result.add(new Connection(i * n + j, ((i + 1) * n) + j));
        } else {
          result.add(new Connection(((i + 1) * n) + j, i * n + j));
        }
      }
    }

    return result;
  }
}
