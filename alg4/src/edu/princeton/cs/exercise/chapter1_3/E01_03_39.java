package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.39 Ring buffer. A ring buffer, or circular queue, is a FIFO data structure of a fixed size N.
 * It is useful for transferring data between asynchronous processes or for storing log files. When
 * the buffer is empty, the consumer waits until data is deposited; when the buffer is full, the
 * producer waits to deposit data. Develop an API for a RingBuffer and an implementation that uses
 * an array representation (with circular wrap-around).
 *
 * <p>环形缓冲. 一个环形缓冲,或者是循环 queue ,是一个拥有固定大小 N 的 FIFO 数据结构.它在异步进程中传输数据或者存储<br>
 * log 文件中是很有用的.当缓冲是空的时候,消费者会等待数据被写入;当缓冲是满的时候,生产者会等待时机产生数据.开发一个 API 给环形<br>
 * 缓冲并且使用数组来实现(使用环形)
 *
 * @author LeonChen
 * @since 1/31/20
 */
class E01_03_39 {

  public static void main(String[] args) {

    RingBuffer<Integer> ringBuffer = new RingBuffer<Integer>(5);
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                for (int i = 0; i < 100; i++) {
                  ringBuffer.enqueue(i);
                }
              }
            })
        .start();

    new Thread(
            new Runnable() {
              @Override
              public void run() {
                for (int i = 0; i < 100; i++) {

                  ringBuffer.dequeue();
                  try {
                    Thread.sleep(2000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                }
              }
            })
        .start();
  }

  public static class RingBuffer<Item> {

    private final int N;
    private Item[] items;
    private int firstInd;
    private int lastInd;
    private Object lock = new Object();

    public RingBuffer(int N) {
      this.N = N;
      items = (Item[]) new Object[N];
      firstInd = 0;
      lastInd = 0;
    }

    boolean isEmpty() {
      return firstInd == lastInd;
    }

    void enqueue(Item x) {
      synchronized (lock) {
        if ((lastInd + 1) % N == firstInd) {

          try {
            items[lastInd] = x;
            StdOut.println("enqueue num = " + x + ", enqueue index = " + lastInd);
            lock.wait();
            lastInd = (lastInd + 1) % N;
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

        } else {
          items[lastInd] = x;
          StdOut.println("enqueue num = " + x + ", enqueue index = " + lastInd);
          lastInd = (lastInd + 1) % N;
          lock.notify();
        }
      }
    }

    Item dequeue() {
      synchronized (lock) {
        if (firstInd == lastInd) {
          try {
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        Item result = items[firstInd];
        StdOut.println("dequeue num = " + result + ", dequeue index = " + firstInd);
        items[firstInd] = null;
        firstInd = (firstInd + 1) % N;
        lock.notify();
        return result;
      }
    }
  }
}
