package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.15 Write a Queue client that takes a command-line argument k and prints the kth from the last
 * string found on standard input (assuming that standard input has k or more strings).
 *
 * <p>编写一个Queue客户端接收一个命令行参数 k 并且打印出从标准输入中得到的倒数第 k 个字符(假设标准输入有k或者更多个字符串)
 *
 * @author LeonChen
 * @since 1/3/20
 */
class e01_03_15 {

  public static void main(String[] args) {
    int k = 3;
    String[] input = new String[] {"1", "2", "3", "4", "5", "6", "7"};

    Queue<String> queue = new Queue<>();
    for (int i = 0; i < input.length; i++) {
      queue.enqueue(input[i]);
    }

    int oldSize = queue.size();
    for (int i = 0; i < oldSize; i++) {
      // 新建oldSize 是为了避免这里引用到错误的大小来计算
      if (i == oldSize - k) {
        StdOut.print(queue.dequeue());
        return;
      } else {
        queue.dequeue();
      }
    }
  }
}
