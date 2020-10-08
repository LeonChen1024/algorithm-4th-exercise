package edu.princeton.cs.exercise.chapter2_4;

import edu.princeton.cs.algs4.MaxPQ;

/**
 * 2.4.22 Array resizing. Add array resizing to MaxPQ, and prove bounds like those of Proposition Q
 * for array accesses, in an amortized sense.
 *
 * <p>数组容量调节.往 MaxPQ中添加数组容量调节,并且证明上限就像提案Q使用数组访问计算一样,使用摊销方式.
 *
 * @author LeonChen
 * @since 9/25/20
 */
class E02_04_22 {

  /**
   * 官方案例如下 <code>
   *         // helper function to double the size of the heap array
   *     private void resize(int capacity) {
   *         assert capacity > n;
   *         Key[] temp = (Key[]) new Object[capacity];
   *         for (int i = 1; i <= n; i++) {
   *             temp[i] = pq[i];
   *         }
   *         pq = temp;
   *     }
   * </code> 示例中优先队列插入比较的成本 lgN +1 ,删除的成本为 2lgN,交换的成本最多为插入lgN,删除 1+lgN-1.<br>
   * 一次比较需要 2 次数组访问，一次交换需要 4 次数组访问,可得 6lgN+2（插入）和 8lgN （删除）.如果触发了<br>
   * 扩容操作那么需要额外的2N次访问.也就是 2 4 8 ... 2n . n 个元素的当次扩容需要2N次操作,加上以前的扩容次数<br>
   * 上限为 4N次操作.平均到每次也就是4次操作. 总的就是可得 6lgN+6（插入）和 8lgN+4（删除）.
   */
  public static void main(String[] args) {
    MaxPQ maxPQ;
  }
}
