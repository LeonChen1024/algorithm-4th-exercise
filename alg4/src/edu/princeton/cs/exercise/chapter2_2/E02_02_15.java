package edu.princeton.cs.exercise.chapter2_2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.2.15 Bottom-up queue mergesort. Develop a bottom-up mergesort implementation based on the
 * following approach: Given N items, create N queues, each containing one of the items. Create a
 * queue of the N queues. Then repeatedly apply the merging operation of Exercise 2.2.14 to the
 * first two queues and reinsert the merged queue at the end. Repeat until the queue of queues
 * contains only one queue.
 *
 * <p>从底向上的合并排序. 基于以下方式开发一个自底向上的合并排序实现: 给定N个项,创建N个队列,每个包含了所有项中的一个<br>
 * 项.创建一个队列.然后重复将之前 Exercise 2.2.14 的合并操作应用于头两个队列然后将队列重新插入到尾部.重复此操作<br>
 * 直到这些队列中只有一个队列.
 *
 * @author LeonChen
 * @since 7/7/20
 */
class E02_02_15 {

  /** @param args */
  public static void main(String[] args) {
    int[] arr = new int[] {4, 2, 57, 23, 52, 42, 43, 676, 8, 23, 654, 1, 465};
    Queue<Queue<Comparable>> queues = new Queue<>();
    for (int i = 0; i < arr.length; i++) {
      Queue<Comparable> queue1 = new Queue<>();
      queue1.enqueue(arr[i]);
      queues.enqueue(queue1);
    }

    Queue<Comparable> queue = bottomUpmergeSorted(queues);

    StdOut.print("Merged queues =  ");
    for (Comparable item : queue) {
      StdOut.print(item + " ");
    }
  }

  private static Queue<Comparable> bottomUpmergeSorted(Queue<Queue<Comparable>> queues) {
    while (queues.size() > 1) {
      Queue<Comparable> queue1 = queues.dequeue();
      Queue<Comparable> queue2 = queues.dequeue();
      Queue<Comparable> mergeQueue = mergeSortedQueue(queue1, queue2);
      queues.enqueue(mergeQueue);
    }

    return queues.dequeue();
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
