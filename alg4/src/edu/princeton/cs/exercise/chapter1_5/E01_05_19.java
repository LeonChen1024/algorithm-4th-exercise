package edu.princeton.cs.exercise.chapter1_5;

import java.awt.*;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.19 Animation. Write a RandomGrid client (see Exercise 1.5.18) that uses UnionFind as in our
 * development client to check connectivity and uses StdDraw to draw the connections as they are
 * processed.
 *
 * <p>Animation.编写一个 RandomGrid 客户端(见练习 1.5.18) 使用 UnionFind 作为我们的开发客户端来检查连接<br>
 * 并使用 StdDraw 来绘制他们处理的连接
 *
 * @author LeonChen
 * @since 4/26/20
 */
class E01_05_19 {

  /** */
  public static void main(String[] args) {

    int N = 5;
    StdDraw.setCanvasSize(500, 500);
    StdDraw.setXscale(-1, N + 1);
    StdDraw.setYscale(-1, N + 1);
    StdDraw.setPenRadius(0.05);
    StdDraw.setPenColor(Color.black);
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        StdDraw.point(i, j);
      }
    }

    StdDraw.setPenRadius(0.025);
    StdDraw.setPenColor(Color.blue);
    for (E01_05_18.Connection connection : E01_05_18.generate(N)) {
      StdOut.println(connection.p + " - " + connection.q);
      StdDraw.line(connection.p / N, connection.p % N, connection.q / N, connection.q % N);
    }
  }
}
