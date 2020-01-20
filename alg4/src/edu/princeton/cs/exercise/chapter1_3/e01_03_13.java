package edu.princeton.cs.exercise.chapter1_3;

/**
 * 1.3.13 Suppose that a client performs an intermixed sequence of (queue) enqueue and dequeue
 * operations. The enqueue operations put the integers 0 through 9 in order onto the queue; the
 * dequeue operations print out the return value. Which of the following sequence(s) could not
 * occur? a.0123456789 b.4687532901 c.2567489310 d.4321056789
 *
 * <p>假设一个客户端执行一个序列(queue) 入队和出队操作.入队操作会按顺序从0到9放进队列中;出队操作打印出返回值.下列<br>
 * 序列中哪个不会出现.
 *
 * @author LeonChen
 * @since 1/2/20
 */
class e01_03_13 {

  public static void main(String[] args) {

    // 根据队列先入先出的原则,所以不论怎么出队,顺序一定不会变.所以只有b,c,d都不可能出现.

  }
}
