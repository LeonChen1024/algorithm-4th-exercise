package edu.princeton.cs.exercise.chapter2_2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.2.14 Merging sorted queues. Develop a static method that takes two queues of sorted items as
 * arguments and returns a queue that results from merging the queues into sorted order.
 *
 * <p>合并排序队列. 开发一个静态方法接收两个队列的有序项作为参数并且返回一个有序队列合并了两个输入队列.
 *
 * @author LeonChen
 * @since 7/7/20
 */
class E02_02_14 {

  /** @param args */
  public static void main(String[] args) {
    Queue<Comparable> queue1 = new Queue<>();
    queue1.enqueue(1);
    queue1.enqueue(4);
    queue1.enqueue(6);
    queue1.enqueue(7);
    queue1.enqueue(11);
    queue1.enqueue(25);
    queue1.enqueue(33);

    Queue<Comparable> queue2 = new Queue<>();
    queue2.enqueue(3);
    queue2.enqueue(5);
    queue2.enqueue(8);
    queue2.enqueue(13);
    queue2.enqueue(18);
    queue2.enqueue(22);
    queue2.enqueue(27);
    queue2.enqueue(34);

    Queue<Comparable> queue = mergeSortedQueue(queue1, queue2);

    StdOut.print("Merged queues =  ");
    for (Comparable item : queue) {
      StdOut.print(item + " ");
    }
  }

  private static Queue<Comparable> mergeSortedQueue(
      Queue<Comparable> queue1, Queue<Comparable> queue2) {
    Queue<Comparable> mergeQueue = new Queue<>();

    while (!queue1.isEmpty() && !queue2.isEmpty()) {
      if (queue1.peek().compareTo(queue2.peek()) <= 0) {
        mergeQueue.enqueue(queue1.dequeue());
      } else {
        mergeQueue.enqueue(queue2.dequeue());
      }
    }

    while (!queue1.isEmpty()) {
      mergeQueue.enqueue(queue1.dequeue());
    }

    while (!queue2.isEmpty()) {
      mergeQueue.enqueue(queue2.dequeue());
    }

    return mergeQueue;
  }
}
