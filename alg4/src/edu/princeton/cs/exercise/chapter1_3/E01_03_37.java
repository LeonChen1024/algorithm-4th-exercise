package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.37 Josephus problem. In the Josephus problem from antiquity, N people are in dire straits and
 * agree to the following strategy to reduce the population. They arrange themselves in a circle (at
 * positions numbered from 0 to N–1) and proceed around the circle, eliminating every Mth person
 * until only one person is left. Legend has it that Josephus figured out where to sit to avoid
 * being eliminated. Write a Queue client Josephus that takes N and M from the command line and
 * prints out the order in which people are eliminated (and thus would show Josephus where to sit in
 * the circle). <code>
 *     % java Josephus 7 2 1350426
 * </code>
 *
 * <p>Josephus 问题.在古老的Josephus问题中,N个人陷入了困境并且同意按照以下策略来减少人口.他们将自己排成一个圆圈<br>
 * （位置编号从0到N-1），然后绕着圆圈前进,消除每个第M人，直到只剩下一个人。传说中 Josephus 找到了一个位置可以避免被<br>
 * 消除.编写一个 Queue 客户端 Josephus 从命令行接收 N 和 M 并且打印出被消除的人的列表(并且由此像 Josephus 展示要坐在<br>
 * 哪里. <code>
 *     % java Josephus 7 2 1350426
 * </code>
 *
 * @author LeonChen
 * @since 1/29/20
 */
class E01_03_37 {

  public static void main(String[] args) {

    int N = 7;
    int M = 2;
    josephusSitWhere(N, M);
  }

  /**
   * 使用数组的每个位置放一个 1 代表有一个人,消除后变成 0,变成 0 的位置不算在前进的步数里.最后数组里唯一一个有 1 的位置就是存活位置.
   *
   * @param N
   * @param M
   */
  private static void josephusSitWhere(int N, int M) {

    int[] seats = new int[N];
    for (int i = 0; i < seats.length; i++) {
      seats[i] = 1;
    }

    int i = -1;
    int left = N;
    while (left > 1) {
      for (int j = 0; j < M; j++) {
        i++;
        if (i == N) {
          i = 0;
        }
        // 已经没有人的座位要跳过
        while (seats[i] == 0) {
          i++;
          if (i == N) {
            i = 0;
          }
        }
      }
      StdOut.print(i);
      seats[i] = 0;
      left--;
    }

    StdOut.println();
    for (int j = 0; j < seats.length; j++) {
      if (seats[j] == 1) {
        StdOut.print("Josephus should sit in position " + j);
      }
    }
  }
}
