package edu.princeton.cs.exercise.chapter2_4;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.4.30 Dynamic median-finding. Design a data type that supports insert in logarithmic time, find
 * the median in constant time, and delete the median in logarithmic time. Hint: Use a min-heap and
 * a max-heap.
 *
 * <p>动态找寻中间值.设计一个数据类型支持以对数时间插入数据,常量级时间找到中间值,并且以对数时间删除中间值.<br>
 * 提示:使用 一个最小堆和一个最大堆
 *
 * @author LeonChen
 * @since 11/5/20
 */
class E02_04_30 {

  /**
   * 使用一个变量跟踪中间值,然后将前半部分元素放在一个最大堆中,后半部分元素放在一个最小堆中.然后保证两个堆中的数量<br>
   * 不超过1,如果超过1,就将mid元素插入数量比较少的堆,再从数量比较多的堆中取出值放在mid中.插入元素的时候和mid对比,<br>
   * 比mid大就放到最小堆中,比mid小就放到最大堆中.删除中间值时,就从数量多的堆中取值放入mid即可
   *
   * @param args
   */
  public static void main(String[] args) {
    MedianPQ medianPQ = new MedianPQ();
    medianPQ.insert(4);
    StdOut.println(medianPQ);
    medianPQ.insert(2);
    medianPQ.insert(8);
    medianPQ.insert(23);
    medianPQ.insert(11);
    medianPQ.insert(66);
    medianPQ.insert(6);
    medianPQ.insert(3);
    StdOut.println(medianPQ.getMid());
    medianPQ.delMid();
    StdOut.println(medianPQ.getMid());
    medianPQ.delMid();
    StdOut.println(medianPQ.getMid());
    StdOut.println(medianPQ);
  }

  private static class MedianPQ {

    private MinPQ<Integer> minPQ;
    private MaxPQ<Integer> maxPQ;
    private Integer mid;

    public MedianPQ() {
      minPQ = new MinPQ<>();
      maxPQ = new MaxPQ<>();
    }

    public void insert(Integer i) {
      if (mid == null) {
        mid = i;
      } else {
        if (i > mid) {
          minPQ.insert(i);
        } else {
          maxPQ.insert(i);
        }
        if (Math.abs(maxPQ.size() - minPQ.size()) > 1) {
          resort();
        }
      }
    }

    private void resort() {
      if (maxPQ.size() > minPQ.size()) {
        minPQ.insert(mid);
        mid = maxPQ.delMax();
      } else {
        maxPQ.insert(mid);
        mid = minPQ.delMin();
      }
    }

    public Integer getMid() {
      return mid;
    }

    public Integer delMid() {
      Integer v = mid;
      if (maxPQ.size() > minPQ.size()) {
        mid = maxPQ.delMax();
      } else {
        mid = minPQ.delMin();
      }
      return v;
    }

    @Override
    public String toString() {
      return "MedianPQ{" + "minPQ=" + minPQ + ", maxPQ=" + maxPQ + ", mid=" + mid + '}';
    }
  }
}
